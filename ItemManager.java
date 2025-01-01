import java.io.*;
import java.util.*;

class Product {
    String productName;
    double productPrice;

    Product(String name, double price) {
        this.productName = name;
        this.productPrice = price;
    }

    @Override
    public String toString() {
        return productName + ": $" + productPrice;
    }
}

public class ItemManager {
    private static final String DATA_FILE = "products.dat";

    public static void main(String[] args) {
        try (RandomAccessFile randomFile = new RandomAccessFile(DATA_FILE, "rw");
             Scanner sc = new Scanner(System.in)) {

            int userChoice;
            System.out.println("Item Management System");
            System.out.println("1. Search by name");
            System.out.println("2. Display all items");
            System.out.println("3. Find the most expensive item");
            System.out.println("4. Add a new item");
            System.out.println("0. Exit");

            do {
                System.out.print("Enter your choice: ");
                userChoice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (userChoice) {
                    case 1 -> {
                        System.out.print("Enter the product name to search: ");
                        searchProduct(randomFile, sc.nextLine());
                    }
                    case 2 -> displayAllProducts(randomFile);
                    case 3 -> findMostExpensiveProduct(randomFile);
                    case 4 -> {
                        System.out.print("Enter product name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter product price: ");
                        double price = sc.nextDouble();
                        addProduct(randomFile, new Product(name, price));
                    }
                    case 0 -> System.out.println("Exiting the program...");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (userChoice != 0);

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void addProduct(RandomAccessFile file, Product product) throws IOException {
        file.seek(file.length());
        file.writeUTF(product.productName);
        file.writeDouble(product.productPrice);
        System.out.println("Product added successfully: " + product);
    }

    private static void searchProduct(RandomAccessFile file, String name) throws IOException {
        file.seek(0);
        while (file.getFilePointer() < file.length()) {
            String currentName = file.readUTF();
            double currentPrice = file.readDouble();
            if (currentName.equalsIgnoreCase(name)) {
                System.out.println("Product found: " + new Product(currentName, currentPrice));
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private static void displayAllProducts(RandomAccessFile file) throws IOException {
        file.seek(0);
        boolean isAnyProduct = false;

        System.out.printf("%-20s %-10s%n", "Product Name", "Price");
        System.out.println("----------------------------");

        while (file.getFilePointer() < file.length()) {
            String name = file.readUTF();
            double price = file.readDouble();
            System.out.printf("%-20s $%-9.2f%n", name, price);
            isAnyProduct = true;
        }

        if (!isAnyProduct) {
            System.out.println("No products available.");
        }
    }

    private static void findMostExpensiveProduct(RandomAccessFile file) throws IOException {
        file.seek(0);
        double highestPrice = 0;
        String expensiveProduct = null;

        while (file.getFilePointer() < file.length()) {
            String name = file.readUTF();
            double price = file.readDouble();
            if (price > highestPrice) {
                highestPrice = price;
                expensiveProduct = name;
            }
        }

        if (expensiveProduct != null) {
            System.out.println("Most expensive product: " + expensiveProduct + " ($" + highestPrice + ")");
        } else {
            System.out.println("No products found.");
        }
    }
}
