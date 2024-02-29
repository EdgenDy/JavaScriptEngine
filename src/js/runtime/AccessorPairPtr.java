package js.runtime;

import js.storage.JSHeap;

public class AccessorPairPtr extends JSValuePtr {
  public AccessorPairPtr(JSHeap heap, int index) {
    super(heap, index);
  }
}
