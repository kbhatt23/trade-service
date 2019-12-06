package com.metallica.tradeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metallica.tradeservice.bean.dao.TradeEntity;

public interface TradeRepository extends JpaRepository<TradeEntity, Long>{
	
	

}
