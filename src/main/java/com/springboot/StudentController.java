package com.springboot;

import java.util.List;

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
	public ResponseEntity<List<Student>> getStudentDetails(@PathVariable String name) {
		List<Student> result = repository.findByName(name);
		if (result.size() == 0) {
			throw new StudentNotFoundException();
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Student> insertStudentDetails(@RequestBody Student student) {
		Student result = repository.save(student);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
}
