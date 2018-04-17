package DAO;

import Entities.Category;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAOImpl {

    private static SQLConnectorFactory connector = new SQLConnectorFactory();


    /**
     * Gir tilgang til en kategori basert på kategoriens ID
     *
     * @param id, IDen til addressen
     * @return Objektet
     */
    public Category accessCategory(int id) {
        Connection conn = connector.connect();
        Category category = new Category();
        String SQL = "SELECT * FROM category " +
                "WHERE category_id = " + id + ";";

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(SQL);

            category.setCategoryId(result.getInt("category_id"));
            category.setCategoryName(result.getString("category_name"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return category;
    }

    /**
     * Legger til et kategori-objekt til databasen
     *
     * @param category, objektet som skal inn.
     */

    public void addCategory(Category category) {
        Connection con = connector.connect();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO category (category_id, category_name)" +
                            "values (?, ?);"
            );
            preparedStatement.setInt(1, category.getCategoryId());
            preparedStatement.setString(2, category.getCategoryName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

    /**
     * Sletter et objekt fra databasen basert på dens id
     *
     * @param id, id som går inn.
     */

    public void deleteCategory(int id) {
        Connection con = connector.connect();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM category WHERE id = " + id + ";");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

    /**
     * Henter ut en liste med alle kategorier
     *
     * @return Listen
     */

    public List<Category> getAllCategories() {

        Connection conn = connector.connect();
        List<Category> all = new LinkedList<>();

        String query = "SELECT * FROM category;";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("category_name"));
                all.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return all;
    }

    /**
     * Gjør det mulig å endre en kategori
     *
     * @param category Kategoriobjektet som skal endres.
     */

    public void editCategory(Category category) {
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE category SET " +
                    "category_name = ? " +
                    "WHERE category_id = " + category.getCategoryId() + ";");
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }
}
