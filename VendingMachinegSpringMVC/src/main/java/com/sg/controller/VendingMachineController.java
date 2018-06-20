package com.sg.controller;

import com.sg.dao.VendingMachinePersistenceException;
import com.sg.service.VendingMachineInsufficientFundsException;
import com.sg.service.VendingMachineItemUnavailableException;
import com.sg.service.VendingMachineServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

//controller annotation allows spring to recognize controller component
@Controller
public class VendingMachineController {

    VendingMachineServiceLayer service;

    //inject annotation on controller depends on the service
    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    //RequestMapping compare value with incoming request URL pattern
    //if match, execute method
    //landing page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    //parameter of model to put values into model
    //parameter HttpServletRequest to get information from user actions
    public String index(Model model, HttpServletRequest request) throws VendingMachinePersistenceException {

        //put into into Model
        model.addAttribute("moneyToBeAdded", service.returnBalance());
        model.addAttribute("itemList", service.retrieveAllVendingMachineItems());
        model.addAttribute("change", service.returnChange());
        model.addAttribute("selectedItemId", request.getParameter("selectedItemId"));
        //model.addAttribute("itemUnavailable", request.getParameter("itemUnavailable"));
        //model.addAttribute("insufficientFunds", request.getParameter("insufficientFunds"));
        //model.addAttribute("thankYou", request.getParameter("thankYou"));

        return "index";
    }

    //RequestMapping compare value with incoming request URL pattern
    //if match, execute method
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    //parameter HttpServletRequest to get information from user actions
    public String itemId(HttpServletRequest request) {
        //request to get selectedItemId and store as selectedItemId
        String selectedItemId = request.getParameter("selectedItemId");
        //redirect to landing page
        return "redirect:/?selectedItemId=" + selectedItemId;
    }

    //RequestMapping compare value with incoming request URL pattern
    //if match, execute method
    @RequestMapping(value = "/moneyButtons/{amount}", method = RequestMethod.GET)
    //@PathVariable - TESTING
    public String addMoney(@PathVariable String amount, HttpServletRequest request) {

        //request selectedItemId store as selectedItemId
        String selectedItemId = request.getParameter("selectedItemId");
        //pass amount (Dollar, Quarter, Dime, Nickel) as amount
        service.addMoneyCoins(amount);
        //redirect to landing page
        return "redirect:/?selectedItemId=" + selectedItemId;
    }

    //RequestMapping compare value with incoming request URL pattern
    //if match, execute method
    @RequestMapping(value = "/makePurchase", method = RequestMethod.GET)
    //parameter of model to put values into model
    //parameter HttpServletRequest to get information from user actions
    public String makePurchase(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws VendingMachinePersistenceException {

        //request item id and store as itemToBePurchased
        String selectedItemId = request.getParameter("selectedItemId");
        long itemId = Long.parseLong(selectedItemId);
        try {
            //pass itemToBePurchased to service and add value to model
            model.addAttribute(service.purchaseItem(itemId));

            //store thank you as successfulPurchase
            String successfulPurchase = "Thank you!!!";
            //add to model as key value pair
            model.addAttribute("thankYou", successfulPurchase);

        } catch (VendingMachineItemUnavailableException e) {
            //system.out.println(e.getMessage());
            //add key value pair (e.getMessage holds message)
            //model.addAttribute("itemUnavailable", e.getMessage());
            redirectAttributes.addFlashAttribute("message", "out.of.stock");

        } catch (VendingMachineInsufficientFundsException e) {
            //System.out.println(e.getMessage());
            //add key value pair (e.getMessage holds message)
            //model.addAttribute("insufficientFunds", e.getMessage());
            redirectAttributes.addFlashAttribute("message", "insufficient.funds");

        }
        return "redirect:/?selectedItemId=" + selectedItemId;
    }

    //RequestMapping compare value with incoming request URL pattern
    //if match, execute method
    @RequestMapping(value = "/getChange", method = RequestMethod.GET)
    public String getChange() {
        //call to service to convert remaining money in machine to change for user
        service.convertDollarsToChange();
        //redirect to landing page
        return "redirect:/";
    }

}
