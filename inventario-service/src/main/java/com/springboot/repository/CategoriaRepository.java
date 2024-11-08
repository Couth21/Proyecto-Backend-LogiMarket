package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    // Métodos personalizados si los necesitas
}