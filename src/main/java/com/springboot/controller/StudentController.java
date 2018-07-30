package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.exception.StudentNotFoundException;
import com.springboot.model.Student;
import com.springboot.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@Value("${configuration.property.value}")
	private String from_config_server;

	@DeleteMapping(path = "{id}")
	public ResponseEntity<String> deleteStudentDetails(@PathVariable(name = "id") String id) {
		repository.delete(id);
		return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
	}

	@GetMapping(path = "/config")
	public String fromConfigServer() {
		return from_config_server;
	}

	@GetMapping(path = "{name}")
	public ResponseEntity<List<Student>> getStudentDetails(@PathVariable String name) {
		final List<Student> result = repository.findByName(name);
		if (result.size() == 0)
			throw new StudentNotFoundException();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Student> insertStudentDetails(@RequestBody Student student) {
		return new ResponseEntity<>(repository.save(student), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Student> updateStudentDetails(@RequestBody Student student) {
		final Student responseBody = repository.saveAndFlush(student);
		return new ResponseEntity<>(responseBody, HttpStatus.OK);
	}
}