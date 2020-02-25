package structural.proxy;

import java.util.HashMap;
import java.util.Map;

public class Exercise {
    public static void main(String[] args) {
        DbContext dbContext = new DbContext();
        Product product = dbContext.getProduct(1);
        product.setName("Updated Name");
        dbContext.saveChanges();
        product.setName("Another name");
        dbContext.saveChanges();
    }
}

class DbContext {
    private Map<Integer, Product> updatedObjects = new HashMap<>();
    public Product getProduct(int id) {
        System.out.printf("SELECT * FROM products WHERE product_id = %d \n", id);
        ProductProxy product = new ProductProxy(id, this);
        product.setName("Product 1");
        return product;
    }
    public void saveChanges() {
        for (var updatedObject : updatedObjects.values())
            System.out.printf("UPDATE products SET name = '%s' WHERE product_id = %d \n", updatedObject.getName(), updatedObject.getId());
        updatedObjects.clear();
    }
    public void markAsChanged(Product product) {
        updatedObjects.put(product.getId(), product);
    }
}

class Product {
    private int id;
    private String name;
    public Product(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

class ProductProxy extends Product {
    private DbContext context;
    public ProductProxy(int id, DbContext context) {
        super(id);
        this.context = context;
    }
    @Override
    public void setName(String name) {
        super.setName(name);
        context.markAsChanged(this);
    }
}