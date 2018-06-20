package com.sg.superhero.controller;

import com.sg.model.commandmodel.hero.edithero.HeroEditCommandModel;
import com.sg.model.commandmodel.hero.heropage.HeroPageCreateCommandModel;
import com.sg.model.viewmodel.hero.detailshero.HeroDetailsViewModel;
import com.sg.model.viewmodel.hero.edithero.HeroEditViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroPageViewModel;
import com.sg.webservice.HeroWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/hero")
public class HeroController {

    @Inject
    HeroWebService heroWebService;

    //Display
    @RequestMapping(value = "/homepage")
    public String homePage(Model model) {

        HeroPageViewModel viewModel = heroWebService.retrieveHeroPageViewModel(Integer.MAX_VALUE, 0, 5);

        model.addAttribute("viewModel", viewModel);

        //Manually pass command model to the JSP
        model.addAttribute("commandModel", viewModel.getHeroPageCreateCommandModel());

        return "/hero/homepage";
    }

    //On form submission
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") HeroPageCreateCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()){
            HeroPageViewModel viewModel = heroWebService.retrieveHeroPageViewModel(Integer.MAX_VALUE, 0, 5);

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "/hero/homepage";
        }
        //Save commandModel
        heroWebService.saveHeroPageCreateCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Display
    @RequestMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {

        HeroEditViewModel viewModel = heroWebService.retrieveHeroEditViewModel(id);

        model.addAttribute("viewModel", viewModel);

        model.addAttribute("commandModel", viewModel.getHeroEditCommandModel());

        return "/hero/edit";
    }

    //On form submission
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel")HeroEditCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()) {
            HeroEditViewModel viewModel = heroWebService.retrieveHeroEditViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);

            return "hero/edit";
        }
        heroWebService.saveHeroEditCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Delete - add popup confirmation
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id) {

        heroWebService.removeHeroViewModel(id);

        return "redirect:homepage";
    }

    //Show hero details
    @RequestMapping(value = "/details")
    public String details(@RequestParam Long id, Model model) {

        HeroDetailsViewModel viewModel = heroWebService.retrieveHeroDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "/hero/details";
    }
}
