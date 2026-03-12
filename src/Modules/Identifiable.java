package Modules;

public interface Identifiable {
    int id();
    Category category();

    double priceWithTax();
    double price();
}
