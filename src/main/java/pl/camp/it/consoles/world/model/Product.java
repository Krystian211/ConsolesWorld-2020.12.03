package pl.camp.it.consoles.world.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private String brand;
    private Category category;
    private int pieces;
    private String manufacturerCode;
    private BigDecimal price;

    public Product(String name, String brand, Category category, int pieces, String manufacturerCode, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.pieces = pieces;
        this.manufacturerCode = manufacturerCode;
        this.price = price;
    }

    public Product(Product product){
        this.name = product.getName();
        this.brand = product.getBrand();
        this.category = product.getCategory();
        this.pieces = product.getPieces();
        this.manufacturerCode = product.getManufacturerCode();
        this.price = product.getPrice();
    }

    public Product() {
    }

    public String getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(String manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public enum Category {
        CONSOLES, CONTROLLERS, ACCESSORIES;

        public static Category toCategory(String category){
            for (Category value : Category.values()) {
                if (value.name().equalsIgnoreCase(category)) {
                    return value;
                }
            }
            return null;
        }
    }
}