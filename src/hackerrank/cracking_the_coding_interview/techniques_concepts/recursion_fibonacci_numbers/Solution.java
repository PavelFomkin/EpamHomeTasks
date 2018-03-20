package hackerrank.cracking_the_coding_interview.techniques_concepts.recursion_fibonacci_numbers;

import java.util.Scanner;

class Solution {

    public static int fibonacci(int n) {
        // Complete the function.
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
