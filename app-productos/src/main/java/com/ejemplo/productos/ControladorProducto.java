package com.ejemplo.productos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorProducto {
	
	@Autowired
	private ServicioProducto servicio;
	
	@GetMapping("/productos")
	public List<Producto> lista(){
		return servicio.obtenerTodos();
	}
	
	@PostMapping("/productos")
	public Producto guardar(@RequestBody Producto producto) {
		return servicio.guardarProducto(producto);
	}
	
	@GetMapping("/productos/{id}")
	public Optional<Producto> obtenerPorId(@PathVariable Long id) {
	    return servicio.obtenerPorId(id);
	}

	@DeleteMapping("/productos/{id}")
	public void eliminar(@PathVariable Long id) {
	    servicio.eliminarProducto(id);
	}

	@PutMapping("/productos")
	public Producto actualizar(@RequestBody Producto producto) {
	    return servicio.actualizarProducto(producto);
	}
	


}
