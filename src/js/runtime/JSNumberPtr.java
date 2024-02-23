package js.runtime;

import js.storage.Heap;

public class JSNumberPtr extends JSValuePtr {
  public JSNumberPtr(Heap heap, int index) {
    super(heap, index);
  }

  public double value() {
    return toJSNumber().value;
  }

  public JSNumber toJSNumber() {
    return (JSNumber) toJSValue();
  }
}
