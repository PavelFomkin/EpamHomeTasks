package rectangle;

class Rectangle {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    Rectangle(Rectangle first, Rectangle second) {
        if (second.getX1() > first.getX1() && second.getX1() < first.getX2()) {
            this.x1 = second.getX1();
        }
        else if (second.getX2() > first.getX1() && second.getX2() < first.getX2()){
            this.x1 = second.getX2();
        }
        if (second.getY1() > first.getY1() && second.getY1() < first.getY2()) {
            this.y1 = second.getY1();
        }
        else if (second.getY2() > first.getY1() && second.getY2() < first.getY2()){
            this.y1 = second.getY2();
        }
        if (first.getX1() > second.getX1() && first.getX1() < second.getX2()) {
            this.x2 = first.getX1();
        }
        else if (first.getX2() > second.getX1() && first.getX2() < second.getX2()){
            this.x2 = first.getX2();
        }
        if (first.getY1() > second.getY1() && first.getY1() < second.getY2()) {
            this.y2 = first.getY1();
        }
        else if (first.getY2() > second.getY1() && first.getY2() < second.getY2()) {
            this.y2 = first.getY2();
        }
    }

    private int getX1() {
        return x1;
    }

    private int getY1() {
        return y1;
    }

    private int getX2() {
        return x2;
    }

    private int getY2() {
        return y2;
    }

    double getArea() {
        return (double) (Math.abs(x1-x2) * Math.abs(y1-y2));
    }

    @Override
    public String toString() {
        return "Coordinates of rectangle:\n\t point one: x = "+x1+"  y = "+y1+"\n\t point two: x = "+x2+"  y = "+y2;
    }
}
