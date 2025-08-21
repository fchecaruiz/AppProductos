package com.ejemplo.productos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ServicioProducto {
	
	@Autowired
	private RepositorioProducto repositorio;
	
	public List<Producto> obtenerTodos(){
		return repositorio.findAll();
	}
	
	
	public Producto guardarProducto(Producto producto) {
		return repositorio.save(producto);
	}
	
	public Optional<Producto> obtenerPorId(Long id) {
	    return repositorio.findById(id);
	}
    
	public void eliminarProducto(Long id) {
	    repositorio.deleteById(id);
	}
    
	public Producto actualizarProducto(Producto producto) {
	    return repositorio.save(producto);
	}
	
	public List<Producto> obtenerProductosDisponibles() {
	    List<Producto> todosLosProductos = obtenerTodos();
	    return todosLosProductos.subList(0, Math.min(3, todosLosProductos.size()));
	}

}
