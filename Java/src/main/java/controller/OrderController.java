package controller;

import dao.OrderDAO;
import model.Order;
import model.OrderLine;
import util.InputUtil;
import view.OrderView;

import java.util.ArrayList;

public class OrderController {
    OrderView orderView = new OrderView();
    OrderDAO orderDAO = new OrderDAO();

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
}
