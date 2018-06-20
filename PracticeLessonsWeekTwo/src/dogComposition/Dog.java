package dogComposition;

public class Dog {

    //property
    //every dog has a name
    private String name;
    //every dog belongs to a human (instance is owner)
    private Human owner;

    //constructor
    //Who ever calls this class has to setup with this constructor
    public Dog(String name, Human owner) {
        this.name = name;
        this.owner = owner;
    }

    public String toString() {
        //%s is a place holder for what we want want to enter (s = String)
        //Replace %s with name
        //Replace %s with owner
        //owner is not a string -  we will get weird output if left like this
        //changes need to be made in Human class

        return String.format("My name is: %s, My owner's name is: %s", name, owner);
    }
}
