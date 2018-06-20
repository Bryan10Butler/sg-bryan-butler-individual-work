package practiceLessons;

public class SpringForwardFallBack {
    public static void main(String[] args) {

        //start at 0 and stop at 9
        System.out.println("It's Spring...!");
        for (int i = 1; i < 11; i++) {
            System.out.println(i + ", ");
        }
        //start at 10 and stop at 1
        System.out.println("\nOh no, it's fall...");
        for (int i = 10; i > 0; i--) {
            System.out.println(i + ", ");
        }
    }
}