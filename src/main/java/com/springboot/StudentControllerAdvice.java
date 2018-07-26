package com.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException exception) {
		ErrorResponse response = new ErrorResponse();
		response.setCode(Error.STUDENT_ERROR_CODE.value());
		response.setMessage(Error.STUDENT_ERROR_MESSAGE.value());
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}
}
