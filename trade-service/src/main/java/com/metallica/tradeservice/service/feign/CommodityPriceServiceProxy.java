package com.metallica.tradeservice.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("market-price-service")
public interface CommodityPriceServiceProxy {
	
	@GetMapping("/market-price/{metal}")
	public Double fetchCommodityPrice(@PathVariable("metal") String commodityName);

}
