package practiceLessons;

//Public meaning it can be used in other code
//What it does is open to the public
public class Dog {

    //Properties
    private String name;
    private double weight;

    //Behaviours
    //Constructor - special method that creates an instance of your class
    public Dog() {

    }

    public Dog(String nameIn, double weightIn) {
        this.name = nameIn;
        this.weight = weightIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void bark() {
        System.out.println("WOOF!");
    }

    public void sit() {
        System.out.println("Sitting...");
    }

}

