/**
 * 
 */
package com.aiblockchain.client;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

/**
 * @author Athi
 *
 */
public class JerseyFileUpload {
	private final static String contentType = "multipart/mixed";

	public static void postFile(String serverURL, File imgFile, File pdfFile, 
													File jsonFile, File zipFile) {
		MultiPart multiPart = null;
		try {
			Client client = ClientBuilder.newBuilder().
								register(MultiPartFeature.class).build();
			WebTarget server = client.target(serverURL);
			multiPart = new MultiPart();

			FileDataBodyPart pdfBodyPart = new FileDataBodyPart("pdfFile", pdfFile,
					MediaType.APPLICATION_OCTET_STREAM_TYPE);
			FileDataBodyPart imgBodyPart = new FileDataBodyPart("imgFile", imgFile,
					MediaType.APPLICATION_OCTET_STREAM_TYPE);
			FileDataBodyPart jsonBodyPart = new FileDataBodyPart("jsonFile", jsonFile,
					MediaType.APPLICATION_OCTET_STREAM_TYPE);
			FileDataBodyPart zipBodyPart = new FileDataBodyPart("zipFile", zipFile,
					MediaType.APPLICATION_OCTET_STREAM_TYPE);
			// Add body part
			multiPart.bodyPart(pdfBodyPart);
			multiPart.bodyPart(imgBodyPart);
			multiPart.bodyPart(jsonBodyPart);
			multiPart.bodyPart(zipBodyPart);

			Response response = server.request(MediaType.APPLICATION_JSON_TYPE)
					.post(Entity.entity(multiPart, contentType));
			if (response.getStatus() == 200) {
				String respnse = response.readEntity(String.class);
				System.out.println(respnse);
			} else {
				System.out.println("Response is not ok");
			}
		} catch (Exception e) {
			System.out.println("Exception has occured "+ e.getMessage());
		} finally {
			if (null != multiPart) {
				try {
					multiPart.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
