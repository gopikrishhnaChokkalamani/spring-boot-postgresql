package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	List<Student> findByName(String name);
}