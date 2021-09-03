package com.test.lambda.handler.event;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.LexEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.lambda.handler.util.Util;

// Handler value: example.HandlerLex
public class HandlerLex implements RequestHandler<LexEvent, String>{
  Gson gson = new GsonBuilder().setPrettyPrinting().create();
  @Override
  public String handleRequest(LexEvent event, Context context)
  {
    LambdaLogger logger = context.getLogger();
    String response = new String("200 OK");
    // log execution details
    Util.logEnvironment(event, context, gson);
    return response;
  }
}