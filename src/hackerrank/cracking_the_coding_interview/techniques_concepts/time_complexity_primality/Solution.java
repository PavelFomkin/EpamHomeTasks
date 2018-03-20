package hackerrank.cracking_the_coding_interview.techniques_concepts.time_complexity_primality;

import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++){
            int n = in.nextInt();
            boolean flag = true;
            if(n > 0 && n <= 3){
                System.out.println(n==1 ? "Not prime" : "Prime");
            } else if(n%2==0 || n%3==0){
                System.out.println("Not prime");
            } else {
                int i = 5;
                while(i*i <= n){
                    if(n%i==0 || n%(i+2)==0){
                        flag = false;
                        break;
                    }
                    i++;
                }
                System.out.println(flag ? "Prime" : "Not prime");
            }
        }
    }
}
