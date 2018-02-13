/**
 * 
 */
package com.aiblockchain.rest.jpa.resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.data.util.RestUtil;
import com.aiblockchain.rest.jpa.entity.ServiceResponse;
import com.aiblockchain.rest.jpa.entity.User;
import com.aiblockchain.rest.jpa.entity.Users;
import com.aiblockchain.rest.jpa.entity.cre.Fault;
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;
import com.aiblockchain.rest.jpa.service.cre.FaultAssetService;
import com.aiblockchain.rest.jpa.service.cre.FaultService;
import com.aiblockchain.rest.jpa.service.cre.SampleFaultDataService;
import com.aiblockchain.rest.security.Utils;
import com.google.gson.Gson;

/**
 * @author Athi
 *
 */
@Path("/fault")
@Component("FaultResource")
public class FaultResource {
	//Users users = null;
	Gson gson = new Gson();
	
	//@Autowired
	//Users users;
	
	public FaultResource () {
		//RestUtil.setPropsForEnv("cre");		
	}

    @GET
    @Path("/home")
    public Response getHome() {
    	return Response.ok().entity("Got Fault Resource").build();
    }
    
    @GET
    @Path("/user/create")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response createUser(@QueryParam("user") String user, @QueryParam("password") String password) throws Exception {
    	String encUserName = Utils.encrypt(user, "FaultRestService");
    	String encPassword = Utils.encrypt(password, "FaultRestService");
    	String authorizationStr = user + ":" + password;
    	
    	byte[] encodedBytes;
        encodedBytes = Base64.getEncoder().encode(authorizationStr.getBytes());
        String authorization = "Basic " + new String(encodedBytes);
    	
    	User faultUser = new User(user, encUserName, encPassword, authorization);
    	String userJson = gson.toJson(faultUser);
    	
    	Users users = (Users) SpringDataContext.getBean("Users");
    	users.addUser(faultUser);   	
    	String usersJson = gson.toJson(users);
    	
		try (FileWriter file = new FileWriter("src/main/resources/config/users.json")) {
			file.write(usersJson);
			System.out.println("Successfully wrote user Json Object to File...");
			System.out.println("\nJSON Object: " + usersJson);
		}
		
    	return Response.ok().entity(userJson).build();
    }

    @GET
    @Path("/user/getauth")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getUserAuth(@QueryParam("auth") String auth) {
    	Users users = RestUtil.readUsersFromFile();
    	if (users.getUsers().size() > 0) {
	    	for (User user : users.getUsers()) {
	    		if (user.getAuthorization().contentEquals(auth))
	    			return Response.ok().entity(user).build();
	    	}
    	}
		return Response.noContent().build();
    }
 
    @GET
    @Path("/user/getuser")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getUserName(@QueryParam("user") String name) {
    	Users users = RestUtil.readUsersFromFile();
    	if (users.getUsers().size() > 0) {
	    	for (User user : users.getUsers()) {
	    		if (user.getUser().contentEquals(name))
	    			return Response.ok().entity(user).build();
	    	}
    	}
		return Response.noContent().build();
    }
    
    @GET
    @Path("/users")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getAllUsers() {
    	Users users = RestUtil.readUsersFromFile();
    	List<User> usersList = users.getUsers();
    	System.out.println("users list size " + usersList.size());
    	System.out.println(usersList);
    	
    	List<Object> objectList = new ArrayList<Object>();
    	for (User user : users.getUsers()) {
    		objectList.add(user);
    	}
    	//ServiceResponse response = new ServiceResponse();
    	//response.setList(usersList);
		return Response.ok().entity(objectList).build();
    }
    
    @GET
	@Path("/assets")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getFaultAssetList() {
    	FaultAssetService assetService = (FaultAssetService) SpringDataContext.getBean("FaultAssetService");
    	List<FaultAsset> assetsList = assetService.getAssetList();
    	System.out.println("Asset size : " + assetsList.size());
    	
    	List<Object> objectList = new ArrayList<Object>();
    	for (FaultAsset asset : assetsList) {
    		objectList.add(asset);
    	}
    	/*ServiceResponse response = new ServiceResponse();
    	response.setList(assetsList);*/
    	
    	return Response.ok().entity(objectList).header("faultasset", "AllAssets").build();
    }
    
    @GET
	@Path("/faults")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getFaultList() {
    	FaultService faultService = (FaultService) SpringDataContext.getBean("FaultService");
    	List<Fault> faultsList = faultService.getFaultList();
    	System.out.println("Faults size : " + faultsList.size());
    	
    	List<Object> objectList = new ArrayList<Object>();
    	for (Fault fault : faultsList) {
    		objectList.add(fault);
    	}
    	
    	/*ServiceResponse response = new ServiceResponse();
    	response.setList(faultsList);*/
    	
    	return Response.ok().entity(objectList).header("fault", "AllFaults").build();
    } 
    
    @GET
	@Path("/sample/save")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response saveFault () {
    //public Response saveAsset (Asset asset, @Context UriInfo uriInfo) {
    	//Asset asset = new Asset(null, "1.5", "none", "vvs1", "colorless", "round", "Test asset", "1x1x1", "Superior", "test1", "test2");
    	SampleFaultDataService sampleDataService = (SampleFaultDataService) SpringDataContext.getBean("SampleFaultDataService");
    	sampleDataService.saveSampleData();
    	
        //UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        //builder.path(Integer.toString(dbAsset.getId()));
        //builder.path(Integer.toString(dbAsset.getId()));
        
    	return Response.ok().build();
    }	    
}
