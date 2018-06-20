package com.sg.flooringcalculatorservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet(name = "FlooringCalculatorServlet")
public class FlooringCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BigDecimal laborCost;
        //get input from user from index
        String inputWidth = request.getParameter("width");
        String inputLength = request.getParameter("length");
        String inputCost = request.getParameter("cost");
        //convert
        BigDecimal width = new BigDecimal(inputWidth);
        BigDecimal length = new BigDecimal(inputLength);
        BigDecimal cost = new BigDecimal(inputCost);

        //calculate square feet (area) width * length
        BigDecimal area = width.multiply(length);

        //calculate the hourly rate / 20
        //variable for total time
        BigDecimal totalTimeHour = area.divide(new BigDecimal(20));

        //put total time into minutes
        BigDecimal totalTimeMins = totalTimeHour.multiply(new BigDecimal(60));

        //calculate the number of 15 minute increments
        BigDecimal numIncrements = totalTimeMins.divide(new BigDecimal(15).setScale(0, RoundingMode.UP));

        //multiply into hours (60 / 15 = 4)
        BigDecimal totalTimeCharged = numIncrements.divide(new BigDecimal(4));

        laborCost = totalTimeCharged.multiply(new BigDecimal(86));

        //calculate total cost area + labor cost
        BigDecimal materialCost = cost.multiply(area);
        BigDecimal totalCost = laborCost.add(materialCost);

        //set attributes to make available for result
        //request.setAttribute("width", width);
        //request.setAttribute("length", length);
        request.setAttribute("totalCost", totalCost);
        request.setAttribute("totalTime", totalTimeHour);

        //forward request to result.jsp (what we created)
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        //forward the request//takes in request and response on parameters
        rd.forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
