/*
 * package com.metallica.tradeservice.service;
 * 
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.metallica.tradeservice.bean.Trade; import
 * com.metallica.tradeservice.publisher.RedisMessagePublisher;
 * 
 * @Service public class TradePublisherService { Logger logger =
 * LoggerFactory.getLogger(TradePublisherService.class);
 * 
 * 
 * @Autowired private RedisMessagePublisher publisher;
 * 
 * public void publishTrade(Trade trade) { //not publishing DB entry as id can
 * be sensitive logger.debug("publishTrade: Publishing trade "+trade);
 * 
 * publisher.publish(trade); } }
 */