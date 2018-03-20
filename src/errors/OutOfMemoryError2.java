package errors;

import java.math.BigInteger;
import java.util.Random;

class OutOfMemoryError2 {
    public static OutOfMemoryError2 head;
    private BigInteger big = new BigInteger(256,new Random());
    private OutOfMemoryError2 next;
    public void setNext(OutOfMemoryError2 out2){
        next = out2;
    }
    public OutOfMemoryError2 getNext() {
        return next;
    }

    public static void main(String[] args) {
        OutOfMemoryError2.head = new OutOfMemoryError2();
        OutOfMemoryError2 next = new OutOfMemoryError2();
        OutOfMemoryError2.head.setNext(next);
        for (long i = 1L; i < 1_000_000_000_000_000L; i++) {
            next.setNext(new OutOfMemoryError2());
            next = next.getNext();
            if (i % 100_000 == 0) {
                System.out.println(i);
            }
        }
    }
}
