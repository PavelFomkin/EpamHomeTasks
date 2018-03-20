package hackerrank.thirty_days_of_code.day06_lets_review;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scn = new Scanner(System.in);
        int n = Integer.parseInt(scn.nextLine());
        for(int i = 0; i<n; i++){
            String str = scn.nextLine();
            for(int j = 0; j < str.length(); j+=2){
                System.out.print(str.charAt(j));
            }
            System.out.print(" ");
            for(int j = 1; j < str.length(); j+=2){
                System.out.print(str.charAt(j));
            }
            System.out.println();
        }
    }
}
