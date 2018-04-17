package INFO233.Oblig3.GUI;

import DAO.AddressDAOImpl;
import Entities.Address;
import INFO233.Oblig3.SQLConnector.SQLSchemaReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class EditAddressPrestageController {

    @FXML
    private TextField addressid;

    @FXML
    private Text alertid;

    @FXML
    private Parent parent;

    private AddressDAOImpl addressDAO = new AddressDAOImpl();

    /**
     * Brukes på knappen "Back", for å returnere brukeren tilbake til main-scene.
     */
    public void onBack() {
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("MainSceneFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Oppretter et nytt objekt med IDen som brukeren angir og sjekker om
     * det finnes et slikt element. Brukes i onNext() metoden.
     *
     * @return true eller false
     */
    private boolean doesIDExist() {
        Address address = addressDAO.accessAddress(Integer.parseInt(addressid.getText()));
        return address.getStreetName() != null;
    }


    /**
     * Henter først ID angitt i tekstfeltet, og tar med seg denne IDen til neste scene.
     * Hvis IDen ikke eksisterer fra før, går man ikke videre til Edit.
     */
    public void onNext() {

        int collectedId = Integer.parseInt(addressid.getText());
        if (!doesIDExist()) {
            alertid.setText("Not a valid ID. Create a new one or input a valid ID");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EditAddressFXML.fxml"));
                AnchorPane anchor = loader.load();
                EditAddressController controller = loader.getController();
                controller.setId(collectedId);
                Scene scene = new Scene(anchor);
                Stage stage = (Stage) parent.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
