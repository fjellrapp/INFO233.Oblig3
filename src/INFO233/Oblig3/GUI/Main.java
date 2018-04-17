package INFO233.Oblig3.GUI;

import DAO.AddressDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.InvoiceItemsDAOImpl;
import DAO.ProductDAOImpl;
import Entities.Address;
import Entities.InvoiceItems;
import Entities.Product;
import INFO233.Oblig3.GUI.InvoiceMain;
import INFO233.Oblig3.SQLConnector.SQLConnectorFactory;
import INFO233.Oblig3.SQLConnector.SQLSchemaReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main extends Application {

    public static void main(String args[]) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("MainSceneFXML.fxml"));
        primaryStage.setTitle("Salgssystem");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
