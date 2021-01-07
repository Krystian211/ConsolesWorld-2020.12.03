package pl.camp.it.consoles.world.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "torderposition")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pieces;
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private BigDecimal overallPrice;

    public OrderPosition(int id, int pieces, Order order, Product product, BigDecimal overallPrice) {
        this.id = id;
        this.pieces = pieces;
        this.order = order;
        this.product = product;
        this.overallPrice = overallPrice;
    }

    public OrderPosition() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getOverallPrice() {
        return overallPrice;
    }

    public void setOverallPrice(BigDecimal overallPrice) {
        this.overallPrice = overallPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosition that = (OrderPosition) o;
        return id == that.id &&
                pieces == that.pieces &&
                Objects.equals(order, that.order) &&
                Objects.equals(product, that.product) &&
                Objects.equals(overallPrice, that.overallPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pieces, order, product, overallPrice);
    }
}
