package com.mhw.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class ProjectConfigurer {
	
	static {
		
		TimeZone.setDefault(TimeZone.getTimeZone("+09:00"));
	}

	@Bean
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
		return builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).build();
	}
	
}
