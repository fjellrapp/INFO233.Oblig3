package GUI;

import DAO.CustomerDAOImpl;
import Entities.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EditCustomerPrestageController {

    @FXML
    private Parent parent;

    @FXML
    private TextField id;

    @FXML
    private Text alertid;

    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    /**
     * Sjekker om en ID eksisterer
     * @return sant eller usant.
     */
    private boolean doesIDExist() {
        Customer customer = customerDAO.accessCustomer(Integer.parseInt(id.getText()));
        return customer.getCustomerName() != null;
    }

    /**
     * Håndterer hva som skjer når man trykker "next".
     */
    public void onNext() {
        int collectedId = Integer.parseInt(id.getText());
        if (!doesIDExist()) {
            alertid.setText("Not a valid ID. Create a new one or input a valid ID");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EditCustomerFXML.fxml"));
                AnchorPane anchor = loader.load();
                EditCustomerController controller = loader.getController();
                controller.setTextId(collectedId);
                Scene scene = new Scene(anchor);
                Stage stage = (Stage) parent.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Håndterer hva som skjer når man trykket "back".
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


}
