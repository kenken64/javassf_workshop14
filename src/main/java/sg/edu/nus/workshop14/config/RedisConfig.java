package sg.edu.nus.workshop14.config;

import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

@Configuration
public class RedisConfig {
        private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);
    
        @Value("${spring.redis.host}") 
        private String redisHost;

        @Value("${spring.redis.port}") 
        private Optional<Integer> redisPort;

        // @Value("${spring.redis.password}") 
        // private String redisPassword;
        
        @Bean
        @Scope("singleton")
        public RedisTemplate<String, Object> redisTemplate(){
                final RedisStandaloneConfiguration config 
                        = new RedisStandaloneConfiguration();
                logger.info("redis host port> " + 
                        redisHost + ' ' + redisPort.get());
                 
                config.setHostName(redisHost);
                config.setPort(redisPort.get());
                //config.setPassword(redisPassword);

                final JedisClientConfiguration jedisClient = JedisClientConfiguration
                                .builder().build();
                final JedisConnectionFactory jedisFac = 
                        new JedisConnectionFactory(config, jedisClient); 
                jedisFac.afterPropertiesSet();
                        
                RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
                template.setConnectionFactory(jedisFac);
                template.setKeySerializer(new StringRedisSerializer());
                template.setHashKeySerializer(new StringRedisSerializer());

                RedisSerializer<Object> serializer 
                        = new JdkSerializationRedisSerializer(getClass().getClassLoader());
                template.setValueSerializer(
                        serializer
                );
                template.setHashValueSerializer(serializer);
                
                return template;
        }
}