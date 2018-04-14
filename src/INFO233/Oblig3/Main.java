package INFO233.Oblig3;

import DAO.AddressDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.InvoiceItemsDAOImpl;
import DAO.ProductDAOImpl;
import Entities.Address;
import Entities.InvoiceItems;
import Entities.Product;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;
import INFO233.Oblig3.SQLConnector.SQLSchemaReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String args[])  {

        InvoiceItemsDAOImpl invoiceItemsDAO = new InvoiceItemsDAOImpl();
        ProductDAOImpl productDAO = new ProductDAOImpl();

        List<Product> productList = productDAO.getAllProducts();

        for (Product n : productList){

            System.out.println(n.getProductName());
        }



    }
}
