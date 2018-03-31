import sun.util.calendar.LocalGregorianCalendar;

import java.math.BigDecimal;

public class Order {
    private int OrderId;
    private LocalGregorianCalendar.Date orderDate;
    private BigDecimal totalPrice;
    private int customerId;
    private int status;

    public int getOrderId() {
        return OrderId;
    }

    public LocalGregorianCalendar.Date getOrderDate() {
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

    @Override
    public String toString() {
        return "Order ID: " + getOrderId() + "\nCustomer ID: " + getCustomerId() + "\nOrder Date: " + getOrderDate() +
                "\nTotal Price: " + getTotalPrice() + "\nStatus: " + getStatus();
    }
}
