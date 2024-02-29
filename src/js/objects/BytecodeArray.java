package js.objects;

import js.runtime.JSValue;

public class BytecodeArray extends JSValue {
  private FixedArray constant_pool;
  private FixedArray handler_table;
  //private FixedArray source_position_table;
  private byte[] bytes;

  public BytecodeArray(int size) {
    this.bytes = new byte[size];
    constant_pool = null;
    handler_table = null;
  }

  public byte get(int index) {
    return bytes[index];
  }

  public void set(int index, int value) {
    bytes[index] = (byte) value;
  }

  public FixedArray getConstantPool() {
    return constant_pool;
  }

  public void setConstantPool(FixedArray constant_pool) {
    this.constant_pool = constant_pool;
  }

  public FixedArray getHandlerTable() {
    return handler_table;
  }

  public void setHandlerTable(FixedArray handler_table) {
    this.handler_table = handler_table;
  }
}
