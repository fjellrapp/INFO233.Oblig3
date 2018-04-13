package DAO;

import Entities.Customer;
import Entities.InvoiceItems;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceItemsDAOImpl {

    SQLConnectorFactory connector = new SQLConnectorFactory();

    public InvoiceItems accessInvoiceItems(int id){
        Connection conn = connector.connect();
        InvoiceItems items = new InvoiceItems();

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM invoice_items " +
                            "WHERE invoice = " + id + ";"
            );
            if (resultSet.next()){
                items.setInvoice(resultSet.getInt("invoice"));
                items.setProduct(resultSet.getInt("product"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
        return items;
    }

    public void addInvoiceItems(InvoiceItems items){
        Connection conn = connector.connect();
       try{
           Statement statement = conn.createStatement();
           statement.setQueryTimeout(20);
           PreparedStatement preparedStatement = conn.prepareStatement(
                   "INSERT INTO invoice_items (invoice, product) VALUES " +
                           "(?,?);"
           );
           preparedStatement.execute();
       }catch (SQLException e){
           e.printStackTrace();
       }finally {
           connector.disconnect();
       }
    }

    public void deleteInvoiceItems(int id){
        Connection conn = connector.connect();
        try{
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(20);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM invoice_items WHERE invoice = " + id + ";"
            );
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
    }

    private List<InvoiceItems> getAllAddresses(){
        InvoiceItems invoiceItems = new InvoiceItems();
        Connection conn = connector.connect();
        List<InvoiceItems> all = new ArrayList<>();

        String query = "SELECT * FROM invoice_items;";

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                invoiceItems.setInvoice(resultSet.getInt("invoice"));
                invoiceItems.setProduct(resultSet.getInt("product"));
                all.add(invoiceItems);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
        return all;
    }

}
