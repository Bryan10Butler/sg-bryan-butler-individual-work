package com.sg.luckysevensjspservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

@WebServlet(name = "LuckySevensJspServlet")
public class LuckySevensJspServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get user input and convert
        BigDecimal userBet = new BigDecimal(request.getParameter("userBet"));
        //variables
        BigDecimal maxMoney = userBet;
        BigDecimal currentMoney = userBet;
        int maxRollCount = 0;
        int rollCount = 0;

        while (!currentMoney.equals(new BigDecimal(0))) {
        //Dice
        Random rNUm = new Random();
        int di1 = rNUm.nextInt(6) + 1;
        int di2 = rNUm.nextInt(6) + 1;
        int sum = di1 + di2;
            if (sum == 7){
                currentMoney = currentMoney.add(new BigDecimal(4));
                if(currentMoney.compareTo(maxMoney) > 0) {
                    maxMoney = currentMoney;
                    maxRollCount = rollCount;
                }
            }
            else {
                currentMoney = currentMoney.subtract(new BigDecimal(1));
            }
            rollCount ++;
        }


        request.setAttribute("maxRollCount", maxRollCount);
        request.setAttribute("rollCount", rollCount);
        request.setAttribute("userBet", userBet);
        request.setAttribute("maxMoney", maxMoney);

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
