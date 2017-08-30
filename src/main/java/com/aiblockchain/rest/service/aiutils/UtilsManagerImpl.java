package com.aiblockchain.rest.service.aiutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.aiblockchain.context.AppContext;
import com.aiblockchain.rest.model.ServerProperties;

import io.netty.handler.codec.http.HttpResponse;

public class UtilsManagerImpl implements UtilsManager {
	
	Properties properties = (Properties) AppContext.getBean(AppContext.APP_PROPS);
	
	private static final String USER = "user";
	private static final String PASSWD = "password";
		
	public Properties getProperties() {
		return properties;
	}

	public List<ServerProperties> getPropertiesList() {		
		//System.out.println("UtilsManager");
		//System.out.println(getProperties().values());
	
		List<ServerProperties> propsList = new ArrayList<ServerProperties>();
		for (Entry<Object, Object> entry : getProperties().entrySet()) {
			propsList.add(new ServerProperties((String)entry.getKey(), (String)entry.getValue()));
		}
		System.out.println("ServerProperties");
		System.out.println(Arrays.asList(propsList));
		return propsList;
	}
	
	String url = "http://localhost:8332";
	UUID uuid = UUID.randomUUID();
	String getBlockCount = "{\"jsonrpc\": \"1.0\", \"id\":\""+uuid.toString()+"\", \"method\": \"getblockcount\", \"params\": [] }";
	//String numBlocks = executeQuery(url, getBlockCount);
	
	public String executeQuery(String url, String jsonInput) throws Exception {
		/*CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, PASSWD));
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(jsonInput,ContentType.create("application/json")));
        HttpResponse response = (HttpResponse) client.execute(post);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        client.close();
        return result;*/
		return null;
	}	
}
