import Modules.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner      = new Scanner(System.in);
        boolean isMenuOpened = true;
        int userChoice;
        String input;
        double price;

        Inventory<Product> inventory = new Inventory<>();
        Category categoryChosen = Category.FOOD;
        int productId = 1;

        String[] mainMenuOptions = {
            "Add product",
            "Remove product",
            "Find product by ID",
            "Browse by category",
            "Show all products",
            "Show most expensive product",
        };

        while(isMenuOpened) {
            System.out.println("===== INVENTORY MANAGER =====");
            System.out.println("Choose from one of the options below: ");
            for (int i = 0; i < mainMenuOptions.length; i++) {
                System.out.println((i + 1) + " " + mainMenuOptions[i]);
            }
            System.out.println("0. Exit");

            try {
                userChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                scanner.next();
                continue;
            }

            switch (userChoice) {
                // Exit
                case 0:
                    isMenuOpened = false;
                    System.out.println("Goodbye!!!");
                    break;

                // Add Product
                case 1:
                    System.out.println("Enter product name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();

                    try {
                        System.out.println("Enter product price: ");
                        price = scanner.nextDouble();
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid product price, please enter a number");
                        continue;
                    }

                    Category.generateCategoryMenu();
                    categoryChosen = Category.chooseCategory();

                    System.out.println("Chosen category: " + categoryChosen.getDisplayName());
                    try {
                        Product newProduct = new Product(productId, name, price, categoryChosen);
                        inventory.addProduct(newProduct);
                        System.out.println("Successfully added new product with id: " + productId);
                        productId++;
                    }
                    catch (DuplicateProductException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // Remove Product
                case 2:
                    System.out.println("Choose which product to remove by id: ");
                    scanner.nextLine();
                    input = scanner.nextLine();

                    try {
                        productId = Integer.parseInt(input);
                        inventory.removeProduct(productId);

                        System.out.println("Successfully removed product with id: " + productId);
                    }
                    catch (NumberFormatException | ProductNotFoundException  e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;

                // Find Product by ID
                case 3:
                    System.out.println("Choose which product to find by id: ");
                    scanner.nextLine();
                    input = scanner.nextLine();

                    try {
                        productId        = Integer.parseInt(input);
                        Product searched = inventory.findById(productId);

                        System.out.println("Found product: " + searched);
                    }
                    catch (NumberFormatException | ProductNotFoundException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;

                // Browse by Category
                case 4:
                    System.out.println("Browse products by category");
                    Category.generateCategoryMenu();
                    categoryChosen = Category.chooseCategory();

                    List<Product> categoryProducts = inventory.findByCategory(categoryChosen);
                    if (categoryProducts.isEmpty()) {
                        System.out.println("No products for category " + categoryChosen.getDisplayName() + " yet");
                        continue;
                    }

                    for (Product product : categoryProducts) {
                        System.out.println(product);
                    }
                    break;

                // Show all products
                case 5:
                    inventory.printAll();
                    break;

                // Show most expensive product
                case 6:
                    try {
                        Product mostExpensive = inventory.findMostExpensive();
                        System.out.println(mostExpensive);
                    }
                    catch (NullPointerException e) {
                        System.out.println("No products yet");
                        continue;
                    }
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}
