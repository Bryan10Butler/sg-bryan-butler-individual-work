package com.company;

import java.util.Scanner;

public class DoItBetter {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        double miles;
        int hotDogs;
        int languages;

        System.out.println("How many miles can you run?");
        miles = inputReader.nextDouble();
        System.out.println("You can only run " + miles + ". I can run " + ((miles *2) +1));

        System.out.println("How many hot dogs can you eat?");
        hotDogs = inputReader.nextInt();
        System.out.println("You can only eat " + hotDogs + ". I can run " + ((hotDogs *2) +1));

        System.out.println("How many languages can you speak?");
        languages = inputReader.nextInt();
        System.out.println("You can only speak " + languages + ". I can run " + ((languages *2) +1));
    }
}
