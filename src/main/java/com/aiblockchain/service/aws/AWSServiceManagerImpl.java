package com.aiblockchain.service.aws;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aiblockchain.context.AppContext;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AWSServiceManagerImpl implements AWSServiceManager {
	AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
	Properties properties = (Properties) AppContext.getBean(AppContext.APP_PROPS);	

	public AmazonS3 getS3Client() {
		return s3Client;
	}

	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * readFromS3 - This method reads file from a bucket given the key.
	 * @param bucketName
	 * @param key
	 * @throws IOException
	 */
	public void readFromS3(String bucketName, String key) throws IOException {
		Logger l = Logger.getLogger("AWSServiceManager.readFromS3");
		
		S3Object s3object = getS3Client().getObject(new GetObjectRequest(bucketName, key));
		l.info(s3object.getObjectMetadata().getContentType());
		l.info(String.valueOf(s3object.getObjectMetadata().getContentLength()));

		BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
		String line;
		while ((line = reader.readLine()) != null) {
			// can copy the content locally as well
			// using a buffered writer
			l.info(line);
		}
	}

	public void readS3ObjectUsingByteArray(String bucketName, String key, String outputFileName) throws IOException {
		S3Object s3object = getS3Client().getObject(new GetObjectRequest(bucketName, key));
		Logger l = Logger.getLogger("readS3ObjectUsingByteArray");
		InputStream stream = s3object.getObjectContent();

		String bufferSize = getProperties().getProperty("buffer.size");
		//l.info("buffer size =" + bufferSize);
		byte[] content = new byte[Integer.valueOf(bufferSize)];

		//String downloadedFileLocation = "c://dev//git//aiblockchain//aiblockchain-rest//files//" + outputFileName;
		String downloadedFileLocation = getProperties().getProperty("files.dir") + outputFileName;
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadedFileLocation));
		int totalSize = 0;
		int bytesRead;
		while ((bytesRead = stream.read(content)) != -1) {
			if (l.isLoggable(Level.FINE))
				l.fine(String.format("%d bytes read from stream", bytesRead));
			outputStream.write(content, 0, bytesRead);
			totalSize += bytesRead;
		}
		l.fine("Total Size of file in bytes = " + totalSize);
		// close resource even during exception
		outputStream.close();
	}
	
	// write to AWS
	/**
	 * writeToAWSS3 - This method uploads the file to the S3 bucket with a given key.
	 * @param bucketName
	 * @param keyName
	 * @param uploadFileName
	 */
	public void writeToAWSS3(String bucketName, String keyName, String uploadFileName) {
		System.out.println("Uploading a new object to S3 from a file\n");
		try {
			File file = new File(uploadFileName);
			PutObjectResult result = getS3Client().putObject(new PutObjectRequest(bucketName, keyName, file));
			System.out.println("Etag = " + result.getETag());
			System.out.println("Md5 Hash=" + result.getContentMd5());
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}
	
	/**
	 * listKeys - method to get a listing of keys in a bucket.
	 * @param bucketName
	 */
	public void listKeys (String bucketName) {
		try {
			ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName);
			ListObjectsV2Result result;
	        do {               
	           result = getS3Client().listObjectsV2(req);
	           
	           for (S3ObjectSummary objectSummary : 
	               result.getObjectSummaries()) {
	               System.out.println(" - " + objectSummary.getKey() + "  " +
	                       "(size = " + objectSummary.getSize() + 
	                       ")");
	               System.out.println("Hash Code = " + objectSummary.hashCode() + 
	            		   			  ", Etag = " + objectSummary.getETag() +
	            		   			  ", Link = " + "https://s3.amazonaws.com/"+bucketName+"/"+objectSummary.getKey());
	           }
	           System.out.println("Next Continuation Token : " + result.getNextContinuationToken());
	           req.setContinuationToken(result.getNextContinuationToken());
	        } while(result.isTruncated() == true ); 
		} catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, " +
            		"which means your request made it " +
                    "to Amazon S3, but was rejected with an error response " +
                    "for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, " +
            		"which means the client encountered " +
                    "an internal error while trying to communicate" +
                    " with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
	}
	
	public void getObjectData(String bucketName, String keyName) {
		try {              
			ObjectMetadata result = getS3Client().getObjectMetadata(bucketName, keyName);
			Map <String, Object> metadata = result.getRawMetadata();
			System.out.println("Metadata = " + metadata.toString());
           System.out.println("MD5 = " + result.getContentMD5() + ", Etag=" + result.getETag());
		} catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, " +
            		"which means your request made it " +
                    "to Amazon S3, but was rejected with an error response " +
                    "for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, " +
            		"which means the client encountered " +
                    "an internal error while trying to communicate" +
                    " with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }		
	}
}
