package INFO233.Oblig3;

import INFO233.Oblig3.SQLConnector.SQLConnector;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {

    public static void main(String args[]) throws SQLException, FileNotFoundException {

        SQLConnector connector = new SQLConnector();

        String filename = "oblig3v1_database.db";
        File f = new File(filename);

        if (f.isFile()){
            System.out.println("File already exists");
        }else {
            connector.connectDatabase();
        }

    }
}
