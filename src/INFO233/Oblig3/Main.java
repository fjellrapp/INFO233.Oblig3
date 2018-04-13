package INFO233.Oblig3;

import DAO.AddressDAOImpl;
import DAO.CustomerDAOImpl;
import Entities.Address;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;
import INFO233.Oblig3.SQLConnector.SQLSchemaReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String args[])  {

        CustomerDAOImpl customer = new CustomerDAOImpl();
        System.out.println(customer.accessCustomer(1).getCustomerName());

        AddressDAOImpl address = new AddressDAOImpl();
        System.out.println(address.accessAddress(1).getStreetName());


    }
}
