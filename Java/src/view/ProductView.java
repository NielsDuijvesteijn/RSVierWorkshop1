package view;

import model.Product;

import java.util.ArrayList;

public class ProductView {
    public void requestProductNameAndPrice(){
        System.out.println("Enter the product name, press enter, and it's price: ");
    }

    public void showProductList(ArrayList<Product> products) {
        for (Product product: products) {
            System.out.println(product.toString());
        }
    }
}
