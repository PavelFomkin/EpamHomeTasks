package hackerrank.cracking_the_coding_interview.data_structures.stacks_balanced_brackets;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Solution {

    public static boolean isBalanced(String expression) {
        char[] elements = expression.toCharArray();
        ArrayDeque<Character> arr = new ArrayDeque<>();
        for(char element : elements){
            switch (element){
                case('{'):
                    arr.add('}');
                    break;
                case('['):
                    arr.add(']');
                    break;
                case('('):
                    arr.add(')');
                    break;
                case(')'):
                    if(arr.size() == 0 || arr.pollLast()!=')'){
                        return false;
                    }
                    break;
                case(']'):
                    if(arr.size() == 0 || arr.pollLast() !=']'){
                        return false;
                    }
                    break;
                case('}'):
                    if(arr.size() == 0 || arr.pollLast()!='}'){
                        return false;
                    }
                    break;
                default: return false;
            }
        }
        return arr.isEmpty();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
    }
}
