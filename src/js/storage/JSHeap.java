package js.storage;

import java.util.HashMap;
import java.util.Map;

import js.runtime.Descriptor;
import js.runtime.DescriptorPtr;
import js.runtime.InstanceType;
import js.runtime.JSNumber;
import js.runtime.JSNumberPtr;
import js.runtime.JSObject;
import js.runtime.JSObjectPtr;
import js.runtime.JSString;
import js.runtime.JSStringPtr;
import js.runtime.JSValue;
import js.runtime.JSValuePtr;
import js.runtime.Oddball;
import js.runtime.OddballKind;
import js.runtime.OddballPtr;

public class JSHeap {
  private Storage space;
  private Map<CharSequence, JSStringPtr> string_cache;

  private JSValuePtr zero_value;
  private JSValuePtr nan_value;
  private JSValuePtr undefined_value;
  private JSValuePtr null_value;
  private JSValuePtr hole_value;
  private JSValuePtr true_value;
  private JSValuePtr false_value;

  private JSObjectPtr object_prototype;

  public JSHeap(int size) {
    space = new Storage(size);
    string_cache = new HashMap<>();
  }

  public void set(int index, JSValue value) {
    space.set(index, value);
  }

  public JSValue get(int index) {
    return (JSValue) space.get(index);
  }

  public int allocate(JSValue value) {
    int index = space.allocate(value);
    if (index == -1) callMemoryAllocationFailedError();
    return index;
  }

  public void free(int index) {
    space.free(index);
  }

  // { value, writable: true, enumerable: true, configurable: true }
  public DescriptorPtr allocateDescriptor(JSValuePtr value, int attributes) {
    Descriptor descriptor = new Descriptor();
    descriptor.instance_type = InstanceType.DESCRIPTOR;
    descriptor.value = value;
    descriptor.attributes = attributes;

    return new DescriptorPtr(this, allocate(descriptor));
  }

  // { };
  public JSObjectPtr allocateEmptyObject() {
    JSObject object = new JSObject();
    object.instance_type = InstanceType.OBJECT;
    object.constructor = null;
    object.extensible = true;
    object.properties = new HashMap<>();
    object.prototype = null;

    return new JSObjectPtr(this, allocate(object));
  }

  // "string characters"
  public JSStringPtr allocateString(CharSequence charSequence) {
    JSStringPtr stringPtr = string_cache.get(charSequence);
    if (stringPtr != null) return stringPtr;

    JSString string = new JSString();
    string.instance_type = InstanceType.STRING;
    string.value = charSequence;

    stringPtr = new JSStringPtr(this, allocate(string));
    string_cache.put(charSequence, stringPtr);
    return stringPtr;
  }

  public JSNumberPtr allocateNumber(double value) {
    JSNumber number = new JSNumber();
    number.instance_type = InstanceType.NUMBER;
    number.value = value;
    return new JSNumberPtr(this, allocate(number));
  }

  private static void callMemoryAllocationFailedError() {
    throw new RuntimeException("Memory allocation failed.");
  }

  public int getSize() {
    return space.getSize();
  }

  public JSValuePtr nan_value() {
    return nan_value;
  }

  public JSValuePtr zero_value() {
    return zero_value;
  }

  public JSValuePtr null_value() {
    return null_value;
  }

  public JSValuePtr undefined_value() {
    return undefined_value;
  }

  public JSValuePtr hole_value() {
    return hole_value;
  }

  public JSValuePtr true_value() {
    return true_value;
  }

  public JSValuePtr false_value() {
    return false_value;
  }

  public JSObjectPtr object_prototype() {
    return object_prototype;
  }

  public void initialize() {
    JSNumber zeroNumber = new JSNumber();
    zeroNumber.instance_type = InstanceType.NUMBER;
    zeroNumber.value = 0.0;
    zero_value = new JSValuePtr(this, allocate(zeroNumber));
    
    JSNumber nanValue = new JSNumber();
    nanValue.instance_type = InstanceType.NUMBER;
    nanValue.value = Double.NaN;
    nan_value = new JSValuePtr(this, allocate(nanValue));

    JSStringPtr undefinedString = allocateString("undefined");
    JSStringPtr nullString = allocateString("null");
    JSStringPtr trueString = allocateString("true");
    JSStringPtr falseString = allocateString("false");
    JSStringPtr holeString = allocateString("hole");
    JSStringPtr objectString = allocateString("object");
    JSStringPtr booleanString = allocateString("boolean");

    Oddball undefinedValue = new Oddball();
    undefinedValue.kind = OddballKind.UNDEFINED;
    undefinedValue.toString = undefinedString;
    undefinedValue.toNumber = (JSNumberPtr) nan_value;
    undefinedValue.type = undefinedString;
    undefined_value = new OddballPtr(this, allocate(undefinedValue));

    Oddball nullValue = new Oddball();
    nullValue.kind = OddballKind.NULL;
    nullValue.toString = nullString;
    nullValue.toNumber = (JSNumberPtr) zero_value;
    nullValue.type = objectString;
    null_value = new OddballPtr(this, allocate(nullValue));

    Oddball holeValue = new Oddball();
    holeValue.kind = OddballKind.HOLE;
    holeValue.toString = holeString;
    holeValue.toNumber = (JSNumberPtr) zero_value;
    holeValue.type = undefinedString;
    hole_value = new OddballPtr(this, allocate(holeValue));

    Oddball trueValue = new Oddball();
    trueValue.kind = OddballKind.TRUE;
    trueValue.toString = trueString;
    trueValue.toNumber = allocateNumber(1);
    trueValue.type = booleanString;
    true_value = new OddballPtr(this, allocate(trueValue));

    Oddball falseValue = new Oddball();
    falseValue.kind = OddballKind.FALSE;
    falseValue.toString = falseString;
    falseValue.toNumber = allocateNumber(0);
    falseValue.type = booleanString;
    false_value = new OddballPtr(this, allocate(falseValue));

    JSObject objectPrototype = new JSObject();
    objectPrototype.instance_type = InstanceType.OBJECT;
    objectPrototype.constructor = null; // Object contructor
    objectPrototype.extensible = true;
    objectPrototype.prototype = null;
    objectPrototype.properties = new HashMap<>();

    object_prototype = new JSObjectPtr(this, allocate(objectPrototype));

  }
}
