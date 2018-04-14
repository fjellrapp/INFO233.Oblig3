package DAO;

import Entities.InvoiceItems;
import Entities.Product;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductDAOImpl {

    SQLConnectorFactory connector = new SQLConnectorFactory();

    public Product accessProduct(int id) {
        Connection conn = connector.connect();
        Product product = new Product();

        try {
            Statement statment = conn.createStatement();
            ResultSet resultSet = statment.executeQuery(
                    "SELECT * FROM product WHERE product_id = " + id + ";"
            );
            if (resultSet.next()) {
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                product.setCategory(resultSet.getInt("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return product;
    }

    public void addProduct(Product product) {
        Connection conn = connector.connect();

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(20);

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO product (product_id, product_name, description, price, category) " +
                            "VALUES (?, ?, ?, ?, ?);"
            );
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setFloat(4, product.getPrice());
            preparedStatement.setInt(5, product.getCategory());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }

    }

    public void deleteProduct(int id) {
        Connection conn = connector.connect();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(20);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM product WHERE product_id = " + id + ";"
            );
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }

    }

    public List<Product> getAllProducts() {

        Connection conn = connector.connect();
        List<Product> all = new LinkedList<>();

        String query = "SELECT * FROM product;";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Product current = new Product();
                current.setProductId(resultSet.getInt("product_id"));
                current.setProductName(resultSet.getString("product_name"));
                current.setDescription(resultSet.getString("description"));
                current.setPrice(resultSet.getFloat("price"));
                current.setCategory(resultSet.getInt("category"));
                all.add(current);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return all;
    }

    public float sumAllProducts(int index) {

        float sum = 0;
        Connection conn = connector.connect();
        String sql = "SELECT sum(price) FROM product, invoice_items " +
                "WHERE invoice_items.product = invoice_items.invoice;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.getResultSet();
            sum = resultSet.getFloat("sum(price)");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return sum;

    }
}



