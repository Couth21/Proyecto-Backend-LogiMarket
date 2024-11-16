package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.entity.DetallePedidoEntity;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Integer> {
    // Puedes agregar consultas personalizadas si las necesitas
}
