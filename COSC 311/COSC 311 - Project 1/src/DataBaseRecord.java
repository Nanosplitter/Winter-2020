public class DataBaseRecord {
    private String ID;
    private String first;
    private String last;

    //Constructor to pass the fields to
    public DataBaseRecord(String ID, String first, String last) {
        this.ID = ID;
        this.first = first;
        this.last = last;
    }
    
    //Getter methods if needed in future
    public String getID() {
        return ID;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    //To string to print out the record
    @Override
    public String toString() {
        return this.ID + " " + this.first + " " + this.last;
    }

}
