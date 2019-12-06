package com.metallica.tradeservice.bean;

import java.util.Date;
import java.util.function.BiFunction;

public class TradeFunctions {

	public static BiFunction<Trade, Double, Trade> manipulateTradeEntity
				= (trade,unitPrice) -> {
					trade.setPrice(trade.getQuantity()*unitPrice);
					trade.setTradeDate(new Date());
					trade.setCommodityUnitPrice(unitPrice);
					trade.setTradeStatus(TradeStatus.OPEN);
					return trade;
				};
}
