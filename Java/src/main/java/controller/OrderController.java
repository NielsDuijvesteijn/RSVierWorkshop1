package controller;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.OrderLine;
import model.Product;
import util.InputUtil;
import view.OrderView;

import java.util.ArrayList;

public class OrderController {
    OrderView orderView = new OrderView();
    OrderDAO orderDAO = new OrderDAO();
    ProductDAO productDAO = new ProductDAO();

    public void findOrder(){
        orderView.requestOrderId();

        int orderId = InputUtil.getIntInput();
        Order order = orderDAO.getOrder(orderId);
        if(order == null) {
            orderView.orderNotFound();
            return;
        }
        orderView.showOrder(order);
        ArrayList<OrderLine> orderLines = orderDAO.getOrderLine(orderId);
        if (orderLines == null) {
            orderView.emptyOrder();
            return;
        }
        orderView.showOrderLines(orderLines);
    }

    public void newOrder(){
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        ArrayList<Product> products = productDAO.getProductList();
        newOrderMenu(orderLines, products);
    }

    public void newOrderMenu(ArrayList<OrderLine> orderLines, ArrayList<Product> products) {
        orderView.printOrderMenu();
        switch (InputUtil.getIntInput()){

            case 1: addProductToOrder(orderLines); break;
            case 2: orderView.showOrderLines(orderLines); break;
            case 3: orderView.printOrderID(orderDAO.placeOrder(orderLines));
            case 4: return;
            default: System.out.println("Option not found, please try again.");
        }
        newOrderMenu(orderLines, products);
    }

    private void addProductToOrder(ArrayList<OrderLine> orderLines) {
        orderView.requestProductId();
        int productId = InputUtil.getIntInput();
        //todo check if product exists
        orderView.requestProductAmount();
        int amount = InputUtil.getIntInput();
        orderLines.add(new OrderLine(productId, amount));
    }
}
