package com.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.VentaEntity;

public interface VentaRepository extends JpaRepository<VentaEntity, Long>
{

}