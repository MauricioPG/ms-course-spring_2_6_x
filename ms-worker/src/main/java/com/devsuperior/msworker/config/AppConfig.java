package com.devsuperior.msworker.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Value("${spring.profiles.active}")
	private String profile;

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		if (profile.equals("test")) {
	    return Server.createWebServer(
	      "-web", "-webAllowOthers", "-webPort", "9091");
		}
		return null;
	}

}
