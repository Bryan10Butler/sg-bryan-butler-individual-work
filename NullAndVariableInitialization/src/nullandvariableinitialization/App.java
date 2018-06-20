package nullandvariableinitialization;

public class App {

    public static void main(String[] args) {

        int count = 19;
        //declare a Person() object with "new". This will be stored on heap.
        //person is now a reference to the object on the heap Person()
        Person person = new Person();
        person.setAge(35);
        person.setName("Bryan");

        //person = null;

        System.out.println("Count = " + count);
        System.out.println("Age = " + person.getAge());
        System.out.println("Name = " + person.getName());

    }
}
