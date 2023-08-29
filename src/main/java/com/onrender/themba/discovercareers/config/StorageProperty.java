package com.onrender.themba.discovercareers.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperty {
    private String uploadsLocation = "uploads";
    private String staticLocation = "src/main/resources/static";
    public String getUploadsLocation() {
        return uploadsLocation;
    }
    public void setUploadsLocation(String uploadsLocation) {
        this.uploadsLocation = uploadsLocation;
    }

    public String getStaticLocation() {
        return staticLocation;
    }
    public void setStaticLocation(String staticLocation) {
        this.staticLocation = staticLocation;
    }
}
