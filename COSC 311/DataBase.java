public class DataBase {
    private DataBaseArray dbArr;
    private IndexArray firstIA, lastIA, IDIA;
    private DeleteStack deleteStack;

    public DataBase() {
        this.dbArr = new DataBaseArray(100);
        this.firstIA = new IndexArray(100);
        this.lastIA = new IndexArray(100);
        this.IDIA = new IndexArray(100);
        this.deleteStack = new DeleteStack(100);
    }

    public DataBase(int maxSize) {
        this.dbArr = new DataBaseArray(maxSize);
        this.firstIA = new IndexArray(maxSize);
        this.lastIA = new IndexArray(maxSize);
        this.IDIA = new IndexArray(maxSize);
        this.deleteStack = new DeleteStack(maxSize);
    }

    public void insertIt(String ID, String fname, String lname) {
        DataBaseRecord record = new DataBaseRecord(ID, fname, lname);
        int where = -1;
        if (this.deleteStack.isEmpty()) {
            this.dbArr.addRecord(record);
        } else {
            where = this.deleteStack.pop();
            this.dbArr.setRecord(record, where);
        }
        if (where < 0) {
            where = this.dbArr.getSize() - 1;
        }
        this.firstIA.add(new IndexRecord<String>(fname, where));
        this.lastIA.add(new IndexRecord<String>(lname, where));
        this.IDIA.add(new IndexRecord<String>(ID, where));
    }

    public void delete(int index) {
        this.deleteStack.push(index);
//        firstIA.delete(dbArr.getRecord(index).getFirst());
//        lastIA.delete(dbArr.getRecord(index).getLast());
//        IDIA.delete(dbArr.getRecord(index).getID());
    }

    public void dumpDataBase() {
        for (int i = 0; i < dbArr.getSize(); i++) {
            System.out.println(dbArr.getRecord(i));
        }
    }

    public void list(IndexArray ia, boolean asc) {
        if (asc) {
            ia.iteratorInitFront();
            while(ia.hasNext()) {
                System.out.println(this.dbArr.getRecord(ia.getNext()));
            }
        } else {
            ia.iteratorInitBack();
            while(ia.hasPrevious()) {
                System.out.println(this.dbArr.getRecord(ia.getPrevious()));
            }
        }
    }

    public void listByFirstAscending() {
        this.list(firstIA, true);
    }

    public void listByFirstDecending() {
        this.list(firstIA, false);
    }

    public void listByLastAscending() {
        this.list(lastIA, true);
    }

    public void listByLastDecending() {
        this.list(lastIA, false);
    }

    public void listByIDAscending() {
        this.list(IDIA, true);
    }

    public void listByIDDecending() {
        this.list(IDIA, false);
    }
}
