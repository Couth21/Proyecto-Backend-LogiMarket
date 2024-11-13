package com.springboot.dto;

import com.springboot.entity.ProductoEntity;

public class ProductoDto {
	private long idProducto;
	private long idUsuario;
    private int idSubCategoria;
    private ProductoEntity producto;
    private int idCategoria;
    
    
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}
	public ProductoEntity getProducto() {
		return producto;
	}
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
    
    
    

}
