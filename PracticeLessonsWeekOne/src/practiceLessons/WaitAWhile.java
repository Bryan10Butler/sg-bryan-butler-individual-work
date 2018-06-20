package practiceLessons;

public class WaitAWhile {
    public static void main(String[] args) {
        //If timeNow is 11 the code would not run because it fails in condition
        int timeNow = 5;
        int bedTime = 10;

        while (timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " O'clock!");
            System.out.println("I think I'll stay up just a liiiiiitle longer....");
            //If timeNow is commented out there is no condition to be met - Infinite Loop
            timeNow++; //Time passes
        }
        System.out.println("Oh. It's " + timeNow + " o'clock");
        System.out.println("Guess I should go to bed...");
    }
}
