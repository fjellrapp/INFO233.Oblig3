package INFO233.Oblig3.SQLConnector;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectorFactory {

    private static File schema = new File("oblig3v1_database.sql");
    private static Connection connection = null;


    /**
     * Oppretter en connection til databasen.
     * Sjekker om .db-filen allerede eksisterer, hvis ikke, opprettes den fra det gitte schemaet.
     * @return En connection.
     */
    public Connection connect() {
        String url = "jdbc:sqlite:oblig3v1_database.db";

        if (SQLSchemaReader.fileExists()) {
            try {
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                System.out.println("File didn't exists. Creating it from schema.");
                connection = DriverManager.getConnection(url);
                SQLSchemaReader.intitializeDB(connection, schema);
            } catch (SQLException b) {
                b.printStackTrace();
            }
        }
        return connection;
    }


    /**
     * Sjekker om det er en connection. Hvis ja, lukker den connection og setter
     * den til null.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}





