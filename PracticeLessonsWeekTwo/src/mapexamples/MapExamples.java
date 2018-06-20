package mapexamples;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExamples {

    public static void main(String[] args) {

        //declare a map variabls
        //String, Integer Objects
        //this creates an empty map
        Map<String, Integer> populations = new HashMap<>();

        //put is similar toa add
        populations.put("USA", 200000000);

        populations.put("Canada", 34000000);

        populations.put("United Kingdom", 63000000);

        populations.put("Japan", 127000000);

        //ask a map how big it is (how many entries it has - 4)
        System.out.println("Map size is: " + populations.size());

        //NEW LESSON BELOW

        //print out the population of the USA
        //this will grab the value associated with USA and give us the population
        Integer usaPopulation = populations.get("USA");

        //print out variable usaPopulation
        System.out.println(usaPopulation);

        //.put syntax is key, value
        //this will repalce the above usa
        populations.put("USA", 313000000);

        //again, get out USA get to get new value
        usaPopulation = populations.get("USA");

        //print out variable usaPopulation
        System.out.println(usaPopulation);

        //NEW LESSON




    }
}
