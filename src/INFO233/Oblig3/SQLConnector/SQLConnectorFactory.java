package INFO233.Oblig3.SQLConnector;

import java.io.File;
import java.sql.*;

public class SQLConnectorFactory {

    private static File schema = new File("oblig3v1_database.sql");

    public Connection getConnection(){

        Connection connection = null;
        String url = "jdbc:sqlite:oblig3v1_database.db";

        if (SQLSchemaReader.fileExists()) {
            System.out.println("File already existed. Connecting..");
            try {
                connection = DriverManager.getConnection(url);
                if (connection != null) {
                    DatabaseMetaData meta = connection.getMetaData();
                    System.out.println("Navn på driver: " + meta.getDriverName());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else{
            try {
                System.out.println("File didn't exists. Creating it from schema.");
                connection = DriverManager.getConnection(url);
                SQLSchemaReader.intitializeDB(connection, schema);
                if (connection != null){
                    DatabaseMetaData meta = connection.getMetaData();
                    System.out.println("Navn på driver " + meta.getDriverName());
                }
            }catch (SQLException b){
                b.printStackTrace();
            }

        }
        return connection;
    }


    public void disconnectDatabase() {
        if (getConnection() != null) {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    private void setSchema(Connection conn){
            SQLSchemaReader.intitializeDB(conn, schema);
    }
    */

    }





