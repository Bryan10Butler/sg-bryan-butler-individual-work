package simpleCalculator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String stringUserSelection;
        //String stringUserNumberOne;
        //String stringUserNumberTwo;
        boolean readyToQuit = false;
        double numberOne;
        double numberTwo;
        double result = 0;
        //instantiate a pointer to object UserIOConsoleImpl
        UserIOConsoleImpl userIOInterface = new UserIOConsoleImpl();

        userIOInterface.print("Hello, please select either:");

        do {
            stringUserSelection = userIOInterface.readString( "\n1 - Addition \n2 - Subtraction " +
                    "\n3 - Multiplication \n4 - Division \n5 - Quit\"");

            if (stringUserSelection.startsWith("5")) {

                readyToQuit = true;
                System.out.println("Thank you for playing!");
                break;
            }//end of if quit

            //user input for number 1
            numberOne = userIOInterface.readDouble("Please choose number 1");

            //user input for number 2
            numberTwo = userIOInterface.readDouble("Please choose number2");

            switch (stringUserSelection) {

                case "1":

                    result = SimpleCalculator.add(numberOne,numberTwo);
                    break;

                case "2":

                    result = SimpleCalculator.subtract(numberOne, numberTwo);
                    break;

                case "3":

                    result = SimpleCalculator.multiple(numberOne, numberTwo);
                    break;

                case "4":

                    result = SimpleCalculator.divide(numberOne, numberTwo);
                    break;

                default:
                    userIOInterface.print("Enter values 1, 2, 3, 4, 5");

            }//end of switch

            userIOInterface.print("Please see the below result!\n" + result);

        //because we have ready to quit assigned to false, if user enters 5 this will trigger
        //change to true and hit our termination condition while (true).
        }while (!readyToQuit);

    }//end of main
}//end of class app
