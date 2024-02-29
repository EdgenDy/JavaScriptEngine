package js.interpreter;

import js.objects.BytecodeArray;

public final class Interpreter {
  private BytecodeArray bytecode_array;
  private int accumulator = 0;

  public BytecodeArray getBytecodeArray() {
    return bytecode_array;
  }

  public void setBytecodeArray(BytecodeArray bytecode_array) {
    this.bytecode_array = bytecode_array;
  }

  public void setAccumulator(int value) {
    accumulator = value;
  }

  public int getAccumulator() {
    return accumulator;
  }
}
