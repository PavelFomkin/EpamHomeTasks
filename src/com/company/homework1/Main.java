package com.company.homework1;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(4, 5, 8, 8);
        Rectangle rectangle2 = new Rectangle(2, 3, 5, 6);
        System.out.println(rectangle1.getS());
        System.out.println(rectangle2.getS());

        Rectangle rectangle3 = rectangle1.getIntersectionOfRectangles(rectangle2);
        System.out.println(rectangle3.getS());
    }
}

