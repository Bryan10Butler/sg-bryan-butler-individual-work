package StateCapitalsTwo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        //create new map
        Map<String, Capital> capitalMap = new HashMap<>();

        //Instantiate new capitals of Capital object
        //setters not needed due to constructor
        Capital montgomery = new Capital("Montgomery", 205000,156);
        Capital juneau = new Capital("Juneau", 31000, 3255);
        Capital phoenix = new Capital("Phoenix", 1445000, 517);
        Capital littleRock = new Capital("Little Rock", 193000, 116);

        //put information into map
        capitalMap.put("Alabama", montgomery);
        capitalMap.put("Alaska", juneau);
        capitalMap.put("Arizona", phoenix);
        capitalMap.put("Arkansas", littleRock);

        System.out.println("STATE/CAPITOL PAIRS: " +"\n======================");

        //grab all keys from capitalMap and store as currentKey
        for (String currentKey : capitalMap.keySet()) {

            //interesting that I had to store as a capital object
            //grab all values associated with the current key being passed in
            Capital capitalInformation = capitalMap.get(currentKey);

            //print out currentKey and capital information
            System.out.println(currentKey + " - " + capitalInformation);
        }

        System.out.println("..." + "\n...");

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the lower limit for capital city population: ");
        String userInput = sc.nextLine();
        Integer userInputPopulation = Integer.parseInt(userInput);

        //put the pair (Key, Value) into stateCapitals to access via "get"
        //use Map.Entry
        //use .getKey
        //use .getValue.get(whatever you need for value)
        for (Map.Entry<String, Capital> stateCapitals : capitalMap.entrySet()) {
            if (userInputPopulation <= stateCapitals.getValue().getPopulation()) {
                System.out.print(stateCapitals.getKey() + " - ");
                System.out.print(stateCapitals.getValue().getName() + " | Pop: ");
                System.out.print(stateCapitals.getValue().getPopulation() + " | Area: ");
                System.out.print(stateCapitals.getValue().getMileage() + " sq mi");
                System.out.println("\n");
            }
        }
    }
}
