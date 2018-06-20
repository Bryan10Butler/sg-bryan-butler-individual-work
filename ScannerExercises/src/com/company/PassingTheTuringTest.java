package com.company;

import java.util.Scanner;

public class PassingTheTuringTest {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        String name;
        String color;
        String food;
        int number;

        System.out.println("Hello there!");
        System.out.println("What is your name: ");
        name = inputReader.next();

        System.out.println("Hi " + name + "!");
        System.out.println("what is your favorite color: ");
        color = inputReader.next();

        System.out.println("Huh, " + color + "? Mine is red!");
        System.out.println("I really like apples. They are my favorite fruit, too.");
        System.out.println("What's YOUR favorite fruit, " + name + "? ");
        food = inputReader.next();

        System.out.println("Really? " + food + "! That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number?");
        number = inputReader.nextInt();
        System.out.println(number + " is a cool number! Mine favorite number is 10");
        System.out.println("Did you know that " + number + " multiplied by -7 is " + (number * -7) + ". That is a cool number to!" );
        System.out.println("Well, thanks for talking to me, " + name + "!");
    }
}
