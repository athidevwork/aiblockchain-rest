package com.aiblockchain.server;

/**
 * 
 * @author Athi
 *
 */
public class NettyConfig {
    public static final String HOSTNAME = "localhost";
    public static final Integer PORT = 9999;
    public static final String JAXRS_RESOURCES = "com.aiblockchain";

    public static String getBaseURL(Integer port) {
        if(port == null) {
            port = NettyConfig.PORT;
        }
        StringBuilder sb = new StringBuilder("http://");
        sb.append(NettyConfig.HOSTNAME).append(":").append(String.valueOf(port)).append("/");
        return sb.toString();
    }
}
