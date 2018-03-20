package hackerrank.cracking_the_coding_interview.data_structures.hash_tables_ransom_note;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        String magazine[] = new String[m];
        for(int magazine_i=0; magazine_i < m; magazine_i++){
            magazine[magazine_i] = in.next();
        }
        String ransom[] = new String[n];
        for(int ransom_i=0; ransom_i < n; ransom_i++){
            ransom[ransom_i] = in.next();
        }


        int countWords = 0;
        int[] ransomHash = new int[ransom.length];
        for (int i = 0; i < ransom.length; i++){
            ransomHash[i]=ransom[i].hashCode();
        }
        int[] magazineHash = new int[magazine.length];
        for (int i = 0; i < magazine.length; i++){
            magazineHash[i]=magazine[i].hashCode();
        }
        for (Integer words : magazineHash) {
            for (int i = 0; i < ransom.length; i++) {
                if (words == ransomHash[i]) {
                    countWords++;
                    ransomHash[i] = 0;
                    break;
                }
            }
        }
        if (countWords == n){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
