package listexamples;

import java.util.ArrayList;
import java.util.List;

public class ListExamples {

    public static void main(String[] args) {

        //list of strings
        List<String> stringList = new ArrayList<>();

        //this will print null
        System.out.println("List size: " + stringList.size());

        //this will add one
        stringList.add("The first string");

        //this will print one due to we are adding 1
        System.out.println("List size: " + stringList.size());

        //this will add one
        stringList.add("List size: " + stringList.size());

        //this will print one due to we are adding one
        System.out.println("List size: " + stringList.size());

        //remove method
        stringList.remove(1);

        //this will remove one
        System.out.println("List size: " + stringList.size());


    }//end of main
}
