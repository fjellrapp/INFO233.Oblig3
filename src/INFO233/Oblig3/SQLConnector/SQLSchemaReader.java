package INFO233.Oblig3.SQLConnector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLSchemaReader {


    public static void intitializeDB(Connection connection, File file) {

        InputStream fileIn = null;

            try {
                fileIn = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Scanner scanner = new Scanner(fileIn);
            scanner.useDelimiter(";");

            try {
                Statement statement = connection.createStatement();

                while (scanner.hasNext()) {
                    String line = scanner.next();

                    if((line.startsWith("/*!") && line.endsWith("*/"))){
                        int i = line.indexOf(' ');
                        line = line.substring(i + 1, line.length() - " */".length());
                    }
                    if (line.trim().length() > 0){
                        statement.execute(line);
                    }
                }
                statement.close();

            } catch (SQLException s) {
                s.printStackTrace();
            }
        }





    public static Boolean fileExists(){
        File file = new File("oblig3v1_database.db");
        if (file.exists() && !file.isDirectory()){
            return true;
        }else{
            return false;
        }
    }



}
