package com.perlaagriculture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.perlaagriculture.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class PerlaagricultureApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PerlaagricultureApplication.class, args);
	}

}
