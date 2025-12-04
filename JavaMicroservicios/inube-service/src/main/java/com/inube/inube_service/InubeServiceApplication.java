package com.inube.inube_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication
public class InubeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InubeServiceApplication.class, args);
	}

}
