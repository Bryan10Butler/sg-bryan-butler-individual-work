package MilestoneOneAssignments;

import java.util.Random;
import java.util.Scanner;

public class DogGenetics {
    //Start execution of Program name main
    public static void main(String[] args) {

        //Declare variables for Random Number generator
        int rNum1;
        int rNum2;
        int rNum3;
        int rNum4;
        int rNum5;
        int max = 100;
        int rNumbersTotal;

        //Declare, initialize and reserve array space of 5
        String dogsName;
        String dogBreed[] = new String [5];
        dogBreed[0] = "St. Bernard";
        dogBreed[1] = "Chihuahua";
        dogBreed[2] = "Dramatic RedNosed Asian Pug";
        dogBreed[3] = "Common Cur";
        dogBreed[4] = "King Doberman";

        //Figure out the bounds
        //Generate random numbers that equate to 100
        //First random number is 85
        //100 - 85 = 15
        //The random number generator cannot generate a number above 15
        //This logic is continued
        Random randomNumber = new Random();
        rNum1 = randomNumber.nextInt(max) + 1;
        rNum2 = randomNumber.nextInt(max - rNum1) + 1;
        rNum3 = randomNumber.nextInt(max - (rNum1 + rNum2)) + 1;
        rNum4 = randomNumber.nextInt(max - (rNum1 + rNum2 + rNum3)) + 1;
        //In my mind, this is similar to "else". Take whatever remaining number is left to equate to 100
        rNum5 = 100 - (rNum1 + rNum2 + rNum3 + rNum4);

        //Store random numbers in Array
        int rNumbersArray[] = new int [5];
        rNumbersArray[0] = rNum1;
        rNumbersArray[1] = rNum2;
        rNumbersArray[2] = rNum3;
        rNumbersArray[3] = rNum4;
        rNumbersArray[4] = rNum5;

        //Ask user for their dog's name
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Hello, what is your dog's name?");
        dogsName = inputReader.next();

        System.out.println("Well than, I have this highly reliable report on " + dogsName +
                "'s prestigious background right here");

        System.out.println(dogsName + " is:");

        //dogBreed and rNum have the same index
        //User either dogBreed.length or rNumbersArray.length in for loop
        //Increment i (counter) as long as i is less than or equal to the length of array
        //Still exploring <= name.length -1
        //still exploring < name -1
        for (int i = 0; i <= dogBreed.length -1; i++) {
            System.out.println(rNumbersArray[i] + "%" + " " + dogBreed[i]);
            //System.out.println(rNum2 + "%" + " " + dogBreed[1]);
            //System.out.println(rNum3 + "%" + " " + dogBreed[2]);
            //System.out.println(rNum4 + "%" + " " + dogBreed[3]);
            //System.out.println(rNum5 + "%" + " " + dogBreed[4]);
        }
        System.out.println("\nWow, that is QUITE the dog!");
    }
}

