package com.springboot;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mockit.Tested;

public class SpringBootPostgresqlApplicationTest {

	@Tested
	private SpringBootPostgresqlApplication tested;

	@Test
	public void haveSpringBootApplicationAnnotation() throws Exception {
		assertThat(SpringBootPostgresqlApplication.class.getAnnotations().equals(SpringBootApplication.class),
				notNullValue());
	}
}