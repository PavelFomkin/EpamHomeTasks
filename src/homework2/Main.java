package homework2;

class Main{
    public static void main(String[] args) {

        // for checking homework2.
        Matrix myMatrix = new Matrix(5,7);
        myMatrix.printMatrix();
        System.out.println();
        myMatrix.printSpirally();

        // for checking a random variant. (task with 12 coins)
//        new CoinsExpert().detectFake();

        // for checking all variants. (task with 12 coins)
//        CoinsExpert myCoins = new CoinsExpert();
//        int value =1;
//        for (int i=0; i<2; i++){
//            for (int j=0; j<12; j++) {
//                myCoins.changeByMyself(j, value);
//                myCoins.printArray();
//                System.out.println(myCoins.detectFake());
//            }
//            value = 3;
//        }

        // for checking task about 12 coins.
//        CoinsExpert test = new CoinsExpert();
//        test.printArray();
//        test.detectFake();
    }
}
