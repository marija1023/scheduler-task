package com.example.scheduler.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.scheduler.exception.TaskNotFoundException;
import com.example.scheduler.model.Task;
import com.example.scheduler.repository.ITaskRepository;

@RestController
public class TaskController {
	
	@Autowired
	private ITaskRepository taskRepository;
	
	@Autowired
	public TaskController(ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
//	@GetMapping(path = "/")
//	public List<Task> getAllTasks() {
//		return (List<Task>) this.taskRepository.findAll();
//	}
	
	@GetMapping(path = "/")
	public ModelAndView getAllTasks() {
		Map<String, List<Task>> model = new HashMap<>();
		model.put("tasks", this.taskRepository.findAll());
		return new ModelAndView("index", model);
	}
	
//	@GetMapping(path = "/{id}")
//	public Task getTaskById(@PathVariable Long id) {
//		return this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found, id=" + id));
//	}
	@GetMapping(path = "/{id}")
	public ModelAndView getAllTasks(@PathVariable Long id) {
		Map<String, Task> model = new HashMap<>();
		model.put("task", this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found")));
		return new ModelAndView("task", model);
	}
	
//	@PostMapping(path = "/new")
//	public Task createTask(@RequestBody Task task) {
//		return this.taskRepository.save(task);
//	}
	@PostMapping(path = "/new")
	public String createTask(@RequestBody Task task) {
		Map<String, Task> model = new HashMap<>();
		model.put("task", this.taskRepository.save(task));
		return "redirect:/index";
	}
	
//	@PutMapping(path = "/edit/{id}")
//	public Task putTask(@PathVariable Long id, @RequestBody Task newTask) {
//		return this.taskRepository.findById(id).map(
//					task -> {
//						task.setId(id);
//						task.setName(newTask.getName());
//						task.setRecurrency(newTask.getRecurrency());
//						task.setCode(newTask.getCode());
//						return this.taskRepository.save(task);
//					}
//				).orElseGet(
//							() -> {
//								newTask.setId(id);
//								return this.taskRepository.save(newTask);
//							}
//						);
//	}

	@PutMapping(path = "/edit/{id}")
	public String putTask(@PathVariable Long id, @RequestBody Task newTask) {
		Task result =  this.taskRepository.findById(id).map(
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
		Map<String, Task> model = new HashMap<>();
		model.put("task", result);
		return "redirect:/index";
	}
	
	
	@DeleteMapping(path = "/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
		this.taskRepository.deleteById(id);
		return "redirect:/index";
	}

}
