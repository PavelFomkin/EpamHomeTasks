package rectangle;

public class Main {
    public static void main(String[] args) {

        /*
        * You should point coordinates of rectangle by two points.
        * This program count area of rectangles.
        * Also you can create a rectangle from the intersection of the rectangles.
        */

        Rectangle rectangle1 = new Rectangle(4, 5, 8, 8);
        Rectangle rectangle2 = new Rectangle(2, 3, 5, 6);
        Rectangle rectangle3 = new Rectangle(rectangle1, rectangle2);

        System.out.println(rectangle1.getArea());
        System.out.println(rectangle2.getArea());
        System.out.println(rectangle3);
        System.out.println(rectangle3.getArea());
    }
}

