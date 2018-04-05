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

    public void doProductList() {
        ArrayList<Product> products = productDAO.getProductList();
        productView.showProductList(products);
    }

    public void updateProduct() {
        productView.requestProductName();
        String productName = InputUtil.getStringInput();
        Product product = productDAO.getProductByName(productName);
        //if product is null, then inform that product name was wrong or doesn't exist
        if(product == null) {
            productView.productNotFound();
            return;
        }
        productView.showProduct(product);
        updateMenu(product);
    }

    private void updateMenu(Product product){
        productView.printProductUpdateMenu();


        switch (InputUtil.getIntInput()) {
            case 1: productView.requestNewProductName();
                    product.setProductName(InputUtil.getStringInput()); break;
            case 2: productView.requestNewPrice();
                    product.setProductPrice(InputUtil.getBigDecimalInput());break;
            case 3: productView.requestNewShippingTime();
                    product.setShippingTime(InputUtil.getIntInput());break;
            case 4: productView.requestNewStock();
                    product.setProductStock(InputUtil.getIntInput());break;
            case 5: if (!productDAO.updateProduct(product)) {  productView.updateFailed();  }  return;
            case 6: productDAO.deleteProduct(product.getProductID()); return;
            case 9: return;
            default: System.out.println("Option not found, please try again.");
        }

        updateMenu(product);
    }


}
