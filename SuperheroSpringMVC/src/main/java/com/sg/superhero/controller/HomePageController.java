package com.sg.superhero.controller;

import com.sg.model.viewmodel.homepage.HomePageViewModel;
import com.sg.webservice.HomePageWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
public class HomePageController {

    @Inject
    HomePageWebService homePageWebService;

    //On page load
    @RequestMapping(value = "/")
    public String list(Model model) {
        HomePageViewModel homePageViewModel = homePageWebService.retrieveHomePageViewModel(10);

        model.addAttribute("homePageViewModel", homePageViewModel);

        return "index";
    }
}
