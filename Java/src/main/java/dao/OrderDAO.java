package dao;

import model.Order;
import model.OrderLine;
import util.DatabaseConnection;
import java.util.logging.Logger;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class OrderDAO {
    private final static Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection;

    public Order getOrder(int orderId) {

        try {
            connection = DatabaseConnection.getConnection();
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
        finally {
            closeAll(resultSet, preparedStatement);
        }
        return null;
    }

    public ArrayList<OrderLine> getOrderLine(int orderId){
        ArrayList<OrderLine> orderLines = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement("select * from orderline where Orders_idOrder = ?");
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int productId = resultSet.getInt("Products_idProduct");
                String productName = resultSet.getString("ProductName");
                int amount = resultSet.getInt("amount");
                BigDecimal productPrice = resultSet.getBigDecimal("ProductPrice");
                OrderLine orderLine = new OrderLine(orderId, productId, productName, amount, productPrice);
                orderLines.add(orderLine);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        finally {
            closeAll(resultSet, preparedStatement);
        }
        return orderLines;
    }

    public int placeOrder(ArrayList<OrderLine> orderLines, BigDecimal totalPrice) {
        int orderId;
        
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement("insert into orders (TotalPrice, Status) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBigDecimal(1, totalPrice);
            preparedStatement.setInt(2, 1);
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            orderId = resultSet.getInt(1);

            for (OrderLine orderLine : orderLines) {
                preparedStatement = connection.prepareStatement("insert into orderline (Orders_idOrder, Products_idProduct, amount, ProductName, ProductPrice) values (?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, orderLine.getProductId());
                preparedStatement.setInt(3, orderLine.getAmount());
                preparedStatement.setString(4, orderLine.getProductName());
                preparedStatement.setBigDecimal(5, orderLine.getProductPrice());
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
            return 0;
        }
        finally {
            closeAll(resultSet, preparedStatement);
        }
        return orderId;
    }

    private static void closeAll(ResultSet resultSet, PreparedStatement preparedStatement){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                System.out.print(ex.toString());
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                System.out.print(ex.toString());
            }
        }
    }
}
