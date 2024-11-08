package com.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>
{
	Optional<UsuarioEntity> findByEmail(String email);
	boolean existsByEmail(String email);
	

	@Query("SELECT u FROM UsuarioEntity u WHERE u.rolEntity.idRol = ?1")
	List<UsuarioEntity> findByRolId(Integer rolId);
	
	@Query("SELECT u FROM UsuarioEntity u WHERE u.usuario = ?1")
    Optional<UsuarioEntity> findByUsername(String username);
	

	Optional<UsuarioEntity> findByDni(Integer dni);
    boolean existsByDni(Integer dni);

    Optional<UsuarioEntity> findByUsuario(String usuario);
    boolean existsByUsuario(String usuario);
    
    @Query("SELECT u FROM UsuarioEntity u WHERE u.rolEntity.idRol = :idRolProveedor")
    List<UsuarioEntity> findByRolProveedor(int idRolProveedor);
}
