package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.model.Address;
import com.springboot.model.Student;
import com.springboot.repository.StudentRepository;

@SpringBootApplication
public class SpringBootPostgresqlApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPostgresqlApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Student student = new Student();
		//student.setId("12345");
		student.setAge("17");
		student.setName("John");
		Address address = new Address();
		//address.setAddress_id("addr123");
		address.setCity("city");
		address.setHouseNumber("A123");
		address.setState("state");
		address.setZipCode("12345");
		address.setStreet("street");
		student.setAddress(address);
		repository.save(student);
	}
}
