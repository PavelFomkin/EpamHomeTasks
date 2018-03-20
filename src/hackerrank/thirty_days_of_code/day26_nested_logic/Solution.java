package hackerrank.thirty_days_of_code.day26_nested_logic;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scn = new Scanner(System.in);
        int currentDay = scn.nextInt();
        int currentMonth = scn.nextInt();
        int currentYear = scn.nextInt();
        int endDay = scn.nextInt();
        int endMonth = scn.nextInt();
        int endYear = scn.nextInt();

        if (currentYear-endYear>0){
            System.out.println(10000);
        }
        else if (currentYear-endYear==0 && currentMonth-endMonth>0){
            System.out.println(500*(currentMonth-endMonth));
        }
        else if (currentYear-endYear==0 && currentMonth-endMonth==0 && currentDay-endDay>0) {
            System.out.println(15*(currentDay-endDay));
        }
        else {
            System.out.println(0);
        }

    }
}
