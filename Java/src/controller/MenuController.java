package controller;

import util.InputUtil;
import view.MenuView;

public class MenuController {
    MenuView menuView = new MenuView();
    ProductController productController = new ProductController();
    public void doMenu(){
        menuView.printMenu();
        changeMenu(InputUtil.getIntInput());

    }


    public void changeMenu(int menuNumber){
        switch (menuNumber){
            // nieuw product
            case 1: productController.createProduct(); break;
            case 2: productController.doProductList(); break;
            case 3: ; break;
            case 9: return;
            default:
        }

        doMenu();

    }
}
