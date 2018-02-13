/**
 * 
 */
package com.aiblockchain.rest.data.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.jpa.entity.Users;
import com.google.gson.Gson;

/**
 * @author Athi
 *
 */
public class RestUtil {
	static Logger LOGGER = LoggerFactory.getLogger(RestUtil.class);
	
	public static Users readUsersFromFile() {
		Users users = (Users) SpringDataContext.getBean("Users");
		System.out.println("\nUsers before reading in file : \n" + users);
    	try (BufferedReader br = new BufferedReader(  
                new FileReader("src/main/resources/config/users.json"))) {
    		System.out.println("Reading users from file src/main/resources/config/users.json ..."); 
            users = (new Gson()).fromJson(br, Users.class);
            System.out.println("\nUsers after reading from file : \n" + users);
    		System.out.println("Successfully read users from File src/main/resources/config/users.json...");            
        } catch (FileNotFoundException e) {
        	System.out.println("src/main/resources/config/users.json file not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException while trying to read src/main/resources/config/users.json");
			e.printStackTrace();
		}
    	return users;
	}
	
	public static void setPropsForEnv (String location, String schema) {		
		String schemaProp = (String) SpringDataContext.getProperty(schema + ".schema");
		String hostProp = (String) SpringDataContext.getProperty(location + ".mysql.host");
		String portProp = (String) SpringDataContext.getProperty(location + ".mysql.port");
		LOGGER.debug("Env " + schema + ".schema : " + schemaProp);
		LOGGER.debug("Env " + location + ".mysql.host : " + hostProp);
		LOGGER.debug("Env " + schema + ".mysql.port : " + portProp);
	
		String url = "mysql://" + hostProp + ":" + portProp + "/" + schemaProp + "?autoReconnect=true&useSSL=false";
		System.out.println("Jdbc Url for schema " + schema + "jdbc.url : " + url);
		if (url.isEmpty()) 
			LOGGER.debug("Environment setting for schema " + schema + " not found. Using default setting for datasource");
		else {
			DriverManagerDataSource datasource = (DriverManagerDataSource) SpringDataContext.getBean("dataSource");
			datasource.setUrl(url);
		}
		
		LOGGER.debug("Datasource set for parameter jdbc.url : " + url);
		System.out.println("Datasource set for parameter jdbc.url : " + url);
	}
}
