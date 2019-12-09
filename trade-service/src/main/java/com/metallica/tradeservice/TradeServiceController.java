package com.metallica.tradeservice;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.metallica.tradeservice.bean.Trade;
import com.metallica.tradeservice.bean.TradeFunctions;
import com.metallica.tradeservice.bean.dao.TradeEntity;
import com.metallica.tradeservice.publisher.RabbitMQMessagePublisher;
import com.metallica.tradeservice.service.TradeService;
import com.metallica.tradeservice.service.feign.CommodityPriceServiceProxy;

@RestController
@RequestMapping("/trades")
public class TradeServiceController {
	
	@Autowired
	private TradeService tradeService;
	
	/*
	 * @Autowired private TradePublisherService tradePublisherService;
	 */
	
	@Autowired
	private RabbitMQMessagePublisher rabbitMQMessagePublisher;
	
	@Autowired
	private CommodityPriceServiceProxy commodityPriceServiceProxy;
	
	Logger logger = LoggerFactory.getLogger(TradeServiceController.class);
	
	@PostMapping()
	public ResponseEntity<Object> createTrade(@RequestBody Trade trade){
		//calling price web service
		//modifying total price using unit price form live service
		
		logger.debug("createTrade: creating trade "+trade);
		double unitPrice = commodityPriceServiceProxy.fetchCommodityPrice(trade.getCommodity());
		logger.debug("createTrade: unit price fetched "+unitPrice);
		TradeFunctions.manipulateTradeEntity.apply(trade, unitPrice);
		//not publishing DB entry as it can have sensitive data
		//tradePublisherService.publishTrade(trade);
		rabbitMQMessagePublisher.publish(trade);
		
		
		TradeEntity tradeEntity = tradeService.createTrade(trade);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{tradeId}")
					.buildAndExpand(tradeEntity.getId()).toUri();
		return  ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/{tradeId}")
	public TradeEntity fetchTrade(@PathVariable Long tradeId ){
		
		return tradeService.fetchTradeEntity(tradeId);
	}
	{
		
	}
	

}
