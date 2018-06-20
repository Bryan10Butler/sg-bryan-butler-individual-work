package JavaPractice;

public class MethodsThree {
    public static void main(String[] args) {
        //call method and pass int "literal"
        silly(42);
        //call method and pass variable in
        int num = 42;
        silly(num);
        //pass expression
        silly(5 + 4);

    }

    //create method with no return, but with parameters.
    public static void silly(int i) {
        System.out.println("My parameter is: " + i);

    }
}
