package com.ejemplo.productos;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@SessionScope
@Component
public class Carrito {

    private List<Producto> items = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        this.items.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        this.items.remove(producto);
    }

    public List<Producto> getItems() {
        return items;
    }

    public double getPrecioTotal() {
        return items.stream().mapToDouble(Producto::getPrecio).sum();
    }
    public void removeItem(Long id) {
        items.removeIf(item -> item.getId().equals(id));
    }
}