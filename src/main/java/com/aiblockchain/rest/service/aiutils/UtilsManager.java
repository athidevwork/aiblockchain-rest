package com.aiblockchain.rest.service.aiutils;

import java.util.List;

import com.aiblockchain.rest.model.ServerProperties;

public interface UtilsManager {
	public List<ServerProperties> getPropertiesList();
	public String executeQuery(String url, String jsonInput) throws Exception;
}
