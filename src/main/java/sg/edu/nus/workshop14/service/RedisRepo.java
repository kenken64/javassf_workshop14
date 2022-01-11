package sg.edu.nus.workshop14.service;

import sg.edu.nus.workshop14.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedisRepo {
        public void save(final Contact ctc);
        public Contact findById(final String contactId);
        public List<Contact> findAll(int startIndex);
}
