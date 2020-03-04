public class IndexRecord<K extends Comparable<K>> {
    K key;
    int where;
    
    IndexRecord<K> next;
    IndexRecord<K> prev;
    
    public IndexRecord<K> getNext() {
		return next;
	}

	public void setNext(IndexRecord<K> next) {
		this.next = next;
	}

	public IndexRecord<K> getPrev() {
		return prev;
	}

	public void setPrev(IndexRecord<K> prev) {
		this.prev = prev;
	}

	//Constructor to pass key and where to
    public IndexRecord(K key, int where) {
        this.key = key;
        this.where = where;
    }
    
    //Compares key of this record and another record
    public int compareTo(IndexRecord<K> otherRecord) {
        return this.key.compareTo((K) otherRecord.key);
    }
}
