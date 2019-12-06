package com.metallica.tradeservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.metallica.tradeservice.publisher.MessagePublisher;
import com.metallica.tradeservice.publisher.RedisMessagePublisher;

@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfig {
	 @Bean
	    JedisConnectionFactory jedisConnectionFactory() {
	        return new JedisConnectionFactory();
	    }

	    @Bean
	    public RedisTemplate<String, Object> redisTemplate() {
	        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	        template.setConnectionFactory(jedisConnectionFactory());
	       // template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
	        template.setKeySerializer(new StringRedisSerializer());                                           
	        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
	        return template;
	    }


	    @Bean
	    MessagePublisher redisPublisher() {
	        return new RedisMessagePublisher(redisTemplate(), topic());
	    }

	    @Bean
	    ChannelTopic topic() {
	        return new ChannelTopic("pubsub:queue");
	    }
}
