package luckySevens;

import java.util.Random;
import java.util.Scanner;

public class luckySevens {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String stringInitialMoney;
        int initialMoney = 0;
        int maxMoney = 0;
        int maxRollCount = 0;
        int rollCount = 0;
        int currentMoney, dice1, dice2, sum;

        while (initialMoney < 1) {
            System.out.println("How many dollars do you have? ");
            stringInitialMoney = sc.nextLine();
            initialMoney = Integer.parseInt(stringInitialMoney);
        }

        currentMoney=initialMoney;
        maxMoney=currentMoney;

        while (currentMoney > 0) {
            sum = nextRollOfDice();
            rollCount ++;
            if (sum==7) {
                currentMoney = currentMoney + 4;
                if (currentMoney > maxMoney) {
                    maxMoney = currentMoney;
                    maxRollCount = rollCount;

                }
            }
            else {
                    currentMoney=currentMoney -1;
                }
            }
        System.out.println("You are broke after " + rollCount + " rolls.");
        System.out.println("You should have quit after " + maxRollCount + " rolls when you had $ " + maxMoney);
    }

    public static int nextRollOfDice() {
        int dice1;
        int dice2;
        int sum;
        Random rNum = new Random();
        dice1 = rNum.nextInt (6) + 1;
        dice2 = rNum.nextInt (6) + 1;
        sum = dice1 + dice2;
        return sum;
    }
}
