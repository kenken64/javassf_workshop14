package sg.edu.nus.workshop14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.workshop14.model.Contact;

@Service
public class ContactsRedis implements ContactsRepo{
        //private final String CONTACT_CACHE = "CONTACT";


        @Autowired
        RedisTemplate<String, String> redisTemplate;

        @Override
        public void save(final Contact ctc){
                redisTemplate.opsForValue().set(ctc.getId() + "_email", ctc.getEmail());
                redisTemplate.opsForValue().set(ctc.getId() + "_name", ctc.getName());
                redisTemplate.opsForValue().set(ctc.getId() + "_phoneNumber", Integer.toString(ctc.getPhoneNumber()));
        }

        @Override
        public Contact findById(final String contactId){
                String email = redisTemplate.opsForValue().get(contactId + "_email");
                String name = redisTemplate.opsForValue().get(contactId + "_name");
                String phoneNumber = redisTemplate.opsForValue().get(contactId + "_phoneNumber");
                Contact returnCtc  = new Contact(name, email, Integer.parseInt(phoneNumber));
                return returnCtc;
        }

        // @Override
        // public List<Contact> findAll(){
        //         return redisTemplate.opsForList().range(CONTACT_CACHE, 0 , -1);
        // }

        // @Override
        // public void delete(String id){
        //         redisTemplate.opsForValue().getAndDelete(id);
        // }

}
