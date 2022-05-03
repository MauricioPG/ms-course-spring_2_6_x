package com.devsuperior.msapigateway.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback1")
    public String userFallback() {
        return "User service is not available";
    }

    @GetMapping("/auth-fallback")
    public String authFallback() {
        return "Auth service is not available";
    }
    
    @GetMapping("/fallback")
    public String userGenFallback() {
        return "User service is not available";
    }
}