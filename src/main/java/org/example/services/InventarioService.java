package org.example.services;
import org.example.models.Inventario;

public class InventarioService {
    private Inventario inventario;

    public InventarioService(Inventario inventario) {
        this.inventario = inventario;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public boolean validarIndices(int libro, int sucursal) {
        return libro >= 0 && libro < inventario.getNumLibros() &&
                sucursal >= 0 && sucursal < inventario.getNumSucursales();
    }

    public int obtenerCantidad(int libro, int sucursal) {
        return inventario.getStock()[libro][sucursal];
    }

    public void actualizarCantidad(int libro, int sucursal, int cantidad) {
        inventario.setCantidad(libro, sucursal, cantidad);
    }

    public int calcularTotalLibro(int libro) {
        int total = 0;
        for (int j = 0; j < inventario.getNumSucursales(); j++) {
            total += inventario.getStock()[libro][j];
        }
        return total;
    }

    public int calcularTotalSucursal(int sucursal) {
        int total = 0;
        for (int i = 0; i < inventario.getNumLibros(); i++) {
            total += inventario.getStock()[i][sucursal];
        }
        return total;
    }

    // Retorna [sucursal, cantidad]
    public int[] obtenerMayorDisponibilidad(int libro) {
        int maxCantidad = -1;
        int mejorSucursal = -1;
        for (int j = 0; j < inventario.getNumSucursales(); j++) {
            if (inventario.getStock()[libro][j] > maxCantidad) {
                maxCantidad = inventario.getStock()[libro][j];
                mejorSucursal = j;
            }
        }
        return new int[]{mejorSucursal, maxCantidad};
    }
}