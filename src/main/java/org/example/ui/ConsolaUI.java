package org.example.ui;
import org.example.services.InventarioService;
import java.util.Scanner;

public class ConsolaUI {
    private InventarioService service;
    private Scanner sc;

    public ConsolaUI(InventarioService service) {
        this.service = service;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n====== INVENTARIO POR SUCURSAL ======");
            System.out.println("1. Mostrar inventario completo");
            System.out.println("2. Consultar disponibilidad");
            System.out.println("3. Actualizar disponibilidad");
            System.out.println("4. Total disponible de un libro");
            System.out.println("5. Total de inventario por sucursal");
            System.out.println("6. Mostrar libros con bajo inventario");
            System.out.println("7. Sucursal con mayor disponibilidad");
            System.out.println("0. Salir");
            System.out.print("\nSeleccione una opcion: ");
            opcion = sc.nextInt();

            procesarOpcion(opcion);
        } while (opcion != 0);
        sc.close();
    }

    private void procesarOpcion(int opcion) {
        int libro, sucursal;
        switch (opcion) {
            case 1:
                mostrarInventarioCompleto();
                break;
            case 2:
                System.out.print("Ingrese el numero del libro (0-4): "); libro = sc.nextInt();
                System.out.print("Ingrese el numero de la sucursal (0-2): "); sucursal = sc.nextInt();
                if(service.validarIndices(libro, sucursal)) {
                    System.out.println("La cantidad disponible es: " + service.obtenerCantidad(libro, sucursal));
                } else { System.out.println("Indices no validos."); }
                break;
            case 3:
                System.out.print("Ingrese el numero del libro (0-4): "); libro = sc.nextInt();
                System.out.print("Ingrese el numero de la sucursal (0-2): "); sucursal = sc.nextInt();
                if(service.validarIndices(libro, sucursal)) {
                    System.out.print("Ingrese la nueva cantidad: "); int nuevaCant = sc.nextInt();
                    service.actualizarCantidad(libro, sucursal, nuevaCant);
                    System.out.println("Inventario actualizado con exito.");
                } else { System.out.println("Indices no validos."); }
                break;
            case 4:
                System.out.print("Ingrese el numero del libro (0-4): "); libro = sc.nextInt();
                if(service.validarIndices(libro, 0)) {
                    System.out.println("Total de unidades del Libro " + libro + ": " + service.calcularTotalLibro(libro));
                } else { System.out.println("Indice de libro no valido."); }
                break;
            case 5:
                System.out.print("Ingrese el numero de la sucursal (0-2): "); sucursal = sc.nextInt();
                if(service.validarIndices(0, sucursal)) {
                    System.out.println("Inventario total de la Sucursal " + sucursal + ": " + service.calcularTotalSucursal(sucursal));
                } else { System.out.println("Indice de sucursal no valido."); }
                break;
            case 6:
                mostrarBajoInventario();
                break;
            case 7:
                System.out.print("Ingrese el numero del libro (0-4): "); libro = sc.nextInt();
                if(service.validarIndices(libro, 0)) {
                    int[] resultado = service.obtenerMayorDisponibilidad(libro);
                    System.out.println("Mayor disponibilidad en Sucursal " + resultado[0] + " con " + resultado[1] + " unidades.");
                } else { System.out.println("Indice de libro no valido."); }
                break;
            case 0:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    private void mostrarInventarioCompleto() {
        int[][] stock = service.getInventario().getStock();
        System.out.println("\n--- Inventario Completo ---");

        // Imprimir espacio vacío inicial (12 caracteres)
        System.out.printf("%-12s", "");

        // Imprimir encabezados de sucursales alineados a la izquierda (10 caracteres)
        for (int j = 0; j < service.getInventario().getNumSucursales(); j++) {
            System.out.printf("%-10s", "Suc" + j);
        }
        System.out.println();

        // Imprimir las filas
        for (int i = 0; i < service.getInventario().getNumLibros(); i++) {
            // Imprimir "Libro X:" alineado a la izquierda (12 caracteres)
            System.out.printf("%-12s", "Libro " + i + ":");

            // Imprimir las cantidades alineadas a la izquierda (10 caracteres)
            for (int j = 0; j < service.getInventario().getNumSucursales(); j++) {
                System.out.printf("%-10d", stock[i][j]);
            }
            System.out.println();
        }
    }

    private void mostrarBajoInventario() {
        int[][] stock = service.getInventario().getStock();
        System.out.println("\n--- Alertas de Bajo Inventario (<= 3) ---");
        boolean hayAlertas = false;
        for (int i = 0; i < service.getInventario().getNumLibros(); i++) {
            for (int j = 0; j < service.getInventario().getNumSucursales(); j++) {
                if (stock[i][j] <= 3) {
                    System.out.println("Libro " + i + " en Sucursal " + j + " (Cantidad: " + stock[i][j] + ")");
                    hayAlertas = true;
                }
            }
        }
        if(!hayAlertas) System.out.println("No hay libros con bajo inventario.");
    }
}