package com.codegenius.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan//扫描filter
@MapperScan("com.codegenius.shop.web.mapper")
public class ApplicationStarter/* extends SpringBootServletInitializer*/{

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
//		SpringApplication app = new SpringApplication(ApplicationStarter.class);
	}
	
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//		return builder.sources(ApplicationStarter.class);
//	}
}
