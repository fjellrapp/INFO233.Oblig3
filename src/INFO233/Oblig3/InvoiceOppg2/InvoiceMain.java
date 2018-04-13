package INFO233.Oblig3.InvoiceOppg2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class InvoiceMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlloader = new FXMLLoader(InvoiceMain.class.getResource("InvoiceFXML.fxml"));
        GridPane rootLayout = fxmlloader.load();

      //  Parent root = FXMLLoader.load(getClass().getResource("INFO233/Oblig3/InvoiceOppg2/InvoiceFXML.fxml"));
        primaryStage.setTitle("Faktura");
        Scene scene = new Scene(rootLayout, 620, 1280);
        scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
