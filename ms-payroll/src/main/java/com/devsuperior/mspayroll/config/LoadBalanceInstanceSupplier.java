package com.devsuperior.mspayroll.config;

/* Turned of class 03-03 forward */
import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;

import reactor.core.publisher.Flux;

/* Example on
 * https://github.com/eugenp/tutorials/tree/master/spring-cloud/spring-cloud-loadbalancer/spring-cloud-loadbalancer-client
 */

class LoadBalanceInstanceSupplier implements ServiceInstanceListSupplier {
	private final String serviceId;

	public LoadBalanceInstanceSupplier(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String getServiceId() {
		return serviceId;
	}
	
	/* Here instances are hard(ish) coded */
	@Override
	public Flux<List<ServiceInstance>> get() {
		return Flux.just(Arrays.asList(
				new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8001, false),
				new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8002, false)));
	}
}