package JavaPractice;

import java.sql.Driver;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Maps {

    public static void main(String[] args) {

        //create new empty HashMap
        Map<String, Integer> populations = new HashMap<>();

        //Add entries to HashMap
        populations.put("USA", 316000000);

        populations.put("Canada", 340000000);

        populations.put("United Kingdom", 63000000);

        populations.put("Japan", 127000000);

        //prints out the size of the map which is 4 entries
        System.out.println("The Map size is: " + populations.size());

        //Get the Key USA
        //The map will return the value associated with that key
        //The map will store into Integer usaPopulation
        Integer usaPopulation = populations.get("USA");

        //print the population of USA
        System.out.println("The population of USA: " + usaPopulation);

        //if you realize the population is incorrect, put a  new population in
        populations.put("USA", 313000000);

        //store the new population in the already existing usaPopulation
        usaPopulation = populations.get("USA");

        //print the new usaPopulation
        System.out.println("The new USA Population is: " + usaPopulation);

        //set is a type of collection. No duplication. Unordered

        //we want a set of String out
        //this grabs ONLY Keys
        Set<String> keys = populations.keySet();

        //for every "keys", store in currentKey and we will do something in the code block
        for(String currentKey : keys) {

            //all keys from currentKey store in current Population
            //this allows to grab each key from the map
            //the map will print each value associated to each key that is printed
            Integer currentPopulation = populations.get(currentKey);

            System.out.println("The population of: " + currentKey + " is " + currentPopulation);

        }

        Collection<Integer> populationValues = populations.values();

        //no guarantee of order
        //we could have duplicate values
        for(Integer currentPopulation : populationValues) {
            System.out.println(currentPopulation);
        }



    }
}
