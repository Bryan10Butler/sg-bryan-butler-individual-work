package com.sg.superhero.controller;

import com.sg.dto.Organization;
import com.sg.model.commandmodel.organization.editorganization.OrganizationEditCommandModel;
import com.sg.model.commandmodel.organization.organizationpage.OrganizationPageCreateCommandModel;
import com.sg.model.viewmodel.organization.detailsorganization.OrganizationDetailsViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageViewModel;
import com.sg.webservice.OrganizationWebService;
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
@RequestMapping(value = "/organization")
public class OrganizationController {

    @Inject
    OrganizationWebService organizationWebService;

    //Display
    @RequestMapping(value = "/homepage")
    public String homePage(Model model) {

        OrganizationPageViewModel viewModel = organizationWebService.retrieveOrganizationPageViewModel(Integer.MAX_VALUE, 0, 5);

        //Display organizations
        model.addAttribute("viewModel", viewModel);

        //Manually pass command model to the JSP
        model.addAttribute("commandModel", viewModel.getOrganizationPageCreateCommandModel());

        return "/organization/homepage";
    }

    //On form submission
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") OrganizationPageCreateCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()) {
            OrganizationPageViewModel viewModel = organizationWebService.retrieveOrganizationPageViewModel(Integer.MAX_VALUE, 0,5);

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);

            return "/organization/homepage";
        }
        //Save commandModel
        organizationWebService.saveOrganizationPageCreateCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Display
    @RequestMapping(value = "/edit")
    public String edit(@RequestParam Long id, Model model) {

        OrganizationEditViewModel viewModel = organizationWebService.retrieveOrganizationEditViewModel(id);

        model.addAttribute("viewModel", viewModel);

        model.addAttribute("commandModel", viewModel.getOrganizationEditCommandModel());

        return "/organization/edit";
    }

    //On form submission
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEdit(@Valid @ModelAttribute("commandModel")OrganizationEditCommandModel commandModel, BindingResult brs, Model model) {

        if(brs.hasErrors()) {
            OrganizationEditViewModel viewModel = organizationWebService.retrieveOrganizationEditViewModel(commandModel.getOrganizationId());

            model.addAttribute("viewModel", viewModel);

            model.addAttribute("commandModel", commandModel);

            return "/organization/edit";
        }

        organizationWebService.saveOrganizationEditCommandModel(commandModel);

        return "redirect:homepage";
    }

    //Delete - add popup confirmation
    @RequestMapping(value = "/delete")
    public String delete(@RequestParam Long id) {

        organizationWebService.removeOrganizationViewModel(id);

        return "redirect:homepage";
    }

    //Show organization details
    @RequestMapping(value = "/details")
    public String details(@RequestParam Long id, Model model) {

        OrganizationDetailsViewModel viewModel = organizationWebService.retrieveOrganizationDetailsViewModel(id);

        model.addAttribute("viewModel", viewModel);

        return "/organization/details";
    }
}
