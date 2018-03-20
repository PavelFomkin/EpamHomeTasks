package hackerrank.cracking_the_coding_interview.data_structures.trees_is_this_a_binary_search_tree;

class Node {
    int data;
    Node left;
    Node right;
}

class BinaryTreeChecker{
    boolean checkBST(Node root){
        return check(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private boolean check(Node root, int min, int max){
        if(root==null)
            return true;
        if(root.data > min && root.data < max){
            return check(root.left, min, root.data) && check(root.right, root.data, max);
        }
        return false;
    }
}