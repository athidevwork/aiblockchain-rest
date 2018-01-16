/**
 * 
 */
package com.aiblockchain.server;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.PropertyConfigurator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aiblockchain.rest.data.resource.DataAssetResource;
import com.aiblockchain.rest.exception.ExceptionMapper;
import com.aiblockchain.rest.jpa.resource.DigitalAssetTransferResource;
import com.aiblockchain.rest.resource.AIBlockChainResource;
import com.aiblockchain.rest.resource.DbResource;
import com.aiblockchain.rest.resource.DiamondResource;
import com.aiblockchain.rest.resource.HelloWorldResource;
import com.aiblockchain.rest.resource.SapHana2Resource;
import com.aiblockchain.rest.resource.UserResource;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import io.netty.channel.Channel;


/**
 * @author Athi
 *
 */
public class NettyServer {
    //private String baseurl = null;
	/*protected int portNumber;
	
	public void startServer(int port) throws Exception
	{
		portNumber = port;
	}*/
	
    public NettyServer(Integer port) {
        // Configure the server
    	//this.baseurl = NettyConfig.getBaseURL(port);
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(port).build();

        // Register the REST endpoints
        ResourceConfig config = new ResourceConfig();
        config.register(HelloWorldResource.class)
        .register(UserResource.class)
        .register(AIBlockChainResource.class)
        .register(SapHana2Resource.class)
        .register(DbResource.class)
        .register(DiamondResource.class)
        //.register(AssetResource.class)
        .register(DigitalAssetTransferResource.class)
        .register(ExceptionMapper.class)
        .register(MoxyJsonFeature.class)
        .register(MoxyXmlFeature.class)
        //.register(JaxbContextResolver.class)  // No need to register this provider if no special configuration is required.
        .register(JettisonFeature.class)
        .register(JacksonFeature.class)
        .register(JacksonJsonProvider.class)        
        .register(MultiPartFeature.class)
        //.register(NettyHttpContainerProvider.class)
        //.register(HttpService.class)
        .register(org.glassfish.jersey.filter.LoggingFilter.class);
        
        Channel server = NettyHttpContainerProvider.createServer(baseUri, config, false);

		PropertyConfigurator.configure(System
				.getProperty("log4j.configuration"));
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ServerSpringConfig.class);
		
		// For the destroy method to work.
		context.registerShutdownHook();
		
		//hookup spring data jpa
		/*AbstractApplicationContext springDataACAContext = new AnnotationConfigApplicationContext(SpringDataConfig.class);
		
		springDataACAContext.registerShutdownHook();*/
		AbstractApplicationContext springDataCPXAContext = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");	
		springDataCPXAContext.registerShutdownHook();
		
		// Start tcp and flash servers
		//ServerManager manager = (ServerManager)context.getBean("serverManager");
        System.out.println("Netty jersey server started with Uri: " + baseUri.toString());
        
        //ClientConfig clientConfig = new ClientConfig();
        //clientConfig.register(HttpClientResponseFilter.class);
        //Client client = ClientBuilder.newClient(clientConfig);
        
        /*Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://example.com/aiblockchain");
        
        WebTarget resourceWebTarget = webTarget.path("resource");
        
        WebTarget helloworldWebTarget = resourceWebTarget.path("helloworld");
        WebTarget helloworldWebTargetWithQueryParam =
                helloworldWebTarget.queryParam("greeting", "Hi World!");*/
        
        /*URI httpBaseUri = UriBuilder.fromUri("http://localhost/").port(4567).build();
        ResourceConfig httpConfig = new ResourceConfig();
        httpConfig.register(HelloWorldResource.class);*/
        
        //NettyHttpContainerProvider.createHttp2Server(httpBaseUri, httpConfig, false);
        
        /*try {
			System.out.println ("Http Server starting at port 4567.");
			new WebServer()

			// Simple GET request
			.get("/hello", (request, response) -> "Hello world")

			.get("/testget", (request, response) -> {
				URI uri = request.body().getRequestURI();
			    String myresponse = "Path: " + uri.getPath() + "\n";
			    request.sendResponseHeaders(200, myresponse.length());
			    OutputStream os = request.getResponseBody();
			    os.write(myresponse.getBytes());
			    os.close();				
			})
			
			// Simple POST request
			.post("/hello", (request, response) -> {
			    return "Hello world: " + request.body();
			})

			// Error handling
            .get("/boom", (request, response) -> {
                throw new Exception("asdf");
            })

            // GET body?
            .get("/getbody", (request, response) -> {
                return "What is this? " + request.body();
            })
            
			// Start the server
			.start();
		} catch (Exception e) {
			System.out.println("Exception while starting http server");
			e.printStackTrace();
		}*/
    }

    public static void main(String[] args) {
        Integer port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = NettyConfig.PORT;
        }
        new NettyServer(port);
    }
}
