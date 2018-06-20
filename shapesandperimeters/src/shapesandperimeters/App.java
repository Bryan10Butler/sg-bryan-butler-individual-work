package shapesandperimeters;

public class App {

    public static void main(String[] args) {

        Square mySquare = new Square (2);

        System.out.println(mySquare.getPerimeter());

        mySquare.setColor("blue");

        System.out.println("Square is: " + mySquare.getColor());

        //We can do this because of polymorphism
        //A square is a square
        //but it also is a Shape
        Shape myShape = new Square(3);

        System.out.println(myShape.getArea());

        Rectangle myRectangle = new Rectangle(2,3);
        System.out.println(myRectangle.getColor());

        myRectangle.setLength(4);
        System.out.println(myRectangle.getLength());
        Rectangle bryanRec = new Rectangle()

    }
}
