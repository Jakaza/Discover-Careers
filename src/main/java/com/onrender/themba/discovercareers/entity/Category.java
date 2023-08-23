package com.onrender.themba.discovercareers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CAREER_CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String imagePath;
    @OneToOne(cascade = CascadeType.ALL)
    private CategoryFile categoryFile;
}
