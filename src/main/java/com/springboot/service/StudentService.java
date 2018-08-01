package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.AddressEntity;
import com.springboot.entity.StudentEntity;
import com.springboot.model.Address;
import com.springboot.model.Major;
import com.springboot.model.ResponseMessage;
import com.springboot.model.Student;
import com.springboot.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	public Student insertStudentRecord(Student student) {
		StudentEntity entity = mapModelToEntity(student);
		Student response = mapEntityToModel(repository.save(entity));
		return response;
	}

	public boolean deleteStudentRecord(String id) {
		try {
			repository.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public StudentEntity mapModelToEntity(Student student) {
		StudentEntity entity = new StudentEntity();
		entity.setAge(student.getAge());
		entity.setMajor(student.getMajor().value());
		entity.setName(student.getName());
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setCity(student.getAddress().getCity());
		addressEntity.setHouseNumber(student.getAddress().getHouseNumber());
		addressEntity.setState(student.getAddress().getState());
		addressEntity.setStreet(student.getAddress().getStreet());
		addressEntity.setZipCode(student.getAddress().getZipCode());
		entity.setAddress(addressEntity);
		return entity;
	}

	public Student mapEntityToModel(StudentEntity entity) {
		Student student = new Student();
		student.setId(entity.getId());
		student.setAge(entity.getAge());
		student.setMajor(Major.valueOf(entity.getMajor().toUpperCase()));
		student.setName(entity.getName());
		Address address = new Address();
		address.setAddress_id(entity.getAddress().getAddress_id());
		address.setCity(entity.getAddress().getCity());
		address.setHouseNumber(entity.getAddress().getHouseNumber());
		address.setState(entity.getAddress().getState());
		address.setStreet(entity.getAddress().getStreet());
		address.setZipCode(entity.getAddress().getZipCode());
		student.setAddress(address);
		ResponseMessage message = new ResponseMessage();
		message.setResponseCode("success");
		message.setResponseMsg("record added to database");
		student.setResponse(message);
		return student;
	}
}