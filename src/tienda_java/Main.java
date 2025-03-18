package tienda_java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    private static final String[] NOMBRES = {"Juan", "Maria", "Carlos", "Ana", "Luis", "Laura"};
    private static final String[] APELLIDOS = {"Gomez", "Rodriguez", "Lopez", "Martinez", "Garcia", "Perez"};
    private static final String[] NOMBRES_PRODUCTOS = {"Laptop", "Smartphone", "Tablet", "Monitor", "Teclado", "Ratón"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            crearArchivosVendedores(5);  // Genera archivos de ventas
            crearArchivoProductos(10);  // Genera archivo de productos
            crearArchivoInformacionVendedores(5);  // Genera archivo de vendedores
            System.out.println("Archivos generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error generando archivos: " + e.getMessage());
        }
    }

    public static void crearArchivosVendedores(int cantidadVendedores) throws IOException {
        for (int i = 1; i <= cantidadVendedores; i++) {
            String nombreArchivo = "vendedor_" + i + ".txt";
            try (FileWriter writer = new FileWriter(nombreArchivo)) {
                String tipoDocumento = "CC";
                long numeroDocumento = 10000000L + i;
                writer.write(tipoDocumento + ";" + numeroDocumento + "\n");

                for (int j = 0; j < random.nextInt(10) + 1; j++) {
                    int idProducto = random.nextInt(10) + 1;
                    int cantidad = random.nextInt(5) + 1;
                    writer.write(idProducto + ";" + cantidad + ";\n");
                }
            }
        }
    }

    public static void crearArchivoProductos(int cantidadProductos) throws IOException {
        try (FileWriter writer = new FileWriter("productos.txt")) {
            for (int i = 1; i <= cantidadProductos; i++) {
                String nombreProducto = NOMBRES_PRODUCTOS[random.nextInt(NOMBRES_PRODUCTOS.length)];
                double precio = 50 + random.nextInt(500);  
                writer.write(i + ";" + nombreProducto + ";" + precio + "\n");
            }
        }
    }

    public static void crearArchivoInformacionVendedores(int cantidadVendedores) throws IOException {
        try (FileWriter writer = new FileWriter("informacion_vendedores.txt")) {
            for (int i = 1; i <= cantidadVendedores; i++) {
                String tipoDocumento = "CC";
                long numeroDocumento = 10000000L + i;
                String primerNombre = NOMBRES[random.nextInt(NOMBRES.length)];
                String apellido = APELLIDOS[random.nextInt(APELLIDOS.length)];
                writer.write(tipoDocumento + ";" + numeroDocumento + ";" + primerNombre + ";" + apellido + "\n");
            }
        }
    }
}


