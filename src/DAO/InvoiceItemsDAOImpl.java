package DAO;

import Entities.Customer;
import Entities.Invoice;
import Entities.InvoiceItems;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InvoiceItemsDAOImpl {

    SQLConnectorFactory connector = new SQLConnectorFactory();

    public ArrayList<InvoiceItems> accessInvoiceItems(int id){

        ArrayList<InvoiceItems> list = new ArrayList<>();

        Connection conn = connector.connect();
        InvoiceItems items = new InvoiceItems();
        String SQL = "SELECT * FROM invoice_items " +
                "WHERE invoice = " + id + ";";

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            if (resultSet.next()){
                items.setInvoice(resultSet.getInt("invoice"));
                items.setProduct(resultSet.getInt("product"));
                list.add(items);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
        return list;
    }

    public void addInvoiceItems(InvoiceItems items){
        Connection conn = connector.connect();
       try{
           PreparedStatement preparedStatement = conn.prepareStatement(
                   "INSERT INTO invoice_items (invoice, product) VALUES " +
                           "(?,?);"
           );
           preparedStatement.setInt(1, items.getInvoice());
           preparedStatement.setInt(2, items.getProduct());
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

    public List<InvoiceItems> getAllInvoiceItems(){

        Connection conn = connector.connect();
        List<InvoiceItems> all = new LinkedList<>();

        String query = "SELECT * FROM invoice_items;";

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                InvoiceItems invoiceItems = new InvoiceItems();
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

    public void editInvoiceItems(InvoiceItems invoiceItems){
        Connection conn = connector.connect();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE invoice_items SET " +
            "product = ? "+
            "WHERE invoice = " + invoiceItems.getInvoice() + ";");
            preparedStatement.setInt(1, invoiceItems.getProduct());
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }


    }

}
