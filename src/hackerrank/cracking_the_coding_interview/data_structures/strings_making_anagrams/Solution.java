package hackerrank.cracking_the_coding_interview.data_structures.strings_making_anagrams;

import java.util.Scanner;

public class Solution {
    public static int numberNeeded(String first, String second) {
        char[] firstArr = first.toCharArray();
        char[] secondArr = second.toCharArray();
        int number=0;

        for (int i = 0; i<firstArr.length; i++){
            for (int j = 0; j<secondArr.length; j++){
                if (firstArr[i] != 0 && firstArr[i] == secondArr[j]){
                    firstArr[i]=0;
                    secondArr[j]=0;
                    number +=2;
                    break;
                }
            }
        }
        return ((firstArr.length + secondArr.length)-number);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
