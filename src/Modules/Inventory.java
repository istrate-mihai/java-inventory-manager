package Modules;
import java.util.ArrayList;
import java.util.List;

public class Inventory<T extends Identifiable> {
    private T[] products;
    private int count = 0;
    private static final int DEFAULT_CAPACITY = 100;

    @SuppressWarnings("unchecked")
    public Inventory() {
        this.products = (T[]) new Identifiable[DEFAULT_CAPACITY];
    }

    public void addProduct(T p) throws DuplicateProductException {
        int index = findIndexById(p.id());
        if (index != -1) {
            throw new DuplicateProductException(p.id());
        }

        if (count > DEFAULT_CAPACITY - 1) {
            System.out.println("Warning: Inventory full, cannot add anymore products.");
            return;
        }

        products[count] = p;
        count++;
    }

    public void removeProduct(int id) throws ProductNotFoundException {
        int index = findIndexById(id);
        if (index == -1) {
            throw new ProductNotFoundException(id);
        }

        System.arraycopy(products, index + 1, products, index, count - index - 1);
        products[--count] = null;
    }

    public T findById(int id) throws ProductNotFoundException {
        int index = findIndexById(id);
        if (index == -1) {
            throw new ProductNotFoundException(id);
        }

        return products[index];
    }

    public List<T> findByCategory(Category c) {
        int matchCount = 0;
        int index      = 0;

        for (int i = 0; i < count; i++) {
            if (products[i].category() == c) {
                matchCount++;
            }
        }

        List<T> results = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (products[i].category() == c) {
                results.add(products[i]);
                index++;
            }
        }

        return results;
    }

    public T findMostExpensive() throws ProductNotFoundException {
        T mostExpensive      = products[0];
        double greatestPrice = products[0].price();

        for (int i = 0; i < count; i++) {
            double currentProductPrice = products[i].price();
            if (currentProductPrice > greatestPrice) {
                greatestPrice = currentProductPrice;
                mostExpensive = products[i];
            }
        }

        return mostExpensive;
    }

    public int findIndexById(int id) {
        for (int i = 0; i < count; i++) {
            if (products[i].id() == id) {
                return i;
            }
        }
        return -1;
    }

    public void printAll() {
        if (count == 0) {
            System.out.println("No products yet");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(products[i] + " [price with tax = " + products[i].priceWithTax() + "]");
        }
    }
}
