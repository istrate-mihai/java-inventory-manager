package Modules;

public record Product(int id, String name, double price, Category category) implements Identifiable {
    public Product {
        if (price < 0 || name.isBlank()) {
            throw new IllegalArgumentException("Price must be positive and name cannot be empty");
        }
    }

    public double priceWithTax() {
        return price + category.calculateTax(price);
    }
}
