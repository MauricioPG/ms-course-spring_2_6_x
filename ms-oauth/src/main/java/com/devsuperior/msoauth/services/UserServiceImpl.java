package com.devsuperior.msoauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.msoauth.services.UserServiceImpl;
import com.devsuperior.msoauth.entities.Role;
import com.devsuperior.msoauth.entities.User;
import com.devsuperior.msoauth.feignclients.UserFeignClient;

@Service
public class UserServiceImpl implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeignClient.findByEmail(username).getBody();
		if (user == null) {
			logger.error("Email not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("Email found: " + username);
		logger.info("Roles: ");
		for (Role r: user.getRoles()) {
			logger.info(r.getRoleName());
		}
		return user;
	}
}
