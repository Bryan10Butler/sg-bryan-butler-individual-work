package com.sg.superhero.controller;

import com.sg.model.commandmodel.power.editpower.PowerEditCommandModel;
import com.sg.model.commandmodel.power.powerpage.PowerPageCreateCommandModel;
import com.sg.model.viewmodel.power.detailspower.PowerDetailsViewModel;
import com.sg.model.viewmodel.power.editpower.PowerEditViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerPageViewModel;
import com.sg.webservice.PowerWebService;
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
@RequestMapping(value = "/power")
public class PowerController {

    @Inject
    PowerWebService powerWebService;

    //Display
    @RequestMapping(value = "/homepage")
    public String homePage(Model model) {

        PowerPageViewModel viewModel = powerWebService.retrievePowerPageViewModel(Integer.MAX_VALUE, 0, 5);

        //Display powers
        model.addAttribute("viewModel", viewModel);

        //Manually pass command model to the JSP
        model.addAttribute("commandModel", viewModel.getPowerPageCreateCommandModel());

        return "/power/homepage";
    }

    //On form submission
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") PowerPageCreateCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()) {
            PowerPageViewModel viewModel = powerWebService.retrievePowerPageViewModel(Integer.MAX_VALUE, 0, 5);

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);

            return "/power/homepage";
        }

        //Save commandModel
        powerWebService.savePowerPageCreateCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Display
    @RequestMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {

        PowerEditViewModel viewModel = powerWebService.retrievePowerEditViewModel(id);

        model.addAttribute("viewModel", viewModel);

        model.addAttribute("commandModel", viewModel.getPowerEditCommandModel());

        return "/power/edit";
    }

    //On form submission
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel")PowerEditCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()) {
            PowerEditViewModel viewModel = powerWebService.retrievePowerEditViewModel(commandModel.getPowerId());

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);

            return "/power/edit";
        }

        powerWebService.savePowerEditCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Delete - add popup confirmation
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id) {

        powerWebService.removePowerViewModel(id);

        return "redirect:homepage";
    }

    //Show power details
    @RequestMapping(value = "/details")
    public String details(@RequestParam Long id, Model model) {

        PowerDetailsViewModel viewModel = powerWebService.retrievePowerDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "/power/details";
    }
}
