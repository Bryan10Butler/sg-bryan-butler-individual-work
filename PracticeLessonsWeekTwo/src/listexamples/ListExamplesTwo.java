package listexamples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExamplesTwo {

    public static void main(String[] args) {

        //list of strings
        List<String> stringList = new ArrayList<>();

        //this will add one
        stringList.add("The First String");

        //this will add one
        stringList.add("The Second String");

        //this will add one
        stringList.add("The Third String");

        //This will add one
        stringList.add("The Fourth String");

        //ask the list how big it is
        System.out.println("List size: " + stringList.size());

        //takes each string from stringList out and puts into currentString
        //print out to the screen
        for (String currentString : stringList) {

            System.out.println(currentString);
        }

        //iterator visits all elements in a collection exactly once - List is ordered - Collection is not

        //Another way to print - We are asking the list for it's iterator
        Iterator<String>iterator = stringList.iterator();

        while (iterator.hasNext()) {
            String currentString = iterator.next();
            System.out.println(currentString);
        }
    }//end of main
}

