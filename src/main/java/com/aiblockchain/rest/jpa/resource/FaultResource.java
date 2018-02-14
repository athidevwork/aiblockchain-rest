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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;
import com.aiblockchain.rest.jpa.service.cre.FaultAssetService;
import com.aiblockchain.rest.jpa.service.cre.FaultService;
import com.aiblockchain.rest.jpa.service.cre.FaultDataService;
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
    	
    	saveUser(faultUser);
		
    	return Response.ok().entity(userJson).build();
    }

	private void saveUser(User faultUser) throws IOException {
		Users origUsers = (Users) SpringDataContext.getBean("Users");
		System.out.println("\nUsers before reading in file : \n" + origUsers);
    	try (BufferedReader br = new BufferedReader(  
                new FileReader("src/main/resources/config/users.json"))) {
    		System.out.println("Reading users from file src/main/resources/config/users.json ..."); 
    		origUsers = (new Gson()).fromJson(br, Users.class);
            System.out.println("\nUsers after reading from file : \n" + origUsers);
    		System.out.println("Successfully read users from File src/main/resources/config/users.json...");            
        } catch (FileNotFoundException e) {
        	System.out.println("src/main/resources/config/users.json file not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException while trying to read src/main/resources/config/users.json");
			e.printStackTrace();
		}
    	
    	boolean userFound = false;
    	if (origUsers.getUsers().size() > 0) {
    		for (User user : origUsers.getUsers()) {
    			if (user.getUser().equalsIgnoreCase(faultUser.getUser())) {
    				System.out.println("User found already.");
    				userFound = true;
    				break;
    			}
    		}
    	}
    	
		if (!userFound) {		
	    	origUsers.addUser(faultUser);
	    	String usersJson = gson.toJson(origUsers);    	
			try (FileWriter file = new FileWriter("src/main/resources/config/users.json")) {
				file.write(usersJson);
				System.out.println("Successfully wrote user Json Object to File...");
				System.out.println("\nJSON Object: " + usersJson);
			}	    	
		}    	
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
	@Path("/assets/{searchStr}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getFaultAssetCategoryList(@PathParam(value = "searchStr") String searchStr) {
    	FaultAssetService assetService = (FaultAssetService) SpringDataContext.getBean("FaultAssetService");
    	List<FaultAsset> assetsList = assetService.getAssetsSearchList(searchStr);
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
    public Response saveSampleFault () {
    //public Response saveAsset (Asset asset, @Context UriInfo uriInfo) {
    	//Asset asset = new Asset(null, "1.5", "none", "vvs1", "colorless", "round", "Test asset", "1x1x1", "Superior", "test1", "test2");
    	FaultDataService sampleDataService = (FaultDataService) SpringDataContext.getBean("SampleFaultDataService");
    	sampleDataService.saveSampleData();
    	
        //UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        //builder.path(Integer.toString(dbAsset.getId()));
        //builder.path(Integer.toString(dbAsset.getId()));
        
    	return Response.ok().build();
    }	
    
    @POST
	@Path("/save")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response saveFault (FaultAsset faultAsset) {
    	FaultDataService faultService = (FaultDataService) SpringDataContext.getBean("SampleFaultDataService");
    	FaultAsset savedAsset = faultService.saveFault(faultAsset);        
    	return Response.ok().entity(savedAsset).build();
    }    
}
