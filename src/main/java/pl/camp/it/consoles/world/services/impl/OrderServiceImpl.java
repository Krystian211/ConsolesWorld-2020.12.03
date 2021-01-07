package pl.camp.it.consoles.world.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.consoles.world.dao.IOrderDAO;
import pl.camp.it.consoles.world.dao.IProductDAO;
import pl.camp.it.consoles.world.model.Order;
import pl.camp.it.consoles.world.model.OrderPosition;
import pl.camp.it.consoles.world.model.Product;
import pl.camp.it.consoles.world.services.IOrderService;
import pl.camp.it.consoles.world.session.SessionObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    SessionObject sessionObject;

    @Autowired
    IProductDAO productDAO;

    @Autowired
    IOrderDAO orderDAO;

    @Override
    public void confirmOrder() {
        for (Product basketProduct : this.sessionObject.getBasket()) {
            if (basketProduct.getPieces() > productDAO.getProductById(basketProduct.getId()).getPieces()) {
                this.sessionObject.putMessage("Ilość zamawianego produktu przewyższa stan magazynowy!");
                return;
            }
        }
        Order order = new Order();
        order.setUser(this.sessionObject.getLoggedUser());
        order.setStatus(Order.Status.CONFIRMED);
        for (Product basketProduct : this.sessionObject.getBasket()) {
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setOrder(order);
            orderPosition.setProduct(basketProduct);
            orderPosition.setPieces(basketProduct.getPieces());
            orderPosition.setOverallPrice(basketProduct.getPrice().multiply(BigDecimal.valueOf(basketProduct.getPieces())));
            order.getOrderPositions().add(orderPosition);
        }

        BigDecimal overallPrice = BigDecimal.valueOf(0);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            overallPrice = overallPrice.add(orderPosition.getOverallPrice());
        }
        order.setOverallPrice(overallPrice);

        orderDAO.persistOrder(order);

        for (Product basketProduct : this.sessionObject.getBasket()) {
            Product dbProduct = this.productDAO.getProductById(basketProduct.getId());
            dbProduct.setPieces(dbProduct.getPieces() - basketProduct.getPieces());
            this.productDAO.updateProduct(dbProduct);
        }

        this.sessionObject.getBasket().clear();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return orderDAO.getOrdersByUser(this.sessionObject.getLoggedUser().getId());
    }
}
