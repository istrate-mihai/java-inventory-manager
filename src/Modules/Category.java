package Modules;
import java.util.Scanner;

public enum Category {
    FOOD("Food & Groceries", 0.02),
    BOOKS("Books & Education", 0.00),
    CLOTHING("Clothing", 0.12),
    ELECTRONICS("Electronics", 0.20),
    LUXURY("Luxury Goods", 0.25);

    private final String displayName;
    private final double taxRate;

    private final static String[] categorySubMenuOptions = {
            "Food",
            "Books",
            "Clothing",
            "Electronics",
            "Luxury",
    };

    Category(String displayName, double taxRate) {
        this.displayName = displayName;
        this.taxRate     = taxRate;
    }

    public String getDisplayName() { return displayName; }
    public double calculateTax(double price) { return price * taxRate; }

    public static void generateCategoryMenu() {
        System.out.println("Enter product category, choose number from the following options: ");
        for (int i = 0; i < categorySubMenuOptions.length; i++) {
            System.out.println((i + 1) + " " + categorySubMenuOptions[i]);
        }
    }

    public static Category chooseCategory() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Choose category (1-Food, 2-Books, 3-Clothing, 4-Electronics, 5-Luxury): ");
            String input = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input – please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: return Category.FOOD;
                case 2: return Category.BOOKS;
                case 3: return Category.CLOTHING;
                case 4: return Category.ELECTRONICS;
                case 5: return Category.LUXURY;
                default:
                    System.out.println("No such category. Please choose 1–5.");
            }
        }
    }
}
