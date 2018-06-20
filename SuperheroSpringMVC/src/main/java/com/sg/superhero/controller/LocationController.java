package com.sg.superhero.controller;

import com.sg.model.commandmodel.location.editlocation.LocationEditCommandModel;
import com.sg.model.commandmodel.location.locationpage.LocationPageCreateCommandModel;
import com.sg.model.viewmodel.location.detailslocation.LocationDetailsViewModel;
import com.sg.model.viewmodel.location.editlocation.LocationEditViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationPageViewModel;
import com.sg.webservice.DependencyException;
import com.sg.webservice.LocationWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/location")
public class LocationController {

    @Inject
    LocationWebService locationWebService;

    //Display
    @RequestMapping(value = "/homepage")
    public String homePage(Model model) {

        LocationPageViewModel viewModel = locationWebService.retrieveLocationPageViewModel(Integer.MAX_VALUE, 0, 5);

        model.addAttribute("viewModel", viewModel);

        //Manually pass command model to the JSP
        model.addAttribute("commandModel", viewModel.getLocationPageCreateCommandModel());

        return "/location/homepage";
    }

    //On form submission
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") LocationPageCreateCommandModel commandModel, BindingResult brs, Model model) {

        if (brs.hasErrors()) {
            LocationPageViewModel viewModel = locationWebService.retrieveLocationPageViewModel(Integer.MAX_VALUE, 0, 5);

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);
            return "/location/homepage";
        }
        //Save commandModel
        locationWebService.saveLocationPageCreateCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Display
    @RequestMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {

        LocationEditViewModel viewModel = locationWebService.retrieveLocationEditViewModel(id);

        model.addAttribute("viewModel", viewModel);

        model.addAttribute("commandModel", viewModel.getLocationEditCommandModel());

        return "/location/edit";
    }

    //On form submission
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel")LocationEditCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()) {
            LocationEditViewModel viewModel = locationWebService.retrieveLocationEditViewModel(commandModel.getLocationId());

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);

            return "/location/edit";
        }

        locationWebService.saveLocationEditCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Delete - add popup confirmation
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes){

        try {
            locationWebService.removeLocationViewModel(id);
        }catch(DependencyException e) {
            redirectAttributes.addFlashAttribute("message", "dependency");
        }
        return "redirect:homepage";
    }

    //Show location details
    @RequestMapping(value = "/details")
    public String details(@RequestParam Long id, Model model) {

        LocationDetailsViewModel viewModel = locationWebService.retrieveLocationDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "/location/details";
    }
}
