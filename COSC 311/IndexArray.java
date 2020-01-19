public class IndexArray {
    private IndexRecord[] indexArr;
    private int maxSize;
    private int currSize;
    private int iter;
    private boolean primaryKey;

    public IndexArray(int maxSize, boolean primaryKey) {
        this.indexArr = new IndexRecord[maxSize];
        this.maxSize = maxSize;
        this.currSize = 0;
        this.iter = currSize;
        this.primaryKey = primaryKey;
    }

    public int searchByWhere(int dbIndex) {
        for(int i = 0; i < this.currSize; i++) {
            if (this.indexArr[i].where == dbIndex) {
                return i;
            }
        }
        return -1;
    }

    public <K> int searchByKey(K key) {
        int mid = -1;
        if (this.primaryKey) {
            int low = 0;
            int high = this.currSize;
            int compare = -1;

            while (low + 1 <= high) {
                mid = (low + high) / 2;
                compare = this.indexArr[mid].key.compareTo(key);
                if (compare == 0) {
                    break;
                } else if (compare > 0) {
                    high = mid;
                } else {
                    low = mid;
                }
            }
        } else {
            for(int i = 0; i < this.currSize; i++) {
                if(this.indexArr[i].key.compareTo(key) == 0) {
                    mid = i;
                }
            }
        }

        return mid;
    }

    public void deleteByKey() {

    }
    //TODO: write this, also write delete by key using binary search
    public void deleteByIndex(int dbIndex) {
        int indexToDelete = searchByWhere(dbIndex);
        
        for (int i = indexToDelete; i < this.currSize - 1; i++) {
            this.indexArr[i] = this.indexArr[i + 1];
        }
    }

    public void add(IndexRecord ir) {
        int i;
        for (i = currSize - 1; i >= 0; i--) {
            if (this.indexArr[i].compareTo(ir) < 0) {break;}
            this.indexArr[i + 1] = this.indexArr[i];
        }
        this.indexArr[i + 1] = ir;
        this.currSize++;
    }

    public void printIt() {
        String res = "";
        for (int i = 0; i < this.currSize; i++) {
            res += "(" + indexArr[i].key + ", " + indexArr[i].where + "), ";
        }
        System.out.println(res);
    }

    public void iteratorInitFront() {
        this.iter = 0;
    }

    public void iteratorInitBack() {
        this.iter = this.currSize - 1;
    }

    public boolean hasNext() {
       return this.iter < this.currSize;
    }

    public boolean hasPrevious() {
        return this.iter > 0;
    }

    public int getNext() {
        return this.indexArr[this.iter++].where;
    }

    public int getPrevious() {
        return this.indexArr[this.iter--].where;
    }


}
