package com.onrender.themba.discovercareers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
@Configuration
public class WebMvcLocation implements WebMvcConfigurer {
    private final Path uploadsLocation;
    private final Path staticLocation;
    public WebMvcLocation(StorageProperty storageProperty) {
        this.uploadsLocation = Paths.get(storageProperty.getUploadsLocation());
        this.staticLocation = Paths.get(storageProperty.getStaticLocation());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadsLocationAbsolutePath = this.uploadsLocation.toFile().getAbsolutePath();
        System.out.println(uploadsLocationAbsolutePath);
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:/"+ uploadsLocationAbsolutePath + "/");
        String staticLocationAbsolutePath = this.staticLocation.toFile().getAbsolutePath();
        System.out.println(staticLocationAbsolutePath);
        registry.addResourceHandler("//**").addResourceLocations("classpath:/" +staticLocationAbsolutePath );
    }
}
