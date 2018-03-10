package homework4.hometask2;

public class StackOverflowError2 {
    void method1 (){
        method2();
    }
    void method2 (){
        method1();
    }

    public static void main(String[] args) {
        StackOverflowError2 stack = new StackOverflowError2();
        stack.method1();
    }
}
