package com.onrender.themba.discovercareers.repository;

import com.onrender.themba.discovercareers.entity.CareerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<CareerEntity, Long> {
}
