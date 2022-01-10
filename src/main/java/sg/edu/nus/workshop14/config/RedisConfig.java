package sg.edu.nus.workshop14.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import sg.edu.nus.workshop14.model.Contact;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
        @Bean
        public RedisTemplate<String, Contact> redisTemplate(
                RedisConnectionFactory connectionFactory){
                        RedisTemplate<String, Contact> template = new RedisTemplate();
                        template.setConnectionFactory(connectionFactory);
                        return template;
        }
}