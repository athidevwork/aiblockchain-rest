/**
 * 
 */
package com.aiblockchain.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Athi
 *
 */
public class FileManagerImpl implements FileManager {

	/**
	 * writeToFile - Method to write a stream to a files directory.
	 * @param uploadedInputStream
	 * @param uploadedFileLocation
	 */
	public void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) 
			throws Exception {
		OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
		int read = 0;
		byte[] bytes = new byte[1024];

		out = new FileOutputStream(new File(uploadedFileLocation));
		while ((read = uploadedInputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}
}
