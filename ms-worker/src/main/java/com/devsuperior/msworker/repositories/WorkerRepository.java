package com.devsuperior.msworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.msworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
