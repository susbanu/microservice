package com.test.lambda.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.test.lambda.handler.model.WeatherData;

class InvokeHandlerTest {

	private static final Logger logger = LoggerFactory.getLogger(InvokeHandlerTest.class);

	  @Test
	  @DisplayName("Testing Lambda Handler")
	  @Disabled(value = "Testing only Weather Data Test case")
	  void invokeTest() {
	    logger.info("Invoke TEST");
	    String eventInput = "This is input parameter of Lambda";
	    Context context = new TestContext();
	    LambdaHandler handler = new LambdaHandler();
	    String result = handler.handleRequest(eventInput, context);
	    assertTrue(result.contains("200 OK"));
	  }
	  
	  @Test
	  @DisplayName("Testing Weather Data handler")
	  void invokeWeatherDataTest() {
		  logger.info("Invoke Weather Data TEST");
		  WeatherData weatherData = new WeatherData();
		  weatherData.setHumidityPct(10d);
		  weatherData.setPressureHPa(210);
		  weatherData.setTemperatureK(30);
		  weatherData.setWindKmh(70);
		  
		  HandlerWeatherData handlerWeatherData = new HandlerWeatherData();
		  Context context = new TestContext();
		  WeatherData responseWeatherData = handlerWeatherData.handleRequest(weatherData, context);
		  assertEquals(205, responseWeatherData.getPressureHPa().intValue());
		  
	  }
}
