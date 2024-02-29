package js.runtime;

import js.storage.JSHeap;

public abstract class JS {
  public static JSValuePtr get(JSHeap heap, JSObject object, NamePtr name) {
    DescriptorPtr ownDesc = object.properties.get(name);
    if (ownDesc != null) {
      JSValuePtr value = ownDesc.value();
      if (!value.isAccessorPair())
        return value;
      // Call the GET Accessor
    }

    JSObjectPtr prototype = (JSObjectPtr) object.prototype;
    if (prototype != null)
      return get(heap, prototype.toJSObject(), name);
      
    return heap.undefined_value();
  }

  public static boolean set(JSHeap heap, JSObject object, NamePtr name, JSValuePtr value) {
    DescriptorPtr ownDesc = getDescriptor(object, name);

    if (ownDesc == null) {
      DescriptorPtr newDesc = heap.allocateDescriptor(value, None);
      object.properties.put(name, newDesc);
      return true;
    } else {
      JSValuePtr desc_value = ownDesc.value();
      if (desc_value.isAccessorPair()) {
        // Call the Set accessor
        //call(desc_value.toAccessorPair().set, object, value);
        return true;
      } else {
        if (!isReadOnly(ownDesc.toDescriptor()))
          ownDesc.toDescriptor().value = value;
      }
    }
    return false;
  }

  private static DescriptorPtr getDescriptor(JSObject object, NamePtr name) {
    DescriptorPtr descriptorPtr = object.properties.get(name);
    if (descriptorPtr == null) {
      JSObjectPtr prototypePtr = object.prototype;
      if (prototypePtr == null)
        return null;
      return getDescriptor(prototypePtr.toJSObject(), name);
    }

    return descriptorPtr;
  }

  public static JSValuePtr call(JSObject callable, JSObject receiver) {
    return null;
  }

  public static boolean isReadOnly(Descriptor descriptor) {
    return (descriptor.attributes & ReadOnly) != 0;
  }

  public static boolean isDontEnum(Descriptor descriptor) {
    return (descriptor.attributes & DontEnum) != 0;
  }

  public static boolean isDontDelete(Descriptor descriptor) {
    return (descriptor.attributes & DontDelete) != 0;
  }

  public static final int None = 1 << 0;
  public static final int ReadOnly = 1 << 1;
  public static final int DontEnum = 1 << 2;
  public static final int DontDelete = 1 << 3;
}
