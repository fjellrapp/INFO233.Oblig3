package INFO233.Oblig3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;


public class Main{

    public static void createNewDatabase(String filename) throws SQLException, FileNotFoundException {

        String url = "jdbc:sqlite:" + filename;
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            if (con != null){
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("Navn på driver: " + meta.getDriverName());
                System.out.println("Database opprettet");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        File schema = new File("oblig3v1_database.sql");
        InputStream input = new FileInputStream(schema);
        importSQL(con, input);

    }

    public static void importSQL(Connection conn, InputStream input) throws SQLException{
        Scanner s = new Scanner(input);
        s.useDelimiter("(;(\r)?\n)|(--\n)");
        Statement statement = null;
        try{
            statement = conn.createStatement();
            while(s.hasNext()){
                String line = s.next();
                if(line.startsWith("/*!") && line.endsWith("*/")){
                    int i = line.indexOf(' ');
                    line.substring(i + 1, line.length() - "*/".length());
                }
                if (line.trim().length() > 0){
                    statement.execute(line);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (statement != null){
                statement.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        String filename = "oblig3v1_database.db";
        File f = new File(filename);


        if (f.isFile()){
            System.out.println("File already exists");
        }else {
            createNewDatabase(filename);
        }
    }



}
