package com.example.scheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.scheduler.exception.TaskNotFoundException;
import com.example.scheduler.model.Task;
import com.example.scheduler.repository.ITaskRepository;

@RestController
public class TaskController {
	
	@Autowired
	private ITaskRepository taskRepository;
	
	public TaskController(ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	@GetMapping(path = "/")
	public List<Task> getAllTasks() {
		return (List<Task>) this.taskRepository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Task getTaskById(@PathVariable Long id) {
		return this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found, id=" + id));
	}
	
	@PostMapping(path = "/")
	public Task createTask(@RequestBody Task task) {
		return this.taskRepository.save(task);
	}
	
	@PutMapping(path = "/{id}")
	public Task putTask(@PathVariable Long id, @RequestBody Task newTask) {
		return this.taskRepository.findById(id).map(
					task -> {
						task.setId(id);
						task.setName(newTask.getName());
						task.setRecurrency(newTask.getRecurrency());
						task.setCode(newTask.getCode());
						return this.taskRepository.save(task);
					}
				).orElseGet(
							() -> {
								newTask.setId(id);
								return this.taskRepository.save(newTask);
							}
						);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteTask(@PathVariable Long id) {
		this.taskRepository.deleteById(id);
	}

}
