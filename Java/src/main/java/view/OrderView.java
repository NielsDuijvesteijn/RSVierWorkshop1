package view;

import model.Order;
import model.OrderLine;

import java.util.ArrayList;

public class OrderView {

    public void requestOrderId(){
        System.out.print("Please enter the Order ID: ");
    }

    public void orderNotFound(){
        System.out.println("No Order found with that ID.");
    }

    public void showOrder(Order order) {
        System.out.println(order.toString());
    }

    public void emptyOrder() {
        System.out.println("This order is empty.");
    }

    public void showOrderLines(ArrayList<OrderLine> orderLines){
        System.out.println("ProductID\tAmount");
        for (OrderLine orderLine:orderLines) {
            System.out.println(orderLine.toString());
        }
    }
}
