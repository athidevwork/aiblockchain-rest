/**
 * 
 */
package com.aiblockchain.rest.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.aiblockchain.context.AppContext;
import com.aiblockchain.rest.model.Diamond;
import com.aiblockchain.rest.service.db.DbManager;
import com.aiblockchain.rest.service.db.DiamondManager;

/**
 * @author Athi
 *
 */
@Path("/diamond")
public class DiamondResource {
	/** The path to the folder where we want to store the uploaded files */
	private static final String UPLOAD_FOLDER = "C:/dev/git/aiblockchain/aiblockchain-rest/uploadedFiles/";

	DbManager dbMgr = (DbManager) AppContext.getBean(AppContext.DB_MANAGER);
	DiamondManager diamondMgr = (DiamondManager) AppContext.getBean(AppContext.DIAMOND_MANAGER);	
	
    public DbManager getDbMgr() {
		return dbMgr;
	}

    public DiamondManager getDiamondMgr() {
		return diamondMgr;
	}

	@GET
    @Produces("text/plain")
    public Response hello() {
        return Response.ok().entity("Hello from Document Resource").build();
    }

    @POST
	@Path("/save")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response saveDocument(Diamond diamond) {
    	if (!getDiamondMgr().doesItemIdExist(diamond)) {
			int id = getDiamondMgr().addDiamond(diamond);
			String result = "Saved diamond : " + diamond + " with ID : " + id;
			return Response.status(201).entity(result).build();
    	}
    	else
    		return Response.status(409).entity(diamond.getItemId()+ " ID already found.").build();
	}

    @GET
	@Path("/lookup/acct/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Diamond> getAccount(@PathParam("id") String acctId) {
    	System.out.println("Account = " + acctId);
    	getDbMgr().init();
    	List<Diamond> diamonds = getDiamondMgr().getDiamondsForAcct(acctId);
    	getDbMgr().shutdown();
    	return diamonds;
    }

    @GET
	@Path("/lookup/item/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Diamond getItem(@PathParam("id") String itemId) {
    	System.out.println("Item = " + itemId);
    	getDbMgr().init();
    	Diamond diamond = getDiamondMgr().getDiamond(itemId);
    	getDbMgr().shutdown();
    	return diamond;
    }

    @GET
	@Path("/list")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Diamond> getDiamonds() {
    	getDbMgr().init();
    	List<Diamond> diamonds = getDiamondMgr().getDiamondList();
    	getDbMgr().shutdown();
    	return diamonds;
    }
    
	@POST
	@Path("/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		// check if all form parameters are provided
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Invalid form data").build();
		// create our destination folder, if it not exists
		try {
			createFolderIfNotExists(UPLOAD_FOLDER);
		} catch (SecurityException se) {
			return Response.status(500)
					.entity("Can not create destination folder on server")
					.build();
		}
		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();
		try {
			saveToFile(uploadedInputStream, uploadedFileLocation);
		} catch (IOException e) {
			return Response.status(500).entity("Can not save file").build();
		}
		return Response.status(200)
				.entity("File saved to " + uploadedFileLocation).build();
	}
	
	/**
	 * Utility method to save InputStream data to target location/file
	 * 
	 * @param inStream
	 *            - InputStream to be saved
	 * @param target
	 *            - full path to destination file
	 */
	private void saveToFile(InputStream inStream, String target)
			throws IOException {
		OutputStream out = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		out = new FileOutputStream(new File(target));
		while ((read = inStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}

	/**
	 * Creates a folder to desired location if it not already exists
	 * 
	 * @param dirName
	 *            - full path to the folder
	 * @throws SecurityException
	 *             - in case you don't have permission to create the folder
	 */
	private void createFolderIfNotExists(String dirName)
			throws SecurityException {
		File theDir = new File(dirName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
	}
}
