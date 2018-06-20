package dogComposition;

public class Human {

    //property
    private String name;

    //constructor
    //Anyone who calls on this class will need to construct with this constructor
    public Human(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}
