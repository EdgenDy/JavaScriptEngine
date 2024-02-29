package js.stack;

import js.api.JavaScriptEngine;

public class JSStack {
  private StackFrame root;
  private StackFrame current;
  private final JavaScriptEngine owner;

  public JSStack(JavaScriptEngine owner) {
    this.owner = owner;
    root = null;
    current = null;
  }

  public JavaScriptEngine getOwner() {
    return owner;
  }

  public StackFrame getRoot() {
    return root;
  }

  public void setRoot(StackFrame root) {
    this.root = root;
  }

  public StackFrame getCurrentFrame() {
    return current;
  }

  public void setCurrentFrame(StackFrame current) {
    this.current = current;
  }
}
