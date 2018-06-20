package JavaPractice;

public class LoopExamples {
    public static void main(String[] args) {
        //starting point 1
        //increment by 1 as long as i is less than 6
        for (int i = 1; i < 6; i++) {
            System.out.println(i);
        }

        //starting point 3
        //increment by 1 as long as i is less than 6
        for (int i = 3; i < 6; i++) {
            System.out.println(i);
        }

        //starting point 1
        //increment i by 2 as long as i is less than 21
        for (int i = 1; i < 21; i = i + 2) {
            System.out.println(i);
        }
        //starting point 2
        //increment i by 2 as long as i is less than 21
        for (int i = 2; i < 21; i = i + 2) {
            System.out.println(i);
        }
        //starting point 5
        //increment i by 5 as long as i is less than 25
        for (int i = 5; i < 25; i = i + 5) {
            System.out.println(i);
        }
    }
}
