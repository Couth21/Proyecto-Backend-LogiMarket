package com.springboot.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.springboot.entity.PedidoEntity;
import com.springboot.entity.UsuarioEntity;
import com.springboot.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService 
{
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
    private RestTemplate restTemplate;
	
	
	public UsuarioEntity getPedidoEntity(int idUsuario) {
        String url = "http://localhost:9091/usuario/detail/" + idUsuario;
        try {
            return restTemplate.getForObject(url, UsuarioEntity.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }
	
	public List<PedidoEntity> list()
	{
		return pedidoRepository.findAll();
	}
	
	public Optional<PedidoEntity> getOne(int id)
	{
		return pedidoRepository.findById(id);
	}
	
	public Optional<PedidoEntity> getByFechaPedido(Date fechaPedido)
	{
		return pedidoRepository.findByFechaPedido(fechaPedido);
	}
	
	public void save(PedidoEntity pedidoEntity)
	{
		pedidoRepository.save(pedidoEntity);
	}
	
	public void delete (int id)
	{
		pedidoRepository.deleteById(id);
	}
}
