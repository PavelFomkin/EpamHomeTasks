package hackerrank.cracking_the_coding_interview.algorithms.hash_tables_ice_cream_parlor;

import java.util.*;

class Solution {

    public static void solve(int[] arr, int money) {
        // Complete this function
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++){
            if(money-arr[i]>0){
                if(map.containsKey(money-arr[i])){
                    int first = i+1;
                    int second = map.get(money-arr[i])+1;
                    System.out.println(first<second ? first+" "+second : second+" "+first);
                    break;
                }
                if(!map.containsKey(arr[i])){
                    map.put(arr[i],i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int money = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            solve(arr, money);
        }
        in.close();
    }
}
