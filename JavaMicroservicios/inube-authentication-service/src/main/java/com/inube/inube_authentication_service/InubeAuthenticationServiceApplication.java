package com.inube.inube_authentication_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableConfigurationProperties
@SpringBootApplication
public class InubeAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InubeAuthenticationServiceApplication.class, args);
	}

}
