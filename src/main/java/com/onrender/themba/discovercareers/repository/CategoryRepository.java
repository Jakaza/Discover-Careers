package com.onrender.themba.discovercareers.repository;

import com.onrender.themba.discovercareers.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
