package com.ejemplo.productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ServicioUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;
    
    @Autowired
    private PasswordEncoder codificador;

//    @Autowired
//    private BCryptPasswordEncoder codificador;

    public void guardarUsuario(Usuario usuario) {
    	System.out.println("Usuario recibido: " + usuario.getNombreUsuario());
        String contrasenaCodificada = codificador.encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaCodificada);
        repositorioUsuario.save(usuario);
    }

    public Optional<Usuario> buscarPorNombre(String nombreUsuario) {
        return repositorioUsuario.findByNombreUsuario(nombreUsuario);
    }
}

