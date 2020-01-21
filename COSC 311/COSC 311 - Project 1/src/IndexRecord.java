public class IndexRecord<K extends Comparable<K>> {
    K key;
    int where;
    
    //Constructor to pass key and where to
    public IndexRecord(K key, int where) {
        this.key = key;
        this.where = where;
    }
    
    //Compares key of this record and another record
    public int compareTo(IndexRecord otherRecord) {
        return this.key.compareTo((K) otherRecord.key);
    }
}
