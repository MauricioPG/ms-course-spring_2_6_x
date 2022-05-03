package com.devsuperior.msoauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MsOauthApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Environment env;
	
	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(MsOauthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application Name => " + appName);
		System.out.println("Spring Version => " + SpringVersion.getVersion());
		System.out.println("SpringBoot Version => " + SpringBootVersion.getVersion());
		System.out.println("Java Version => " + System.getProperty("java.version"));
		System.out.println("PORT = " + env.getProperty("local.server.port"));
		
		// Demo BCrypt password generator
		System.out.println("BCRYPT FOR 123 = " + encoder.encode("123"));
	}

}
