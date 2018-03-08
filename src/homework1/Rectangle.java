package homework1;

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

    double getS() {
        return (double) (Math.abs(x1-x2) * Math.abs(y1-y2));
    }
    Rectangle getIntersectionOfRectangles(Rectangle anotherRectangle) {
        Rectangle rectangle3 = new Rectangle(0,0,0,0);
        if (x1 > anotherRectangle.getX1() && x1 < anotherRectangle.getX2()) {
            rectangle3.setX1(x1);
        }
        else if (x2 > anotherRectangle.getX1() && x2 < anotherRectangle.getX2()){
            rectangle3.setX1(x2);
        }
        if (y1 > anotherRectangle.getY1() && y1 < anotherRectangle.getY2()) {
            rectangle3.setY1(y1);
        }
        else if (y2 > anotherRectangle.getY1() && y2 < anotherRectangle.getY2()){
            rectangle3.setY1(y2);
        }
        if (anotherRectangle.getX1() > x1 && anotherRectangle.getX1() < x2) {
            rectangle3.setX2(anotherRectangle.getX1());
        }
        else if (anotherRectangle.getX2() > x1 && anotherRectangle.getX2() < x2){
            rectangle3.setX2(anotherRectangle.getX2());
        }
        if (anotherRectangle.getY1() > y1 && anotherRectangle.getY1() < y2) {
            rectangle3.setY2(anotherRectangle.getY1());
        }
        else if (anotherRectangle.getY2() > y1 && anotherRectangle.getY2() < y2){
            rectangle3.setY2(anotherRectangle.getY2());
        }
        return rectangle3;
    }
    int getX1() {
        return x1;
    }
    void setX1(int x1) {
        this.x1 = x1;
    }
    int getY1() {
        return y1;
    }
    void setY1(int y1) {
        this.y1 = y1;
    }
    int getX2() {
        return x2;
    }
    void setX2(int x2) {
        this.x2 = x2;
    }
    int getY2() {
        return y2;
    }
    void setY2(int y2) {
        this.y2 = y2;
    }
}
