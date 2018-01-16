/**
 * 
 */
package com.aiblockchain.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author Athi
 *
 */
@Provider
public class DebugExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        return Response.serverError().entity(exception.getMessage()).build();
    } 
}
