package com.devsuperior.msuser.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.msuser.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("select obj.roleName from Role obj")
	List<String> findAllRoles();
}
