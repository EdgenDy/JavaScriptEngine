package js.runtime;

public class Descriptor extends JSValue {
  public JSValuePtr value;
  public int details;

  public static final int ReadOnly = 1 << 1;
  public static final int DontEnum = 1 << 2;
  public static final int DontDelete = 1 << 3;
}
