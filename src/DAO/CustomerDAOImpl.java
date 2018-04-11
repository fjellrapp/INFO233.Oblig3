package DAO;

import Entities.Customer;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;


import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAOImpl {

    private static SQLConnectorFactory connector = new SQLConnectorFactory();


    public Customer accessCustomer(int id) throws SQLException, FileNotFoundException {

        Connection con = connector.getConnection();
        Customer customer = new Customer();

        try{
            Statement stmnt = con.createStatement();
            ResultSet custResults = stmnt.executeQuery("SELECT * FROM customer" +
                    "WHERE " + id);
            if (custResults.next()){

                customer.setCustomerId(custResults.getInt("customer_id"));
                customer.setCustomerName(custResults.getString("customer_name"));
                customer.setAddress(custResults.getInt("address"));
                customer.setPhoneNumber(custResults.getString("phone_number"));
                customer.setBillingAccount(custResults.getString("billing_account"));

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            con.close();
        }

        return customer;

    }
}
