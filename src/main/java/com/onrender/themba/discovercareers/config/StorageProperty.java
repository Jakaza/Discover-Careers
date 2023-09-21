package com.onrender.themba.discovercareers.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperty {
    private String uploadsLocation = "uploads/categories";
    private String subUploadsLocation = "uploads/careers";
    private String staticLocation = "src/main/resources/static";
    private String adminStaticLocation = "src/main/resources/static/assets";
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

    public String getSubUploadsLocation() {
        return subUploadsLocation;
    }

    public void setSubUploadsLocation(String subUploadsLocation) {
        this.subUploadsLocation = subUploadsLocation;
    }

    public String getAdminStaticLocation() {
        return adminStaticLocation;
    }

    public void setAdminStaticLocation(String adminStaticLocation) {
        this.adminStaticLocation = adminStaticLocation;
    }
}
