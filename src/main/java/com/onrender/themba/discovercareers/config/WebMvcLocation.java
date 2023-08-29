package com.onrender.themba.discovercareers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
@Configuration
public class WebMvcLocation implements WebMvcConfigurer {
    private final Path uploadsLocation;
    public WebMvcLocation(StorageProperty storageProperty) {
        this.uploadsLocation = Paths.get(storageProperty.getUploadsLocation());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absolutePath = this.uploadsLocation.toFile().getAbsolutePath();
        System.out.println(absolutePath);
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:/"+ absolutePath);
    }
}
