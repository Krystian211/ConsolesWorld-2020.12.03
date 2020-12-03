package pl.camp.it.consoles.world.database;

import org.thymeleaf.dialect.IProcessorDialect;
import pl.camp.it.consoles.world.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductRepository {
    void addProduct(Product product);
    List<Product> getAllProductList();
    List<Product> getProductsByCategory(Product.Category category);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByNameOrCode(List<Product> products, String nameOrCode);
    List<Product> getProductByCategoryAndNameOrCode(String category, String nameOrCode);
    boolean isManufacturerCodeAvailable(String manufacturerCode);
    boolean isNameAvailable(String name);
    boolean isManufacturerCodeAvailableExcludingProduct(String manufacturerCode, Product excludedProduct);
    boolean isNameAvailableExcludingProduct(String name,Product excludedProduct);
    Product getProductByCode(String code);
    void editProduct(Product editedProduct, Product newProductData);
}
