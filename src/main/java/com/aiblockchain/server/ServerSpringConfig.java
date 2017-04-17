package com.aiblockchain.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/beans/server-beans.xml")
public class ServerSpringConfig {
	/*public @Bean(name="lookupService") LookupService lookupService()
	{
		Map<String,GameRoom> refKeyGameRoomMap = new HashMap<String, GameRoom>();
		SimpleLookupService service = new SimpleLookupService(refKeyGameRoomMap);
		return service;
	}*/
}
