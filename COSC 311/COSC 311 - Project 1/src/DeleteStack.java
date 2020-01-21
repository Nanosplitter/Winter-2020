public class DeleteStack {
    private int[] stack;
    private int currSize;
    
    //Constructor to pass the max size to
    public DeleteStack(int maxSize) {
        this.stack = new int[maxSize];
        this.currSize = 0;
    }
    
    //Pushes database index onto array
    public void push(int index) {
        this.stack[this.currSize++] = index;
    }
    
    //Returns the database index at the end of the array and decrements the end-of-array pointer
    public int pop() {
        return this.stack[--this.currSize];
    }

    //Returns if the stack is empty
    public boolean isEmpty() {
        return this.currSize == 0;
    }
}
