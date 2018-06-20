package Person;

public class Person {

    private String name;
    private int age;

    //a constructor in a class is essentially a starting point. A required step the user must do before
    //using our object
    //These two parameters have to be used
    //Defines how on object has to be created
    public Person(String name, int age) {

        this.name = name;
        this.age = age;
    }
    //these two constructors has the same name, but different parameter list
    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
