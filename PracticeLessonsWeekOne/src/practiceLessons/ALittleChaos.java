package practiceLessons;


import java.util.Random;

public class ALittleChaos {
    public static void main(String[] args) {
        String withWhiteSpace = "String  with     White Space";

        String withoutShiteSpace = withWhiteSpace.replaceAll("\\s+"," ");

        System.out.println(withoutShiteSpace);

    }
}
