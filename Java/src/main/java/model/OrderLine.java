package model;

public class OrderLine {
    private int orderId;
    private int productId;
    private int amount;

    public OrderLine(int orderId, int productId, int amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
    }

    public OrderLine(int productId, int amount) {
        this.productId = productId;
        this.amount = amount;
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
        return productId + "\t\t\t" + amount;
    }
}
