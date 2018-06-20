package com.sg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

//register this component with spring
@Controller
public class FlooringCalculatorController {
    @RequestMapping(value = "/FlooringCalculatorController", method = RequestMethod.POST)

    public String flooringCalculator(HttpServletRequest request, Map<String, Object> model) {

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

        //make available to the result
        model.put("totalCost", totalCost);
        model.put("totalTime", totalTimeHour);
        return "result";
    }

}
