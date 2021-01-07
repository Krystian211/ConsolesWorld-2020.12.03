package pl.camp.it.consoles.world.services;

import java.math.BigDecimal;

public interface IBasketService {
    void addToBasket(int productId);
    BigDecimal calculateOverallPrice();
    void removeProductById(int id);
}
