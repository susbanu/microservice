package com.test.lambda.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.lambda.handler.model.WeatherData;

// Handler value: example.HandlerWeatherData
public class HandlerWeatherData implements RequestHandler<WeatherData, WeatherData> {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final Logger logger = LoggerFactory.getLogger(HandlerWeatherData.class);

	@Override
	public WeatherData handleRequest(WeatherData event, Context context) {
		LambdaLogger lambdaLogger = context.getLogger();
		
		lambdaLogger.log("Event as received: "+ event);
		
		// process event
		lambdaLogger.log("EVENT JSON: " + gson.toJson(event));
		lambdaLogger.log("EVENT TYPE: " + event.getClass().toString());
		
		logger.info("INPUT: {}", gson.toJson(event));
		logger.info("ENVIRONMENT VARIABLES: {}", gson.toJson(System.getenv()));
	    logger.info("CONTEXT: {}", gson.toJson(context));
	    
	    logger.warn("AWS Request Id: {}", gson.toJson(context.getAwsRequestId()));
	    
	    // Perform Business Logic
	    if (event != null) {
		    event.setHumidityPct(event.getHumidityPct() - 5);
		    event.setPressureHPa(event.getPressureHPa() - 5);
		    event.setTemperatureK(event.getTemperatureK() - 5);
		    event.setWindKmh(event.getWindKmh() - 5);
	    }
	    
		return event;
	}
}