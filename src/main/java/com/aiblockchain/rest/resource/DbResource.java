package com.aiblockchain.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aiblockchain.context.AppContext;
import com.aiblockchain.rest.model.Users;
import com.aiblockchain.rest.service.db.DbManager;
import com.aiblockchain.rest.service.db.UserManager;

@Path("/db")
public class DbResource {
	DbManager dbMgr = (DbManager) AppContext.getBean(AppContext.DB_MANAGER);
	UserManager userMgr = (UserManager) AppContext.getBean(AppContext.USER_MANAGER);
	
    public DbManager getDbMgr() {
		return dbMgr;
	}

	public UserManager getUserMgr() {
		return userMgr;
	}

	@GET
    @Produces("text/plain")
    public Response hello() {
        return Response.ok().entity("Hello Db Resource").build();
    }
    
    
    @GET
	@Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Users> getJson() {
    	getDbMgr().init();
    	List<Users> users = getUserMgr().getUsersList();
    	getDbMgr().shutdown();
    	return users;	
    }

    @GET
	@Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Users> getXml() {
    	getDbMgr().init();
    	List<Users> users = getUserMgr().getUsersList();
    	getDbMgr().shutdown();
    	return users;	
    }
    
    @GET
	@Path("/test")
    @Produces("text/plain")
    public Response testDb() {
    	getDbMgr().init();
    	int id = getUserMgr().addUser();
    	getUserMgr().getUsers();
    	getUserMgr().updateUser(id);
    	getUserMgr().getUsers();
    	getDbMgr().shutdown();
        return Response.ok().entity("Test Complete").build();
    }    
}
