package com.aiblockchain.rest.service.aws;

import java.io.IOException;

import com.amazonaws.services.s3.AmazonS3;

public interface AWSServiceManager {
	AmazonS3 getS3Client();
	public void listKeys (String bucketName);
	public void getObjectData(String bucketName, String keyName);
	void readFromS3(String bucketName, String key) throws IOException;
	void readS3ObjectUsingByteArray(String bucketName, String key, String outputFileName) throws IOException;
	void writeToAWSS3(String bucketName, String keyName, String uploadFileName);
}
