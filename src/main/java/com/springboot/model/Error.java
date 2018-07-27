package com.springboot.model;

public enum Error {

	NO_DATA_ERROR_CODE("noData"), NO_DATA_ERROR_MESSAGE(
			"No Data Available for this Request"), UNKNOWN_PROPERTY_ERROR_CODE("unknownElement");

	private String value;

	Error(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}