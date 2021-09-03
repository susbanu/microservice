package com.test.lambda.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.lambda.handler.exception.UnhandledServiceException;
import com.test.lambda.handler.model.Request;
import com.test.lambda.handler.model.Request.HttpMethod;
import com.test.lambda.handler.model.Response;
import com.test.lambda.handler.model.ResponseCodes;
import com.test.lambda.handler.model.WeatherData;

public class ALBLambdaHandler implements RequestHandler<Map<String, Object>, Map<String, Object>> {

	private static final Logger logger = LoggerFactory.getLogger(ALBLambdaHandler.class);
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private Pattern actionRegex = Pattern.compile("/api/(.*)");
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public Map<String, Object> handleRequest(Map<String, Object> albRequest, Context context) {

		LambdaLogger lambdaLogger = context.getLogger();
		lambdaLogger.log("Call handleRequest, ALB Request: " + albRequest);

		logger.info("INPUT: {}", gson.toJson(albRequest));
		lambdaLogger.log("ENVIRONMENT VARIABLES: {}"+ gson.toJson(System.getenv()));
		logger.info("CONTEXT: {}", gson.toJson(context));

		logger.warn("AWS Request Id: {}", gson.toJson(context.getAwsRequestId()));

		try {
			// Request request = extractRequest(albRequest);
			Request request = new Request("GET", null, null, albRequest);
			Response response = dispatch(request);
			return buildResponseMap(response);
		} catch (IllegalArgumentException ex) {
			logger.warn("invalid client request: {}", ex.getMessage());
			return buildResponseMap(new Response(400));
		} catch (UnhandledServiceException ignored) {
			return buildResponseMap(new Response(500));
		} catch (Exception ex) {
			logger.error("unexpected exception during request processing", ex);
			return buildResponseMap(new Response(500));
		}
	}

	/**
	 * Constructs the request object by extracting information from the map provided
	 * by API Gateway.
	 *
	 * @throws IllegalArgumentException if unable to process the strings; should be
	 *                                  transformed to a 400 error.
	 */
	/*
	 * private Request extractRequest(Map<String,Object> albRequest) { // I'm
	 * retrieving the raw data all at once, so that I don't sprinkle access // to
	 * the original request throughout the method; I'm using literals because //
	 * this is the only place that these values will appear
	 * 
	 * String method = (String)CollectionUtil.getVia(albRequest, "httpMethod");
	 * String path = (String)CollectionUtil.getVia(albRequest, "path"); String
	 * accessToken = (String)CollectionUtil.getVia(albRequest, "headers",
	 * "x-amzn-oidc-accesstoken"); String body =
	 * (String)CollectionUtil.getVia(albRequest, "body");
	 * 
	 * logger.info("received {} {}", method, path);
	 * 
	 * Matcher actionMatch = actionRegex.matcher(path); if (! actionMatch.matches())
	 * { throw new IllegalArgumentException("invalid request path: " + path); }
	 * String action = actionMatch.group(1);
	 * 
	 * // body will be empty on GET, but rather than have separate code paths I'll
	 * give a dummy value // TODO - add defaultIfEmpty() to KDGCommons if
	 * (StringUtils.isEmpty(body)) body = "{}";
	 * 
	 * try { return new Request( method, action, accessToken, CollectionUtil.cast(
	 * mapper.readValue(body, HashMap.class), String.class, Object.class)); } catch
	 * (IOException ex) { throw new
	 * IllegalArgumentException("unable to parse request body: " + ex.getMessage());
	 * } }
	 */

	/**
	 * Dispatches based on the request action. This is extracted into a method so
	 * that we can simply return from each switch. Called functions are permitted to
	 * throw any runtime exception; this is handled by caller.
	 */
	private Response dispatch(Request request) {
		/*
		 * switch (request.getAction()) { case RequestActions.LIST: return
		 * invokeIf(request, HttpMethod.GET, authorized(r ->
		 * photoService.listPhotos(r))); case RequestActions.REQUEST_UPLOAD: return
		 * invokeIf(request, HttpMethod.POST, authorized(r ->
		 * photoService.prepareUpload(r))); default:
		 * logger.warn("unknown action, ignoring: {}", request.getAction()); return new
		 * Response(404); } return new Response(404);
		 */
		return invokeIf(request, HttpMethod.GET);
	}

	/**
	 * Verifies that the request matches the desired method, and if yes dispatches
	 * to the provided function.
	 */
	private Response invokeIf(Request req, Request.HttpMethod method) {
		WeatherData event = new WeatherData();
		event.setHumidityPct(100d);
		event.setPressureHPa(70);
		event.setTemperatureK(80);
		event.setWindKmh(60);

		return (req.getMethod() == method) ? new Response(ResponseCodes.SUCCESS, event)
				: new Response(ResponseCodes.INVALID_REQUEST);
	}

	/**
	 * Constructs the map that is sent back to API Gateway.
	 *
	 * @param tokens The current set of authentication tokens.
	 * @param data   The response body, as returned by the service.
	 */
	private Map<String, Object> buildResponseMap(Response response) {
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("statusCode", response.getStatusCode());

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Cache-Control", "no-store");
		responseMap.put("headers", headers);

		// body (if it exists) is always JSON
		responseMap.put("isBase64Encoded", Boolean.FALSE);

		if (response.getBody() != null) {
			try {
				responseMap.put("body", mapper.writeValueAsString(response.getBody()));
			} catch (JsonProcessingException ex) {
				logger.warn("unable to convert response body: " + ex.getMessage());
				responseMap.put("statusCode", 500);
			}
		}

		return responseMap;
	}

	/**
	 * Returns a function that checks authorization before invoking passed function.
	 * This is intended to be passed to {@link invokeIf}, as a semi-DSL.
	 */
	/*
	 * private Function<Request, Response> authorized(Function<Request, Response> f)
	 * { return new Function<Request, Response>() {
	 * 
	 * @Override public Response apply(Request req) { return
	 * userService.invokeCheckedOperation(req, f); } }; }
	 */
}
