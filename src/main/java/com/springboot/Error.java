package com.springboot;

public enum Error {

	STUDENT_ERROR_CODE("noData"), STUDENT_ERROR_MESSAGE("No Data Available for this Request");

	private String value;

	Error(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}