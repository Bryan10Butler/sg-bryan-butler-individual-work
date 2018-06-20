package JavaPractice;

import java.util.Scanner;

public class DoWhileLoop {
    public static void main(String[] args) {
        /*
        //need to declare and assign outside of loop
        int i = 1;
        //guarantees one loop one through
        do {
            System.out.println(i);
            //do something to effect the termination condition to keep from infinite loop
            i++;
            //termination condition below
        } while (i < 6);
        */

        Scanner sc = new Scanner(System.in);
        int userNumber;
        String userNumberString;

        do {
            System.out.println("please enter a number between 1 and 20");
            userNumberString = sc.nextLine();
            userNumber = Integer.parseInt(userNumberString);
        } while (userNumber < 1 || userNumber > 20);

        System.out.println("Thank you! Your number is " + userNumber);
    }
}
