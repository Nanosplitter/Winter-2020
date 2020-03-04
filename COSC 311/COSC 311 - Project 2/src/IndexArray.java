public class IndexArray {
    private IndexRecord iter;
    private boolean primaryKey; //Keeping this in case it is useful in Program 3
    
    private IndexRecord first;
    private IndexRecord last;
    
    //Constructor to specify max size and if field is primary key
    public IndexArray(boolean primaryKey) {
        this.first = null;
        this.last = null;
        this.iter = first;
        this.primaryKey = primaryKey;
    }

    //Returns the index of record with specified database index
    public IndexRecord searchByWhere(int where) {
    	IndexRecord rover = first;
    	
    	while(rover != null && !(rover.where == where)) {
    		rover = rover.getNext();
    	}
    	
    	if (rover == null) {
    		return null;
    	} else {
    		return rover;
    	}
    }

    //Returns the record with specified key
    public <K> IndexRecord searchByKey(K key) {
    	IndexRecord rover = first;
    	
    	while(rover != null && !rover.key.equals(key)) {
    		rover = rover.getNext();
    	}
    	
    	if (rover == null) {
    		return null;
    	} else {
    		return rover;
    	}
    }

    //Returns the database index of record with specified key
    public <K> int getWhereByKey(K key) {
    	IndexRecord res = searchByKey(key);
    	
    	if (res != null) {
    		return res.where;
    	} else {
    		return -1;
    	}
    }
    
    //Deletes record at specified index
    public void deleteNode(IndexRecord ir) {
    	if (ir == last) {
    		ir.getPrev().setNext(ir.getNext());
    		last = ir.getPrev();
    	} else if (ir == first) {
    		ir.getNext().setPrev(ir.getPrev());
    		first = ir.getNext();
    	} else {
    		ir.getPrev().setNext(ir.getNext());
    		ir.getNext().setPrev(ir.getPrev());
    	}
    	
    	
    }

    //Adds an IndexRecord into the array
    public void add(IndexRecord ir) {
    	IndexRecord rover;
    	
    	//If inserting at beginning
    	if (first == null || first.key.compareTo(ir.key) >= 0) {
    		ir.setNext(first);
    		if (first != null) {
    			first.setPrev(ir);
    		} else {
    			last = ir;
    		}
    		first = ir;
    	} else {
    		rover = first;
    		
    		while (rover.getNext() != null && rover.getNext().key.compareTo(ir.key) < 0) {
    			rover = rover.getNext();
    		}
    		
    		ir.setNext(rover.getNext());
    		ir.setPrev(rover);
    		if (rover.getNext() == null) {
    			last = ir;
    		} else {
    			rover.getNext().setPrev(ir);
    		}
    		rover.setNext(ir);
    	}
    	
    }
    
    //Initializes iterator to the front of array
    public void iteratorInitFront() {
        this.iter = first;
    }
    
    //Initializes iterator to back of array
    public void iteratorInitBack() {
        this.iter = last;
    }
    
    //Returns if the iterator can move forward more
    public boolean hasNext() {
       return this.iter != null;
    }

    //Returns if the iterator can move backwards more
    public boolean hasPrevious() {
        return this.iter != null;
    }

    //Returns the next IndexRecord's where using the iterator
    public int getNext() {
    	int ret = this.iter.where;
    	this.iter = this.iter.getNext();
        return ret;
    }

    //Returns the previous IndexRecord's where using the iterator
    public int getPrevious() {
    	int ret = this.iter.where;
    	this.iter = this.iter.getPrev();
        return ret;
    }
}
