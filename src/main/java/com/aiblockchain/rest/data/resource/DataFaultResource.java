/**
 * 
 */
package com.aiblockchain.rest.data.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.data.service.FaultAssetService;
import com.aiblockchain.rest.data.service.FaultService;
import com.aiblockchain.rest.jpa.entity.cre.Fault;
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;

/**
 * @author Athi
 *
 */
@Path("/datafault")
@Component("DataFaultResource")
public class DataFaultResource {
/*	public FaultResource () {
		RestUtil.setPropsForEnv("cre");
	}*/

    @GET
    @Path("/home")
    public Response getHome() {
    	return Response.ok().entity("Got Data Fault Resource").build();
    }
    
    @GET
    @Path("/user/create")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public void createUser() {
    	
    }

    @GET
    @Path("/user/get")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public void getUser() {
    	
    }
    
    @GET
	@Path("/assets")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getFaultAssetList() {
    	FaultAssetService assetService = (FaultAssetService) SpringDataContext.getBean("DataFaultAssetService");
    	List<FaultAsset> assetsList = assetService.getAssetList();
    	System.out.println("Asset size : " + assetsList.size());
    	
    	List<Object> objectList = new ArrayList<Object>();
    	for (FaultAsset asset : assetsList) {
    		objectList.add(asset);
    	}
    	
    	return Response.ok().entity(assetsList).header("assets", "AllAssets").build();
    }
    
    @GET
	@Path("/faults")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getFaultList() {
    	FaultService faultService = (FaultService) SpringDataContext.getBean("DataFaultService");
    	List<Fault> faultsList = faultService.getFaultList();
    	System.out.println("Faults size : " + faultsList.size());
    	
    	List<Object> objectList = new ArrayList<Object>();
    	for (Fault fault : faultsList) {
    		objectList.add(fault);
    	}
    	
    	return Response.ok().entity(objectList).header("fault", "AllFaults").build();
    }    
}
