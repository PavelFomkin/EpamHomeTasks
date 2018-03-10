package homework4.hometask2;

public class StackOverflowError1 {
    StackOverflowError1(){
        new StackOverflowError1();
    }

    public static void main(String[] args) {
        new StackOverflowError1();
    }
}
