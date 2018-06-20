package com.company;

import java.util.Scanner;

public class BiggerBetterAdder {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        int curly;
        int moe;
        int larry;

        System.out.println("Curly: ");
        curly = inputReader.nextInt();

        System.out.println("Moe: ");
        moe = inputReader.nextInt();


        System.out.println("larry: " );
        larry = inputReader.nextInt();

        int sum = curly + moe + larry;

        System.out.println("Sum is: " + sum);
        System.out.println("sum is: " + sum + sum);//the presence of the string won't add together
        System.out.println("sum is: " + (sum + sum));//this will output 200000018
        System.out.println(sum + sum);//this will output 2000018
    }
}