package org.example;
import org.example.models.Inventario;
import org.example.services.InventarioService;
import org.example.ui.ConsolaUI;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        InventarioService service = new InventarioService(inventario);
        ConsolaUI ui = new ConsolaUI(service);
        ui.iniciar();
    }
}