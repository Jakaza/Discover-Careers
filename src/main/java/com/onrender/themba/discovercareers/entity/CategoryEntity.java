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
@Table(name = "CAREER_CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(length = Length.LONG32)
    private String description;
    private String imagePath;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CareerEntity> careers = new ArrayList<>();

    @Transient
    public String getFileLocation(){
        return "/uploads/"+ id + "/" + imagePath;
    }
    @Transient
    public String getSummary(){
        return description.substring(0, 140) + "...";
    }

}
