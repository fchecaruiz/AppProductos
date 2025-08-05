package com.ejemplo.productos;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ControladorWeb {

	
	@Autowired
	private ServicioProducto servicio;
	
	@Autowired
	private Carrito carrito;
	
	@Autowired
	private RepositorioProducto repositorioProducto;
	
	@GetMapping("/vista")
	public String mostrarVista(Model modelo, Authentication authentication) {
		modelo.addAttribute("productos", servicio.obtenerTodos());
		modelo.addAttribute("carrito", carrito);
		
		if (authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			modelo.addAttribute("esAdmin", true);
		} else {
			modelo.addAttribute("esAdmin", false);
		}

		if (authentication != null && authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			modelo.addAttribute("esAdmin", true);
		} else {
			modelo.addAttribute("esAdmin", false);
		}

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
	
	
	@PostMapping("/carrito/agregar/{id}")
	public String agregarAlCarrito(@PathVariable Long id) {
	    Optional<Producto> producto = servicio.obtenerPorId(id);
	    if (producto.isPresent()) {
	        carrito.agregarProducto(producto.get());
	    }
	    return "redirect:/vista";
	}
	
	@GetMapping("/carrito/resetear")
    public String resetearCarrito() {
        carrito.getItems().clear();
        return "redirect:/vista";
    }
	
	@GetMapping("/carrito/eliminar/{id}")
    public String eliminarDelCarrito(@PathVariable Long id) {
        carrito.removeItem(id);
        return "redirect:/vista";
    }
	
	
	@GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        repositorioProducto.deleteById(id);
        return "redirect:/vista";
    }
	
	@GetMapping("/eliminar-todos")
    public String eliminarTodosLosProductos() {
        repositorioProducto.deleteAll();
        return "redirect:/vista";
    }
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Producto producto = repositorioProducto.findById(id).orElse(null);
		
		if (producto != null) {
            model.addAttribute("producto", producto);
            return "editar";
        }
        return "redirect:/vista";
	}
	
	@PostMapping("/guardar-edicion")
    public String guardarEdicion(Producto producto) {
		repositorioProducto.save(producto);
        return "redirect:/vista";
    }
	
	@GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            System.out.println("Login exitoso como administrador");
            return "redirect:/";
        }
        return "redirect:/login?error";
    }

}

