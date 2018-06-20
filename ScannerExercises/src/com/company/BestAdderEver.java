package com.company;

public class BestAdderEver {
    public static void main(String[] args) {
        int curly = 5;
        int moe = 4;
        int larry = 1000000;
        int sum = curly + moe+ larry;

        System.out.println("Curly: " + curly);
        System.out.println("Moe: " + moe);
        System.out.println("larry: " + larry);
        System.out.println("Sum is: " + sum);
        System.out.println("sum is: " + sum + sum);//the presence of the string won't add together
        System.out.println("sum is: " + (sum + sum));//this will output 200000018
        System.out.println(sum + sum);//this will output 2000018
    }
}
