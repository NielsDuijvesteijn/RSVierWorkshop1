package model;

import javax.xml.soap.Text;
import java.math.BigDecimal;

//model class for product
public class Product {
    private int productID;
    private String productName;
    private BigDecimal productPrice;
    private int shippingTime;
    private int productStock;

    public Product(String productName, BigDecimal productPrice){
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product(int productID, String productName, BigDecimal productPrice, int shippingTime, int productStock) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.shippingTime = shippingTime;
        this.productStock = productStock;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public void setShippingTime(int shippingTime) {
        this.shippingTime = shippingTime;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public int getShippingTime() {
        return shippingTime;
    }

    public int getProductStock() {
        return productStock;
    }

    @Override
    public String toString() {
        return "ID: " + getProductID() + " Name: " + getProductName() + " Price: " + getProductPrice() + " Stock: " + getProductStock();
    }
}
