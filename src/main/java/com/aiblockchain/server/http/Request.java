package com.aiblockchain.server.http;

import java.nio.charset.StandardCharsets;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * The Request class provides convenience helpers to the underyling
 * HTTP Request.
 */
public class Request {
    private final FullHttpRequest request;


    /**
     * Creates a new Request.
     *
     * @param request The Netty HTTP request.
     */
    public Request(final FullHttpRequest request) {
        this.request = request;
    }


    /**
     * Returns the body of the request.
     *
     * @return The request body.
     */
    public String body() {
        return request.content().toString(StandardCharsets.UTF_8);
    }
}
