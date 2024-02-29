package js.runtime;

import js.storage.JSHeap;

public class DescriptorPtr extends JSValuePtr {
  public DescriptorPtr(JSHeap heap, int index) {
    super(heap, index);
  }

  public JSValuePtr value() {
    return toDescriptor().value;
  }

  public int attributes() {
    return toDescriptor().attributes;
  }

  public Descriptor toDescriptor() {
    Descriptor descriptor = (Descriptor) toJSValue();
    return descriptor;
  }
}
