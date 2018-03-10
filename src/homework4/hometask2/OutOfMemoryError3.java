package homework4.hometask2;

import java.util.ArrayList;
import java.util.List;

class OutOfMemoryError3 {
    final static List<OutOfMemoryError3> deletedLink = new ArrayList<>();
    private final long ID;
    Long[][] arr = new Long[1000][1000];
    OutOfMemoryError3(long ID){
        this.ID = ID;
    }

    @Override
    protected void finalize() throws Throwable {
        OutOfMemoryError3.deletedLink.add(this);
        System.out.println("The object ID "+this.ID+" was saved.");
    }

    public static void main(String[] args) {
        for(long i = 0; i < 100_000_000_000_000L; i++){
            OutOfMemoryError3 example = new OutOfMemoryError3(i);
        }
    }
}

