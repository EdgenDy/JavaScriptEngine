package js.storage;

public class FreeList {
  private Range root;

  public FreeList(int size) {
    this.root = new Range(0, size - 1, null);
  }

  public boolean hasAvailableIndex() {
    return this.root != null;
  }

  public int acquireIndex() {
    if (this.root == null)
      return -1;
    return this.root.getIndex(this);
  }

  public boolean returnIndex(int index) {
    if (index == -1)
      return false;
    
    Range current = root;
    Range previous = null;

    if (current == null) {
      root = new Range(index, index, null);
      return true;
    }

    while (current != null) {
      if (current.isGreaterThan(index)) {
        if (current.isOneGreaterOf(index)) {
          current.start--;
          if (previous != null && previous.isOneLessOf(current.start)) {
            previous.end = current.end;
            previous.next = current.next;
          }
        } else {
          if (previous == null) {
            root = new Range(index, index, current);
          } else {
            previous.next = new Range(index, index, current);
          }
        }
        return true;
      } else if (current.next == null) {
        if (current.isOneLessOf(index)) {
          current.end++;
        } else {
          current.next = new Range(index, index, null);
        }
        return true;
      }
      previous = current;
      current = current.next;
    }
    return false;
  }

  public Range getRoot() {
    return root;
  }

  public void empty() {
    this.root = null;
  }

  public static class Range {
    public int start;
    public int end;
    public Range next;

    public Range(int start, int end, Range next) {
      this.start = start;
      this.end = end;
      this.next = next;
    }

    public boolean isInRange(int index) {
      return start >= index && end <= index;
    }

    public int getIndex(FreeList freeList) {
      int index = start;
      start++;

      if (start > end) {
        if (next != null) {
          copyNextToSelf();
        } else {
          freeList.empty();
        }
      }

      return index;
    }

    public boolean isAhead(int index) {
      return index < start;
    }

    public boolean isBehind(int index) {
      return end < index;
    }

    public int compare(int index) {
      if (end < index)
        return -1;
      else
        return 1;
    }

    public boolean isOneStepAhead(int index) {
      return start == (index + 1);
    }

    public boolean isOneStepBehind(int index) {
      return index == (end + 1);
    }

    public boolean isGreaterThan(int index) {
      return start > index;
    }

    public boolean isLessThan(int index) {
      return end < index;
    }

    public boolean isOneGreaterOf(int index) {
      return (index + 1) == start;
    }

    public boolean isOneLessOf(int index) {
      return (index - 1) == end;
    }

    private void copyNextToSelf() {
      this.start = this.next.start;
      this.end = this.next.end;
      this.next = this.next.next;
    }

    @Override
    public String toString() {
      String string_value = "Range {" + start + " - " + end;
      if (next != null)
        string_value += ", " + next.toString();
      else
        string_value += ", null";
      string_value += "}";
      return string_value;
    }
  }
}
