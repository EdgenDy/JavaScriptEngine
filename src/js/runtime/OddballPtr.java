package js.runtime;

import js.storage.JSHeap;

public class OddballPtr extends JSValuePtr {
  public OddballPtr(JSHeap heap, int index) {
    super(heap, index);
  }

  public Oddball toOddball() {
    return (Oddball) toJSValue();
  }
}
