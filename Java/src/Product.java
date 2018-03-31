import javax.xml.soap.Text;
import java.math.BigDecimal;

public class Product {
    private int productID;
    private String productName;
    private Text productDescription;
    private BigDecimal productPrice;
    private int productStock;

    public Product(int productID,String productName, Text productDescription, BigDecimal productPrice, int productStock){
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public Text getProductDescription() {
        return productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    @Override
    public String toString() {
        return "ID: " + getProductID() + "\nName: " + getProductName() + "\nDescription: " + getProductDescription() + "\nPrice: " + getProductPrice() + "\nStock: " + getProductStock();
    }
}
