package com.spring.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	// @Retry(name = "default") // default 3 retry attempt
	//@Retry(name = "sample-api", fallbackMethod = "retryFallBackCall")
	//@CircuitBreaker(name = "sample-api", fallbackMethod = "circuitBreakerFallBackCall")
	@RateLimiter(name = "default", fallbackMethod = "exceedRateLimitCall")
	//@Bulkhead(name = "sample-api")
	public String sampleApi() {
		logger.info("Sample api call received");
		//ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
		//		String.class);
		//return forEntity.getBody();
		return "sample-api";
	}
	
	// Different fallback methods for different exceptions
	public String retryFallBackCall(Exception ex) {
		logger.info("Fallback method call");
		return "Fallback response - Retry Fallback: "+ex.getLocalizedMessage();
	}
	
	public String exceedRateLimitCall(Exception ex) {
		logger.info("Fallback method call - exceedRateLimitCall");
		return "Fallback response - Exceed RateLimitCall : "+ex.getLocalizedMessage();
	}
	
	public String circuitBreakerFallBackCall(Exception ex) {
		logger.info("Fallback method call - circuitBreakerFallBackCall");
		return "Fallback response - circuit Breaker : "+ex.getLocalizedMessage();
	}
}
