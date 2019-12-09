package com.metallica.tradeservice.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metallica.tradeservice.bean.Trade;
import com.metallica.tradeservice.config.RabbitMQConfiguration;

@Service
public class RabbitMQMessagePublisher implements MessagePublisher{
	Logger logger = LoggerFactory.getLogger(RabbitMQMessagePublisher.class);
	@Autowired
	private  RabbitTemplate rabbitTemplate;

	@Override
	public void publish(Trade message) {
		logger.debug("publishTrade: Publishing trade "+message);
	        rabbitTemplate.convertAndSend(RabbitMQConfiguration.EXCHANGE_NAME, RabbitMQConfiguration.ROUTING_KEY, message);
	}

}
