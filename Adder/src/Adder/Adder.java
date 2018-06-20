package Adder;

import java.util.Scanner;

public class Adder {
    public static void main(String[] args){
        /*Adder Version 1
        //declare sum and initialize it to 0
        int sum = 0;
        //declare and initialize our operands
        int operand1 = 3;
        int operand2 = 2;

        //assign the sum of operand1 and operand2 to the variable sum
        sum = operand1 + operand2;
        //we can use the + operator to concatenate strings as well
        System.out.println("Sum is: " + sum);
        */
        //declare sum and initialize it to o
        int sum = 0;
        int operand1 = 0;
        int operand2 = 0;

        //declare and initialize a scanner object - the Scanner reads input from console
        Scanner myScanner = new Scanner(System.in);
        //declare and initialize String (text) variables to hold
        //values the user types in.
        String stringOperand1 = "";
        String stringOperand2 = "";

        //as the user to input the first operand
        System.out.println("Please enter the first number to be added");
        //now wait until the user types something in
        //Put the value in stringOperand1
        stringOperand1 = myScanner.nextLine();

        //ask the user to input the second operand
        System.out.println("Please enter the second number to be added");
        //now wait until the user types something in
        //Put the value in stringOperand2
        stringOperand2 = myScanner.nextLine();

        //in order to add the values input by the user we must convert
        //the string values into int values. We use parseInt for this
        operand1 = Integer.parseInt(stringOperand1);
        operand2 = Integer.parseInt(stringOperand2);

        //assign the sum of operand 1 and operand 2 to sum
        sum = operand1 + operand2;

        //print the sum to the console
        System.out.println("Sum is: " + sum);
    }
}
