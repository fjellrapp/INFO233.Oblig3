package DAO;

import Entities.InvoiceItems;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class InvoiceItemsDAOImpl {

    SQLConnectorFactory connector = new SQLConnectorFactory();

    /**
     * Gir tilgang på en fakturagjenstand basert på ID
     *
     * @param id IDen (InvoiceID) til objektet.
     * @return objektet
     */
    public List<InvoiceItems> getAllInvoiceItems(int id) {


        Connection conn = connector.connect();
        List<InvoiceItems> items = new LinkedList<>();
        String SQL = "SELECT * FROM invoice_items " +
                "WHERE invoice = " + id + ";";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                InvoiceItems invoiceItems = new InvoiceItems();
                invoiceItems.setInvoice(resultSet.getInt("invoice"));
                invoiceItems.setProduct(resultSet.getInt("product"));
                items.add(invoiceItems);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return items;
    }

    public InvoiceItems accessInvoiceItems(int id) {


        Connection conn = connector.connect();
        String SQL = "SELECT * FROM invoice_items " +
                "WHERE invoice = " + id + ";";
        InvoiceItems invoiceItems = new InvoiceItems();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                invoiceItems.setInvoice(resultSet.getInt("invoice"));
                invoiceItems.setProduct(resultSet.getInt("product"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return invoiceItems;
    }

    /**
     * Legger til en InvoiceItems-objekt i databasen
     *
     * @param items, InvoiceItem objektet.
     */
    public void addInvoiceItems(InvoiceItems items) {
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO invoice_items (invoice, product) VALUES " +
                            "(?,?);"
            );
            preparedStatement.setInt(1, items.getInvoice());
            preparedStatement.setInt(2, items.getProduct());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

    /**
     * Sletter et objekt fra databasen basert på ID
     *
     * @param id IDen til feltet som skal slettes.
     */

    public void deleteInvoiceItems(int id) {
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM invoice_items WHERE invoice = " + id + ";"
            );
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }


    /**
     * Gjør det mulig å endre på et objekt.
     *
     * @param invoiceItems objektet som skal endres.
     */
    public void editInvoiceItems(InvoiceItems invoiceItems) {
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE invoice_items SET " +
                    "product = ? " +
                    "WHERE invoice = " + invoiceItems.getInvoice() + ";");
            preparedStatement.setInt(1, invoiceItems.getProduct());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }


    }

}
