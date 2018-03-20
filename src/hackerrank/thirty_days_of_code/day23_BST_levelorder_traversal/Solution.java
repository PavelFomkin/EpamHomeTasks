package hackerrank.thirty_days_of_code.day23_BST_levelorder_traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Node{
    Node left,right;
    int data;
    Node(int data){
        this.data=data;
        left=right=null;
    }
}
class Solution{

    static void levelOrder(Node root){
        //Write your code here
        List<Node> arr = new ArrayList<>();
        arr.add(root);
        for (int i=0; i<arr.size();i++){
            System.out.print(arr.get(i).data+" ");
            if (arr.get(i).left!=null) {
                arr.add(arr.get(i).left);
            }
            if (arr.get(i).right!=null) {
                arr.add(arr.get(i).right);
            }
        }
    }

    public static Node insert(Node root,int data){
        if(root==null){
            return new Node(data);
        }
        else{
            Node cur;
            if(data<=root.data){
                cur=insert(root.left,data);
                root.left=cur;
            }
            else{
                cur=insert(root.right,data);
                root.right=cur;
            }
            return root;
        }
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        Node root=null;
        while(T-->0){
            int data=sc.nextInt();
            root=insert(root,data);
        }
        levelOrder(root);
    }
}