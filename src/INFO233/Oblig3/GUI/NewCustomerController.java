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
    private TextField addressid, streetnoid, streetnameid, postalcodeId, postaltownid;

    @FXML
    private TextArea resultArea;

    @FXML
    private TableColumn<Customer, Integer> columnId;

    @FXML
    private TableColumn<Customer, String> columnNameId;

    @FXML
    private TableColumn<Customer, Integer> columnAddressId;
    @FXML
    private TableColumn<Customer, String> columnPhonenrId;
    @FXML
    private TableColumn<Customer, String> columnAccountnoId;

    @FXML
    private Parent parent;

    private AddressDAOImpl addressDAO = new AddressDAOImpl();
    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();


    @FXML
    public void createButtonPressed() {
        addressDAO.addAddress(newAddress());
        customerDAO.addCustomer(newCustomer());
        resultArea.setText("Customer " + newCustomer().getCustomerName() + " Added." +
                "With address " + newAddress().getStreetName() + " " + newAddress().getStreetNumber());

    }

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

    @FXML
    public Address newAddress() {
        Address address = new Address();
        address.setPostalTown(postaltownid.getText());
        address.setPostalCode(postalcodeId.getText());
        address.setStreetName(streetnameid.getText());
        address.setAddressId(Integer.parseInt(addressid.getText()));
        address.setStreetNumber(streetnoid.getText());
        return address;
    }

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
