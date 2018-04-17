package INFO233.Oblig3.GUI;

import DAO.InvoiceDAOImpl;
import Entities.Invoice;
import Entities.InvoiceItems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class EditInvoiceController {

    @FXML
    private TextField invoiceid, customer, date;

    @FXML
    private TextArea systemText;

    @FXML
    private Parent parent;

    private InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();

    public void setId(Integer inheritedID) {
        invoiceid.setText(Integer.toString(inheritedID));
    }

    public void editInvoice() {
        Invoice invoice = invoiceDAO.accessInvoice(Integer.parseInt(invoiceid.getText()));
        if (!customer.getText().trim().isEmpty()) {
            invoice.setCustomer(Integer.parseInt(customer.getText()));
        }
        if (!date.getText().trim().isEmpty()) {
            invoice.setDato(date.getText());
        }
        invoiceDAO.editInvoice(invoice);
    }


    public void onDisplay() {

        try {
            Invoice i = invoiceDAO.accessInvoice(Integer.parseInt(invoiceid.getText()));

            systemText.setText(
                    "Currently registered data on this invoice: " + " \n" +
                            "ID: " + i.getInvoiceId() + " \n" +
                            "CustomerID: " + i.getCustomer() + " \n" +
                            "Date " + i.getDato() + " \n"
            );
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBack() {
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("EditInvoicePrestageFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
