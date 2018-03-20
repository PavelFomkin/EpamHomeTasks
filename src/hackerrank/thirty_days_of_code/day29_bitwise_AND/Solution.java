package hackerrank.thirty_days_of_code.day29_bitwise_AND;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i=0; i<n; i++){
            maxValue(scn.nextInt(),scn.nextInt());
        }
    }
    static void maxValue(int A, int K){
        int max = 0;
        for(int i=1; i<A; i++){
            for(int j=i+1; j<=A;j++){
                if (i==j){
                    continue;
                }
                if((i&j) < K && (i&j) > max){
                    max = (i&j);
                }
            }
        }
        System.out.println(max);
    }
}
