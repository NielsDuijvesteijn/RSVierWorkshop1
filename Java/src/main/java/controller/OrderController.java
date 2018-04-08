package controller;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.OrderLine;
import model.Product;
import util.InputUtil;
import view.OrderView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderController {
    private OrderView orderView = new OrderView();
    private OrderDAO orderDAO = new OrderDAO();
    private ProductDAO productDAO = new ProductDAO();

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

            case 1: addProductToOrder(orderLines, products); break;
            case 2: orderView.showOrderLines(orderLines); break;
            case 3: orderView.printOrderID(orderDAO.placeOrder(orderLines, calculateTotalPrice(orderLines)));
            case 4: return;
            default: System.out.println("Option not found, please try again.");
        }
        newOrderMenu(orderLines, products);
    }

    public BigDecimal calculateTotalPrice(ArrayList<OrderLine> orderLines) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderLine orderLine:orderLines) {
            BigDecimal amount = new BigDecimal(orderLine.getAmount());
            BigDecimal productPrice = orderLine.getProductPrice();
            BigDecimal sum = amount.multiply(productPrice);
            totalPrice = totalPrice.add(sum);
        }
        return totalPrice;
    }

    private void addProductToOrder(ArrayList<OrderLine> orderLines, ArrayList<Product> products) {
        orderView.requestProductId();
        int productId = InputUtil.getIntInput();
        OrderLine orderLine = checkIfProductExists(productId, products);
        if(orderLine == null) {
            orderView.productNotFound();
            return;
        }
        orderLines.add(orderLine);
    }

    private OrderLine checkIfProductExists(int productId, ArrayList<Product> products) {
        OrderLine orderLine = null;

        for (Product product:products) {
               if (productId == product.getProductID()) {
                   orderLine = createOrderLine(product);
               }
        }
        return orderLine;
    }

    private OrderLine createOrderLine(Product product){
        orderView.requestProductAmount();
        int amount = InputUtil.getIntInput();
        return new OrderLine(product.getProductID(), product.getProductName(), amount, product.getProductPrice());
    }
}
