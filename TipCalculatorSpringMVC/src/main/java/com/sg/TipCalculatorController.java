package com.sg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

//register with spring
@Controller
public class TipCalculatorController {
    @RequestMapping(value = "/tipCalculator", method = RequestMethod.POST)

    public String tipCalculator(HttpServletRequest request, Map<String, Object> model) {

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

        model.put("inputBillAmount", inputBillAmount);
        model.put("inputPercentageAmount", inputPercentageAmount);
        model.put("tipAmount", tipAmount);
        model.put("totalBill", totalBill);

        return "result";

    }
}
