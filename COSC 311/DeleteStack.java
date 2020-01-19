public class DeleteStack {
    private int[] stack;
    private int currSize;

    public DeleteStack(int maxSize) {
        this.stack = new int[maxSize];
        this.currSize = 0;
    }

    public void push(int index) {
        this.stack[this.currSize++] = index;
    }

    public int pop() {
        return this.stack[this.currSize--];
    }

    public boolean isEmpty() {
        return this.currSize == 0;
    }
}
