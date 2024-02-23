package js.runtime;

import js.storage.Heap;

public class AccessorPairPtr extends JSValuePtr {
  public AccessorPairPtr(Heap heap, int index) {
    super(heap, index);
  }
}
