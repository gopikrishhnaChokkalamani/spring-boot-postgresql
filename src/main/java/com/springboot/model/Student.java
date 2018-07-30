package com.springboot.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(Include.NON_EMPTY)
@Entity
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(access = Access.READ_ONLY)
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
	// @GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private int id;

	private String name;

	private String age;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "address_id")
	private Address address;

	private Major major;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	@JsonProperty(access = Access.READ_ONLY)
	private String responseCode;

	@JsonProperty(access = Access.READ_ONLY)
	private String responseMsg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
}