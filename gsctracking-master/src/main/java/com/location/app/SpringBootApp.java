package com.location.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan("com")
@SpringBootApplication(scanBasePackages={
		"com", "com.java4s"})
public class SpringBootApp extends SpringBootServletInitializer{
	public static void main(String[] args) {

		 SpringApplication.run(SpringBootApp.class, args);

	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringBootApp.class);
	}
}