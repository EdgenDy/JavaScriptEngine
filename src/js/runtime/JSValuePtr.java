package js.runtime;

import js.storage.Heap;

public class JSValuePtr {
  private final Heap heap;
  private int index = 0;

  public JSValuePtr(Heap heap, int index) {
    this.heap = heap;
    this.index = index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  public Heap getHeap() {
    return heap;
  }

  public boolean isNumber() {
    JSValue value = toJSValue();
    return value.type == ValueType.NUMBER;
  }

  public boolean isString() {
    JSValue value = toJSValue();
    return value.type == ValueType.STRING;
  }

  public boolean isBoolean() {
    JSValue value = toJSValue();
    return value.type == ValueType.BOOLEAN;
  }

  public boolean isObject() {
    JSValue value = toJSValue();
    return value.type == ValueType.OBJECT;
  }

  public boolean isFunction() {
    JSValue value = toJSValue();
    return value.type == ValueType.FUNCTION;
  }

  public boolean isSymbol() {
    JSValue value = toJSValue();
    return value.type == ValueType.SYMBOL;
  }

  public boolean isAccessorPair() {
    JSValue value = toJSValue();
    return value.type == ValueType.ACCESSOR_PAIR;
  }

  protected JSValue toJSValue() {
    return heap.at(index);
  }

  protected JSValuePtr toJSValuePtr() {
    return new JSValuePtr(heap, index);
  }

  public AccessorPairPtr toAccessorPairPtr() {
    return (AccessorPairPtr) this;
  }

  public AccessorPair toAccessorPair() {
    return (AccessorPair) toJSValue();
  }
}
