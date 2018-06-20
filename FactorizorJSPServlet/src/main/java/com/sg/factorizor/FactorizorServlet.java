package com.sg.factorizor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FactorizorServlet")
public class FactorizorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // A List to hold our factors
        List<Integer> factorList = new ArrayList<>();
        // A sum to help us calculate whether or not the number
        // is perfect
        int factorSum = 0;
        boolean isPrime = false;
        boolean isPerfect = false;
        // Get the input from the user and convert it to an int
        // Get the value that was submitted in the index.jsp form
        //numberToFactor on our getParam is the name we gave the input on our index.jsp
        //we are storing as a string
        String input = request.getParameter("numberToFactor");
        //turn into int
        int numberToFactor = Integer.parseInt(input);

        // Factor the number - there are more efficient ways
        // of doing this!
        for (int i = 1; i < numberToFactor; i++) {
            //check to see if number to factor is evenly divisible by i
            //if it is that is a factor
            //if not its not a factor
            if (numberToFactor % i == 0) {
                // i goes into numberToFactor evenly so it
                // is a factor, add it to the list and add
                // it to the sum
                factorList.add(i);
                factorSum += i;
            }
        }

        //perfect, prime, or both
        if (factorSum == numberToFactor) {
            isPerfect = true;
        }

        if (factorSum == 1) {
            isPrime = true;
        }

        // Set all the results as attributes on the request to they
        // are available to result.jsp
        // Set numberToFactor as an attribute on the request so that
        // it is available to result.jsp
        //key is "numberToFactor" and value is numberToFactor
        request.setAttribute("numberToFactor", numberToFactor);
        //set factor list
        request.setAttribute("factors", factorList);
        request.setAttribute("isPrime", isPrime);
        request.setAttribute("isPerfect", isPerfect);
        // Get the Request Dispatcher for result.jsp and forward the
        // request to result.jsp (what we created)
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        //forward the request//takes in request and response on parameters
        rd.forward(request, response);
    }

    //this will tell our server to respond with the index.jsp
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

}
