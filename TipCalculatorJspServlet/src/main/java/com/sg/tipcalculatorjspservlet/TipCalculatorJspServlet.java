package com.sg.tipcalculatorjspservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "TipCalculatorJspServlet")
public class TipCalculatorJspServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get user input
        String billAmount = request.getParameter("amountOnBill");
        //get user input
        String percentageAmount = request.getParameter("tipPercentage");

        //variables to use
        BigDecimal inputBillAmount = new BigDecimal(billAmount);
        BigDecimal inputPercentageAmount = new BigDecimal(percentageAmount);

        //calculate tip amount (total bill amount * tip percentage)
        BigDecimal tipAmount = inputBillAmount.multiply(inputPercentageAmount);

        //calculate total bill (total bill amount + tip amount)
        BigDecimal totalBill = tipAmount.add(inputBillAmount);

        //setup to send to result
        request.setAttribute("inputBillAmount", inputBillAmount);
        request.setAttribute("inputPercentageAmount", inputPercentageAmount);
        request.setAttribute("tipAmount", tipAmount);
        request.setAttribute("totalBill", totalBill);

        //dispatcher
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);

    }
}
