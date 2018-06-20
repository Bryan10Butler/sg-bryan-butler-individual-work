package JavaPractice;

public class bubbleSort {
    public static void main(String[] args) {
        int[] numbers = { 3,7, 2, 4, 7, 12, };
        int temp;
        for (int i = 1; i < numbers.length; i++) {

        }
        for (int j = 0; j < numbers.length-1; j++) {
            temp = numbers[j];
            numbers[j] = numbers[j+1];
            numbers[j + 1] = temp;
        }
    }
}
