package com.ejemplo.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorAutenticacion {

    @Autowired
    private ServicioUsuario servicioUsuario;
    
    @Autowired
    private PasswordEncoder codificarContrasena;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("username") String nombreUsuario,
                                @RequestParam("password") String contrasena,
                                Model model) {

    	Usuario usuario = servicioUsuario.buscarPorNombre(nombreUsuario)
    		    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        System.out.println("Usuario encontrado: " + (usuario != null));
        if (usuario != null) {
            System.out.println("Contraseña guardada (hash): " + usuario.getContrasena());
            System.out.println("Contraseña recibida: " + contrasena);
        }

        if (usuario != null && codificarContrasena.matches(contrasena, usuario.getContrasena())) {
            if ("admin".equals(usuario.getRol())) {
                return "redirect:/admin";
            } else {
                return "redirect:/";
            }
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

}
