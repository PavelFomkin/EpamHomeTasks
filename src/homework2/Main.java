package homework2;

class Main{
    public static void main(String[] args) {

        // for checking homework2.
        Matrix myMatrix = new Matrix(5,7);
        myMatrix.printMatrix();
        System.out.println();
        myMatrix.printSpirally();

        // for checking all variants. (task with 12 coins)
//        CoinsExpert myCoinsExpert = new CoinsExpert();
//        int value =1;
//        for (int i=0; i<2; i++){
//            for (int j=0; j<12; j++) {
//                myCoinsExpert.changeByMyself(j, value);
//                myCoinsExpert.printArray();
//                System.out.println(myCoinsExpert.detectFake());
//            }
//            value = 3;
//        }

//         for checking a random variant. (task with 12 coins)
//        CoinsExpert myCoinsExpert = new CoinsExpert();
//        myCoinsExpert.printArray();
//        myCoinsExpert.detectFake();
    }
}
