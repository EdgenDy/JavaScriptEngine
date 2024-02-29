package js.storage;

public class Storage {
  private int size;
  private Object[] memory;
  public FreeList freeList;

  public Storage(int size) {
    this.size = size;
    this.memory = new Object[size];
    this.freeList = new FreeList(size);
  }

  public int allocate(Object object) {
    int index = freeList.acquireIndex();
    if (index == -1)
      return -1;

    memory[index] = object;
    return index;
  }

  public void free(int index) {
    if (freeList.returnIndex(index))
      memory[index] = null;
  }

  public int getSize() {
    return size;
  }

  public Object get(int index) {
    return memory[index];
  }

  public void set(int index, Object object) {
    this.memory[index] = object;
  }
}
