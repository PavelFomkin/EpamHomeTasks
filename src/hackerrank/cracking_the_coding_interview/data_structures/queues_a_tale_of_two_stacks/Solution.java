package hackerrank.cracking_the_coding_interview.data_structures.queues_a_tale_of_two_stacks;

import java.util.Scanner;
import java.util.Stack;

class Solution {
    public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();

        public void enqueue(T value) { // Push onto newest stack
            stackNewestOnTop.add(value);
        }

        public T peek() {
            if(stackOldestOnTop.empty()){
                while(!stackNewestOnTop.empty()){
                    stackOldestOnTop.add(stackNewestOnTop.pop());
                }
            }
            return stackOldestOnTop.peek();
        }

        public T dequeue() {
            if(stackOldestOnTop.empty()){
                while(!stackNewestOnTop.empty()){
                    stackOldestOnTop.add(stackNewestOnTop.pop());
                }
            }
            return stackOldestOnTop.pop();
        }
    }


    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
