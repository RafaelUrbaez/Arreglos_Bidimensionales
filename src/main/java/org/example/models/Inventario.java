package org.example.models;

public class Inventario {
    private int[][] stock;
    private final int numLibros = 5;
    private final int numSucursales = 3;

    public Inventario() {
        this.stock = new int[][] {
                {10, 5, 2},
                {3, 8, 12},
                {1, 2, 5},
                {7, 4, 1},
                {6, 6, 6}
        };
    }

    public int[][] getStock() { return stock; }
    public int getNumLibros() { return numLibros; }
    public int getNumSucursales() { return numSucursales; }

    public void setCantidad(int libro, int sucursal, int cantidad) {
        this.stock[libro][sucursal] = cantidad;
    }
}