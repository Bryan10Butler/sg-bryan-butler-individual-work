package statickeyword;

public class StaticApp {

    public static void main(String[] args) {

        //type is Adder
        //new is how we instantiate an object
        //an object becomes an object after instantiate occurs
        //similar to how we use scanner and random


        //call method add from Adder object
        int sum = StaticAdder.add(5,4);
        int sumTwo = StaticAdder.add(45,50);

        System.out.println("The sum of the numbers is: " + sum);
        System.out.println("The sum of the numbers is: " + sumTwo);

    }//end of main

}
