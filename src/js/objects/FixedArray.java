package js.objects;

import js.runtime.JSValue;

public class FixedArray extends JSValue {
  private JSValue[] items;

  public FixedArray(int size) {
    this.items = new JSValue[size];
  }

  public JSValue get(int index) {
    return items[index];
  }

  public void set(int index, JSValue value) {
    items[index] = value;
  }
}
