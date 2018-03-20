package hackerrank.thirty_days_of_code.day16_exceptions_string_to_integer;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        String S = in.next();

        try {
            System.out.println(Integer.parseInt(S));
        }
        catch (Exception ex){
            System.out.println("Bad String");
        }
    }
}
