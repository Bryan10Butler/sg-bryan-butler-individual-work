package practiceLessons;

import java.util.Scanner;

public class forTimes {
    public static void main(String[] args) {
        //scanner tool to capture user input
        Scanner userInput = new Scanner(System.in);
        //declare variable to capture user number input
        byte userNum;
        //ask user what number and capture input
        System.out.println("What time table shall I recite?");
        userNum = userInput.nextByte();
        //increment i as long as i is less than 16
        for (int i = 0; i < 16; i++) {
            System.out.println(i + " * " + userNum + " is: " + (i * userNum));
        }
    }
}
