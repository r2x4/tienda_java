package tienda_java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    private static final String[] FIRST_NAMES = {"Juan", "Maria", "Carlos", "Ana", "Luis", "Laura"};
    private static final String[] LAST_NAMES = {"Gomez", "Rodriguez", "Lopez", "Martinez", "Garcia", "Perez"};
    private static final String[] PRODUCT_NAMES = {"Laptop", "Smartphone", "Tablet", "Monitor", "Keyboard", "Mouse"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            createSalesMenFiles(5);
            createProductsFile(10);
            createSalesManInfoFile(5);
            System.out.println("Archivos generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error generando archivos: " + e.getMessage());
        }
    }

    public static void createSalesMenFiles(int randomSalesCount) throws IOException {
        for (int i = 1; i <= randomSalesCount; i++) {
            String fileName = "salesman_" + i + ".txt";
            try (FileWriter writer = new FileWriter(fileName)) {
                String documentType = "CC";
                long documentNumber = 10000000L + i;
                writer.write(documentType + ";" + documentNumber + "\n");

                for (int j = 0; j < random.nextInt(10) + 1; j++) {
                    int productId = random.nextInt(10) + 1;
                    int quantity = random.nextInt(5) + 1;
                    writer.write(productId + ";" + quantity + ";\n");
                }
            }
        }
    }

    public static void createProductsFile(int productsCount) throws IOException {
        try (FileWriter writer = new FileWriter("products.txt")) {
            for (int i = 1; i <= productsCount; i++) {
                String productName = PRODUCT_NAMES[random.nextInt(PRODUCT_NAMES.length)];
                double price = 50 + random.nextInt(500);
                writer.write(i + ";" + productName + ";" + price + "\n");
            }
        }
    }

    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        try (FileWriter writer = new FileWriter("salesmen_info.txt")) {
            for (int i = 1; i <= salesmanCount; i++) {
                String documentType = "CC";
                long documentNumber = 10000000L + i;
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                writer.write(documentType + ";" + documentNumber + ";" + firstName + ";" + lastName + "\n");
            }
        }
    }
}