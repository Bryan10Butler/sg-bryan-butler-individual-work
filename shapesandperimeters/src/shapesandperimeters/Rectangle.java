package shapesandperimeters;

public class Rectangle extends Shape {

    private int width;
    private int length;


    //constructor
    public Rectangle(int width, int length) {
        this.width = width;
        this.length = length;
        //getting the method due string color is private
        //you can use super or this
        //changed string color to protected
        //what to use
        super.setColor("orange");
        this.setColor("red");
        //if the below property in shape is set to private "color" option will not be available
        super.color = ("purple");
        this.color = ("blue");

    }

    //implement methods from Shape due to extends
    @Override
    public int getArea() {
        return width *length;
    }

    @Override
    public int getPerimeter() {
        return (2* width) + (2*length);
    }

    //getters and setters

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
