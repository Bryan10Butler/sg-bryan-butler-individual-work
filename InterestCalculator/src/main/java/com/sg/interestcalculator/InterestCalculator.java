package com.sg.interestcalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InterestCalculator")
public class InterestCalculator extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        Integer years = Integer.parseInt(request.getParameter("years"));
        BigDecimal startingBalance = new BigDecimal(request.getParameter("startingBalance"));
        BigDecimal interestRate = new BigDecimal(request.getParameter("interestRate"));

        BigDecimal quarterlyInterestRate = interestRate.divide(new BigDecimal(4));




        List<Year> yearList = getYears(years, startingBalance, quarterlyInterestRate);



        request.setAttribute("yearList", yearList);


        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);



    }


    public List<Year> getYears(int years, BigDecimal startingBalance, BigDecimal quarterlyInterestRate) {

        List<Year> yearList = new ArrayList();

        int index = 0;
        for (int i = 1; i <= years; i++) {

            Year year = new Year();
            year.setYear(i);
            year.setStartingBalance(startingBalance);



            BigDecimal endBalance = quarterlyInterestRate.divide(new BigDecimal(100))
                    .add(new BigDecimal(1))
                    .multiply(startingBalance);

            BigDecimal interestEarned = endBalance.subtract(startingBalance);


            year.setInterestEarned(interestEarned);
            year.setEndingBalance(endBalance);


            startingBalance = endBalance;

            yearList.add(year);



            index++;
        }


        for (Year year : yearList) {
            year.setStartingBalance(year.getStartingBalance().setScale(2, RoundingMode.HALF_UP));
            year.setEndingBalance(year.getEndingBalance().setScale(2, RoundingMode.HALF_UP));
            year.setInterestEarned(year.getInterestEarned().setScale(2, RoundingMode.HALF_UP));
        }

        return yearList;
    }
}


