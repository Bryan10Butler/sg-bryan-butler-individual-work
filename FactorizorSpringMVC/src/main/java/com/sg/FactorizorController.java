package com.sg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//controller annotation (need to import)
//registers this component with spring
@Controller
public class FactorizorController {

    //method to handle a particular set of requests
    //pass the http servlet request
    //this is registered with spring due to using @controller
    //handle any requests to factorNumber that are of type method.post
    //any post to factorNumber get handled here
    @RequestMapping(value="/factorNumber",
            method=RequestMethod.POST)
    public String factorNumber(HttpServletRequest request,
                               Map<String, Object> model) {
// A List to hold our factors
        List<Integer> factorList = new ArrayList<>();
        // A sum to help us calculate whether or not the number
        // is perfect
        int factorSum = 0;
        boolean isPrime = false;
        boolean isPerfect = false;
        // Get the input from the user and convert it to an int
        String input = request.getParameter("numberToFactor");
        int numberToFactor = Integer.parseInt(input);

        // Factor the number - there are more efficient ways
        // of doing this!
        for (int i = 1; i < numberToFactor; i++) {
            if (numberToFactor % i == 0) {
                // i goes into numberToFactor evenly so it
                // is a factor, add it to the list and add
                // it to the sum
                factorList.add(i);
                factorSum += i;
            }
        }

        if (factorSum == numberToFactor) {
            isPerfect = true;
        }

        if (factorSum == 1) {
            isPrime = true;
        }

        // Set all the results in the model Map so they
        // are available to result.jsp
        //using model map to make available for results
        model.put("numberToFactor", numberToFactor);
        model.put("factors", factorList);
        model.put("isPrime", isPrime);
        model.put("isPerfect", isPerfect);
        return "results";
    }

}
