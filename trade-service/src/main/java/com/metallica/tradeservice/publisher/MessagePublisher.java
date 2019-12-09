package com.metallica.tradeservice.publisher;

import com.metallica.tradeservice.bean.Trade;

public interface MessagePublisher {
	 void publish(final Trade message);
}
