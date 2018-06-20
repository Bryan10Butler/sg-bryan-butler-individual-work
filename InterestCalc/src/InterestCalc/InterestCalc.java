package InterestCalc;

import java.math.RoundingMode;
import java.util.Scanner;
import java.math.BigDecimal;

public class InterestCalc {


    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        //double intRate = 0;
        BigDecimal intRate;
        //double principleAmount;
        BigDecimal principleAmount;
        int numYears;
        int numCompoundsPerYear = 4;
        BigDecimal currentBalance;
        BigDecimal balanceForYear;
        do {
            System.out.println("Please enter the annual interest rate: ");
            String intRateString = console.nextLine();
            //intRate = Double.parseDouble(intRateString);
            intRate = new BigDecimal(intRateString);
            //part of weekend
            if (intRate.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Please enter an amount greater than zero");
            }
        } while (intRate.compareTo(BigDecimal.ZERO) <= 0);

        do {
            System.out.println("Please enter the initial balance to be invested");
            String principleAmountString = console.nextLine();
            //principleAmount = Double.parseDouble(principleAmountString);
            principleAmount = new BigDecimal(principleAmountString);

            if (principleAmount.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("You can't invest an amount less than zero! Duh!");


            }
        }while (principleAmount.compareTo(BigDecimal.ZERO) <= 0);
        do {
            System.out.println("Please enter the number of years you would like to invest your money");
            String numYearsString = console.nextLine();
            numYears = Integer.parseInt(numYearsString);

            if (numYears <= 0) {
                System.out.print("Please enter an amount greater than zero. Thanks!");
            }
        }while (numYears<=0);

        currentBalance = principleAmount;

        for(int i=1;i<=numYears;i++)
        {
            System.out.println("Year: " + i + currentBalance);

            balanceForYear = currentBalance;

            for(int ii=1; ii<=numCompoundsPerYear; ii++)
            {
                //balanceForYear=balanceForYear*(1+ (intRate/numCompoundsPerYear)/100);
                //Review the below code
                BigDecimal one = new BigDecimal(1);
                BigDecimal oneHundred = new BigDecimal (100);
                BigDecimal numCompoundsPeyYearBigDecimal = new BigDecimal(Integer.toString(numCompoundsPerYear));
                balanceForYear = balanceForYear.multiply(one.add(intRate.divide(numCompoundsPeyYearBigDecimal, 2 , RoundingMode.HALF_UP).divide(oneHundred, 2, RoundingMode.HALF_UP)));
            }
            BigDecimal interestForYear = balanceForYear.subtract(currentBalance);
            System.out.print("\t\t Interest Earned: " + (interestForYear));
            System.out.println("\t\t Ending Balance: " + balanceForYear);
            currentBalance=balanceForYear;
        }
    }
}
