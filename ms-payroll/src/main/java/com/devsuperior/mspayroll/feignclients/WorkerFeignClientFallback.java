package com.devsuperior.mspayroll.feignclients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.mspayroll.entities.Worker;

/* For class 03-05, Hystrix should be replaced with CircuitBreaker, since it was deprecated on Netflix.
 * Using reference  https://senoritadeveloper.medium.com/spring-boot-feign-client-load-balancer-and-circuit-breaker-733d243f0957 
 * for construction of fallback (annotation and implementation) */

@Component
public class WorkerFeignClientFallback implements WorkerFeignClient {
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		Worker worker = new Worker(1L, "CircuitBreaker Fallback", 0.0);
		return ResponseEntity.ok(worker);
	}
}
