public class IndexArray {
    private IndexRecord[] indexArr;
    private int maxSize;
    private int currSize;
    private int iter;
    private boolean primaryKey;
    
    //Constructor to specify max size and if field is primary key
    public IndexArray(int maxSize, boolean primaryKey) {
        this.indexArr = new IndexRecord[maxSize];
        this.maxSize = maxSize;
        this.currSize = 0;
        this.iter = currSize;
        this.primaryKey = primaryKey;
    }

    //Returns the index of record with specified database index
    public int searchByWhere(int where) {
        for(int i = 0; i < this.currSize; i++) {
            if (this.indexArr[i].where == where) {
                return i;
            }
        }
        return -1;
    }

    //Returns the index of record with specified key
    public <K> int searchByKey(K key) {
        int mid = -1;
        int low = 0;
        int high = this.currSize - 1;
        int compare = -1;
        if (this.primaryKey) {
            while (low <= high) {
                mid = (low + high) / 2;
                compare = this.indexArr[mid].key.compareTo(key);
                if (compare == 0) {
                    break;
                } else if (compare > 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        } else {
            for(int i = 0; i < this.currSize; i++) {
                if(this.indexArr[i].key.compareTo(key) == 0) {
                    mid = i;
                }
            }
        }
        
        if (low > high) {
        	mid = -1;
        }
        return mid;
    }

    //Returns the database index of record with specified key
    public <K> int getWhereByKey(K key) {
    	int index = searchByKey(key);
    	if (index != -1) {
    		index = this.indexArr[index].where;
    	}
    	
    	return index;
    }
    
    //Deletes record at specified index
    public void deleteIndex(int indexToDelete) {
    	for (int i = indexToDelete; i < this.currSize - 1; i++) {
            this.indexArr[i] = this.indexArr[i + 1];
        }
    	this.currSize--;
    }

    //Adds an IndexRecord into the array
    public void add(IndexRecord ir) {
        int i;
        for (i = this.currSize - 1; i >= 0; i--) {
            if (this.indexArr[i].compareTo(ir) < 0) {break;}
            this.indexArr[i + 1] = this.indexArr[i];
        }
        this.indexArr[i + 1] = ir;
        this.currSize++;
    }

    //Debugging method to print whole array to console
    public void printIt() {
        String res = "";
        for (int i = 0; i < this.currSize; i++) {
            res += "(" + indexArr[i].key + ", " + indexArr[i].where + "), ";
        }
        System.out.println(res);
    }
    
    //Initializes iterator to the front of array
    public void iteratorInitFront() {
        this.iter = 0;
    }
    
    //Initializes iterator to back of array
    public void iteratorInitBack() {
        this.iter = this.currSize - 1;
    }
    
    //Returns if the iterator can move forward more
    public boolean hasNext() {
       return this.iter < this.currSize;
    }

    //Returns if the iterator can move backwards more
    public boolean hasPrevious() {
        return this.iter > 0;
    }

    //Returns the next IndexRecord's where using the iterator
    public int getNext() {
        return this.indexArr[this.iter++].where;
    }

    //Returns the previous IndexRecord's where using the iterator
    public int getPrevious() {
        return this.indexArr[this.iter--].where;
    }


}
