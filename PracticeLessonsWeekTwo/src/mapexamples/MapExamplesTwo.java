package mapexamples;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExamplesTwo {
    public static void main(String[] args) {

        //declare a map variabls
        //String, Integer Objects
        //this creates an empty map
        Map<String, Integer> populations = new HashMap<>();

        //put is similar toa add
        populations.put("USA", 313000000);

        populations.put("Canada", 34000000);

        populations.put("United Kingdom", 63000000);

        populations.put("Japan", 127000000);

        //ask a map how big it is (how many entries it has - 4)
        System.out.println("Map size is: " + populations.size());

        //declare a variable of type set keys
        Set<String> keys = populations.keySet();

        //left hand colon variable of type we have in our keys
        for(String currentKey : keys) {
            //get me the value that is associated with the current key
            Integer currentPopulation = populations.get(currentKey);

            System.out.println("The population of " + currentKey + " is " + currentPopulation);
        }


    }
}
