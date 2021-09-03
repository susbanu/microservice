package com.test.lambda.handler.model;

import java.util.HashMap;
import java.util.Map;

public class Response {
	/**
	 * Top-level fields in the response body.
	 */
	public static class Fields {
		public final static String RESPONSE_CODE = "responseCode";
		public final static String DESCRIPTION = "description";
		public final static String DATA = "data";
	}

	private int statusCode = 200;
	private ResponseCodes responseCode;
	private Object data;

	/**
	 * Primary constructor for service methods. Assumes status 200.
	 *
	 * @param responseCode Service-specific response code with description.
	 * @param data         Service-specific data. Can be any JSON-serializable
	 *                     object, or null.
	 */
	public Response(ResponseCodes responseCode, Object data) {
		this.responseCode = responseCode;
		this.data = data;
	}

	/**
	 * Convenience constructor for service methods that don't return a body. Assumes
	 * status 200.
	 *
	 * @param responseCode Service-specific response code with description.
	 */
	public Response(ResponseCodes responseCode) {
		this(responseCode, null);
	}

	/**
	 * Constructor used by dispatcher exception handlers; does not return data.
	 *
	 * @param statusCode The HTTP status to return.
	 */
	public Response(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Returns the HTTP status code.
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Returns a response body, based on the provided response code and data. Will
	 * return null if the response code is missing.
	 */
	public Map<String, Object> getBody() {
		if (responseCode == null)
			return null;

		HashMap<String, Object> body = new HashMap<>();
		body.put(Response.Fields.RESPONSE_CODE, responseCode.name());
		body.put(Response.Fields.DESCRIPTION, responseCode.getDescription());
		body.put(Response.Fields.DATA, data);
		return body;
	}
}
