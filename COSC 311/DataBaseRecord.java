public class DataBaseRecord {
    private String ID;
    private String first;
    private String last;

    public DataBaseRecord(String ID, String first, String last) {
        this.ID = ID;
        this.first = first;
        this.last = last;
    }

    public String getID() {
        return ID;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return this.ID + " " + this.first + " " + this.last;
    }

}
