package practiceLessons;

import java.util.Scanner;

public class DoOrDoNot {
    public static void main(String[] args) {

        //prints out I know you said not to... if No
        //prints out I did it... if Yes

        Scanner input = new Scanner(System.in);
        System.out.println("Should I do it? (y/n) ");
        boolean doIt;

        if (input.next().equals("y")) {
            doIt = true; // DO IT!
        } else {
            doIt = false; // DONT YOU DARE
        }

        boolean iDidIt = false;

        do {
            iDidIt = true;
            break;

        } while (doIt);

        if (doIt && iDidIt) {
            System.out.println("I did it!");
        } else if (!doIt && iDidIt) {
            System.out.println("I know you said not to... but I totally did anyways.");

        }else {
            System.out.println("Don't look at me, I didn't do anything!");
        }
    }
}