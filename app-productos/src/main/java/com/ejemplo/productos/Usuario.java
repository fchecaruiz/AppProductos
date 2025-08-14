package com.ejemplo.productos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuariosdatos")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long idusuarios;
    
    @Column(name = "nombreUsuario",nullable = false)	
    private String nombreUsuario;
    
    //@Column(name = "contrasena", nullable = false)

    private String contrasena;    
    
    private String rol;
    
    public Usuario() {}

    public Usuario(String nombreUsuario, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    
    public Long getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(Long idusuarios) {
        this.idusuarios = idusuarios;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
}
