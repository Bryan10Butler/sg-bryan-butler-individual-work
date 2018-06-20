package MilestoneOneAssignments;

import java.util.Scanner;

public class HealthyHearts {
    //Start execution of Program name main
    public static void main (String[] args) {
        //Declare variables
        int age;
        int maximumHR;
        String name;
        Scanner inputReader = new Scanner(System.in);

        //Ask user for their name and age by using Scanner
        System.out.println("Hello, what is your name?");
        name = inputReader.next();
        System.out.println("What is your age?");
        age = inputReader.nextInt();

        //Calculate Max Heart Rate. // This can be done in your println as well
        maximumHR = 220-age;

        //Print results for user
        System.out.println(name + " , your maximum heart rate should be " + maximumHR + " beats per minute ");
        System.out.println("and...");
        System.out.println(name + ", your target HR Zone is " + (maximumHR * 50) + " - " + (maximumHR * .85) +
                " beats per minute");
    }
}
