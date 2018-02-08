package homework2;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class CoinsExpert {
    private static final int STANDARD_WEIGHT = 2;
    private int[] arr = new int[12];
    CoinsExpert(){
        Arrays.fill(arr,STANDARD_WEIGHT);
        changeOneToFake(arr);
        printArray();
    }

    private void changeOneToFake(int[] arr){
        Random random = new Random();
        arr[random.nextInt(12)] = (random.nextInt()>0 ? STANDARD_WEIGHT+1 : STANDARD_WEIGHT-1);
    }

    private int sumOfCoins(int... args){
        int result = 0;
        for (int i : args){
            result += i;
        }
        return result;
    }

    public void printArray(){
        System.out.println(Arrays.toString(arr));
    }

    void changeByMyself(int index, int value){
        Arrays.fill(arr,STANDARD_WEIGHT);
        if (index > 11 || index < 0){
            System.out.println("Index is not correct.");
            return;
        }
        arr[index] =(value < 2 ? STANDARD_WEIGHT-1 : STANDARD_WEIGHT+1);
    }

    int detectFake(){
        System.out.println("Step 1: Divide all coins into three parts, compare first two parts.");
        if (sumOfCoins(arr[0],arr[1],arr[2],arr[3]) > sumOfCoins(arr[4],arr[5],arr[6],arr[7])){
            System.out.println("Result step 1: First part (1,2,3,4) is more than another one (5,6,7,8). It means third part (9,10,11,12) has true weight");
            System.out.println("Step 2: Put in first group 1,2,3,5,6 coins, in second group put third part (9,10,11,12) and coin number 4. Compare them.");
            if (sumOfCoins(arr[0],arr[1],arr[2],arr[4],arr[5]) > sumOfCoins(arr[8],arr[9],arr[10],arr[11],arr[3])){
                System.out.println("Result step 2: First group (1,2,3,5,6) is more than another one (9,10,11,12,4), like a step before.");
                System.out.println("Result step 2: It means 5,6,7,8 from second part and 4 from first part aren't fake.");
                System.out.println("Step 3: Compare coin 1 to coin 2.");
                if (arr[0] == arr[1]){
                    System.out.println("Result step 3: coin 1 and coin 2 have the same weight, it means coin 3 is fake.");
                    return arr[2];
                }
                else {
                    System.out.println("Result step 3: coin "+(arr[0] > arr[1] ? 1 : 2)+" more than another one, and it's fake.");
                    return (arr[0] > arr[1] ? arr[0] : arr[1]);
                }
            }
            else if (sumOfCoins(arr[0],arr[1],arr[2],arr[4],arr[5]) < sumOfCoins(arr[8],arr[9],arr[10],arr[11],arr[3])){
                System.out.println("Result step 2: First group (1,2,3,5,6) is less than another one (9,10,11,12,4).");
                System.out.println("Result step 2: It means 1,2,3 from first part and 6,7 from second part aren't fake");
                System.out.println("Step 3: Compare coin 5 to coin 6.");
                if (arr[4] == arr[5]){
                    System.out.println("Result step 3: coin 5 and coin 6 have the same weight, it means coin 4 is fake.");
                    return arr[3];
                }
                else {
                    System.out.println("Result step 3: coin "+(arr[4] < arr[5] ? 5 : 6)+" less than another one, and it's fake.");
                    return (arr[4] < arr[5] ? arr[4] : arr[5]);
                }
            }
            else{
                System.out.println("Result step 2: First group (1,2,3,5,6) and second group (9,10,11,12,4) have the same weight.");
                System.out.println("Result step 2: It means there aren't fake in these groups.");
                System.out.println("Step 3: Compare coin 7 to coin 8");
                System.out.println("Result step 3: coin"+(arr[6] < arr[7] ? 7 : 8)+" less than another one, and it's fake.");
                return (arr[6] < arr[7] ? arr[6] : arr[7]);
            }
        }
        else if (sumOfCoins(arr[0],arr[1],arr[2],arr[3]) < sumOfCoins(arr[4],arr[5],arr[6],arr[7])){
            System.out.println("Result step 1: First part (1,2,3,4) is less than another one (5,6,7,8). It means third part (9,10,11,12) has true weight");
            System.out.println("Step 2: Put in first group 1,2,3,5,6 coins, in second group put third part (9,10,11,12) and coin number 4. Compare them.");
            if (sumOfCoins(arr[0],arr[1],arr[2],arr[4],arr[5]) < sumOfCoins(arr[8],arr[9],arr[10],arr[11],arr[3])){
                System.out.println("Result step 2: First group (1,2,3,5,6) is less than another one (9,10,11,12,4).");
                System.out.println("Result step 2: It means 5,6,7,8 from second part and 4 from first part aren't fakes.");
                System.out.println("Step 3: Compare coin 1 to coin 2.");
                if (arr[0] == arr[1]){
                    System.out.println("Result step 3: coin 1 and coin 2 have the same weight, it means coin 3 is fake.");
                    return arr[2];
                }
                else {
                    System.out.println("Result step 3: coin "+(arr[0] < arr[1] ? 1 : 2)+" less than another one, and it's fake.");
                    return (arr[0] < arr[1] ? arr[0] : arr[1]);
                }
            }
            else if (sumOfCoins(arr[0],arr[1],arr[2],arr[4],arr[5]) > sumOfCoins(arr[8],arr[9],arr[10],arr[11],arr[3])){
                System.out.println("Result step 2: First group (1,2,3,5,6) is more than another one (9,10,11,12,4).");
                System.out.println("Result step 2: It means 1,2,3 from first part and 6,7 from second part aren't fake");
                System.out.println("Step 3: Compare coin 5 to coin 6.");
                if (arr[4] == arr[5]){
                    System.out.println("Result step 3: coin 5 and coin 6 have the same weight, it means coin 4 is fake.");
                    return arr[3];
                }
                else {
                    System.out.println("Result step 3: coin "+(arr[4] > arr[5] ? 5 : 6)+" more than another one, and it's fake.");
                    return (arr[4] > arr[5] ? arr[4] : arr[5]);
                }
            }
            else{
                System.out.println("Result step 2: First group (1,2,3,5,6) and second group (9,10,11,12,4) have the same weight.");
                System.out.println("Result step 2: It means there aren't fake in these groups.");
                System.out.println("Step 3: Compare coin 7 to coin 8");
                System.out.println("Result step 3: coin "+(arr[6] > arr[7] ? 7 : 8)+" more than another one, and it's fake.");
                return (arr[6] > arr[7] ? arr[6] : arr[7]);
            }
        }
        else {
            System.out.println("Result step 1: First (1,2,3,4) and second parts (5,6,7,8) have the same weight. It means third part (9,10,11,12) has fake");
            System.out.println("Step 2: Compare coins 9,10,11 to three coins with right weight.");
            if (sumOfCoins(arr[8], arr[9], arr[10]) > sumOfCoins(arr[0], arr[1],arr[2])) {
                System.out.println("Result step 2: These coins more than coins with right weight. Coin 12 isn't fake.");
                System.out.println("Step 3: Compare coin 9 to coin 10");
                if (arr[8] == arr[9]) {
                    System.out.println("Result step 3: coin 9 and coin 10 have the same weight, it means coin 11 is fake.");
                    return arr[10];
                } else {
                    System.out.println("Result step 3: coin "+(arr[8] > arr[9] ? 9 : 10)+" more than another one, and it's fake.");
                    return (arr[8] > arr[9] ? arr[8] : arr[9]);
                }
            } else if (sumOfCoins(arr[8], arr[9], arr[10]) < sumOfCoins(arr[0], arr[1],arr[2])) {
                System.out.println("Result step 2: These coins less than coins with right weight. Coin 12 isn't fake.");
                System.out.println("Step 3: Compare coin 9 to coin 10");
                if (arr[8] == arr[9]) {
                    System.out.println("Result step 3: coin 9 and coin 10 have the same weight, it means coin 11 is fake.");
                    return arr[10];
                } else {
                    System.out.println("Result step 3: coin "+(arr[8] < arr[9] ? 9 : 10)+" less than another one, and it's fake.");
                    return (arr[8] < arr[9] ? arr[8] : arr[9]);
                }
            } else {
                System.out.println("Result step 2: These coins and coins with right weight have the same weight. Coin 12 is fake.");
                return arr[11];
            }
        }
    }
}
