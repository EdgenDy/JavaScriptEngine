package js.runtime;

import js.storage.JSHeap;

public class JSNumberPtr extends JSValuePtr {
  public JSNumberPtr(JSHeap heap, int index) {
    super(heap, index);
  }

  public double value() {
    return toJSNumber().value;
  }

  public JSNumber toJSNumber() {
    return (JSNumber) toJSValue();
  }
}
