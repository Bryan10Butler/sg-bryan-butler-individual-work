package JavaPractice;

import java.util.Random;
import java.util.Scanner;

public class WhileLoop {
    public static void main(String[] args) {
        /*
        //setup you counter
        int i = 1;
        //setup while loop with termination condition ()
        // setup wile loop with what it will be doing {}
        while(i < 6) {
            System.out.println(i);
            i++;
        }

        Random rGen = new Random();
        int randomNum = rGen.nextInt(10) + 1;
        while (randomNum < 8) {
            System.out.println(randomNum);
            randomNum = rGen.nextInt(10) + 1;
        }


        int min = 0;
        int max = 0;
            boolean invalidInput = false;
            int number = 0;
            do {
                if (number < min || number > max) {
                    System.out.println("Please provide a number greater than " + min + " and less than " + max);
                    invalidInput = true;
                }else {
                    invalidInput = false;
                }// end of else

            }while (invalidInput);

        }

*/

/*
        if (dayOfWeek == 1) {
            System.out.println("Monday");
        } else if (dayOfWeek == 2) {
            System.out.println("Tuesday");
        } else if (dayOfWeek == 3) {
            System.out.println("Wednesday");
        } else if (dayOfWeek == 4) {
            System.out.println("Thursday");
        } else if (dayOfWeek == 5) {
            System.out.println("Friday");
        } else {
            System.out.println("not a valid number");
        }

*/

        int intDayOfWeek;
        String stringDayOfWeek;
        String printDayOfWeek;
        Scanner userInput = new Scanner(System.in);

        System.out.println("Please select a number 1 - 5");
        stringDayOfWeek = userInput.nextLine();
        intDayOfWeek = Integer.parseInt(stringDayOfWeek);

        switch (intDayOfWeek) {
            case 1:
                printDayOfWeek = "Monday";
                break;
            case 2:
                printDayOfWeek = "Tuesday";
                break;
            case 3:
                printDayOfWeek = "Wednesday";
                break;
            case 4:
                printDayOfWeek = "Thursday";
                break;
            case 5:
                printDayOfWeek = "Friday";
                break;
            default:
                printDayOfWeek = "no such day";
        }
        System.out.println(printDayOfWeek);

        int timenow = 5;
        int bedtime = 10;

        while (timenow < bedtime) {
            System.out.println("It is only " + timenow);
            System.out.println("I'm staying up");
            timenow ++;//as time passes
        }

        System.out.println("Oh it is" + timenow);
        System.out.println("I am going to bed");



    }
}
