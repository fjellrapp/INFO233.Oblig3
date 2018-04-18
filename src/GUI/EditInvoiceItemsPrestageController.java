package GUI;

import DAO.InvoiceItemsDAOImpl;
import Entities.InvoiceItems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EditInvoiceItemsPrestageController {

    @FXML
    private TextField id;

    @FXML
    private Parent parent;

    @FXML
    private Text alertid;

    private InvoiceItemsDAOImpl invoiceItemsDAO = new InvoiceItemsDAOImpl();

    /**
     * Sjekker om en ID eksisterer.
     * @return true eller false
     */
    private boolean doesIDExist() {
        InvoiceItems invoiceItems = invoiceItemsDAO.accessInvoiceItems(Integer.parseInt(id.getText()));
        return invoiceItems.getProduct() >= 1;
    }

    /**
     * Håndterer hva som skjer når brukeren trykker next.
     */
    public void onNext() {
        int collectedId = Integer.parseInt(id.getText());

        if (!doesIDExist()) {
            alertid.setText("Not a valid ID. Create a new one or input a valid ID");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EditInvoiceItemsFXML.fxml"));
                AnchorPane anchor = loader.load();
                EditInvoiceItemsController controller = loader.getController();
                controller.setId(collectedId);
                Scene scene = new Scene(anchor);
                Stage stage = (Stage) parent.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Håndterer hva som skjer når brukeren trykker back
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
