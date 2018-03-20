package hackerrank.thirty_days_of_code.day14_scope;

import java.util.Arrays;
import java.util.Scanner;

class Difference {
    private int[] elements;
    public int maximumDifference;

    // Add your code here

    Difference(int[] a) {
        this.elements = Arrays.copyOf(a, a.length);
    }

    void computeDifference() {
        int maxElement = 0;
        int minElement = 99;
        for (int i : this.elements) {
            if (i > maxElement) {
                maxElement = i;
            }
            if (i < minElement) {
                minElement = i;
            }
        }
        this.maximumDifference = maxElement - minElement;
    }
}
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        Difference difference = new Difference(a);

        difference.computeDifference();

        System.out.print(difference.maximumDifference);
    }
}
