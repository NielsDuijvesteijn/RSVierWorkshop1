package view;

import model.Product;

import java.util.ArrayList;

public class ProductView {
    public void requestProductNameAndPrice() {
        System.out.print("Enter the product name, press enter, and it's price: ");
    }

    public void printProductUpdateMenu() {
        System.out.print("1: Edit ProductName\n2: Edit Price\n3: Edit Shipping time\n4: Edit Stock\n5: Save updates\n6: Delete product\n9: Exit Update Menu without saving\nSelect Option: ");
    }

    public void showProductList(ArrayList<Product> products) {
        for (Product product: products) {
            System.out.println(product.toString());
        }
    }

    public void requestProductName() {
        System.out.print("Enter the product name: ");
    }

    public void productNotFound(){
        System.out.println("No product found with that name, check spelling.");
    }

    public void showProduct(Product product) {
        System.out.println(product.toString());
    }



    public void requestNewProductName() {
        System.out.print("Enter the new product name: ");
    }

    public void requestNewPrice() {
        System.out.print("Enter the new price: ");
    }

    public void requestNewShippingTime() {
        System.out.print("Enter the new Shipping time: ");
    }

    public void requestNewStock() {
        System.out.print("Enter the new stock amount: ");
    }

    public void updateFailed() {
        System.out.println("Update failed.");
    }
}
