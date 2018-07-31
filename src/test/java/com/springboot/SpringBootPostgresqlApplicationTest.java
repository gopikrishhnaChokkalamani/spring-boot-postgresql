package com.springboot;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class SpringBootPostgresqlApplicationTest {

	@Tested
	private SpringBootPostgresqlApplication tested;

	@Mocked
	private SpringApplication springApplicationMock;

	@Test
	public void haveSpringBootApplicationAnnotation() throws Exception {
		assertThat(SpringBootPostgresqlApplication.class.getAnnotation(SpringBootApplication.class), notNullValue());
	}

	@Test
	public void runSpringApplication() throws Exception {
		final String[] args = new String[] {};
		SpringBootPostgresqlApplication.main(args);
		new Verifications() {
			{
				SpringApplication.run(SpringBootPostgresqlApplication.class, args);
			}
		};
	}
}