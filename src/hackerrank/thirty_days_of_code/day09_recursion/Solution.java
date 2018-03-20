package hackerrank.thirty_days_of_code.day09_recursion;

import java.util.Scanner;

public class Solution {

    static int factorial(int n) {
        // Complete this function
        if (n == 1){
            n = 1;
        }
        else {
            n = n * factorial(n-1);
        }
        return n;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int result = factorial(n);
        System.out.println(result);
    }
}
