package js.runtime;

import java.util.Map;

public class JSObject extends JSValue {
  public JSObjectPtr prototype;
  public boolean extensible;
  public JSValuePtr constructor;
  public Map<NamePtr, DescriptorPtr> properties;
}
