package dogComposition;

public class Main {

    public static void main(String[] args) {
        Human bob = new Human("Bob");
        Dog tom = new Dog("Tom", bob);

        System.out.println(tom);
    }
}
