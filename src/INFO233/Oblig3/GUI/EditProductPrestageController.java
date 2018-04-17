package INFO233.Oblig3.GUI;

import DAO.ProductDAOImpl;
import Entities.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EditProductPrestageController {

    @FXML
    private TextField productid;

    @FXML
    private Parent parent;


    @FXML
    private Text alertid;

    private ProductDAOImpl productDAO = new ProductDAOImpl();

    /**
     * Sjekker om en gitt id eksisterer
     * @return true eller false
     */
    private boolean doesIDExist() {
        Product product = productDAO.accessProduct(Integer.parseInt(productid.getText()));
        return product.getProductName() != null;
    }


    /**
     * Håndterer hva som skjer når brukeren trykker next
     */
    public void onNext() {
        int collectedId = Integer.parseInt(productid.getText());
        if (!doesIDExist()) {
            alertid.setText("Not a valid ID. Create a new one or input a valid ID");
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditProductFXML.fxml"));
            AnchorPane anchor = loader.load();
            EditProductController controller = loader.getController();
            controller.setId(collectedId);
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Håndterer hva som skjer når brukeren trykket Back
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
