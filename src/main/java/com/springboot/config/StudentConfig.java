package com.springboot.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.validator.ValidationRegistry;

@Configuration
public class StudentConfig {

	@Bean
	public FactoryBean<?> factoryBean() {
		ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
		bean.setServiceLocatorInterface(ValidationRegistry.class);
		return bean;
	}
}