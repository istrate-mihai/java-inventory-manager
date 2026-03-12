package Modules;

public class DuplicateProductException extends Exception {
    public DuplicateProductException(int id) {
        super("Product with id " + id + " already exists");
    }
}
