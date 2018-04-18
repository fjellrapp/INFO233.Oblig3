package GUI;

import DAO.AddressDAOImpl;
import Entities.Address;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewAddressController {

    @FXML
    private TextField addressid, streetno, streetname, postalcode, postaltown;

    @FXML
    private TextArea systemText;

    @FXML
    private Parent parent;

    private AddressDAOImpl addressDAO = new AddressDAOImpl();

    /**
     * Oppretter en ny addresse ved å sjekke for input fra brukeren.
     * @return et nytt Address-objekt
     */
    @FXML
    public Address newAddress() {
        Address address = new Address();
        address.setPostalTown(postaltown.getText());
        address.setPostalCode(postalcode.getText());
        address.setStreetName(streetname.getText());
        address.setAddressId(Integer.parseInt(addressid.getText()));
        address.setStreetNumber(streetno.getText());
        return address;
    }

    /**
     * Når brukeren trykket "create", legges addressen til i addAddress og bruker newAddress() som parameter.
     */
    @FXML
    public void onCreate() {
        addressDAO.addAddress(newAddress());
        systemText.setText("Address " + newAddress().getStreetName() + " was added.");

    }

    /**
     * Spesifiserer hva som skjer når brukeren trykker back.
     * Blir deretter først tilbake til menyen.
     */
    @FXML
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
}
