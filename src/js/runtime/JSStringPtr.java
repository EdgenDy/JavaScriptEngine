package js.runtime;

import js.storage.JSHeap;

public class JSStringPtr extends NamePtr {
  public JSStringPtr(JSHeap heap, int index) {
    super(heap, index);
  }

  public JSString toJSString() {
    return (JSString) toJSValue();
  }
}
