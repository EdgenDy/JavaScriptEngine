package js.runtime;

import js.storage.Heap;

public class DescriptorPtr extends JSValuePtr {
  public DescriptorPtr(Heap heap, int index) {
    super(heap, index);
  }

  public JSValuePtr value() {
    return toDescriptor().value;
  }

  public int details() {
    return toDescriptor().details;
  }

  public Descriptor toDescriptor() {
    Descriptor descriptor = (Descriptor) toJSValue();
    return descriptor;
  }
}
