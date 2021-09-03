package com.spring.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.microservices.currencyconversionservice.bean.CurrencyConversion;

// Remove static host name of service
// @FeignClient(name = "currency-exchange-service", url = "localhost:8000")

// Eureka client comes with load balancer dependencies.
// Inside the Currency Conversion Microservice, there is a load balancer component which is 
// talking to the naming server, finding the instances, and doing automatic load balancing between them.
// And this is what is called client-side load balancing. And this is happening through feign.

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxyClient {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable("from") String from, 
			@PathVariable("to") String to);

}
