package JavaPractice;

public class Methods {
    public static void main(String[] args) {
        //Open and closed parenthesis after an identifier for a method identifies it as the method
        doIT();
        doIT();
        doIT();

        int sum = add(4,5);
        System.out.println(sum);

    }
    //create your method with no return type and no parameters. Use print line to print value if method is called.
    public static void doIT() {
        System.out.println("Hello");
    }
    public static int add(int a, int b) {
        return a + b;
    }
}

