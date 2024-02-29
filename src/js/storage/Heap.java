package js.storage;

import java.util.HashMap;
import java.util.Map;

import js.runtime.JSNumber;
import js.runtime.JSNumberPtr;
import js.runtime.JSString;
import js.runtime.JSStringPtr;
import js.runtime.JSValue;
import js.runtime.JSValuePtr;
import js.runtime.Oddball;
import js.runtime.OddballKind;
import js.runtime.InstanceType;

public class Heap {
  private JSValue[] storage;
  private JSValuePtr nan_value;
  private JSValuePtr undefined_value;
  private JSValuePtr null_value;
  private JSValuePtr zero_value;
  private JSValuePtr hole_value;
  private JSValuePtr true_value;
  private JSValuePtr false_value;
  private Map<String, Integer> stringCache;
  
  private int index = 0;

  private Heap(int size) {
    this.storage = new JSValue[size];
    this.stringCache = new HashMap<String, Integer>();
  }

  public JSValuePtr nan_value() {
    return nan_value;
  }

  public JSValuePtr undefined_value() {
    return undefined_value;
  }

  public JSValuePtr null_value() {
    return null_value;
  }

  public JSValuePtr getZeroValue() {
    return zero_value;
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

  public JSValue at(int index) {
    return storage[index];
  }

  public void update(int index, JSValue value) {
    storage[index] = value;
  }

  public JSValuePtr allocate(JSValue value) {
    //int current = index;
    storage[index++] = value;
    return null;
  }

  public JSStringPtr allocateExternalString(String value) {
    JSString string = new JSString();
    string.instance_type = InstanceType.STRING;
    string.value = value;
    JSValuePtr valueptr = allocate(string);
    stringCache.put(value, valueptr.getIndex());

    return null;
  }

  public JSNumberPtr allocateNumber(double value) {
    JSNumber number = new JSNumber();
    number.instance_type = InstanceType.NUMBER;
    number.value = value;
    return (JSNumberPtr) allocate(number);
  }

  public static Heap initialize(int size) {
    Heap heap = new Heap(size);

    JSNumber zeroNumber = new JSNumber();
    zeroNumber.instance_type = InstanceType.NUMBER;
    zeroNumber.value = 0.0;
    heap.zero_value = heap.allocate(zeroNumber);
    
    JSNumber nanValue = new JSNumber();
    nanValue.instance_type = InstanceType.NUMBER;
    nanValue.value = Double.NaN;
    heap.nan_value = heap.allocate(nanValue);

    JSStringPtr undefinedString = heap.allocateExternalString("undefined");
    JSStringPtr nullString = heap.allocateExternalString("null");
    JSStringPtr trueString = heap.allocateExternalString("true");
    JSStringPtr falseString = heap.allocateExternalString("false");
    JSStringPtr holeString = heap.allocateExternalString("hole");
    JSStringPtr objectString = heap.allocateExternalString("object");
    JSStringPtr booleanString = heap.allocateExternalString("boolean");

    Oddball undefinedValue = new Oddball();
    undefinedValue.kind = OddballKind.UNDEFINED;
    undefinedValue.toString = undefinedString;
    undefinedValue.toNumber = (JSNumberPtr) heap.nan_value;
    undefinedValue.type = undefinedString;
    heap.undefined_value = heap.allocate(undefinedValue);

    Oddball nullValue = new Oddball();
    nullValue.kind = OddballKind.NULL;
    nullValue.toString = nullString;
    nullValue.toNumber = (JSNumberPtr) heap.zero_value;
    nullValue.type = objectString;
    heap.null_value = heap.allocate(nullValue);

    Oddball holeValue = new Oddball();
    holeValue.kind = OddballKind.HOLE;
    holeValue.toString = holeString;
    holeValue.toNumber = (JSNumberPtr) heap.zero_value;
    holeValue.type = undefinedString;
    heap.hole_value = heap.allocate(holeValue);

    Oddball trueValue = new Oddball();
    trueValue.kind = OddballKind.TRUE;
    trueValue.toString = trueString;
    trueValue.toNumber = heap.allocateNumber(1);
    trueValue.type = booleanString;
    heap.true_value = heap.allocate(trueValue);

    Oddball falseValue = new Oddball();
    falseValue.kind = OddballKind.FALSE;
    falseValue.toString = falseString;
    falseValue.toNumber = heap.allocateNumber(0);
    falseValue.type = booleanString;
    heap.false_value = heap.allocate(falseValue);


    return heap;
  }
}
