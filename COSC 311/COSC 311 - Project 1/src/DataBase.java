import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataBase {
    private DataBaseArray dbArr;
    private IndexArray firstIA, lastIA, IDIA;
    private DeleteStack deleteStack;
    private Scanner scan;
    private String nld = "\n-----------------------------\n";
    private String dnl = "-----------------------------\n";

    //Default Constructor
    public DataBase() {
        this.dbArr = new DataBaseArray(100);
        this.firstIA = new IndexArray(100, false);
        this.lastIA = new IndexArray(100, false);
        this.IDIA = new IndexArray(100, true);
        this.deleteStack = new DeleteStack(100);
        readInDataFromFile();
        scan = new Scanner(System.in);
    }

    //Constructor for custom size
    public DataBase(int maxSize) {
        this.dbArr = new DataBaseArray(maxSize);
        this.firstIA = new IndexArray(maxSize, false);
        this.lastIA = new IndexArray(maxSize, false);
        this.IDIA = new IndexArray(maxSize, true);
        this.deleteStack = new DeleteStack(maxSize);
        readInDataFromFile();
        scan = new Scanner(System.in);
    }
    
    //Read in the initial records
    public void readInDataFromFile() {
    	File dbData = new File("dbData.txt");
    	Scanner scan = new Scanner(System.in); //Have to initialize to something for compilation
		try {
			scan = new Scanner(dbData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	String ID = "";
    	String fname = "";
    	String lname = "";
    	String[] values;
    	
    	while(scan.hasNextLine()) {
    		values = scan.nextLine().split(",");
    		lname = values[0];
    		fname = values[1];
    		ID = values[2];
    		this.insertIt(ID, fname, lname);
    	}
    	
    }
    
    //Ask user to specify ID, delete that record
    public void deleteIt() {
    	String ID = "";
    	System.out.println("\nDELETING" + nld);
    	System.out.println("Please enter the ID of the record to be deleted: ");
    	ID = this.scan.nextLine();
    	if (this.IDIA.searchByKey(ID) == -1) {
    		System.out.println(dnl + "Record not found, please try again." + nld);
    		return;
    	}
    	
    	this.delete(ID);
    	System.out.println(dnl + "Record successfully deleted" + nld);
    }
    
    //Ask user to specify ID, return data of that record
    public void findIt() {
    	String ID = "";
    	System.out.println("\nFINDING" + nld);
    	System.out.println("Please enter the ID of the record to be found: ");
    	ID = this.scan.nextLine();
    	int iaIndexOfRecord = this.IDIA.searchByKey(ID);
    	
    	if (iaIndexOfRecord == -1) {
    		System.out.println(dnl + "Record not found, please try again." + nld);
    		return;
    	}
    	
    	System.out.println(this.dbArr.getRecord(this.IDIA.getWhereByKey(ID)));
    	System.out.println(dnl + "Record found" + nld);
    }
    
    //Ask user to add a new record
    public void addIt() {
    	String ID = "";
    	String first = "";
    	String last = "";
    	System.out.println("\nADDING" + nld);
    	System.out.println("Please enter the ID of the record: ");
    	ID = this.scan.nextLine();
    	if (this.IDIA.searchByKey(ID) != -1) {
    		System.out.println(dnl + "ID already in use, please try again." + nld);
    		return;
    	}
    	
    	System.out.println("Please enter the first name of the record: ");
    	first = this.scan.nextLine();
    	
    	System.out.println("Please enter the last name of the record: ");
    	last = this.scan.nextLine();
    	
    	this.insertIt(ID, first, last);
    	System.out.println(dnl + "Record successfully added" + nld);
    	
    }

    //Insert record into database
    public void insertIt(String ID, String fname, String lname) {
    	if (IDIA.searchByKey(ID) == -1) {
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
	       
    	
    }

    //Delete a record by passing the key of the record to be deleted
    public <K> void delete(K key) {
    	int whereToDelete = IDIA.getWhereByKey(key);
    	
    	IDIA.deleteIndex(IDIA.searchByKey(key));
    	firstIA.deleteIndex(firstIA.searchByWhere(whereToDelete));
    	lastIA.deleteIndex(lastIA.searchByWhere(whereToDelete));
    	deleteStack.push(whereToDelete);
    	
    }

    //Debugging method to print entire database
    public void dumpDataBase() {
        for (int i = 0; i < dbArr.getSize(); i++) {
            System.out.println(dbArr.getRecord(i));
        }
    }

    //List the database by passing the desired IndexArray and order to list in
    public void list(IndexArray ia, boolean asc) {
    	System.out.println("\nLISTING" + nld);
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
        
        System.out.println(dnl + "Records successfully listed" + nld);
    }

    //Driver methods for listing
    public void ListByFirstAscending() {
        this.list(firstIA, true);
    }

    public void ListByFirstDescending() {
        this.list(firstIA, false);
    }

    public void ListByLastAscending() {
        this.list(lastIA, true);
    }

    public void ListByLastDescending() {
        this.list(lastIA, false);
    }

    public void ListByIDAscending() {
        this.list(IDIA, true);
    }

    public void ListByIDDescending() {
        this.list(IDIA, false);
    }
}
