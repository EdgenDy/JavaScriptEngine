package js.stack;

public class StackFrame {
  public int[] parameters;
  public int[] registers;
  public int return_index;
  public StackFrame previous;
}
