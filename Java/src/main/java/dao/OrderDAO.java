package dao;

import model.Order;
import model.OrderLine;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class OrderDAO {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private String url = "jdbc:mysql://localhost/test1";
    private String username = "root";
    private String password = "root";
    private Connection connection;

    public Order getOrder(int orderId) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }

        try {
            preparedStatement = connection.prepareStatement("select * from orders where idOrder = ?");
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idOrder = resultSet.getInt("idOrder");
            BigDecimal totalPrice = resultSet.getBigDecimal("TotalPrice");
            int status = resultSet.getInt("Status");
            int customerId = resultSet.getInt("Customer_idCustomer");
            Date orderDate = resultSet.getDate("date");
            Order order = new Order(idOrder, orderDate, totalPrice, status, customerId);
            connection.close();
            return order;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public ArrayList<OrderLine> getOrderLine(int orderId){
        ArrayList<OrderLine> orderLines = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }

        try {
            preparedStatement = connection.prepareStatement("select * from orderline where Orders_idOrder = ?");
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int productId = resultSet.getInt("Products_idProduct");
                int amount = resultSet.getInt("amount");
                OrderLine orderLine = new OrderLine(orderId, productId, amount);
                orderLines.add(orderLine);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        return orderLines;
    }

    public int placeOrder(ArrayList<OrderLine> orderLines) {
        int orderId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }

        try {
            preparedStatement = connection.prepareStatement("insert into orders (TotalPrice, Status) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, 100);
            preparedStatement.setInt(2, 1);
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            orderId = resultSet.getInt(1);

            for (OrderLine orderLine : orderLines) {
                preparedStatement = connection.prepareStatement("insert into orderline (Orders_idOrder, Products_idProduct, amount) values (?, ?, ?)");
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, orderLine.getProductId());
                preparedStatement.setInt(3, orderLine.getAmount());
                preparedStatement.execute();
            }


        } catch (SQLException ex) {
            System.out.print(ex.toString());
            return 0;
        }

        return orderId;
    }
}
