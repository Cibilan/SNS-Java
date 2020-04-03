package com.amazonaws.samples;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;

public class SNSTopic {

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
		System.out.println("Publishing with Amazon SNS");
		System.out.println("===========================================\n");

		// Create an Amazon SNS topic.
		final CreateTopicRequest createTopicRequest = new CreateTopicRequest("MyAWSSNSTopic4");
		CreateTopicResult createTopicResponse = snsClient.createTopic(createTopicRequest);
		String TOPIC_ARN_FROM_JAVA = createTopicResponse.getTopicArn();
		// Print the topic ARN.
		System.out.println("TopicArn:" + TOPIC_ARN_FROM_JAVA);

		// Print the request ID for the CreateTopicRequest action.
		System.out.println("CreateTopicRequest: " + snsClient.getCachedResponseMetadata(createTopicRequest));

		//Set the email Address
		SubscribeRequest subscribeRequest = new SubscribeRequest(TOPIC_ARN_FROM_JAVA, "email", "example@test.com");
		snsClient.subscribe(subscribeRequest);
	}
}
