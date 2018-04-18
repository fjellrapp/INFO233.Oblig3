package DAO;

import Entities.Product;
import INFO233.Oblig3.SQLConnector.SQLConnector;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class ProductDAOImpl {

    SQLConnector connector = new SQLConnector();

    /**
     * Gir tilgang til et product basert på dens ID
     *
     * @param id IDen til produktet
     * @return Product-objektet
     */
    public Product accessProduct(int id) {
        Connection conn = connector.connect();
        Product product = new Product();

        String SQL = "SELECT * FROM product WHERE product_id = " + id + ";";

        try {
            Statement statment = conn.createStatement();
            ResultSet resultSet = statment.executeQuery(SQL);
            while (resultSet.next()) {
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

    /**
     * Legger til et produkt til databasen
     *
     * @param product produkt-objektet som skal inn
     */
    public void addProduct(Product product) {
        Connection conn = connector.connect();

        try {
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

    /**
     * Sletter et produkt fra databasen basert på dens ID.
     *
     * @param id Produktet ID
     */

    public void deleteProduct(int id) {
        Connection conn = connector.connect();
        try {
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

    /**
     * Returnerer en liste av alle produkter i en database
     *
     * @return Listen med produkter.
     */
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

    /**
     * Gjør det mulig å endre et produkts verdier
     *
     * @param product Produktobjektet.
     */

    public void editProduct(Product product) {
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE product SET " +
                    "product_name = ?, " +
                    "description = ?, " +
                    "price = ?, " +
                    "category = ? " +
                    "WHERE product_id = " + product.getProductId() + ";");
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setInt(4, product.getCategory());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

}





