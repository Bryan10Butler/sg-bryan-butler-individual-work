package JavaPractice;

public class SingleDimensionalArray {
    public static void main(String[] args) {
        //declare array and initialize array with 5 storage areas
        int[] teamScores;

        teamScores = new int[5];

        teamScores[0] = 2;
        teamScores[1] = 45;
        teamScores[2] = 4;
        teamScores[3] = 8;
        teamScores[4] = 99;

        //short cut to initialize and array. Notice we used {}
        int[] golfScores = {72, 74, 68, 71};

        int currentGolfScore = golfScores[0];

        //changing our array index. 70 prints out not 68
        golfScores[2] = 70;

        for (int i = 0; i < golfScores.length; i ++) {
            System.out.println(golfScores[i]);
        }

        //as long as numbers exist in golfScores, take all numbers and store in currentScore
        for (int currentScore : golfScores) {
            System.out.println(currentScore);
        }

    }
}
