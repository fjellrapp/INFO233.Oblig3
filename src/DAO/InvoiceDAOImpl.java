package DAO;

import Entities.Invoice;
import INFO233.Oblig3.SQLConnector.SQLConnector;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class InvoiceDAOImpl {

    private SQLConnector connector = new SQLConnector();

    /**
     * Returnerer en liste med alle fakturaer.
     *
     * @return Listen med alle fakturaobjekter.
     */
    public List<Invoice> getAllInvoices() {

        Connection conn = connector.connect();
        List<Invoice> all = new LinkedList<>();

        String query = "SELECT * FROM Invoice;";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getInt("invoice_id"));
                invoice.setDato(resultSet.getString("dato"));
                invoice.setCustomer(resultSet.getInt("customer"));
                all.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return all;
    }

    /**
     * Gir tilgang på en faktura basert på dens ID
     *
     * @param id fakturaens ID som brukes i spørringen
     * @return Et fakturaobjekt som samsvarer med den gitte IDen.
     */

    public Invoice accessInvoice(int id) {
        Connection conn = connector.connect();
        Invoice invoice = new Invoice();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM invoice WHERE invoice_id = " + id + ";"
            );
            if (resultSet.next()) {
                invoice.setInvoiceId(resultSet.getInt("invoice_id"));
                invoice.setCustomer(resultSet.getInt("customer"));
                invoice.setDato(resultSet.getString("dato"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return invoice;
    }

    /**
     * Legger til et fakturaobjekt i databasen
     *
     * @param invoice Objektet sendes inn.
     */
    public void addInvoice(Invoice invoice) {
        Connection conn = connector.connect();

        try {
            PreparedStatement prepStatement = conn.prepareStatement("" +
                    "INSERT INTO invoice (invoice_id, customer, dato) VALUES " +
                    "(?,?,?);");
            prepStatement.setInt(1, invoice.getInvoiceId());
            prepStatement.setInt(2, invoice.getCustomer());
            prepStatement.setString(3, invoice.getDato());
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

    /**
     * Sletter en faktura basert på dens ID
     *
     * @param id IDen til fakturaen.
     */
    public void deleteInvoice(int id) {
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM invoice WHERE invoice_id = " + id + ";"
            );
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }


    /**
     * Gjør det mulig å endre på et fakturaobjekt.
     *
     * @param invoice Objektet som skal endres på .
     */
    public void editInvoice(Invoice invoice) {
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE invoice SET " +
                    "customer = ?, " +
                    "dato = ? " +
                    "WHERE invoice_id = " + invoice.getInvoiceId() + ";");
            preparedStatement.setInt(1, invoice.getCustomer());
            preparedStatement.setString(2, invoice.getDato());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

}
