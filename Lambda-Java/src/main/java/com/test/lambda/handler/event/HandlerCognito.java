package com.test.lambda.handler.event;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.lambda.handler.util.Util;

// Handler value: example.HandlerCognito
public class HandlerCognito implements RequestHandler<CognitoEvent, String>{
  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  @Override
  public String handleRequest(CognitoEvent event, Context context)
  {
    LambdaLogger logger = context.getLogger();
    String response = new String("200 OK");
    // log execution details
    Util.logEnvironment(event, context, gson);
    return response;
  }
}