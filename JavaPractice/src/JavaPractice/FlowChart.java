package JavaPractice;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Random;
import java.util.Scanner;

public class FlowChart {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int answer;
        int guess;
        //generate a random number between 1 and 20 to begin the game
        Random rGen = new Random();
        answer = rGen.nextInt(20) + 1;
        //Using a While loop because we are unsure how many times it will take user
        //We could use a for loop if we want to limit the amount of times the user is able to input
        while (true) {
            System.out.println("Enter a guess between 1 and 20");
            guess = sc.nextInt();

            if (guess == answer) {
                break;
            }
            //if guess is out of range, user will be brought back to top to guess in range
            if (guess < 1 || guess > 20) {
                System.out.println("Your guess must be between 1 and 20");
                continue;
            }
            //if use needs to be lower they will be brought back to top guess a lower number
            if (guess > answer) {
                System.out.println("Lower!");
                continue;
            }
            //no continue is necessary because the loop will go back to the top if number is not guessed
            System.out.println("Higher!");
            }
        System.out.println("You got it! The answer was " + answer);
        }

    }

