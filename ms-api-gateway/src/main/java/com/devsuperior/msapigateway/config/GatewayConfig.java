package com.devsuperior.msapigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class GatewayConfig {

	@Autowired
	AuthenticationFilter filter;

	/* In order to services work properly, was necessary to rewrite the paths after matching routes,
	 * because mainly of the actuator/refresh, which doesn´t carry the service predicate (ms-**) in URI.
	 * The discovery.locator.enable = true was tested, but was giving some trouble with authentication
	 * filter, that is, for some yet unknown reason, didn´t find route after filter.
	 * So adding the rewritePath on filter, solved the problem.
	 * 
	 * regex from: 
	 * https://stackoverflow.com/questions/64967797/spring-cloud-gateway-rewrite-path-syntax
	 * **/
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/ms-user/**")
                        .filters(f -> f
                        		.filter(filter)
                        		.rewritePath("/ms-user/(?<segment>.*)","/$\\{segment}"))
                        .uri("lb://ms-user"))

                .route("payroll-service", r -> r.path("/ms-payroll/**")
                        .filters(f -> f
                        		.filter(filter)
                        		.rewritePath("/ms-payroll/(?<segment>.*)","/$\\{segment}"))
                        .uri("lb://ms-payroll"))
                
                .route("worker-service", r -> r.path("/ms-worker/**")
                        .filters(f -> f
                        		.filter(filter)
                        		.rewritePath("/ms-worker/(?<segment>.*)","/$\\{segment}"))
                        .uri("lb://ms-worker"))
                        
                .route("oauth-service", r -> r.path("/ms-oauth/**")
                        .filters(f -> f
                        		.filter(filter)
                        		.rewritePath("/ms-oauth/(?<segment>.*)","/$\\{segment}"))
                        .uri("lb://ms-oauth"))
                
                .build();
    }
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
