package com.example.scheduler.model;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

import org.codehaus.groovy.tools.shell.Shell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

@Entity
@Component
public class Task {
  
	@Id
	@GeneratedValue
	private Long Id;
	
	@Size(max = 50)
	private String Name;
	
	@Size(max = 30)
	private String Recurrency;
	
	@Lob
	private String Code;
	
	public Task() {	}
	
	public Task(String name, String rec, String code) {
		System.out.println("konstruktor");
		this.Name = name;
		this.Recurrency = rec;
		this.Code = code;
		
	}
	
	public Long getId() {
		return Id;
	}
	
	public String getName() {
		return Name;
	}
	
	public String getRecurrency() {
		return Recurrency;
	}
	
	public String getCode() {
		return Code;
	}
	
	public void setId(Long id) {
		Id = id;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public void setRecurrency(String recurrency) {
		Recurrency = recurrency;
	}
	
	public void setCode(String code) {
		Code = code;
	}
	
	@Override
	public String toString() {
		return "{id: " + this.Id + ", name: " + this.Name + ", recurrency: " + this.Recurrency + ", code: " + this.Code + "}";
	}
}
