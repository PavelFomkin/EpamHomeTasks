package hackerrank.thirty_days_of_code.day10_binary_numbers;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scn = new Scanner(System.in);
        String binaryNumber = Integer.toBinaryString(scn.nextInt());
        int count = 0;
        int max = 0;
        for (int i=0; i < binaryNumber.length(); i++){
            if(binaryNumber.charAt(i) == '0'){
                if(max<count){
                    max = count;
                }
                count = 0;
            }
            else{
                count++;
            }
        }
        System.out.println(max>count ? max : count);

    }
}
