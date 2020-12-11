package pl.camp.it.consoles.world.dao;

import pl.camp.it.consoles.world.model.Product;

import java.util.List;

public interface IProductDAO {
    List<Product> getProductsByCategory(String category);
    Product getProductByManufacturerCode(String manufacturerCode);
    Product getProductById(int productId);
    List<Product> getAllProducts();
    void persistProduct(Product product);
    void updateProduct(Product productData);
}
