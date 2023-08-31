package com.onrender.themba.discovercareers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(length = Length.LONG32)
    private String description;
    private String imagePath;
    @OneToMany(mappedBy = "category",  cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CareerEntity> careers = new ArrayList<>();
    @Transient
    public String getFileLocation(){
        return "/uploads/categories/"+ id + "/" + imagePath;
    }
    @Transient
    public String getSummary(){
        return description.substring(0, 140) + "...";
    }

    public CategoryEntity() {
    }

    public CategoryEntity(Long id, String title, String description, String imagePath, List<CareerEntity> careers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.careers = careers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<CareerEntity> getCareers() {
        return careers;
    }

    public void setCareers(List<CareerEntity> careers) {
        this.careers = careers;
    }
}
