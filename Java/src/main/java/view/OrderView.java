package view;

import model.Order;
import model.OrderLine;

import java.util.ArrayList;

public class OrderView {

    public void printOrderMenu() {
        System.out.print("1: Add Product\n2: View current Order\n3: Place Order\n4: Cancel Order\nSelect option: ");
    }

    public void requestProductId() {
        System.out.print("Please enter Product ID: ");
    }

    public void requestProductAmount() {
        System.out.print("Please enter the amount you would like to order: ");
    }

    public void printOrderID(int orderId) {
        System.out.println("Order placed with Order ID: " + orderId);
    }

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

    public void productNotFound() {
        System.out.println("Requested product not found.");
    }

    public void showOrderLines(ArrayList<OrderLine> orderLines){
        System.out.println("ProductID\tProduct Name\t\t\tAmount\t\tPrice");
        for (OrderLine orderLine:orderLines) {
            System.out.println(orderLine.toString());
        }
    }
}
