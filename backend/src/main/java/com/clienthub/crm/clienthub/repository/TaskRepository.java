package com.clienthub.crm.clienthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clienthub.crm.clienthub.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
