package INFO233.Oblig3;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {

    public static void createNewDatabase(String filename){
        try{
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e){
            System.out.println("Feil i lasting av driver " + e);
        }

        String url = "jdbc:sqlite:" + filename;
        try {
            Connection con = DriverManager.getConnection(url);
            if (con != null){
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("Navn på driver: " + meta.getDriverName());
                System.out.println("Database opprettet");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
	// write your code here

        String filename = "oblig3v1_database.db";
        File f = new File(filename);

        if (f.exists()){
            System.out.println("File already exists");
        }else {
            createNewDatabase(filename);
        }
    }
}
