package hackerrank.cracking_the_coding_interview.techniques_concepts.bit_manipulation_lonely_integer;

import java.util.Arrays;
import java.util.Scanner;

class Solution {

    public static int lonelyInteger(int[] a) {
        Arrays.sort(a);
        for(int i=0; i<a.length-1; i++){
            if(a[i]==a[i+1]){
                i++;
            } else{
                return a[i];
            }
        }
        return a[a.length-1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        System.out.println(lonelyInteger(a));
    }
}
