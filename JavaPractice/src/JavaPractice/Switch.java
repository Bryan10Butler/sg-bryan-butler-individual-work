package JavaPractice;

public class Switch {
    public static void main(String[] args) {
/*
        int day = 4;
        String dayName;

        //expression day
        switch (day) {
            //case constant
            case 1:
                //execute code if expression == constant
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sudnay";
                break;

            default:
                dayName = "Invalid day";
        }
        System.out.println(dayName);
    }
}
*/

        int day = 4;
        String dayType = "";

        switch (day) {

            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                dayType = "The Weekday";
                break;
            case 6:
            case 7:
                dayType = "The Weekend";
                break;
            default:
                dayType = "Invalid Day";
        }
        System.out.println(dayType);
    }
}