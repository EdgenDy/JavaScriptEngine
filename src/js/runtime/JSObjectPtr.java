package js.runtime;

import js.storage.Heap;

public class JSObjectPtr extends JSValuePtr {
  public JSObjectPtr(Heap heap, int index) {
    super(heap, index);
  }

  public JSValuePtr getProperty(NamePtr name) {
    DescriptorPtr descriptor = getDescriptor(name);
    JSValuePtr value = descriptor.value();

    if (value.isAccessorPair()) {
      // JSValuePtr get = value.toAccessorPair().get;
    }
    return value;
  }

  public DescriptorPtr getDescriptor(NamePtr name) {
    JSObject object = toJSObject();
    DescriptorPtr descriptor = object.properties.get(name);
    if (descriptor == null && object.prototype != null)
      return ((JSObjectPtr) object.prototype).getDescriptor(name);
    return descriptor;
  }

  public void setDescriptor(NamePtr name, DescriptorPtr descriptor) {
    DescriptorPtr current = getDescriptor(name);
    if (current != null) {

      return;
    }
    JSObject object = toJSObject();
    object.properties.put(name, descriptor);
  }

  public JSValuePtr constructor() {
    JSObject object = (JSObject) toJSValue();
    return object.constructor;
  }

  public JSValuePtr prototype() {
    JSObject object = (JSObject) toJSValue();
    return object.prototype;
  }

  public boolean isExtensible() {
    JSObject object = (JSObject) toJSValue();
    return object.extensible;
  }

  public JSObject toJSObject() {
    return (JSObject) toJSValue();
  }
}
