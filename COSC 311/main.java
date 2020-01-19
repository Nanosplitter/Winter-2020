public class main {
    public static void main(String[] args) {
        DataBase d=new DataBase();

        d.insertIt("1234", "alice", "jones");
        d.insertIt("1235", "zelda", "smith");
        d.insertIt("9999", "mike", "adams");
        d.insertIt("9988", "dave", "bing");
        d.insertIt("2233", "sue", "charles");
        d.insertIt("2244", "ed", "smiley");
        d.insertIt("6655", "mary", "rogers");
        d.insertIt("6633", "ellen", "nance");
        d.insertIt("4234", "roger", "morris");
        d.insertIt("3234", "mac", "edwards");


        System.out.println("List By ID Ascending");
        System.out.println("--------------------");
        d.listByIDAscending();

        System.out.println();
        System.out.println("List By First Ascending");
        System.out.println("--------------------");
        d.listByFirstAscending();

        System.out.println();
        System.out.println("List By Last Ascending");
        System.out.println("--------------------");
        d.listByLastAscending();

        System.out.println();
        System.out.println("List By ID Decending");
        System.out.println("--------------------");
        d.listByIDDecending();

        System.out.println();
        System.out.println("List By First Descending");
        System.out.println("--------------------");
        d.listByFirstDecending();

        System.out.println();
        System.out.println("List By Last Descending");
        System.out.println("--------------------");
        d.listByLastDecending();
    }
}
