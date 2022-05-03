package com.devsuperior.msapigateway.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

// https://stackoverflow.com/questions/70561543/configure-cors-policy-for-spring-cloud-gateway

@Configuration
public class CorsConfiguration extends org.springframework.web.cors.CorsConfiguration {

  @Bean
  public CorsWebFilter corsFilter() {
    org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
    corsConfiguration.addAllowedHeader("origin");
    corsConfiguration.addAllowedHeader("content-type");
    corsConfiguration.addAllowedHeader("accept");
    corsConfiguration.addAllowedHeader("authorization");
    corsConfiguration.addAllowedHeader("cookie");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsWebFilter(source);
  }
}