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

public class GUI extends Application {

    Text headText = new Text();
    Text fakturaNrText = new Text();
    Text beskrivelse = new Text();
    GridPane root;
    HBox informationBox;

    @Override
    public void start(Stage primaryStage) throws Exception {

            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

            root = new GridPane();
            informationBox = new HBox(beskrivelse, fakturaNrText);


            headText.setText("Faktura");
            fakturaNrText.setText("FakturaNr");
            beskrivelse.setText("Beskrivelse");



            root.add(headText, 0, 0);
            root.add(beskrivelse, 0, 1);
            root.add(fakturaNrText, 1, 1);
            root.setPadding(new Insets(15));
            root.setHgap(10);
            root.setAlignment(Pos.CENTER);



            Scene scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());
            scene.getStylesheets().addAll(this.getClass().getResource("Styles.css").toExternalForm());

            primaryStage.setTitle("Faktura");
            primaryStage.setScene(scene);
            primaryStage.show();

        }

        public void setID(){
            headText.setId("headTitle");
            fakturaNrText.setId("fakturaText");
            beskrivelse.setId("beskrivelseText");

        }



}

