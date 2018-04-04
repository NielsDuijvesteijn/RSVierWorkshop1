package dao;

import model.Product;
import java.util.logging.Logger;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    //private Connection connect = null;

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private String url = "jdbc:mysql://localhost/test1";
    private String username = "root";
    private String password = "root";
    private Connection connection;

//todo put connection in Util or create one try-catch block
    public void addProduct(Product product){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }
       try{
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?autoReconnect=true&useSSL=false");

            preparedStatement = connection.prepareStatement("insert into test1.products (ProductName, Price) values (?, ?)");
            //productName, productPrice");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setBigDecimal(2, product.getProductPrice());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.print("error" + ex.toString());
        }
        finally {
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

    //todo close connection
    public ArrayList<Product> getProductList(){
        ArrayList<Product> productList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.out.print("Database not found.");
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }

        try {
            preparedStatement = connection.prepareStatement("Select * from products");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int idProduct = resultSet.getInt("idProduct");
                String productName = resultSet.getString("ProductName");
                BigDecimal price = resultSet.getBigDecimal("Price");
                int shippingTime = resultSet.getInt("ShippingTime");
                int stock = resultSet.getInt("Stock");
                Product product = new Product(idProduct, productName, price, shippingTime, stock);
                productList.add(product);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.print(ex.toString());
            return null;
        }
        return productList;
    }

    public Product getProductByName(String productName) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.out.print("Database not found.");
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }

        try {
            preparedStatement = connection.prepareStatement("Select * from products where ProductName = ?");
            preparedStatement.setString(1, productName);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idProduct = resultSet.getInt("idProduct");
            productName = resultSet.getString("ProductName");
            BigDecimal price = resultSet.getBigDecimal("Price");
            int shippingTime = resultSet.getInt("ShippingTime");
            int stock = resultSet.getInt("Stock");
            Product product = new Product(idProduct, productName, price, shippingTime, stock);
            connection.close();
            return product;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public boolean updateProduct(Product product) {
        boolean updateSuccessful = true;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.out.print("Database not found.");
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }

        try {
            preparedStatement = connection.prepareStatement("UPDATE products SET ProductName = ?, Price = ?, ShippingTime = ?, Stock = ? WHERE idProduct = ?");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setBigDecimal(2, product.getProductPrice());
            preparedStatement.setInt(3, product.getShippingTime());
            preparedStatement.setInt(4, product.getProductStock());
            preparedStatement.setInt(5, product.getProductID());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            updateSuccessful = false;
            System.out.println(ex.toString());
        }
        return updateSuccessful;
    }

    public void deleteProduct(int productID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException ex) {
            System.out.print(ex.toString());
            System.out.print("Database not found.");
            System.exit(0);
        } catch (ClassNotFoundException ex) {
            System.out.print(ex.toString());
        }

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM products where idProduct = ?");
            preparedStatement.setInt(1, productID);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
