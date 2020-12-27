package com.example.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scheduler.model.Task;

public interface ITaskRepository extends JpaRepository<Task, Long> {

}
