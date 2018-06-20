package shapesandperimeters;

public class Square extends Shape {

    //properties
    private int side;

    //constructor
    public Square(int side) {
        this.side = side;
    }

    //override is a clue that is method from defined elsewhere
    @Override
    public int getArea() {
        return side*side;
    }

    @Override
    public int getPerimeter() {
        return side*4;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
}
