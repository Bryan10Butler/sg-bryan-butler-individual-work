package shapesandperimeters;

//this class must also be abstract if we want abstract methods
//another class cannot pull in "shape" by itself. No instance of shape. It's too general
public abstract class Shape {

    //properties
    //every class that inherits from shape will have a color
    //change from private to protected
    protected String color;

    //empty methods
    //don't implement {} meaning how can we know the formula for each shape
    //if you declare abstract no curly braces - There's no implementation for this
    //every class that inherits from shape will be required to implement the following methods
    public abstract int getArea();

    //empty methods
    public abstract int getPerimeter();

    //getter and setters
    //every shape that inherits shape will will have the getter and setter
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}//end of shape class
