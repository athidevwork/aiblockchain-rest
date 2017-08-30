package com.aiblockchain.rest.resource;

import static com.aiblockchain.context.AppContext.FILE_MANAGER;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.aiblockchain.context.AppContext;
import com.aiblockchain.rest.service.aws.AWSServiceManager;
import com.aiblockchain.rest.service.file.FileManager;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * 
 * @author Athi
 *
 */
@Path("/aiblockchain")
public class AIBlockChainResource {

	AppContext context = (AppContext) AppContext.getBean(AppContext.APP_CONTEXT);
	Properties properties = (Properties) AppContext.getBean(AppContext.APP_PROPS);
	AWSServiceManager awsMgr = (AWSServiceManager) AppContext.getBean(AppContext.AWS_MANAGER);
	FileManager fileMgr = (FileManager) AppContext.getBean(FILE_MANAGER);
	
	public Properties getProperties() {
		return properties;
	}

	public AppContext getContext() {
		return context;
	}

	public AWSServiceManager getAwsMgr() {
		return awsMgr;
	}

	public FileManager getFileMgr() {
		return fileMgr;
	}

	@GET
	@Produces("text/plain")
	public Response hello() {
		return Response.ok().entity("Hello AIBlockChain").build();
	}

	@GET
	@Path("/getfile")
	@Produces("text/plain")
	public Response uploadFileUrl(@QueryParam("fileurl") String fileUrl) {
		Logger l = Logger.getLogger("AIBlockChainResource.uploadFileUrl");
		l.info("present dir = " + System.getProperty("user.dir"));
		try {
			String content = new String(Files.readAllBytes(Paths.get(fileUrl)));
			l.info(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.ok().entity("Got file " + fileUrl).build();
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		Logger l = Logger.getLogger("AIBlockChainResource.uploadFile");
		/*String uploadedFileLocation = "c://dev//git//aiblockchain//aiblockchain-rest//files//"
				+ fileDetail.getFileName();*/
		String uploadedFileLocation = getProperties().getProperty("files.dir") + fileDetail.getFileName();
		l.info("File path : " + uploadedFileLocation);
		
		// save it to file
		try {
			getFileMgr().writeToFile(uploadedInputStream, uploadedFileLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/readFromS3")
	@Produces("text/plain")
	public Response readDataFromS3(@QueryParam("bucket") String bucketName, @QueryParam("key") String key,
			@QueryParam("file") String outputFile) {
		Logger l = Logger.getLogger("AIBlockChainResource.readDataFromS3");
		l.info("present dir = " + System.getProperty("user.dir"));

		try {
			getAwsMgr().readS3ObjectUsingByteArray(bucketName, key, outputFile);
		} catch (Exception e) {
			l.info("Exception reading from s3: " + e.getMessage());
			e.printStackTrace();
			return Response.serverError().entity("Exception reading from S3").build();
		}
		return Response.ok().entity("Got file from s3 bucket : " + bucketName + " with key : " + key).build();
	}

	@GET
	@Path("/writeToS3")
	@Produces("text/plain")
	public Response writeDataToS3(@QueryParam("bucket") String bucketName, @QueryParam("key") String key,
			@QueryParam("file") String outputFile) {
		Logger l = Logger.getLogger("AIBlockChainResource.writeDataToS3");
		l.info("present dir = " + System.getProperty("user.dir"));

		try {
			getAwsMgr().writeToAWSS3(bucketName, key, outputFile);
		} catch (Exception e) {
			l.info("Exception writing to s3: " + e.getMessage());
			return Response.serverError().entity("Exception writing to S3").build();
		}
		return Response.ok().entity("Uploaded file to s3 bucket : " + bucketName + " with key : " + key).build();
	}
}
