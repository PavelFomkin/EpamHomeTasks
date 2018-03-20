package hackerrank.thirty_days_of_code.day25_running_time_and_complexity;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scn = new Scanner(System.in);
        int number = scn.nextInt();
        for(int i=0; i<number; i++){
            isPrime(scn.nextInt());
        }
    }
    static void isPrime(int n){
        if (n%2==0){
            if (n==2){
                System.out.println("Prime");
            }
            else{
                System.out.println("Not prime");
            }
        }
        else{
            for(int i=3; i<n; i+=2){
                if(n%i==0){
                    System.out.println("Not prime");
                    return;
                }
            }
            if (n==1){
                System.out.println("Not prime");
            }
            else{
                System.out.println("Prime");
            }
        }
    }
}
