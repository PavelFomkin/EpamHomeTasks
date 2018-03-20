package hackerrank.thirty_days_of_code.day27_testing;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        System.out.println(5);
        check(5,5,new int[]{-2,0,-1,-45,2}); //yes
        check(9,1,new int[]{-2,3,-3,0,-4,-3,-22,25,-4}); //no
        check(15,9,new int[]{-2,3,4,5,23,-13,24,-3,0,4,3,22,9,-15,-2}); //yes
        check(10,8,new int[]{-2,3,-3,0,-4,-3,-22,25,-4,-1}); //no
        check(4,3,new int[]{-2,12,5,0}); //yes

    }
    static void check(int all, int need, int[] timeOfEnter){
        System.out.println(all+" "+need);
        for (int i=0; i<timeOfEnter.length; i++){
            if (i==timeOfEnter.length-1){
                System.out.println(timeOfEnter[i]);
            }
            else{
                System.out.print(timeOfEnter[i]+" ");
            }
        }
    }
}
