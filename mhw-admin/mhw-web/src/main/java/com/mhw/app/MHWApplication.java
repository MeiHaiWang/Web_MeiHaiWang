package com.mhw.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class })
@ComponentScan(basePackages = { "com.mhw" })
@EnableJpaRepositories(basePackages = { "com.mhw.repository" })
@EntityScan(basePackages = { "com.mhw.entity" })
@EnableScheduling
public class MHWApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MHWApplication.class, args);
	}
	
}
