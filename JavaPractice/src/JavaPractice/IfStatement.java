package JavaPractice;

import java.util.Scanner;

public class IfStatement {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        byte numOne;

        //capture user number input
        System.out.println("Please enter a number");
        numOne = inputReader.nextByte();

        if (numOne < 10) {
            System.out.println(numOne + " is less than 10");
        } else if (numOne > 20) {
            System.out.println("Your number is to higher than 10");
        } else {
            System.out.println("I like your number");
        }
    }
}
