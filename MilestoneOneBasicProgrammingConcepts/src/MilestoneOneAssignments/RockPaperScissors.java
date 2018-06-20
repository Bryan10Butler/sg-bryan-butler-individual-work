package MilestoneOneAssignments;

//Import random class for random numbers
//Import Scanner class to take user input
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    //Start execution of Program name main
    public static void main(String[] args) {

        //Declare new scanner as userInput
        //Declare new random as Randomizer
        //Declare variables and initialize only tie, win and loss as we start at zero
        Scanner userInput = new Scanner(System.in);
        Random randomizer = new Random();
        boolean playAgain = true;
        String numRoundsString;
        int numRounds;
        int computerNum;
        int userTie = 0;
        int userWin = 0;
        int userLoss = 0;
        String userChoice;
        String stringPlayAgain;

        //Massive do while loop - Explained below
        do {
            //Ask how many rounds the user wants to play
            System.out.println("How many rounds of Rock, Paper, Scissors do you want to play?");
            //Create numRoundsString
            numRoundsString = userInput.nextLine();
            //Converting numRoundsString into an Integer
            numRounds = Integer.parseInt(numRoundsString);

            //Exit game if number of rounds is outside of the condition
            //Game breaks if number of rounds is outside of the condition
            if (numRounds > 10 || numRounds < 1) {
                System.out.println("We cannot play that many rounds!");
                break;
            }

            //Massive for loop to ensure the current round is less than the rounds specified by user
            //If so, the game will continue
            for (int currentRound = 1; currentRound <= numRounds; currentRound++) {

                //User's choice of Rock, Paper, or Scissor
                System.out.println("Please select Rock, Paper or Scissors!");
                //Why doesn't next line work - QUESTION
                //nextLine is now working because I am parsing a string to an int
                userChoice = userInput.nextLine();

                //Generate random number
                //I do not need to use Type Int here due to it being Random
                computerNum = randomizer.nextInt(3) + 1;

                //If statement for Rock compare
                if (computerNum == 1) {
                    //Rock
                    if (userChoice.equals("Rock")) {
                        //Tie if Rock == Rock
                        userTie++;
                    } else if (userChoice.equals("Paper")) {
                        userWin++;
                    } else if (userChoice.equals("Scissors")) {
                        userLoss++;
                    }
                //If statement for Paper compare
                }else if (computerNum == 2) {
                    //Paper
                    if (userChoice.equals("Rock")) {
                        userLoss++;
                    } else if (userChoice.equals("Paper")) {
                        userTie++;
                    } else if (userChoice.equals("Scissors")) {
                        userWin++;
                    }
                //If statement for Scissors compare
                }else if (computerNum == 3){
                    //Scissors
                    if (userChoice.equals("Rock")) {
                        userWin++;
                    } else if (userChoice.equals("Paper")) {
                        userLoss++;
                    } else if (userChoice.equals("Scissors")) {
                        userTie++;
                    }
                }
            }
            //Print the number of wins, ties and losses
            //Ask the user if he/she wants to play again
            System.out.println("Number of wins: " + userWin);
            System.out.println("Number of Ties: " + userTie);
            System.out.println("Number of Losses: " + userLoss);
            System.out.println("Do you want to play again?");
            //User input to determine if he/she wants to play again
            //As a result of fixing the above .nextInt and parsing I was able to fix the below .nextLine
            stringPlayAgain = userInput.nextLine();

            //If statement to change the Boolean playAgain to False if user specifies he/she does not want to play
            //If users specifies yes, game will continue and user will be asked how many rounds he/she wants to play
            if (stringPlayAgain.equals("Yes")) {
                playAgain = true;
            } else {
                playAgain = false;
                System.out.println("Thanks for playing!");
            }

            //Termination if users specifies he/she does not want to play again
            //Boolean will be changed to false, ending the game
        } while (playAgain);

    }
}