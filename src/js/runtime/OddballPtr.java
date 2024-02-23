package js.runtime;

import js.storage.Heap;

public class OddballPtr extends JSValuePtr {
  public OddballPtr(Heap heap, int index) {
    super(heap, index);
  }

  public Oddball toOddball() {
    return (Oddball) toJSValue();
  }
}
