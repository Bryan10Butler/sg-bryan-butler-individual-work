package JavaPractice;

public class MethodsTwo {
    public static void main(String[] args) {
        //Remember method calls are expressions. Run get5, return 5 from get5 and stick in num.
        int num = get5();
        System.out.println(num);
        //another way - call get5, return 5 into print line
        System.out.println(get5());

    }
    //declare method. Use return (int as you are promising int) to call 5 in different parts of code.
    public static int get5() {
        return 5;
    }
}
