package sg.edu.nus.workshop14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.edu.nus.workshop14.model.Contact;
import sg.edu.nus.workshop14.service.ContactsRedis;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class AddressbookController {
    private static final Logger logger = LoggerFactory.getLogger(AddressbookController.class);
    
    @Autowired
    ContactsRedis service;
    
    @GetMapping("/")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/getContact/{contactId}")
    public String getContact(Model model, @PathVariable(value="contactId") String contactId) {
        logger.info("contactId > " + contactId);
        Contact ctc = service.findById(contactId);
        logger.info("getId > " + ctc.getId());
        logger.info("getEmail > " + ctc.getEmail());
        
        model.addAttribute("contact", ctc);
        return "showContact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute Contact contact, Model model) {
        logger.info("Name > " + contact.getName());
        logger.info("Email > " + contact.getEmail());
        logger.info("Phone Number > " + contact.getPhoneNumber());
        Contact persistToRedisCtc = new Contact(
                contact.getName(),
                contact.getEmail(),
                contact.getPhoneNumber()
        );
        service.save(persistToRedisCtc);
        model.addAttribute("contact", persistToRedisCtc);
        return "showContact";
    }
}