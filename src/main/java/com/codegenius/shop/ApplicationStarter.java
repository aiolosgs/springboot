package com.codegenius.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
public class ApplicationStarter/* extends SpringBootServletInitializer*/{

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
//		SpringApplication app = new SpringApplication(ApplicationStarter.class);
	}
	
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//		return builder.sources(ApplicationStarter.class);
//	}
}
