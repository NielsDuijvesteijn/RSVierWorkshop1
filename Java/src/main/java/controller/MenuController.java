package controller;

import model.OrderLine;
import util.InputUtil;
import view.MenuView;

import java.util.ArrayList;

public class MenuController {
    MenuView menuView = new MenuView();
    ProductController productController = new ProductController();
    OrderController orderController = new OrderController();
    public void doMenu(){
        menuView.printMenu();
        changeMenu(InputUtil.getIntInput());

    }


    public void changeMenu(int menuNumber){
        switch (menuNumber){
            // nieuw product
            case 1: productController.createProduct(); break;
            //print alle producten
            case 2: productController.doProductList(); break;
            //update/delete een product
            //todo keuze geven om een product te zoeken op naam of id
            case 3: productController.updateProduct(); break;
            case 4: orderController.newOrder(); break;
            case 5: orderController.findOrder(); break;
            case 9: return;
            default: System.out.println("Option not found, please try again.");
        }

        doMenu();

    }
}
