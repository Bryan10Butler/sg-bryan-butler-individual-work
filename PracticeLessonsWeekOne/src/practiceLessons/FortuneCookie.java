package practiceLessons;

import java.util.Random;

public class FortuneCookie {
    public static void main(String[] args) {

        Random randomPhrase = new Random();
        int randomNum = randomPhrase.nextInt(10);

        System.out.println("You geek fortune is: ");

        if (randomNum == 0) {
            System.out.println("Those aren’t the droids you’re looking for.");
        }
        if (randomNum == 1) {
            System.out.println("Never go in against a Sicilian when death is on the line!");
        }
        if (randomNum == 2) {
            System.out.println("Goonies never say die.");
        }
        if (randomNum == 3) {
            System.out.println("With great power there must also come — great responsibility.");
        }
        if (randomNum == 4) {
            System.out.println("Never argue with the data.");
        }
        if (randomNum == 5) {
            System.out.println("Try not. Do, or do not. There is no try.");
        }
        if (randomNum == 6) {
            System.out.println("You are a leaf on the wind, watch how you soar");
        }

        if (randomNum == 7) {
            System.out.println("Do absolutely nothing, and it will be everything that you thought it could be.");
        }

        if (randomNum == 8) {
            System.out.println("Kneel before Zod.");
        }
        if (randomNum == 9) {
            System.out.println("Make it so");
        }
    }
}
