package pl.camp.it.consoles.world.services;

import pl.camp.it.consoles.world.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProductsByCategoryAndKeyword(String category, String keyword);
    List<String> addProduct(Product productData);
    List<String> updateProduct(Product productData);
    Product getProductById(int productId);
}
