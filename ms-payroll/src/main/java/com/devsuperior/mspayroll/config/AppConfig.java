package com.devsuperior.mspayroll.config;

/* Turned off */

import org.springframework.web.client.RestTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;



/* Just for RestTemplate class 02-06 
 * Singleton pattern -> unique instance */

//@Configuration
public class AppConfig {
	
	//@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
