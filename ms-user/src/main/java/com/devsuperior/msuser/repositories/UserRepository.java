package com.devsuperior.msuser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.msuser.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
