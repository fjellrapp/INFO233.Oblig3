package DAO;

import Entities.Invoice;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.*;

public class InvoiceDAOImpl {

    private SQLConnectorFactory connector = new SQLConnectorFactory();

    public Invoice accessInvoice(int id){
        Connection conn = connector.connect();
        Invoice invoice = new Invoice();

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM invoice WHERE invoice_id = " + id + ";"
            );
            if (resultSet.next()){
                invoice.setInvoiceId(resultSet.getInt("invoice_id"));
                invoice.setCustomer(resultSet.getInt("customer"));
                invoice.setDato(resultSet.getString("dato"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
        return invoice;
    }

    public void addInvoice(Invoice invoice){
        Connection conn = connector.connect();

        try{
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(20);

            PreparedStatement prepStatement = conn.prepareStatement("" +
                    "INSERT INTO invoice (invoice_id, customer, dato) VALUES " +
                    "(?,?,?);");
            prepStatement.setInt(1, invoice.getInvoiceId());
            prepStatement.setInt(2, invoice.getCustomer());
            prepStatement.setString(3, invoice.getDato());
            prepStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
    }

    public void deleteInvoice(int id){
        Connection conn = connector.connect();
        try{
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(20);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM invoice WHERE invoice_id = " + id + ";"
            );
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            connector.disconnect();
        }
    }

}
