package pl.camp.it.consoles.world.database.implementation;

import org.springframework.stereotype.Component;
import pl.camp.it.consoles.world.database.IProductRepository;
import pl.camp.it.consoles.world.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListProductRepositoryImpl implements IProductRepository {
    private List<Product> products = new ArrayList<>();

    public ListProductRepositoryImpl() {
        addDefaultProducts();
    }

    private void addDefaultProducts() {
        addProduct(new Product("PlayStation 5",
                "Sony",
                Product.Category.CONSOLES,
                10,
                "711719757313/SONY",
                BigDecimal.valueOf(2250.00)));

        addProduct(new Product("Xbox Series X",
                "Microsoft",
                Product.Category.CONSOLES,
                10,
                "RRT-00010",
                BigDecimal.valueOf(2250.00)));

        addProduct(new Product("Xbox Series S",
                "Microsoft",
                Product.Category.CONSOLES,
                20,
                "RRS-00010",
                BigDecimal.valueOf(1300.00)));

        addProduct(new Product("Kontroler DualSense",
                "Sony",
                Product.Category.CONTROLLERS,
                10,
                "711719399605/SONY",
                BigDecimal.valueOf(350.00)));

        addProduct(new Product("Kontroler Xbox Series",
                "Microsoft",
                Product.Category.CONTROLLERS,
                15,
                "QAS-00002",
                BigDecimal.valueOf(250.00)));

        addProduct(new Product("Słuchawki PULSE 3D",
                "Sony",
                Product.Category.ACCESSORIES,
                5,
                "711719387909/SONY",
                BigDecimal.valueOf(250.00)));
    }

    @Override
    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public List<Product> getAllProductList() {
        return this.products;
    }

    @Override
    public List<Product> getProductsByCategory(Product.Category category) {
        List<Product> products = new ArrayList<>();
        for (Product product : this.products) {
            if (product.getCategory().equals(category)) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        if (category.equalsIgnoreCase("all")) {
            return this.products;
        } else {
            return getProductsByCategory(Product.Category.toCategory(category));
        }
    }

    @Override
    public List<Product> getProductsByNameOrCode(List<Product> inputProducts, String nameOrCode) {
        if (nameOrCode != null) {
            List<Product> outputProducts = new ArrayList<>();
            for (Product inputProduct : inputProducts) {
                if (inputProduct.getName().toLowerCase().contains(nameOrCode.toLowerCase()) ||
                        inputProduct.getManufacturerCode().equals(nameOrCode)) {
                    outputProducts.add(inputProduct);
                }
            }
            return outputProducts;

        } else {
            return inputProducts;
        }
    }

    @Override
    public List<Product> getProductByCategoryAndNameOrCode(String category, String nameOrCode) {
        return getProductsByNameOrCode(getProductsByCategory(category), nameOrCode);
    }

    @Override
    public boolean isManufacturerCodeAvailable(String manufacturerCode) {
        for (Product product : products) {
            if (product.getManufacturerCode().equals(manufacturerCode)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isNameAvailable(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isManufacturerCodeAvailableExcludingProduct(String manufacturerCode, Product excludedProduct) {
        for (Product product : products) {
            if (product.getManufacturerCode().equals(manufacturerCode)) {
                if(!product.getManufacturerCode().equals(excludedProduct.getManufacturerCode())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isNameAvailableExcludingProduct(String name, Product excludedProduct) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                if(!product.getManufacturerCode().equals(excludedProduct.getManufacturerCode())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Product getProductByCode(String code) {
        for (Product product : this.products) {
            if (product.getManufacturerCode().equals(code)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void editProduct(Product editedProduct, Product newProductData) {
        Product dataBaseProduct=getProductByCode(editedProduct.getManufacturerCode());
        dataBaseProduct.setName(newProductData.getName());
        dataBaseProduct.setBrand(newProductData.getBrand());
        dataBaseProduct.setManufacturerCode(newProductData.getManufacturerCode());
        dataBaseProduct.setCategory(newProductData.getCategory());
        dataBaseProduct.setPieces(newProductData.getPieces());
        dataBaseProduct.setPrice(newProductData.getPrice());
    }

}