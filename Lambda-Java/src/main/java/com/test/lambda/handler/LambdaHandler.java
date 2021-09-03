package com.test.lambda.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LambdaHandler implements RequestHandler<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(LambdaHandler.class);
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Override
	public String handleRequest(String input, Context context) {
		
		//LambdaLogger lambdaLogger = context.getLogger();
		//lambdaLogger.log("Call handleRequest");
		
		logger.info("INPUT: {}", gson.toJson(input));
		logger.info("ENVIRONMENT VARIABLES: {}", gson.toJson(System.getenv()));
	    logger.info("CONTEXT: {}", gson.toJson(context));
	    
	    logger.warn("AWS Request Id: {}", gson.toJson(context.getAwsRequestId()));
	    
	    return "200 OK";
	}

}
