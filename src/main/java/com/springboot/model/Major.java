package com.springboot.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Major {

	PHYSICS("physics"), CHEMISTRY("chemistry"), MATHS("maths"), BIOLOGY("biology"), @JsonEnumDefaultValue
	UNKNOWN_ENUM("unknownEnum");

	private String value;

	Major(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}
}
