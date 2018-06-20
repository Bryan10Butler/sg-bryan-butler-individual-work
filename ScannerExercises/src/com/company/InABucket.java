package com.company;

public class InABucket {
    public static void main(String[] args) {

        //You can declare all KINDS of variables.
        String walrus;
        double piesEaten;
        float weightOfTeacupPig;
        int grainsOfSand;

        //But declaring them just sets up the space for data
        //to use the variable, you have to put data IN it first!
        walrus = "Sir Leroy Jenkins III";
        piesEaten = 42.1;
        //float with f
        weightOfTeacupPig = 1.8f;
        grainsOfSand = 6;


        System.out.println("Meet my pet Walrus, " + walrus);
        System.out.print("He was a bit hungry today, and ate this many pies: ");
        System.out.println(piesEaten);
        System.out.println("The weight of the pig is " + weightOfTeacupPig);
        System.out.println("The grains of sand are " + grainsOfSand);
    }
}
