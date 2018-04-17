package INFO233.Oblig3.GUI;

import DAO.CustomerDAOImpl;
import DAO.InvoiceDAOImpl;
import Entities.Customer;
import Entities.Invoice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class NewInvoiceController {

    @FXML
    private TextField newid, newCustomer, newDate;


    @FXML
    private Parent parent;

    @FXML
    private TextArea resultText;

    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    private InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();


    /**
     * Håndterer createknappen. Legger objektet til i DAO og legges inn i databasen.
     */
    public void createButtonPressed() {
        invoiceDAO.addInvoice(newInvoice());
    }

    /**
     * Oppretter et nytt invoice objekt
     * @return objektet
     */
    public Invoice newInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(Integer.parseInt(newid.getText()));
        invoice.setCustomer(Integer.parseInt(newCustomer.getText()));
        invoice.setDato(newDate.getText());
        resultText.setText("Invoice created.");
        return invoice;

    }

    /**
     * Returnerer brukeren tilbake til main
     */
    public void returnToMain() {
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

