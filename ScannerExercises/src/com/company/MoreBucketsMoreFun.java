package com.company;

public class MoreBucketsMoreFun {
    public static void main(String[] args) {
        //Declare ALL THE THINGS
        //(Usually it's a good idea to declare them at the beginning of the program)
        int butterflies, beetles, bugs;
        String color, size, shape, thing;
        double number;

        //Now give a couple of them some values
        butterflies = 2;
        beetles = 4;

        bugs = butterflies + beetles;
        System.out.println("There are only " + butterflies + " butterflies,");
        System.out.println("but " + bugs + " bugs total.");

        System.out.println("Uh oh, my dog ate one.");
        butterflies--;//Decrement Operator
        //bugs = butterflies + beetles; The statement is higher in the code, so after we decrease it by one,
        //the code does not change
        System.out.println("Now there are only " + butterflies + " butterflies left.");
        System.out.println("But still " + bugs + " bugs left, wait a minute!!!");
        System.out.println("Maybe I just counted wrong the first time...");
    }
}
