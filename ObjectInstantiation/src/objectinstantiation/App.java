package objectinstantiation;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        //type is Adder
        //new is how we instantiate an object
        //an object becomes an object after instantiate occurs
        //similar to how we use scanner and random
        Adder adder = new Adder();

        //call method add from Adder object
        int sum = adder.add(5,4);
        int sumTwo = adder.add(45,50);

        System.out.println("The sum of the numbers is: " + sum);
        System.out.println("The sum of the numbers is: " + sumTwo);

    }//end of main

}//end of class App
