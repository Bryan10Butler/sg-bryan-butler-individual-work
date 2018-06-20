package practiceLessons;

import java.util.Scanner;

public class GuessMe {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        int ourNumber = 10;
        int number;

        System.out.println("Please guess a number: ");
        number = inputReader.nextInt();

        if (number == ourNumber) {
            System.out.println("Wow, nice guess! That was it! " + ourNumber);
        } else if (number < ourNumber) {
            System.out.println("Ha, nice try - Too low! I chose # " + ourNumber);
        } else {
            System.out.println("Too bad, way too high. I chose # " + ourNumber);
        }
    }
}
