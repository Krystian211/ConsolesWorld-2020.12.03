package pl.camp.it.consoles.world.dao;

import pl.camp.it.consoles.world.model.Order;

import java.util.List;

public interface IOrderDAO {
    void persistOrder(Order order);
    List<Order> getOrdersByUser(int userId);
}
