package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> 
{
	List<ProductoEntity> findByUsuarioEntityIdUsuario(long idUsuario);
	/*
	List<ProductoEntity> findByUsuarioIdAndSubcategoriaId(long idUsuario, int idSubcategoria);
	*/
	List<ProductoEntity> findByUsuarioEntity_IdUsuarioAndSubcategoria_IdSubCategoria(long idUsuario, int idSubCategoria);

}