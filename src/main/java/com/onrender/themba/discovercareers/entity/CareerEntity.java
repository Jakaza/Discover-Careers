package com.onrender.themba.discovercareers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CAREER")
public class CareerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    private String slug;
//    private String description;
    private List<String> skills;
    private double salary;
//    @Column(length = Length.LONG16)
//    private String duty;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
