package com.aiblockchain.server.http;

public interface Handler {

    Object handle(Request request, Response response) throws Exception;

}
