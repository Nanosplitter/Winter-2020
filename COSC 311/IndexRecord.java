public class IndexRecord<K extends Comparable<K>> {
    K key;
    int where;

    public IndexRecord(K key, int where) {
        this.key = key;
        this.where = where;
    }

    public int compareTo(IndexRecord otherRecord) {
        return this.key.compareTo((K) otherRecord.key);
    }
}
