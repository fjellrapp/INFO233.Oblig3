package DAO;

import Entities.Address;
import Entities.Category;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;


import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAOImpl {

    private static SQLConnectorFactory connector = new SQLConnectorFactory();

    public Category accessCategory(int id){
        Connection conn = connector.connect();
        Category category = new Category();
        String SQL = "SELECT * FROM category " +
                "WHERE category_id = " + id + ";";

        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(SQL);

            category.setCategoryId(result.getInt("category_id"));
            category.setCategoryName(result.getString("category_name"));

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
        return category;
    }

    public void addCategory(Category category){
        Connection con = connector.connect();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO category (category_id, category_name)" +
                            "values (?, ?);"
            );
            preparedStatement.setInt(1, category.getCategoryId());
            preparedStatement.setString(2, category.getCategoryName());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
    }

    public void deleteCategory(int id){
        Connection con = connector.connect();
        try{
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM category WHERE id = " + id + ";");
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
    }

    public List<Category> getAllCategories(){

        Connection conn = connector.connect();
        List<Category> all = new LinkedList<>();

        String query = "SELECT * FROM category;";

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setCategoryName(resultSet.getString("category_name"));
                all.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
        return all;
    }

    public void editCategory(Category category){
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE category SET " +
                    "category_name = ? " +
                    "WHERE category_id = " + category.getCategoryId() + ";");
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
    }
}
