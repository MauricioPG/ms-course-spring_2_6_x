package com.devsuperior.mspayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.mspayroll.entities.Worker;


@Component

/* For class 02-07 - hard code url */
// @FeignClient(name = "ms-worker", url = "localhost:8001", path = "/workers")

/* For class 02-08 - url should be on two ports - defined on application.properties when using ribbon.
 * But, since for springboot 2.6.6 ribbon was deprecated, the application was configured to use 
 * Spring LoadBalancer. Check classes on package config */

/* For class 03-05, Hystrix should be replaced with CircuitBreaker, since it was deprecated on Netflix.
 * Using reference  https://senoritadeveloper.medium.com/spring-boot-feign-client-load-balancer-and-circuit-breaker-733d243f0957 
 * for construction of fallback (annotation and implementation) */

@FeignClient(name = "ms-worker", path = "/workers", fallback = WorkerFeignClientFallback.class)

public interface WorkerFeignClient {
	@GetMapping(value = "/{id}")
	ResponseEntity<Worker> findById(@PathVariable Long id);
}
