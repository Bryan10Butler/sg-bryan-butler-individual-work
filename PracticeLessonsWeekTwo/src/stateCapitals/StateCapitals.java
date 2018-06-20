package stateCapitals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StateCapitals {

    public static void main(String[] args) {

        Map<String, String> stateCapitol = new HashMap<>();

        stateCapitol.put("Massachusetts", "Boston");
        stateCapitol.put("Connecticut", "Hartford");
        stateCapitol.put("Arkansas", "Little Rock");
        stateCapitol.put("Arizona", "Phoenix");

        //use a collection to get the values
        //syntax is (Key, Value)
        Collection<String> stateCapitolValues = stateCapitol.values();

        System.out.println("CAPITOLS\n ==========");
        for (String currentState : stateCapitolValues) {
            System.out.println(currentState);
        }

        Set<String> stateCapitolKeys = stateCapitol.keySet();

        System.out.println("STATES\n ==========");
        for (String currentCapitol : stateCapitolKeys) {
            System.out.println(currentCapitol);
        }

        System.out.println("STATE/CAPITAL PAIRS\n ==========");
        Set<String> keys = stateCapitol.keySet();
        for (String currentKey : keys) {

            String capitol = stateCapitol.get(currentKey);
            System.out.println("State: " + currentKey + "Capitol: " + capitol);

        }

    }//end of main
}
