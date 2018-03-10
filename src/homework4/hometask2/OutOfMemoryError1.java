package homework4.hometask2;

import java.util.ArrayList;
import java.util.List;

class OutOfMemoryError1 {
    private List<Long[][]> list = new ArrayList<>();
    public void go(){
        for(long i = 0; i < 1_000_000_000_000L; i++){
            Long[][] arr = new Long[100][100];
            list.add(arr);
        }
    }

    public static void main(String[] args) {
        OutOfMemoryError1 out = new OutOfMemoryError1();
        out.go();
    }
}
