package JavaPractice;

public class ArrayPractice {
    public static void main(String[] args) {
        //declare and reserve space one way
        int teamScores [] = new int[5];
        //declare another way
        //int[] teamScores;
        //reserve space for our array
        //teamScores = new int [5];

        teamScores[0] = 25;
        teamScores[1] = 29;
        teamScores[2] = 28;
        teamScores[3] = 24;
        teamScores[4] = 22;

        //for as long as i is less than the length of the array
        for (int i = 0; i < teamScores.length; i++) {

            System.out.println("Element " + i + " = " + teamScores[i]);
        }
        //another way to capture an array output
        for (int num : teamScores) {

            System.out.println(num);
        }

    }
}
