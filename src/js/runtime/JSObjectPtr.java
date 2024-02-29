package js.runtime;

import js.storage.JSHeap;

public class JSObjectPtr extends JSValuePtr {
  public JSObjectPtr(JSHeap heap, int index) {
    super(heap, index);
  }

  public JSValuePtr get(NamePtr name) {
    return JS.get(heap, toJSObject(), name);
  }

  public boolean set(NamePtr name, JSValuePtr value) {
    return JS.set(heap, toJSObject(), name, value);
  }

  public JSValuePtr getProperty(NamePtr name) {
    DescriptorPtr descriptor = getDescriptor(name);
    JSValuePtr value = descriptor.value();

    if (value.isAccessorPair()) {
      // JSValuePtr get = value.toAccessorPair().get;
    }
    return value;
  }

  public void setProperty(NamePtr name, DescriptorPtr descriptor) {
    DescriptorPtr current = getDescriptor(name);
    if (current == null) {
      
    }

    JSValuePtr value = current.value();
    if (value.isAccessorPair()) {
      if (value.toAccessorPair().get != null) {
        // calling the set accessor function
      }
    }
  }

  public DescriptorPtr getDescriptor(NamePtr name) {
    JSObject object = toJSObject();
    DescriptorPtr descriptor = object.properties.get(name);
    if (descriptor == null && object.prototype != null)
      return ((JSObjectPtr) object.prototype).getDescriptor(name);
    return descriptor;
  }

  public boolean setDescriptor(NamePtr name, DescriptorPtr descriptor) {
    DescriptorPtr current = getDescriptor(name);
    if (current == null) {
      JSObject object = toJSObject();
      object.properties.put(name, descriptor);
      return true;
    }

    Descriptor current_descriptor = current.toDescriptor();

    if (JS.isDontDelete(current_descriptor)) {
      
    }
    return true;
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
