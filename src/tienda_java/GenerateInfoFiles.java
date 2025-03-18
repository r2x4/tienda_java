package tienda_java;

import java.io.*;
import java.util.*;

public class GenerateInfoFiles {

    // Estructuras de datos
    static Map<String, Producto> productos = new HashMap<>();
    static Map<String, Vendedor> vendedores = new HashMap<>();
    static List<Venta> ventas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            cargarProductos("C:\\Users\\rrs23\\OneDrive\\Documentos\\OneDrive\\Documentos\\Datos_java\\productos.csv");
            cargarVendedores("C:\\Users\\rrs23\\OneDrive\\Documentos\\OneDrive\\Documentos\\Datos_java\\vendedores.csv"); 
            cargarVentas("C:\\Users\\rrs23\\OneDrive\\Documentos\\OneDrive\\Documentos\\Datos_java\\ventas.csv");

            generarArchivoProductos("productos.txt");
            generarArchivoVendedores("informacion_vendedores.txt");
            generarArchivoVentas("ventas.txt");

            System.out.println("Archivos generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error generando archivos: " + e.getMessage());
        }
    }


    // Cargar productos desde el archivo CSV
    public static void cargarProductos(String archivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(";");
            String idProducto = datos[0];
            String nombre = datos[1];
            double precio = Double.parseDouble(datos[2]);

            productos.put(idProducto, new Producto(idProducto, nombre, precio));
        }
        reader.close();
    }

    // Cargar vendedores desde el archivo CSV
    public static void cargarVendedores(String archivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(";");
            String idVendedor = datos[0];
            String tipoDocumento = datos[1];
            long numeroDocumento = Long.parseLong(datos[2]);
            String nombre = datos[3];
            String apellido = datos[4];

            vendedores.put(idVendedor, new Vendedor(idVendedor, tipoDocumento, numeroDocumento, nombre, apellido));
        }
        reader.close();
    }

    // Cargar ventas desde el archivo CSV
    public static void cargarVentas(String archivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(";");
            String idVendedor = datos[0];
            String idProducto = datos[1];
            int cantidad = Integer.parseInt(datos[2]);

            Producto producto = productos.get(idProducto);
            Vendedor vendedor = vendedores.get(idVendedor);

            if (producto != null && vendedor != null) {
                ventas.add(new Venta(vendedor, producto, cantidad));
            }
        }
        reader.close();
    }

    // Generar archivo de productos (productos.txt)
    public static void generarArchivoProductos(String archivo) throws IOException {
        FileWriter writer = new FileWriter(archivo);
        for (Producto producto : productos.values()) {
            writer.write(producto.getId() + ";" + producto.getNombre() + ";" + producto.getPrecio() + "\n");
        }
        writer.close();
    }

    // Generar archivo de vendedores (informacion_vendedores.txt)
    public static void generarArchivoVendedores(String archivo) throws IOException {
        FileWriter writer = new FileWriter(archivo);
        for (Vendedor vendedor : vendedores.values()) {
            writer.write(vendedor.getId() + ";" + vendedor.getTipoDocumento() + ";" + vendedor.getNumeroDocumento() + ";" + vendedor.getNombre() + ";" + vendedor.getApellido() + "\n");
        }
        writer.close();
    }

    // Generar archivo de ventas (ventas.txt)
    public static void generarArchivoVentas(String archivo) throws IOException {
        FileWriter writer = new FileWriter(archivo);
        for (Venta venta : ventas) {
            writer.write(venta.getVendedor().getId() + ";" + venta.getProducto().getId() + ";" + venta.getCantidad() + "\n");
        }
        writer.close();
    }

    // Clases internas para representar productos, vendedores y ventas

    static class Producto {
        private String id;
        private String nombre;
        private double precio;

        public Producto(String id, String nombre, double precio) {
            this.id = id;
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }
    }

    static class Vendedor {
        private String id;
        private String tipoDocumento;
        private long numeroDocumento;
        private String nombre;
        private String apellido;

        public Vendedor(String id, String tipoDocumento, long numeroDocumento, String nombre, String apellido) {
            this.id = id;
            this.tipoDocumento = tipoDocumento;
            this.numeroDocumento = numeroDocumento;
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public String getId() {
            return id;
        }

        public String getTipoDocumento() {
            return tipoDocumento;
        }

        public long getNumeroDocumento() {
            return numeroDocumento;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }
    }

    static class Venta {
        private Vendedor vendedor;
        private Producto producto;
        private int cantidad;

        public Venta(Vendedor vendedor, Producto producto, int cantidad) {
            this.vendedor = vendedor;
            this.producto = producto;
            this.cantidad = cantidad;
        }

        public Vendedor getVendedor() {
            return vendedor;
        }

        public Producto getProducto() {
            return producto;
        }

        public int getCantidad() {
            return cantidad;
        }
    }
}



