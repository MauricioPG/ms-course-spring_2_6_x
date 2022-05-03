package com.devsuperior.msapigateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/users/login",
            "/oauth/token"
    );
    
    public static final List<String> postApi = List.of(
            "/oauth/",
            "/ms-payroll/",
            "/ms-user/",
            "/actuator/",
            "/ms-worker/actuator/"        
    );

    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
    
    
    public Predicate<ServerHttpRequest> isControlled = request -> postApi
            .stream()
            .anyMatch(uri -> request.getURI().getPath().contains(uri));
    

}