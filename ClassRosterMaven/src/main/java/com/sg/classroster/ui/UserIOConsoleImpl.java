package com.sg.classroster.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{

    //any method in this class can use this scanner
    private Scanner inputReader = new Scanner(System.in);



    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        // display prompt
        print(prompt);
        //read in user input as a string
        String numberString = inputReader.nextLine();
        //convert the input to a double
        double number = Double.parseDouble(numberString);
        //return that double
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean invalidInput = false;
        double number;

        do {
            print(prompt);
            String numberString = inputReader.nextLine();

            number = Double.parseDouble(numberString);
            if (number < min || number > max) {
                print("Please provide a number greater than " + min + " and less than " + max);
                invalidInput = true;
            }else {
                invalidInput = false;
            }// end of else


        }while (invalidInput);

        return number;
    }

    @Override
    public float readFloat(String prompt) {
        // display prompt
        print(prompt);
        //read in user input as a string
        String numberString = inputReader.nextLine();
        //convert the input to a double
        float number = Float.parseFloat(numberString);
        //return that double
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean invalidInput = false;
        float number;

        do {
            print(prompt);
            String numberString = inputReader.nextLine();

            number = Float.parseFloat(numberString);
            if (number < min || number > max) {
                print("Please provide a number greater than " + min + " and less than " + max);
                invalidInput = true;
            }else {
                invalidInput = false;
            }// end of else


        }while (invalidInput);

        return number;
    }

    @Override
    public int readInt(String prompt) {
        int number = 0;
        boolean invalidInput = false;
        do {
            try {
                //display the prompt
                System.out.println(prompt);
                //read in the user input as a string
                String numberString = inputReader.nextLine();
                //convert the String to an int
                number = Integer.parseInt(numberString);
            } catch (NumberFormatException e) {
                print("Please enter a valid integer");
                invalidInput = true;
            }//end of Try and Catch
        }while (invalidInput);//end of do
        //return that int
        return number;
    }

    //review
    @Override
    public int readInt(String prompt, int min, int max) {
        boolean invalidInput = false;
        int number;

        do {
            print(prompt);
            String numberString = inputReader.nextLine();

            number = Integer.parseInt(numberString);
            if (number < min || number > max) {
                print("Please provide a number greater than " + min + " and less than " + max);
                invalidInput = true;
            }else {
                invalidInput = false;
            }// end of else

        }while (invalidInput);

        return number;
    }
    @Override
    public long readLong(String prompt) {
        // display prompt
        print(prompt);
        //read in user input as a string
        String numberString = inputReader.nextLine();
        //convert the input to a double
        long number = Long.parseLong(numberString);
        //return that double
        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean invalidInput = false;
        Long number;

        do {
            print(prompt);
            String numberString = inputReader.nextLine();

            number = Long.parseLong(numberString);
            if (number < min || number > max) {
                print("Please provide a number greater than " + min + " and less than " + max);
                invalidInput = true;
            }else {
                invalidInput = false;
            }// end of else


        }while (invalidInput);

        return number;
    }

    @Override
    public String readString(String prompt) {
        // display prompt
        print(prompt);
        //read in user input as a string
        String numberString = inputReader.nextLine();
        //return
        return numberString;
    }
}
