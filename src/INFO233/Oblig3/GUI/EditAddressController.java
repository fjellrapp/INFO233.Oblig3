package INFO233.Oblig3.GUI;

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


public class EditAddressController {

    @FXML
    private TextField addressid, streetno, streetname, postalcode, postaltown;

    @FXML
    private TextArea systemText;

    @FXML
    private Parent parent;

    private AddressDAOImpl addressDAO = new AddressDAOImpl();

    /**
     * Metoden bruker i controlleren "EditAddressPrestageController", for å
     * angi en ID brukeren skal ta med seg inn hit.
     *
     * @param inheritedID, IDen som blir angitt i "EditAddressPretageController".
     */

    public void setId(Integer inheritedID) {
        {
            addressid.setText(Integer.toString(inheritedID));
        }
    }

    /**
     * Metoden kalles fra FXML-filen med (nesten) samme navn, og endrer verdiene til en
     * Addresse basert på input fra brukeren.
     */
    @FXML
    public void editAddress() {
        Address address = addressDAO.accessAddress(Integer.parseInt(addressid.getText()));
        if (!streetno.getText().trim().isEmpty()) {
            address.setStreetNumber(streetno.getText());
        }
        if (!streetname.getText().trim().isEmpty()) {
            address.setStreetName(streetname.getText());
        }
        if (!postalcode.getText().trim().isEmpty()) {
            address.setPostalCode(postalcode.getText());
        }
        if (!postaltown.getText().trim().isEmpty()) {
            address.setPostalTown(postaltown.getText());
        }
        addressDAO.editAddress(address);
    }


    /**
     * Viser informasjon om en gitt addresse basert på en ID som brukeren angir i forkant.
     */
    public void onDisplay() {
        try {
            Address a = addressDAO.accessAddress(Integer.parseInt(addressid.getText()));
            systemText.setText(
                    "Currently registered data on this address: " + " \n" +
                            "Street number: " + a.getStreetNumber() + " \n" +
                            "Street name: " + a.getStreetName() + " \n" +
                            "Postal code: " + a.getPostalCode() + " \n" +
                            "Postal town: " + a.getPostalTown() + " \n"
            );
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode for å returnere tilbake til forstadiet, hvor brukeren kan gi ID til en bruker
     * han ønsker å endre"
     */
    public void onBack() {
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("EditAddressPrestageFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
