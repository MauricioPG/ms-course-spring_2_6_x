package com.devsuperior.msworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.msworker.entities.Worker;
import com.devsuperior.msworker.repositories.WorkerRepository;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")

public class WorkerResource {

	/* For Ribbon tests class 02-08 */
	@Autowired
	private Environment env;
	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

	@Autowired
	private WorkerRepository repository;

	@Value("${test.config}")
	private String testConfig;

	@Value("${public.key}")
	private String publicKey;

	@GetMapping
	public ResponseEntity<List<Worker>> findAll() {
		List<Worker> list = repository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {

		// For class 03-05 - CircuitBreaker (replacing Hystrix)
		// Fallback test - simulating error
		// int x = 1;
		// if (x == 1) throw new RuntimeException("Test CircuitBreaker Fallback");

		// Fallback test - stressing time
		// try {
		// Thread.sleep(3000L);
		// } catch (InterruptedException e) {
		// System.out.println("Time Thread Error!");
		// }

		logger.info("PORT = " + env.getProperty("local.server.port"));

		Worker obj = repository.findById(id).get();
		return ResponseEntity.ok(obj);
	}

	/* See comment on RefreshResource about refreshing seen on class 04-04 */
	@GetMapping(value = "/configs")
	public ResponseEntity<String> getConfigs() {
		return ResponseEntity.ok().body(testConfig);
	}

}
