package dao;

import model.Product;
import util.DatabaseConnection;

import java.util.logging.Logger;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection;

    public void addProduct(Product product){

       try{
            connection = DatabaseConnection.getConnection();

            preparedStatement = connection.prepareStatement("insert into test1.products (ProductName, Price) values (?, ?)");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setBigDecimal(2, product.getProductPrice());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.print("error" + ex.toString());
        }
       finally {
           closeAll(resultSet, preparedStatement);
       }
    }

    public ArrayList<Product> getProductList(){
        ArrayList<Product> productList = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
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
        finally {
            closeAll(resultSet, preparedStatement);
        }
        return productList;
    }

    public Product getProductByName(String productName) {

        try {
            connection = DatabaseConnection.getConnection();
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
        finally {
            closeAll(resultSet, preparedStatement);
        }
        return null;
    }

    public boolean updateProduct(Product product) {
        boolean updateSuccessful = true;

        try {
            connection = DatabaseConnection.getConnection();
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
        finally {
            closeAll(resultSet, preparedStatement);
        }
        return updateSuccessful;
    }

    public void deleteProduct(int productID) {

        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM products where idProduct = ?");
            preparedStatement.setInt(1, productID);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        finally {
           closeAll(resultSet, preparedStatement);
        }
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
