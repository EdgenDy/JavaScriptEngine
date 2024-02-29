package js.api;

import js.interpreter.Interpreter;
import js.runtime.JSNumberPtr;
import js.runtime.JSObjectPtr;
import js.runtime.JSStringPtr;
import js.stack.JSStack;
import js.storage.JSHeap;

public class JavaScriptEngine {
  private JSHeap heap;
  private JSStack stack;
  private Interpreter interpreter;

  public JavaScriptEngine() {
    heap = null;
    stack = null;
    interpreter = null;
  }

  public JSHeap getHeap() {
    return heap;
  }

  public JSStack getStack() {
    return stack;
  }

  public void setHeapSize(int size) {
    heap = new JSHeap(size);
  }

  public int getHeapSize() {
    return heap != null ? heap.getSize() : 0;
  }

  public Interpreter getInterpreter() {
    return interpreter;
  }

  public JSStringPtr newString(CharSequence chars) {
    return heap.allocateString(chars);
  }

  public JSNumberPtr newNumber(double value) {
    return heap.allocateNumber(value);
  }

  public JSObjectPtr newEmptyObject() {
    return heap.allocateEmptyObject();
  }
}
