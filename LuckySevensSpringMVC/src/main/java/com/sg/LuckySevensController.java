package com.sg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

@Controller
public class LuckySevensController {
    @RequestMapping(value = "luckySevens", method = RequestMethod.POST)

    public String luckySevens(HttpServletRequest request, Map<String, Object> model) {

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
        model.put("maxRollCount", maxRollCount);
        model.put("rollCount", rollCount);
        model.put("userBet", userBet);
        model.put("maxMoney", maxMoney);
        return "result";

    }
}
