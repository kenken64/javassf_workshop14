package sg.edu.nus.workshop14.service;

import sg.edu.nus.workshop14.model.Contact;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepo {
        public void save(final Contact ctc);
        public Contact findById(final String contactId);
}
