package controller;

import dao.ProductDAO;
import model.Product;
import util.InputUtil;
import view.ProductView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductController {
    ProductView productView = new ProductView();
    ProductDAO productDAO = new ProductDAO();
    public void createProduct(){
        productView.requestProductNameAndPrice();

        String productName = InputUtil.getStringInput();
        BigDecimal productPrice = InputUtil.getBigDecimalInput();
        Product product = new Product(productName, productPrice);

        productDAO.addProduct(product);
    }

    public void doProductList(){
        ArrayList<Product> products = productDAO.getProductList();
        productView.showProductList(products);

        //select a product from list
        //get menu to change/delete product
    }
}
