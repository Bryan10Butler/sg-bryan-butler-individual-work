package practiceLessons;

import java.util.Scanner;

public class YourLifeInMovies {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        int yearBorn;
        String name;

        //Get user input for name
        System.out.println("Hey, lets play a game! What is your name?");
        name = inputReader.next();

        System.out.println("Okay " + name + " when were you born?");
        yearBorn = inputReader.nextInt();
        System.out.println("Well " + name);

        if (yearBorn < 2005) {
            System.out.println("Did you know that Pixar's 'Up' came out half a decade ago?");
        }
        if (yearBorn < 1995) {
            System.out.println("And that the first Harry Potter came out over 15 years ago!");
        }
        if (yearBorn < 1985) {
            System.out.println("Also, Space Jam came out not last decade, but that one before THAT.");
        }
        if (yearBorn < 1975) {
            System.out.println("The original Jurassic Park release is closer to the lunar landing, than today");
        }
        if (yearBorn < 1965) {
            System.out.println("The MASH has been around for almost half a century!");
        }
        if (yearBorn == 1980) {
            System.out.println("You were born before 2005");
            System.out.println("You were born before 1995");
            System.out.println("You were born before 1985");
        }
    }
}
