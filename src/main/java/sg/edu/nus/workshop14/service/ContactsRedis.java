package sg.edu.nus.workshop14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.workshop14.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;


@Service
public class ContactsRedis implements RedisRepo{
        private static final Logger logger = LoggerFactory.getLogger(ContactsRedis.class);
        private static final String CONTACT_ENTITY = "contactlist";
        @Autowired
        RedisTemplate<String, Object> redisTemplate;

        @Override
        public void save(final Contact ctc){
                redisTemplate.opsForList().leftPush(CONTACT_ENTITY, ctc.getId());
                redisTemplate.opsForHash().put(CONTACT_ENTITY + "_Map", ctc.getId(), ctc);
        }

        @Override
        public Contact findById(final String contactId){
                Contact result = (Contact)redisTemplate.opsForHash()
                        .get(CONTACT_ENTITY + "_Map", contactId);
                logger.info(" >>> " + result.getEmail());
                return result;
        }

        @Override
        public List<Contact> findAll(int startIndex){
                List<Contact> ctcs = new ArrayList<Contact>();
                List<Object> fromContactList = redisTemplate.opsForList()
                        .range(CONTACT_ENTITY, startIndex, startIndex + 9);
                logger.info(" >>> " + fromContactList);
                for(Object obj: fromContactList){
                        logger.info("obj > " + obj);
                        Contact ctcObj = (Contact)redisTemplate.opsForHash()
                                .get(CONTACT_ENTITY + "_Map", (String)obj);
                        ctcs.add(ctcObj);
                }
                return ctcs;
        }
}
