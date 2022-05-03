package com.devsuperior.mspayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.mspayroll.entities.Payment;
import com.devsuperior.mspayroll.entities.Worker;
import com.devsuperior.mspayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {

	/* Just for RestTemplate class 02-06 */
	// @Autowired
	// private RestTemplate restTemplate;
	// @Value("${ms-worker.host}")
	// private String workerHost;
	
	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	public Payment getPayment(long workerId, int days) {

		/* Just for RestTemplate class 02-06 */
		// Map<String, String> uriVariables = new HashMap<>();
		// uriVariables.put("id", String.valueOf(workerId));
		// Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);

		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
