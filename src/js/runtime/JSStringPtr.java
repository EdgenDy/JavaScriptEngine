package js.runtime;

import js.storage.Heap;

public class JSStringPtr extends NamePtr {
  public JSStringPtr(Heap heap, int index) {
    super(heap, index);
  }

  public JSString toJSString() {
    return (JSString) toJSValue();
  }
}
