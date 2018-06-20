package com.sg.superhero.controller;

import com.sg.model.commandmodel.sighting.editsighting.SightingEditCommandModel;
import com.sg.model.commandmodel.sighting.sightingpage.SightingPageCreateCommandModel;
import com.sg.model.viewmodel.sighting.detailsighting.SightingDetailsViewModel;
import com.sg.model.viewmodel.sighting.editsighting.SightingEditViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageViewModel;
import com.sg.webservice.SightingWebService;
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
@RequestMapping(value = "/sighting")
public class SightingController {

    @Inject
    SightingWebService sightingWebService;

    //Display
    @RequestMapping(value = "/homepage")
    public String homePage(Model model) {

        SightingPageViewModel viewModel = sightingWebService.retrieveSightingPageViewModel(Integer.MAX_VALUE, 0, 5);

        model.addAttribute("viewModel", viewModel);

        //Manually pass command model to the JSP
        model.addAttribute("commandModel", viewModel.getSightingPageCreateCommandModel());

        return "/sighting/homepage";
    }

    //On form submission
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") SightingPageCreateCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()) {

            SightingPageViewModel viewModel = sightingWebService.retrieveSightingPageViewModel(Integer.MAX_VALUE, 0, 5);

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);

            return "/sighting/homepage";

        }

        //Save commandModel
        sightingWebService.saveSightingPageCreateCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Display
    @RequestMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {

        SightingEditViewModel viewModel = sightingWebService.retrieveSightingEditViewModel(id);

        model.addAttribute("viewModel", viewModel);

        model.addAttribute("commandModel", viewModel.getSightingEditCommandModel());

        return "/sighting/edit";
    }

    //On form submission
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel")SightingEditCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()) {
            SightingEditViewModel viewModel = sightingWebService.retrieveSightingEditViewModel(commandModel.getSightingId());

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);

            return "/sighting/edit";
        }

        sightingWebService.saveSightingEditCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Delete - add popup confirmation
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id) {

        sightingWebService.removeSightingViewModel(id);

        return "redirect:homepage";
    }

    //Show sighting details
    @RequestMapping(value = "/details")
    public String details(@RequestParam Long id, Model model) {

        SightingDetailsViewModel viewModel = sightingWebService.retrieveSightingDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "/sighting/details";
    }
}
