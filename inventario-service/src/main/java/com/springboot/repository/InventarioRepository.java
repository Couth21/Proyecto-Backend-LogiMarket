package com.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.entity.InventarioEntity;


public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer>
{
	Optional<InventarioEntity> findByCantidadDisponible(int cantidadDisponible);
    boolean existsByCantidadDisponible(int cantidadDisponible);
    
    List<InventarioEntity> findByUsuarioEntityIdUsuario(long idUsuario); // Solo este método
    
 // Si la propiedad en InventarioEntity es 'rol', utiliza:
    List<InventarioEntity> findByRolId(int id);

    // Si la propiedad en InventarioEntity se llama 'rolEntity', utiliza:
    List<InventarioEntity> findByRolEntityId(int id);

}
