package practiceLessons;

import java.util.Random;
import java.util.Scanner;

public class HighRoller {

    public static void main(String[] args) {
        Random diceRoller = new Random();
        Scanner userInput = new Scanner(System.in);

        int userGuessSide;
        int rollResult = diceRoller.nextInt(6) + 1;

        System.out.println("How many sides do you think this dice has?!");
        userGuessSide = userInput.nextInt();
        System.out.println(userGuessSide + " ! 6Great guess! This is a 6 side dice!");

        System.out.println("TIME TO ROOOOL THE DICE!");
        System.out.println("You rolled a " + rollResult);

        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        } else if (rollResult == 6) {
            System.out.println("You rolled a " + rollResult + "which is the max!");
        }
    }
}
