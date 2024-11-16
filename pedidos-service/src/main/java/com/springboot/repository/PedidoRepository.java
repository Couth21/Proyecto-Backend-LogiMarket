package com.springboot.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


import com.springboot.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
	Optional<PedidoEntity> findByFechaPedido(Date fechaPedido);

	boolean existsByFechaPedido(Date fechaPedido);

	List<PedidoEntity> findByUsuarioEntityIdUsuario(long idUsuario);

}
