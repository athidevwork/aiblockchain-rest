/**
 * 
 */
package com.aiblockchain.server;

import com.aiblockchain.server.NettyConfig;

/**
 * @author Athi
 *
 */
public class NettyServerMain {
    public static void main(String[] args) {
        int port = 0;
        int noOfParams = args.length;
        System.out.println ("Args : " + noOfParams + ", port : " + args[0]);
        
        if (noOfParams > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = NettyConfig.PORT;
        }
        new NettyServer(port);
    }
}
