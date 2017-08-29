/**
 * 
 */
package com.aiblockchain.client;

import java.io.File;

/**
 * @author Athi
 *
 */
public class FileUploadRestClient {
	public static void main(String[] args) {
		try {
			String url = "http://localhost:20001/multipart/file/upload";
			File imgFile = new File("src/main/resources//fileupload/scenarios.jpg");
			File pdfFile = new File("src/main/resources/fileupload/Cracking the Coding Interview.pdf");
			File jsonFile = new File("src/main/resources/fileupload/H004344	.json");
			File zipFile = new File("src/main/resources/fileupload/dti.zip");
			
			JerseyFileUpload.postFile(url,imgFile,pdfFile,jsonFile,zipFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
