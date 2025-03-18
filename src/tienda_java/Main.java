package tienda_java;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            // Cargar los archivos CSV de productos, vendedores y ventas desde las rutas indicadas
            GenerateInfoFiles.cargarProductos("C:\\Users\\rrs23\\OneDrive\\Documentos\\OneDrive\\Documentos\\Datos_java\\productos.csv");
            GenerateInfoFiles.cargarVendedores("C:\\Users\\rrs23\\OneDrive\\Documentos\\OneDrive\\Documentos\\Datos_java\\vendedores.csv");
            GenerateInfoFiles.cargarVentas("C:\\Users\\rrs23\\OneDrive\\Documentos\\OneDrive\\Documentos\\Datos_java\\ventas.csv");

            // Generar los archivos de salida en formato TXT y CSV
            GenerateInfoFiles.generarArchivoProductos("productos.txt", "productos.csv");
            GenerateInfoFiles.generarArchivoVendedores("informacion_vendedores.txt", "vendedores.csv");
            GenerateInfoFiles.generarArchivoVentas("ventas.txt", "ventas.csv");

            System.out.println("Archivos generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error generando archivos: " + e.getMessage());
        }
    }
}



