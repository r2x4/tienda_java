package tienda_java;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            Map<String, Double> salesBySalesman = new HashMap<>();
            Map<String, Integer> productQuantities = new HashMap<>();

            // Leer archivos de ventas
            File salesFolder = new File(".");
            for (File file : salesFolder.listFiles()) {
                if (file.getName().startsWith("salesman_") && file.getName().endsWith(".txt")) {
                    processSalesFile(file, salesBySalesman, productQuantities);
                }
            }

            // Leer archivo de información de vendedores
            Map<String, String> salesmanInfo = readSalesmanInfo("salesmen_info.txt");

            // Leer archivo de productos
            Map<String, String> productInfo = readProductInfo("products.txt");

            // Generar reporte de ventas por vendedor
            generateSalesReport(salesBySalesman, salesmanInfo);

            // Generar reporte de productos vendidos
            generateProductReport(productQuantities, productInfo);

            System.out.println("Reportes generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error procesando archivos: " + e.getMessage());
        }
    }

    private static void processSalesFile(File file, Map<String, Double> salesBySalesman, Map<String, Integer> productQuantities) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            String[] header = line.split(";");
            String salesmanKey = header[0] + ";" + header[1];

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String productId = parts[0];
                int quantity = Integer.parseInt(parts[1]);

                // Actualizar ventas por vendedor
                double totalSales = salesBySalesman.getOrDefault(salesmanKey, 0.0);
                double productPrice = getProductPrice(productId);
                totalSales += quantity * productPrice;
                salesBySalesman.put(salesmanKey, totalSales);

                // Actualizar cantidades de productos vendidos
                productQuantities.put(productId, productQuantities.getOrDefault(productId, 0) + quantity);
            }
        }
    }

    private static double getProductPrice(String productId) {
        // Este método debería leer el archivo de productos para obtener el precio
        // Aquí se asume un precio fijo para simplificar
        return 100.0; // Precio fijo para simplificar
    }

    private static Map<String, String> readSalesmanInfo(String fileName) throws IOException {
        Map<String, String> salesmanInfo = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String key = parts[0] + ";" + parts[1];
                String value = parts[2] + " " + parts[3];
                salesmanInfo.put(key, value);
            }
        }
        return salesmanInfo;
    }

    private static Map<String, String> readProductInfo(String fileName) throws IOException {
        Map<String, String> productInfo = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                productInfo.put(parts[0], parts[1] + ";" + parts[2]);
            }
        }
        return productInfo;
    }

    private static void generateSalesReport(Map<String, Double> salesBySalesman, Map<String, String> salesmanInfo) throws IOException {
        List<Map.Entry<String, Double>> sortedSales = new ArrayList<>(salesBySalesman.entrySet());
        sortedSales.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        try (FileWriter writer = new FileWriter("sales_report.csv")) {
            for (Map.Entry<String, Double> entry : sortedSales) {
                String salesmanName = salesmanInfo.get(entry.getKey());
                writer.write(salesmanName + ";" + entry.getValue() + "\n");
            }
        }
    }

    private static void generateProductReport(Map<String, Integer> productQuantities, Map<String, String> productInfo) throws IOException {
        List<Map.Entry<String, Integer>> sortedProducts = new ArrayList<>(productQuantities.entrySet());
        sortedProducts.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        try (FileWriter writer = new FileWriter("product_report.csv")) {
            for (Map.Entry<String, Integer> entry : sortedProducts) {
                String productDetails = productInfo.get(entry.getKey());
                writer.write(productDetails + ";" + entry.getValue() + "\n");
            }
        }
    }
}