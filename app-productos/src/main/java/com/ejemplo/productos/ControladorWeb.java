package com.ejemplo.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorWeb {

	
	@Autowired
	private ServicioProducto servicio;
	
	@GetMapping("/vista")
	public String mostrarVista(Model modelo) {
		modelo.addAttribute("productos", servicio.obtenerTodos());
		return "lista";
	}
	
	@GetMapping("/prueba")
	public String vistaPrueba() {
		return "prueba";
	}
	
	// METODO PARA RECIBIR DATOS DEL FORMULARIO
	
	@PostMapping("/formulario")
	public String guardarDatosFormulario(Producto producto) {
		servicio.guardarProducto(producto);

		return "redirect:/vista";
    }
	
	@GetMapping("/formulario")
	public String mostrarFormulario() {
		return "formulario";
	}
}
