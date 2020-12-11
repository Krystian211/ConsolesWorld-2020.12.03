package pl.camp.it.consoles.world.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.consoles.world.dao.IProductDAO;
import pl.camp.it.consoles.world.model.Product;
import pl.camp.it.consoles.world.services.IProductService;
import pl.camp.it.consoles.world.session.SessionObject;
import pl.camp.it.consoles.world.utils.validation.Validators;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDAO productDAO;

    @Autowired
    SessionObject sessionObject;

    @Override
    public List<Product> getProductsByCategoryAndKeyword(String category, String keyword) {
        if (category.equals("all")) {
            return searchProducts(keyword,this.productDAO.getAllProducts());
        }else {
            return searchProducts(keyword,this.productDAO.getProductsByCategory(category));
        }
    }

    private List<Product> searchProducts(String keyword,List<Product> inputProducts){
        List<Product> outputProducts=new ArrayList<>();
        if (keyword==null) {
            return inputProducts;
        }else {
            for (Product inputProduct : inputProducts) {
                if (inputProduct.getName().toLowerCase().contains(keyword.toLowerCase())||
                        inputProduct.getManufacturerCode().toLowerCase().contains(keyword.toLowerCase())) {
                    outputProducts.add(inputProduct);
                }
            }
        }
        return outputProducts;
    }

    @Override
    public List<String> addProduct(Product productData) {
        List<String> messages;
        if ((messages=this.validateProductData(productData))==null) {
            if (this.productDAO.getProductByManufacturerCode(productData.getManufacturerCode())!=null) {
                messages=new ArrayList<>();
                messages.add("Kod producenta zajęty!");
            }else {
                this.productDAO.persistProduct(productData);
            }
        }
        return messages;
    }

    @Override
    public List<String> updateProduct(Product productData) {
        List<String> messages;
        if ((messages=this.validateProductData(productData))==null) {
            this.productDAO.updateProduct(productData);
        }
        return messages;
    }

    @Override
    public Product getProductById(int productId) {
        return this.productDAO.getProductById(productId);
    }

    private List<String> validateProductData(Product productData){
        List<String> messageList = new ArrayList<>();
        String message;
        if ((message = Validators.validateProductName(productData.getName())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateBrand(productData.getBrand())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateManufacturerCode(productData.getManufacturerCode())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validatePieces(productData.getPieces())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validatePrice(productData.getPrice())) != null) {
            messageList.add(message);
        }
        if (messageList.size()==0) {
            return null;
        }
        return messageList;
    }
}
