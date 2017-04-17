package com.aiblockchain.rest.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author Athi
 *
 */

@Path("/users")
public class UserResource {
    @GET
    @Path("/json")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> json() {
        List<User> users = new ArrayList<User>();
        users.add(new User(100, "test"));
        users.add(new User(200, "test1"));
        return users;
    }

    @GET
    @Path("/xml")
    @Produces({MediaType.APPLICATION_XML})
    public List<User> xml() {
        List<User> users = new ArrayList<User>();
        users.add(new User(100, "test"));
        return users;
    }
    
    @GET
    @Path("/xmltest")
    @Produces({MediaType.APPLICATION_XML})
    public Response xmltest() {
    	Logger l = Logger.getLogger("UserResource");
        List<User> users = new ArrayList<User>();
        users.add(new User(100, "Athi"));
        l.info("Users : " + Arrays.asList(users));
        return Response.ok().entity(users).build();
    }    
}
