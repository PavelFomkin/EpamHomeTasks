package false_coin;

public class Main {
    public static void main(String[] args) {

        /*
        * We have 12 coins and one of them is false, we need to know which one.
        * But we have only lever scales and three attempts.
        * CoinsExpert can find this coin max by 3 steps.
        */

//            for checking a random variant.
        CoinsExpert myCoinsExpert = new CoinsExpert();
        myCoinsExpert.printArray();
        myCoinsExpert.detectFake();

//            for checking all variants.
//        CoinsExpert myCoinsExpertExtra = new CoinsExpert();
//        int value =1;
//        for (int i=0; i<2; i++){
//            for (int j=0; j<12; j++) {
//                myCoinsExpertExtra.setFalseCoin(j, value);
//                myCoinsExpertExtra.printArray();
//                myCoinsExpertExtra.detectFake();
//                System.out.println();
//            }
//            value = 3;
//        }
    }
}
