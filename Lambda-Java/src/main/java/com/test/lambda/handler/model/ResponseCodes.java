package com.test.lambda.handler.model;

public enum ResponseCodes {
	SUCCESS("Success"), 
	INTERNAL_ERROR("An internal error occurred; this has been logged"),
	INVALID_OPERATION("Unsupported operation"), 
	INVALID_REQUEST("The request is missing needed fields");

	private String description;

	private ResponseCodes(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
