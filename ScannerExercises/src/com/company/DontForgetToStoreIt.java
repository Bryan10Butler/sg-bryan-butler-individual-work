package com.company;

import java.util.Scanner;

public class DontForgetToStoreIt {
    public static void main(String[] args) {

        int meaningOfLifeAndEverything = 42;//These literal values trump the user input
        double pi = 3.14159;//These literal values trump the user input
        String cheese, color;

        Scanner inputReader = new Scanner(System.in);

        System.out.println("Give me pi to at least 5 decimals: ");
        inputReader.nextDouble();

        System.out.println("What is the meaning of life, the universe & everything? ");
        inputReader.nextInt();

        System.out.println("What is your favorite kind of cheese? ");
        cheese = inputReader.next();//You are assigning cheese the output of what the user is inputting

        System.out.println("Do you like the color red or blue more? ");
        color = inputReader.next();//You are assigning color the output of what the user is inputting

        System.out.println("Ooh, " + color + " " + cheese +" sounds delicious!");
        System.out.println("The circumference of life is " +( 2 * pi * meaningOfLifeAndEverything));

    }
}
