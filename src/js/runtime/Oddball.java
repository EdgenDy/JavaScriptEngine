package js.runtime;

public class Oddball extends JSValue {
  public OddballKind kind;
  public JSNumberPtr toNumber;
  public JSStringPtr toString;
  public JSStringPtr type;
}
