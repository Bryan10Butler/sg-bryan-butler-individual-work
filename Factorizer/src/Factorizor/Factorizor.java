package Factorizor;

import java.util.Scanner;

public class Factorizor {
    public static void main(String[] args) {
        int num;
        int numFact = 0;
        int perfectSum = 0;
        Scanner userInput = new Scanner(System.in);
        //Get the user input, continue looping if num is not greater
        do {

            System.out.println("What number would you like to factor ? ");
            String numString = userInput.next();
            num = Integer.parseInt(numString);

            if (num <= 0) {
                System.out.println("Please enter a number greater than 0");
            }
        } while (num <= 0);
        System.out.println("The factors of " + num + " are:");
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                perfectSum += i;
                numFact++;
                System.out.println(i);
            }
        }

        //check if the number is perfect
        if (num == perfectSum) {
            System.out.println(num + " is a perfect number");
        } else {
            System.out.println(num + " is not a perfect number");
        }

        //check if the number is prime
        if (numFact == 1) {
            System.out.println(num + " is a prime number.");
        }
        else {
            System.out.println(num + " is not a prime number.");
        }
        /*
        public static boolean isFactor(int number, int possibleFactor) {
            System.out.println(possibleFactor + " is" + (number % possibleFactor == 0));
            return number % possibleFactor == 0;
        }
        */
    }
}