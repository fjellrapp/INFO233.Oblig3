package INFO233.Oblig3;

import DAO.AddressDAOImpl;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;
import INFO233.Oblig3.SQLConnector.SQLSchemaReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static SQLConnectorFactory connector = new SQLConnectorFactory();

    public static void main(String args[])  {


       connector.connect();




    }
}
