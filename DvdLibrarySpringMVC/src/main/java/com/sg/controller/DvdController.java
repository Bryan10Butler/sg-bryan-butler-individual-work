package com.sg.controller;

import com.sg.dao.DvdDao;
import com.sg.model.Dvd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


//register controller with spring
@Controller
public class DvdController {

    @Inject
    DvdDao dao;

    public DvdController(DvdDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<Dvd> dvdList = dao.getAllDvds();
        //retrieve all dvds
        model.addAttribute("dvd", dvdList);
        return "index";
    }

    @RequestMapping(value = "/displayDvdPage", method = RequestMethod.GET)
    public String displayCreateDvdForm(@ModelAttribute("Dvd") Dvd dvd, BindingResult brs) {
        return "Dvds";
    }

    @RequestMapping(value = "/displayEditDvdPage", method = RequestMethod.GET)
    public String displayEditDvdForm(HttpServletRequest request, Model model) {
        String dvdIdParameter = request.getParameter("dvdId");
        Long dvdId = Long.parseLong(dvdIdParameter);
        Dvd dvd = dao.getDvd(dvdId);
        model.addAttribute("Dvd", dvd);
        return "EditDvd";
    }

    @RequestMapping(value = "/deleteDvd", method = RequestMethod.GET)
    public String deleteDvd(@ModelAttribute("Dvd") Dvd dvd, BindingResult brs) {
        dao.removeDvd(dvd.getDvdId());
        return "redirect:/";
    }

    @RequestMapping(value = "/createDvd", method = RequestMethod.POST)
    public String displayCreateDvdPage(@ModelAttribute("Dvd") Dvd dvd, BindingResult brs) {
        //pass dvd object to dao to create
        dao.addDvd(dvd);
        return "redirect:/";
    }

    @RequestMapping(value = "/editDvd", method = RequestMethod.POST)
    public String editDvd(@ModelAttribute("Dvd") Dvd dvd, BindingResult brs) {
        dao.editDvd(dvd);
        return "redirect:/";
    }
}