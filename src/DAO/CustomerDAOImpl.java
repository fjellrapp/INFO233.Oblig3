package DAO;

import Entities.Customer;
import INFO233.Oblig3.SQLConnector.SQLConnector;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAOImpl {

    private static SQLConnector connector = new SQLConnector();


    /**
     * Gir tilgang til en kunde basert på dens ID
     *
     * @param id kundens ID inn
     * @return kundeobjektet.
     */
    public Customer accessCustomer(int id) {

        Connection con = connector.connect();
        Customer customer = new Customer();
        String SQL = "SELECT * FROM customer " +
                "WHERE customer_id = " + id + ";";
        try {
            Statement stmnt = con.createStatement();
            ResultSet custResults = stmnt.executeQuery(SQL);
            if (custResults.next()) {
                customer.setCustomerId(custResults.getInt("customer_id"));
                customer.setCustomerName(custResults.getString("customer_name"));
                customer.setAddress(custResults.getInt("address"));
                customer.setPhoneNumber(custResults.getString("phone_number"));
                customer.setBillingAccount(custResults.getString("billing_account"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connector.disconnect();
        }
        return customer;
    }


    /**
     * Sletter en kunde basert på ID
     *
     * @param id kundens ID
     */
    public void deleteCustomer(int id) {

        Connection conn = connector.connect();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM customer WHERE customer_id = " + id + ";");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

    /**
     * Legger til et kundeobjekt til databasen
     *
     * @param customer, kundeobjektet.
     */
    public void addCustomer(Customer customer) {

        Connection conn = connector.connect();

        try {

            PreparedStatement prepStatement = conn.prepareStatement("INSERT INTO customer (customer_id, customer_name, address, phone_number, billing_account) " +
                    "VALUES (?,?,?,?,?);");
            prepStatement.setInt(1, customer.getCustomerId());
            prepStatement.setString(2, customer.getCustomerName());
            prepStatement.setInt(3, customer.getAddress());
            prepStatement.setString(4, customer.getPhoneNumber());
            prepStatement.setString(5, customer.getBillingAccount());
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }

    /**
     * Returnerer en liste av alle kunder i databasen
     *
     * @return Listen med kunder.
     */
    public List<Customer> getAllCustomers() {

        Connection conn = connector.connect();
        List<Customer> all = new LinkedList<>();

        String query = "SELECT * FROM customer;";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setCustomerName(resultSet.getString("customer_name"));
                customer.setAddress(resultSet.getInt("address"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setBillingAccount(resultSet.getString("billing_account"));
                all.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return all;
    }

    /**
     * Gjør det mulig å endre et kundeobjekt.
     *
     * @param customer, kunden som skal endres.
     */
    public void editCustomer(Customer customer) {
        Connection conn = connector.connect();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE customer SET " +
                    "customer_name = ?, " +
                    "address = ?, " +
                    "phone_number = ?," +
                    "billing_account = ?" +
                    "WHERE customer_id = " + customer.getCustomerId() + ";");
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setInt(2, customer.getAddress());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getBillingAccount());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
    }


}
