package JavaPractice;

public class Arrays {
    public static void main(String[] args) {
        int[] numbers = {3,7,2,4,7,12};
        int sum = 0;
        int min = numbers[0];
        int max = numbers[0];

        for (int i = 0; i < numbers.length; i ++) {
           //running total
            sum += numbers[i];
             /*0 = 0 + 3,
               3 = 3 + 7,
               10 = 10 + 2,
               12 = 12 + 4,
               16 = 16 + 7,
               23 = 23 + 12,*/
             if (numbers[i] < min) {
                 min = numbers[i];

                 /*
                 3 < 3 false
                 7 < 3 false
                 2 < 3 true, store 2
                 4 < 2 false
                 7 < 2 false
                 12 < 2 false
                 */
             }
             if (numbers[i] > max) {
                 max = numbers[i];
                 /*
                 3 > 3 false
                 7 > 3 true, store 7
                 2 > 7 false
                 4 > 7 false
                 7 > 7 false
                 12 > 7 true
                  */
             }

        }
        System.out.println(min);
        System.out.println(max);
        System.out.println(sum);
    }
}
