package practiceLessons;

import java.util.Scanner;

public class RollerCoaster {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("we're going to go on a roller coaster...");
        System.out.println("Let me know when you want to get off...!");

        //This value feeds the value "y" into keepRiding to kick off the while loop
        String keepRiding = "y";
        int loopsLooped = 0;
        while (keepRiding.equals("y")) {
        //while (keepRiding.equals("n")) {
            System.out.println("WheEeeeE...!!!");
            System.out.println("want to keep going? (y/n)");
            keepRiding = userInput.nextLine();
            //no int because this is counting our number of times looped
            loopsLooped++;
        }
        System.out.println("Wow, that was FUN!");
        System.out.println("We ooped that loop " + loopsLooped + " times!!");
    }
}
