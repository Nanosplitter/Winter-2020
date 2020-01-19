import java.util.ArrayList;

public class DataBaseArray {
    private DataBaseRecord[] data;
    private int maxSize;
    private int currSize;

    public DataBaseArray(int maxSize) {
        this.data = new DataBaseRecord[maxSize];
        this.maxSize = maxSize;
        this.currSize = 0;
    }

    public void setRecord(DataBaseRecord dbr, int index) {
        this.data[index] = dbr;
    }

    public void addRecord(DataBaseRecord dbr) {
        if (currSize != maxSize) {
            this.data[currSize] = dbr;
            this.currSize++;
        }
    }

    public int getSize() {
        return this.currSize;
    }

    public DataBaseRecord getRecord(int index) {
        return data[index];
    }
}
