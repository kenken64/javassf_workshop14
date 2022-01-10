package sg.edu.nus.workshop14.service;

import java.util.List;

import sg.edu.nus.workshop14.model.Contact;


public interface ContactsRepo {
        public void save(final Contact ctc);
        public Contact findById(final String contactId);
        // public List<Contact> findAll();
        // public void delete(String id);
}
