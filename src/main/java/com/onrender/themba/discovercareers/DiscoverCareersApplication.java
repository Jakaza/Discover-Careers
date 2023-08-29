package com.onrender.themba.discovercareers;

import com.onrender.themba.discovercareers.config.StorageProperty;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.FileSystemUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperty.class)
public class DiscoverCareersApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiscoverCareersApplication.class, args);
	}
	@Bean
	CommandLineRunner init(){
		return (args -> {
			StorageProperty storageProperty = new StorageProperty();
			Path root = Paths.get(storageProperty.getUploadsLocation());
			FileSystemUtils.deleteRecursively(root.toFile());
		});
	}
}
