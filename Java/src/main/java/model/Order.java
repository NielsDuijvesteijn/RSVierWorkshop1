package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {
    private int orderId;
    private Date orderDate;
    private BigDecimal totalPrice;
    private int customerId;
    private int status;

    public Order(int customerId){
        this.customerId = customerId;
    }

    public Order(int orderId, Date orderDate, BigDecimal totalPrice, int customerId, int status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getStatus() {
        return status;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "model.Order ID: " + getOrderId() + "\nCustomer ID: " + getCustomerId() + "\nmodel.Order Date: " + getOrderDate() +
                "\nTotal Price: " + getTotalPrice() + "\nStatus: " + getStatus();
    }
}
