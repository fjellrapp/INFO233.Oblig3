package INFO233.Oblig3.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class InvoiceMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlloader = new FXMLLoader(InvoiceMain.class.getResource("InvoiceFXML.fxml"));
        StackPane rootLayout = fxmlloader.load();

      //  Parent root = FXMLLoader.load(getClass().getResource("INFO233/Oblig3/GUI/InvoiceFXML.fxml"));
        primaryStage.setTitle("Faktura");
        Scene scene = new Scene(rootLayout, 720, 1280);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
