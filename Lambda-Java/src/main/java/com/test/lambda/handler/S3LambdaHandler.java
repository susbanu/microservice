package com.test.lambda.handler;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class S3LambdaHandler implements RequestHandler<S3Event, String> {

	@Override
	public String handleRequest(S3Event s3Event, Context context) {

		LambdaLogger lambdaLogger = context.getLogger();
		lambdaLogger.log("Event as received: " + s3Event);

		// To get to the actual object you have to invoke the AWS SDK yourself
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();  
		String bucketName = null;
		// S3Event object only transports the reference to the object and not the object itself.
		for (S3EventNotificationRecord s3record : s3Event.getRecords()) {
			String s3Key = s3record.getS3().getObject().getKey();
			String s3Bucket = s3record.getS3().getBucket().getName();
			lambdaLogger.log("S3 Bucket : " + s3Bucket + " S3 Key" + s3Key);
			// retrieve s3 object
			S3Object object = s3Client.getObject(new GetObjectRequest(s3Bucket, s3Key));
			bucketName = object.getBucketName();
			lambdaLogger.log("S3 Bucket using S3 SDK : "+ bucketName);
			// insert object into elasticsearch
		}
		return bucketName;
	}

}
