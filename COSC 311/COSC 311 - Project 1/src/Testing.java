public class Testing {
    int[] indexArr = new int[10];
    int currSize = 0;
    public void add(int a) {
        indexArr[currSize] = a;
        int i;
        for (i = currSize - 1; i >= 0; i--) {
            if (indexArr[i] < a) {break;}
            indexArr[i + 1] = indexArr[i];
        }
        indexArr[i + 1] = a;
        currSize++;
    }

    public void printIt() {
        String res = "";
        for (int i = 0; i < currSize; i++) {
            res += indexArr[i] + ", ";
        }
        System.out.println(res);
    }
    public static void main(String[] args) {
        Testing test = new Testing();
        test.add(3);
        test.printIt();
        test.add(2);
        test.printIt();
        test.add(6);
        test.printIt();
        test.add(4);
        test.printIt();
        test.add(1);
        test.printIt();
    }

}
