public class DataBaseArray {
    private DataBaseRecord[] data;
    private int maxSize;
    private int currSize;
    
    //Constructor to specify size of database
    public DataBaseArray(int maxSize) {
        this.data = new DataBaseRecord[maxSize];
        this.maxSize = maxSize;
        this.currSize = 0;
    }

    //Inserts a record into the desired index
    public void setRecord(DataBaseRecord dbr, int index) {
        this.data[index] = dbr;
    }

    //Inserts a record at the end of the array
    public void addRecord(DataBaseRecord dbr) {
        if (currSize != maxSize) {
            this.data[currSize] = dbr;
            this.currSize++;
        }
    }

    //Returns the current size of the array
    public int getSize() {
        return this.currSize;
    }
    
    //Returns the record at the specified index
    public DataBaseRecord getRecord(int index) {
        return data[index];
    }
}
