package com.ejemplo.productos;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


@Component
@SessionScope
public class Carrito {

    private List<Producto> items = new ArrayList<>();
    
    public List<Producto> getItems() { // METODO GET PARA ACCEDER CON THYMELEAF
        return items;
    }

    public void agregarProducto(Producto producto) {
        this.items.add(producto);
    }

    // Método para eliminar un producto por su id
    public void eliminarProducto(Long id) {
        Iterator<Producto> iterator = items.iterator();
        while (iterator.hasNext()) {
            Producto p = iterator.next();
            if (p.getId().equals(id)) {
                iterator.remove();
                break;  // salir tras eliminar 1 producto
            }
        }
    }

    public double getPrecioTotal() {
        return items.stream().mapToDouble(Producto::getPrecio).sum();
    }
    
    public double getPrecioTotalConIVA() {
        double iva = getPrecioTotal() * 0.21; // Calculo del 21% de IVA
        return getPrecioTotal() + iva;
    }
}
