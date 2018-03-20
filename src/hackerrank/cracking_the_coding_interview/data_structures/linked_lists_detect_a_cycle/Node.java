package hackerrank.cracking_the_coding_interview.data_structures.linked_lists_detect_a_cycle;

import java.util.HashSet;
import java.util.Set;

class Node {
    int data;
    Node next;
}

class ListCycleChecker{
    boolean hasCycle(Node head) {

        Set<Node> seen = new HashSet<>();
        while (head != null) {
            seen.add(head);
            head = head.next;
            if (seen.contains(head)) return true;
        }
        return false;

    }
}


