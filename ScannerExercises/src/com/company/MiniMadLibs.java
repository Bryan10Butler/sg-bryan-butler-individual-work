package com.company;

import java.util.Scanner;

public class MiniMadLibs {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        String noun1, adjective1, noun2, number, adjective2, pluralNoun1, pluralNoun2, pluralNoun3, verbPresentTense, verbPastTense;

        System.out.println("I need a noun: ");
        noun1 = inputReader.next();

        System.out.println("Now an adj: ");
        adjective1 = inputReader.next();

        System.out.println("Another noun: ");
        noun2 = inputReader.next();

        System.out.println("And a number: ");
        number = inputReader.next();

        System.out.println("Another adj: ");
        adjective2 = inputReader.next();

        System.out.println("A plural noun: ");
        pluralNoun1 = inputReader.next();

        System.out.println("Another one: ");
        pluralNoun2 = inputReader.next();

        System.out.println("One more: ");
        pluralNoun3 = inputReader.next();

        System.out.println("A verb (present tense): ");
        verbPresentTense = inputReader.next();

        System.out.println("Same verb (past tense):  ");
        verbPastTense = inputReader.next();

        System.out.println();

        System.out.println(noun1 + ": the " + adjective1 + "frontier. These are the voyages of the starship" + noun2 + ". Its " + number + "-year mission: to explore strange " + adjective2 + " " + pluralNoun1 + " , to seek out " + adjective2 + " " + pluralNoun2 + " and " + adjective2 + " " + pluralNoun3 + " , to boldly " + verbPresentTense + " where no one has " + verbPastTense + " before.");
    }
}
