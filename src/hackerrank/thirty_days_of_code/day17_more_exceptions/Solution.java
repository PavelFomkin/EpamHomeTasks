package hackerrank.thirty_days_of_code.day17_more_exceptions;

import java.util.Scanner;

class Solution{

    //Write your code here
    static class Calculator {
        int power(int n, int p) throws Exception {
            if(n<0 || p<0){
                throw new Exception("n and p should be non-negative");
            }
            if(n==0){
                return 0;
            }
            if(p==0){
                return 1;
            }
            int result = n;
            while (p-- > 1){
                result = result*n;
            }
            return result;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {

            int n = in.nextInt();
            int p = in.nextInt();
            Calculator myCalculator = new Calculator();
            try {
                int ans = myCalculator.power(n, p);
                System.out.println(ans);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }
}
