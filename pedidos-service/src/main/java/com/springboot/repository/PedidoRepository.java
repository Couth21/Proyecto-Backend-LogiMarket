package com.springboot.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.entity.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> 
{
    Optional<PedidoEntity> findByFechaPedido(Date fechaPedido);
	boolean existsByFechaPedido(Date fechaPedido);
	
	List<PedidoEntity> findByUsuarioEntityIdUsuario(long idUsuario); // Solo este método
}
