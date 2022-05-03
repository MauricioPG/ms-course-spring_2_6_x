package com.devsuperior.msuser.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.msuser.repositories.RoleRepository;

@RestController
@RequestMapping(value = "/roles")
public class RoleResources {
	
	@Autowired
	private RoleRepository repository;
	
	@GetMapping
	public ResponseEntity<List<String>> findAllRoles() {
		return ResponseEntity.ok(repository.findAllRoles());
	}
}
