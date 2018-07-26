package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@GetMapping(path = "{name}")
	public ResponseEntity<Student> getStudentDetails(@PathVariable String name) {
		return new ResponseEntity<Student>(repository.findByName(name), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> insertStudentDetails(@RequestBody Student student) {
		repository.save(student);
		return new ResponseEntity<String>("Successfully installed", HttpStatus.OK);
	}
}
