/**
 * 
 */
package com.aiblockchain.rest.data.mongo.cre.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.data.mongo.cre.entity.MongoFaultAsset;
import com.aiblockchain.rest.data.mongo.cre.service.MongoFaultAssetService;

/**
 * @author Athi
 *
 */
@Path("/mongo/fault")
public class MongoFaultResource {
    @GET
    @Path("/home")
    public Response getHome() {
    	return Response.ok().entity("Got Mongo Fault Resource").build();
    }
    
    @GET
	@Path("/assets")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getFaultAssetList() {
    	MongoFaultAssetService assetService = (MongoFaultAssetService) SpringDataContext.getBean("MongoFaultAssetService");
    	List<MongoFaultAsset> assetsList = assetService.getAssetList();
    	System.out.println("Asset size : " + assetsList.size());
    	
    	List<Object> objectList = new ArrayList<Object>();
    	for (MongoFaultAsset asset : assetsList) {
    		objectList.add(asset);
    	}
    	
    	return Response.ok().entity(objectList).header("faultasset", "MongoAllAssets").build();
    }
    
    @POST
	@Path("/save")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response saveFaultAsset (MongoFaultAsset faultAsset) {
    	MongoFaultAssetService faultService = (MongoFaultAssetService) SpringDataContext.getBean("MongoFaultAssetService");
    	MongoFaultAsset savedAsset = faultService.saveFaultAsset(faultAsset);        
    	return Response.ok().entity(savedAsset).build();
    }
  
    @GET
    @Path("asset/get")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAssetByBuildingLocationUnit(@QueryParam("building") String building, @QueryParam("location") String location, @QueryParam("unit") String unit) {
    	MongoFaultAssetService faultService = (MongoFaultAssetService) SpringDataContext.getBean("MongoFaultAssetService");
    	
    	List<MongoFaultAsset> assets = faultService.getFaultAsset(building, location, unit);
    	System.out.println("Assets in resource : " + assets);
    	return Response.ok(assets).build();    	
    }
    
    @GET
    @Path("/asset/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAsset(@PathParam(value = "id") String id) {
    	MongoFaultAssetService faultService = (MongoFaultAssetService) SpringDataContext.getBean("MongoFaultAssetService");
    	
    	MongoFaultAsset asset = faultService.getFaultAsset(id);
    	System.out.println("Asset : " + asset);
    	return Response.ok(asset).build();
    }
    
    @PUT
    @Path("/asset/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})    
    public Response updateAsset(@PathParam(value = "id") String id, MongoFaultAsset asset) {
    	MongoFaultAssetService faultService = (MongoFaultAssetService) SpringDataContext.getBean("MongoFaultAssetService");
    	if (faultService.updateFaultAsset(id, asset))       
    		return Response.ok(faultService.getFaultAsset(id)).build();
    	else
    		return Response.noContent().entity("Id not found in db for " + id).build();    	
    }
    
    @DELETE
    @Path("/asset/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    //@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})    
    public Response deleteAsset(@PathParam(value = "id") String id) {
    	MongoFaultAssetService faultService = (MongoFaultAssetService) SpringDataContext.getBean("MongoFaultAssetService");
    	if (faultService.deleteFaultAsset(id))       
    		return Response.ok(faultService.getFaultAsset(id)).build();
    	else
    		return Response.noContent().entity("Id not found in db for " + id).build();    	
    }
}
