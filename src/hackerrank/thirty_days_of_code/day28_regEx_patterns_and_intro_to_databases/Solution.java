package hackerrank.thirty_days_of_code.day28_regEx_patterns_and_intro_to_databases;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        List<String> result = new LinkedList<>();
        for (int i=0; i<n; i++){
            String name = scn.next();
            if(scn.next().endsWith("@gmail.com")){
                result.add(name);
            }
        }
        Collections.sort(result);
        for (String str : result){
            System.out.println(str);
        }
    }
}
