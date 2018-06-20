package com.sg.controller;

import com.sg.dao.ContactListDao;
import com.sg.model.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//register this controller with spring MVC
@Controller
public class ContactController {

    ContactListDao dao;

    //hand a DAO instance to a controller
    //@Inject annotation tells the Spring DI container that
    //we would like it to inject an object of type ContactListDao into
    //our controller when it is constructed
    //DI framework goes through all the beans that it knows about,
    //finds one of type ContactListDao, and then hands it to the
    //constructor when our ContactController is instantiated
    @Inject
    public ContactController(ContactListDao dao) {
        this.dao = dao;
    }

    //end point to take requests /displayContactsPage
    @RequestMapping(value="/displayContactsPage", method= RequestMethod.GET)
    public String displayContactsPage(Model model) {

        //get all the contacts from the DAO
        List<Contact> contactList = dao.getAllContacts();

        //put the list of contacts on the model
        model.addAttribute("contactList", contactList);

        //logical view name to match to contact.jsp
        return "contacts";
    }

    //take parameter of type http servlet request to get the values submitted on the form
    @RequestMapping(value= "/createContact", method = RequestMethod.POST)
    public String createContact(HttpServletRequest request) {
        //grab the incoming values from the form and create a  new contact object
        Contact contact = new Contact();
        contact.setFirstName(request.getParameter("firstName"));
        contact.setLastName(request.getParameter("lastName"));
        contact.setCompany(request.getParameter("company"));
        contact.setPhone(request.getParameter("phone"));
        contact.setEmail(request.getParameter("email"));

        //persist the new Contact
        dao.addContact(contact);

        // we don't want to forward to a View component - we want to
        // redirect to the endpoint that displays the Contacts Page
        // so it can display the new Contact in the table.
        return "redirect:displayContactsPage";
    }

    //URL pattern /displayContactDetails to this method,
    //Because this request will come as a result of the user clicking on a link
    @RequestMapping(value = "/displayContactDetails", method = RequestMethod.GET)
    public String displayContactDetails(HttpServletRequest request, Model model) {
        //Get the contactId from the HTTP request
        String contactIdParameter = request.getParameter("contactId");
        int contactId = Integer.parseInt(contactIdParameter);

        //Retrieve the Contact associated with the id from the DAO
        Contact contact = dao.getContactById(contactId);

        //put the contact into the model
        model.addAttribute("contact", contact);

        //Return the logical name of the view component
        // that will display the Contact details
        return "contactDetails";
    }

    //Maps the /deleteContact URL pattern to this method -
    //because this request is sent as a result of clicking on a link, the HTTP method is GET.
    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public String deleteContact(HttpServletRequest request) {
        String contactIdParameter = request.getParameter("contactId");
        long contactId = Long.parseLong(contactIdParameter);
        dao.removeContact(contactId);
        return "redirect:displayContactsPage";
    }

    @RequestMapping(value = "/displayEditContactForm", method = RequestMethod.GET)
    public String displayEditContactForm(HttpServletRequest request, Model model) {
        String contactIdParameter = request.getParameter("contactId");
        long contactId = Long.parseLong(contactIdParameter);
        Contact contact = dao.getContactById(contactId);
        model.addAttribute("contact", contact);
        return "editContactForm";
    }
    @RequestMapping(value = "/editContact", method = RequestMethod.POST)
    public String editContact(@ModelAttribute("contact") Contact contact) {
        dao.updateContact(contact);

        return "redirect:displayContactsPage";
    }


}
