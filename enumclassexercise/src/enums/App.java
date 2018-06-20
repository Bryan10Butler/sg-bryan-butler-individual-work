package enums;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        //look into get values

        System.out.println("Please enter a day of the week Monday, Tuesday, Wednesday, Thursday, Friday," +
                "Saturday, Sunday");
        Scanner scanner = new Scanner(System.in);

        String userString = null;
        int daysAwayFromFriday = 0;

        DaysOfWeek daysOfWeek = null;
        boolean invalidDay = true;

        while (invalidDay) {
            try {
                userString = scanner.nextLine();
                daysOfWeek = DaysOfWeek.valueOf((userString.toUpperCase()));
                invalidDay = false;
            }catch (IllegalArgumentException e) {
                System.out.println("Try Again");
                invalidDay = true;
            }
        }


        switch (DaysOfWeek.valueOf(userString.toUpperCase())) {

            case MONDAY:
                daysAwayFromFriday = 4;
                break;
            case TUESDAY:
                daysAwayFromFriday = 3;
                break;
            case WEDNESDAY:
                daysAwayFromFriday = 2;
                break;
            case THURSDAY:
                daysAwayFromFriday = 1;
                break;
            case FRIDAY:
                daysAwayFromFriday = 0;
                break;
            case SATURDAY:
                daysAwayFromFriday = 6;
                break;
            case SUNDAY:
                daysAwayFromFriday = 5;
                break;
            default:
                System.out.println("Please enter a valid day!");
        }

        System.out.println(daysAwayFromFriday + " days until Friday");

    }


}
