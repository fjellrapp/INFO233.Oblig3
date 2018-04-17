package INFO233.Oblig3.GUI;

import DAO.AddressDAOImpl;
import DAO.CustomerDAOImpl;
import Entities.Address;
import Entities.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewCustomerController {

    @FXML
    private TextField newnameId, newId, newAddressId, newPhonenrId, newAccountnoId;



    @FXML
    private TextArea resultArea;


    @FXML
    private Parent parent;


    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    /**
     * Hånderer createknappen. Sender et customer-objekt til databasen for å opprettes.
     */
    @FXML
    public void createButtonPressed() {
        customerDAO.addCustomer(newCustomer());
        resultArea.setText("Customer " + newCustomer().getCustomerName() + " added.");

    }

    /**
     * Oppretter et nytt customer-objekt basert på bruker input.
     * @return
     */
    @FXML
    public Customer newCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(Integer.parseInt(newId.getText()));
        customer.setBillingAccount(newAccountnoId.getText());
        customer.setPhoneNumber(newPhonenrId.getText());
        customer.setAddress(Integer.parseInt(newAddressId.getText()));
        customer.setCustomerName(newnameId.getText());
        return customer;
    }


    /**
     * Returnerer brukeren til main.
     */
    @FXML
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
