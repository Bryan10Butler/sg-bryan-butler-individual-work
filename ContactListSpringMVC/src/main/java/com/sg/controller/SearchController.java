package com.sg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//register this controller with spring mvc
@Controller
public class SearchController {

    //end point to take requests /displaySearchPage
    @RequestMapping(value="/displaySearchPage", method= RequestMethod.GET)
    public String displaySearchPage(){

        //logical view name to map to jsp
        return "search";
    }
}
