package com.aiblockchain.rest.service.file;

import java.io.InputStream;

public interface FileManager {
	void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws Exception;
}
