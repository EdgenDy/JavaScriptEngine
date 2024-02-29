package js.runtime;

import js.storage.JSHeap;

public class JSValuePtr {
  protected final JSHeap heap;
  private int index = 0;

  public JSValuePtr(JSHeap heap, int index) {
    this.heap = heap;
    this.index = index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  public JSHeap getHeap() {
    return heap;
  }

  public boolean isNumber() {
    JSValue value = toJSValue();
    return value.instance_type == InstanceType.NUMBER;
  }

  public boolean isString() {
    JSValue value = toJSValue();
    return value.instance_type == InstanceType.STRING;
  }

  public boolean isBoolean() {
    JSValue value = toJSValue();
    return value.instance_type == InstanceType.BOOLEAN;
  }

  public boolean isObject() {
    JSValue value = toJSValue();
    return value.instance_type == InstanceType.OBJECT;
  }

  public boolean isFunction() {
    JSValue value = toJSValue();
    return value.instance_type == InstanceType.FUNCTION;
  }

  public boolean isSymbol() {
    JSValue value = toJSValue();
    return value.instance_type == InstanceType.SYMBOL;
  }

  public boolean isAccessorPair() {
    JSValue value = toJSValue();
    return value.instance_type == InstanceType.ACCESSOR_PAIR;
  }

  protected JSValue toJSValue() {
    return heap.get(index);
  }

  protected JSValuePtr toJSValuePtr() {
    return new JSValuePtr(heap, index);
  }

  public AccessorPairPtr toAccessorPairPtr() {
    return (AccessorPairPtr) this;
  }

  public JSNumberPtr toJSNumberPtr() {
    return (JSNumberPtr) this;
  }

  public AccessorPair toAccessorPair() {
    return (AccessorPair) toJSValue();
  }
}
