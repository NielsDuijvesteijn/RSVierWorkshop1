package model;

import java.math.BigDecimal;

public class OrderLine {
    private int orderId;
    private int productId;
    private String productName;
    private int amount;
    private BigDecimal productPrice;


    public OrderLine(int orderId, int productId, String productName, int amount, BigDecimal productPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
        this.productPrice = productPrice;
    }

    public OrderLine(int productId, String productName, int amount, BigDecimal productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return productId + "\t\t\t" + productName + "\t\t\t" + amount + "\t\t\t" + productPrice;
    }
}
