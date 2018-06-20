package com.company;

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        int age;
        int maxRate;

        System.out.println("what is your age?");
        age = inputReader.nextInt();
        maxRate = 220 - age;

        System.out.println("Your maximum heart rate is " + maxRate + " beats per minute");

        System.out.println("Your target HR zone show be between " + (maxRate * .50) + " and " + (maxRate * .85) + ".");

    }
}
