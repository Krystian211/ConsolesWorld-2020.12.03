package pl.camp.it.consoles.world.services;

import pl.camp.it.consoles.world.model.Order;

import java.util.List;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}
