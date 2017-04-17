package com.aiblockchain.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aiblockchain.context.AppContext;
import com.aiblockchain.rest.model.ServerProperties;
import com.aiblockchain.service.aiutils.UtilsManager;
import com.aiblockchain.service.aws.AWSServiceManager;

@Path("/saphana2")
public class SapHana2Resource {
	UtilsManager utils = (UtilsManager) AppContext.getBean(AppContext.UTILS_MANAGER);
	AWSServiceManager awsMgr = (AWSServiceManager) AppContext.getBean(AppContext.AWS_MANAGER);
	
    public UtilsManager getUtils() {
		return utils;
	}

    
	public AWSServiceManager getAwsMgr() {
		return awsMgr;
	}


	@GET
    @Produces("text/plain")
    public Response hello() {
        return Response.ok().entity("Hello Sap Hana2 Demo").build();
    }    
    
    @GET
    @Path("/props")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ServerProperties> getProperties() {
    	return getUtils().getPropertiesList();
    }       
    
    @GET
    @Path("/share")
    @Produces(MediaType.TEXT_PLAIN)
    public Response share(@QueryParam("bucketName") String bucketName,
    					  @QueryParam("keyName") String keyName) {
    	getAwsMgr().listKeys(bucketName);
    	getAwsMgr().getObjectData(bucketName, keyName);
    	return Response.ok().entity("Bob uploaded photo").build();
    }
    
    @GET
    @Path("/userAIResponse")
    @Produces(MediaType.TEXT_PLAIN)
    public Response userAIResponse(@QueryParam("objectHash") String objectHash) {
    	System.out.println ("Got hash = " + objectHash);
    	return Response.ok().entity("Got response").build();
    }
}
