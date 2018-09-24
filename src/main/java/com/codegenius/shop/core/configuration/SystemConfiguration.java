package com.codegenius.shop.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
//@Profile("system")
@ConfigurationProperties(prefix="system")
public class SystemConfiguration {

	private String name;
	
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
