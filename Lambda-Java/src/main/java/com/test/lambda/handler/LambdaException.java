package com.test.lambda.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.lambda.handler.exception.InputLengthException;

public class LambdaException implements RequestHandler<List<Integer>, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(LambdaException.class);
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public Integer handleRequest(List<Integer> event, Context context) {

		LambdaLogger lambdaLogger = context.getLogger();
		lambdaLogger.log("Call handleRequest");

		logger.info("INPUT: {}", gson.toJson(event));

		if (event.size() != 2) {
			throw new InputLengthException("Input must be an array that contains 2 numbers.");
		}
		int numerator = event.get(0);
	    int denominator = event.get(1);
	    
	    logger.debug("EVENT: {0}", gson.toJson(event));
	    logger.debug("EVENT TYPE: {0}", event.getClass().toString());
	    return numerator/denominator;
	}
}
