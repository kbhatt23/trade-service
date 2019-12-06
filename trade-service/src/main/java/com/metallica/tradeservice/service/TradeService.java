package com.metallica.tradeservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metallica.tradeservice.bean.Trade;
import com.metallica.tradeservice.bean.dao.TradeEntity;
import com.metallica.tradeservice.exception.TradeServiceException;
import com.metallica.tradeservice.repository.TradeRepository;

@Service
public class TradeService {
	@Autowired
	private TradeRepository tradeRepository;
	
	public TradeEntity createTrade(Trade trade) {
		TradeEntity tradeEntity = new TradeEntity();
		tradeEntity.setCommodity(trade.getCommodity());
		tradeEntity.setCommodityUnitPrice(trade.getCommodityUnitPrice());
		tradeEntity.setCounterParty(trade.getCounterParty());
		tradeEntity.setPrice(trade.getPrice());
		tradeEntity.setQuantity(trade.getQuantity());
		tradeEntity.setSide(trade.getSide());
		tradeEntity.setTradeDate(trade.getTradeDate());
		tradeEntity.setTradeStatus(trade.getTradeStatus());
		
		return tradeRepository.save(tradeEntity);
		
	}
	
	public TradeEntity fetchTradeEntity(Long tradeId) {
		return tradeRepository.findById(tradeId).orElseThrow(() -> new TradeServiceException(tradeId+ " not found")) ;
	}
}
