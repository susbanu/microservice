package com.test.lambda.handler;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.test.lambda.handler.model.PersonRequest;
import com.test.lambda.handler.model.PersonResponse;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

public class SavePersonHandler implements RequestHandler<PersonRequest, PersonResponse> {

	private static final String DYNAMODB_TABLE_NAME = "Person";
	DynamoDbClient client = DynamoDbClient.builder().region(Region.AP_SOUTH_1).build();
	
	@Override
	public PersonResponse handleRequest(PersonRequest personRequest, Context context) {

		persistData(personRequest, client);
		PersonResponse personResponse = new PersonResponse();
		personResponse.setMessage(personRequest.getFirstName() + " Saved Successfully!!!");
		return personResponse;
	}

	private PutItemResponse persistData(PersonRequest personRequest, DynamoDbClient client) throws ConditionalCheckFailedException {
		
		Map<String,AttributeValue> itemValues = new HashMap<>();
		itemValues.put("id", AttributeValue.builder().n(String.valueOf(personRequest.getId())).build());
		itemValues.put("city", AttributeValue.builder().s(personRequest.getCity()).build());
		itemValues.put("firstName", AttributeValue.builder().s(personRequest.getFirstName()).build());
		itemValues.put("lastName", AttributeValue.builder().s(personRequest.getLastName()).build());
		
		PutItemRequest putItemRequest = PutItemRequest.builder()
											.tableName(DYNAMODB_TABLE_NAME)
											.item(itemValues)
											.build();
		
		try {
			return client.putItem(putItemRequest);

        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The Amazon DynamoDB table \"%s\" can't be found.\n",  DYNAMODB_TABLE_NAME);
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            System.exit(1);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
		return null;
	}

}