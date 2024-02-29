package js.runtime;

import js.storage.JSHeap;

public class JSSymbolPtr extends NamePtr {
  public JSSymbolPtr(JSHeap heap, int index) {
    super(heap, index);
  }

  public JSStringPtr description() {
    JSSymbol symbol = (JSSymbol) toJSValue();
    return symbol.description;
  }
}
