package com.devsuperior.mspayroll.config;

/* Turned of class 03-03 forward */

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

/* Example on
 * https://github.com/eugenp/tutorials/tree/master/spring-cloud/spring-cloud-loadbalancer/spring-cloud-loadbalancer-client
 */

// @Configuration
class LoadBalanceServerInstanceConfiguration {
    
	// @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new LoadBalanceInstanceSupplier("ms-worker");
    }
}