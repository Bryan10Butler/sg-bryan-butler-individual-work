package practiceLessons;


import java.util.Scanner;

public class ReturnToSender {
    public static void main(String[] args) {

        char aMystery = mystery();
        String totallyUnexpected = unexpected();
        double aSurprise = surprise();
        boolean itsClassified = classified();
        int aSecret = secret();
        Scanner userInput = new Scanner(System.in);



        System.out.println("The methods have returned! Their results... \n");
        System.out.println("Mysterious: " + aMystery);
        System.out.println("    Secret: " + aSecret);
        System.out.println("Surprising: " + aSurprise);
        System.out.println("Classified: " + itsClassified);
        System.out.println("Unexpected: " + totallyUnexpected);

    }

    public static int secret() {
        return 42;
    }

    public static double surprise() {
        return 3.14;
    }

    public static char mystery() {
        return 'x';
    }

    public static boolean classified() {
        return true;
    }

    public static String unexpected() {
        return "Spanish Inquisition";
    }
}





