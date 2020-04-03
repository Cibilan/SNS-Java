package com.amazonaws.samples;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SNSPublish{

	public static void main(String[] args) {
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		try {
			credentialsProvider.getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (C:\\Users\\Welcome\\.aws\\credentials), and is in valid format.", e);
		}

		AmazonSNS snsClient = AmazonSNSClientBuilder.standard().withCredentials(credentialsProvider)
				.withRegion(Regions.US_EAST_1).build();

		System.out.println("===========================================");
		System.out.println("Getting Started with Amazon SNS");
		System.out.println("===========================================\n");

		// Publish a message to an Amazon SNS topic.
		final String topicArn = "arn:aws:sns:us-east-1:570254315960:MyAWSSNSTopic4";
		final String msg = "If you receive this message, publishing a message to test .";
		final PublishRequest publishRequest = new PublishRequest(topicArn, msg);
		final PublishResult publishResponse = snsClient.publish(publishRequest);

		// Print the MessageId of the message.
		System.out.println("MessageId: " + publishResponse.getMessageId());

	}
}
