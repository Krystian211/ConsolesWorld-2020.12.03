package pl.camp.it.consoles.world.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.consoles.world.dao.IProductDAO;
import pl.camp.it.consoles.world.model.Product;
import pl.camp.it.consoles.world.services.IBasketService;
import pl.camp.it.consoles.world.session.SessionObject;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class BasketServiceImpl implements IBasketService {

    @Autowired
    IProductDAO productDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void addToBasket(int productId) {
        for (Product product : this.sessionObject.getBasket()) {
            if (product.getId()==productId) {
                product.setPieces(product.getPieces()+1);
                return;
            }
        }
        Product product=this.productDAO.getProductById(productId);
        product.setPieces(1);
        this.sessionObject.getBasket().add(product);
    }

    @Override
    public BigDecimal calculateOverallPrice() {
        BigDecimal price=BigDecimal.valueOf(0);
        for (Product product : this.sessionObject.getBasket()) {
            price=price.add(product.getPrice().multiply(BigDecimal.valueOf(product.getPieces())));
        }
        return price;
    }

    @Override
    public void removeProductById(int id) {
        for (Product basketProduct : this.sessionObject.getBasket()) {
            if (basketProduct.getId()==id) {
                this.sessionObject.getBasket().remove(basketProduct);
                return;
            }
        }
    }
}
