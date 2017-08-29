package com.aiblockchain.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aiblockchain.context.AppContext;
import com.aiblockchain.rest.model.Users;
import com.aiblockchain.service.db.DbManager;

@Path("/db")
public class DbResource {
	DbManager dbMgr = (DbManager) AppContext.getBean(AppContext.DB_MANAGER);
	
    public DbManager getDbMgr() {
		return dbMgr;
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
    	List<Users> users = getDbMgr().getUsersList();
    	getDbMgr().shutdown();
    	return users;	
    }

    @GET
	@Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Users> getXml() {
    	getDbMgr().init();
    	List<Users> users = getDbMgr().getUsersList();
    	getDbMgr().shutdown();
    	return users;	
    }
    
    @GET
	@Path("/test")
    @Produces("text/plain")
    public Response testDb() {
    	getDbMgr().init();
    	int id = getDbMgr().addUser();
    	getDbMgr().getUsers();
    	getDbMgr().updateUser(id);
    	getDbMgr().getUsers();
    	getDbMgr().shutdown();
        return Response.ok().entity("Test Complete").build();
    }    
}
