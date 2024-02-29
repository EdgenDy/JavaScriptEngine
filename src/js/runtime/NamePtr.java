package js.runtime;

import js.storage.JSHeap;

public abstract class NamePtr extends JSValuePtr {
  protected NamePtr(JSHeap heap, int index) {
    super(heap, index);
  }

  public boolean isString() {
    return this instanceof JSStringPtr;
  }

  public boolean isSymbol() {
    return this instanceof JSSymbolPtr;
  }
}
