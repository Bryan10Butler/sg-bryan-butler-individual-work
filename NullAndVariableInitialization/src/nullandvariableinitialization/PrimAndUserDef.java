package nullandvariableinitialization;

public class PrimAndUserDef {

    //This is all demonstrating how both myPerson and person are pointing
    //to the same Person object on the heap. It's like having two remotes for one tv.
    //Pay attention to how your code is setup. User defined and Primitives.
    public static void main(String[] args) {
        int count = 19;
        changeMyValue(count);
        System.out.println("Count = " + count);

        Person myPerson = new Person();
        myPerson.setAge(24);
        myPerson.setName("Joe");
        System.out.println("Age = " + myPerson.getAge());
        System.out.println("Name = " + myPerson.getName());

        changeMyName(myPerson);
        System.out.println("Age = " + myPerson.getAge());
        System.out.println("Name = " + myPerson.getName());
    }
    public static void changeMyValue(int value) {
        value = 21;
    }

    public static void changeMyName(Person person){
        person.setName("Steve");

    }


}
