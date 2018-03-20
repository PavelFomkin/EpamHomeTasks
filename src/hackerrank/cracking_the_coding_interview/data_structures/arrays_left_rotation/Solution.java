package hackerrank.cracking_the_coding_interview.data_structures.arrays_left_rotation;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        if (k > n) {
            k = k % n;
        }
        for (int k_i=1; k_i <= k; k_i++){
            int x = a[0];
            for (int n_i=1; n_i <a.length; n_i++){
                a[n_i-1] = a[n_i];
            }
            a[a.length-1]= x;
        }
        for (int i=0; i<a.length; i++){
            if (i == a.length-1){
                System.out.println(a[i]);
                break;
            }
            System.out.print(a[i] + " ");
        }
        in.close();
    }
}
