package mapexamples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GetAllValues {
    public static void main(String[] args) {

        //this creates an empty map for us
        Map<String, Integer> populations = new HashMap<>();

        //
        populations.put("USA ", 200000000);

        populations.put("Canada ", 34000000);

        populations.put("United Kingdom ", 63000000);

        populations.put("Japan ", 127000000);

        System.out.println("Map size is: " + populations.size());

        //declare variable of collection
        //Have to ask the populations maps to ge the values
        Collection<Integer> populationValues = populations.values();

        for(Integer currentPopulation : populationValues) {

            System.out.println(currentPopulation);

        }





    }
}






