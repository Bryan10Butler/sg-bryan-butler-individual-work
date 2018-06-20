package JavaPractice;

import java.util.Random;

public class RandomNumberPractice {
    public static void main(String[] args) {
        int rNum1;
        int rNum2;
        int rNum3;
        int rNum4;
        int rNum5;
        int max = 100;

        Random randomNumber = new Random();

        rNum1 = randomNumber.nextInt(max) + 1;
        System.out.println(rNum1);
        rNum2 = randomNumber.nextInt(max - rNum1) + 1;
        System.out.println(rNum2);
        rNum3 = randomNumber.nextInt(max - (rNum1 + rNum2)) + 1;
        System.out.println(rNum3);
        rNum4 = randomNumber.nextInt(max - (rNum1 + rNum2 + rNum3)) + 1;
        System.out.println(rNum4);
        rNum5 = 100 - (rNum1 + rNum2 + rNum3 + rNum4);
        System.out.println(rNum5);

        System.out.println("Sum: " + (rNum1 + rNum2 + rNum3 + rNum4 + rNum5));




    }
}
