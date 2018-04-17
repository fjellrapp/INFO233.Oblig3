package INFO233.Oblig3.GUI;

import DAO.InvoiceItemsDAOImpl;
import Entities.InvoiceItems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class EditInvoiceItemsController {

    @FXML
    private TextField id, productid;

    @FXML
    private TextArea systemText;

    @FXML
    private Parent parent;

    private InvoiceItemsDAOImpl invoiceItemsDAO = new InvoiceItemsDAOImpl();

    public void setId(Integer inheritedID) {
        id.setText(Integer.toString(inheritedID));
    }

    public void editInvoiceItems() {
        InvoiceItems invoiceItems = invoiceItemsDAO.accessInvoiceItems(Integer.parseInt(id.getText()));
        if (!productid.getText().trim().isEmpty()) {
            invoiceItems.setProduct(Integer.parseInt(productid.getText()));
        }
        invoiceItemsDAO.editInvoiceItems(invoiceItems);
    }


    public void onDisplay() {

        try {
            InvoiceItems in = invoiceItemsDAO.accessInvoiceItems(Integer.parseInt(id.getText()));
            systemText.setText(
                    "Currently registered data on this invoice: " + " \n" +
                            "Invoice ID: " + in.getInvoice() + " \n" +
                            "Products: " + in.getProduct()
            );

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void onBack() {
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("EditInvoiceItemsPrestageFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
