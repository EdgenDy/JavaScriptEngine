package js.runtime;

import js.storage.Heap;

public class JSSymbolPtr extends NamePtr {
  public JSSymbolPtr(Heap heap, int index) {
    super(heap, index);
  }

  public JSStringPtr description() {
    JSSymbol symbol = (JSSymbol) toJSValue();
    return symbol.description;
  }
}
