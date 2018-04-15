package INFO233.Oblig3.GUI;

import Entities.Address;
import Entities.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewCustomerController {

    @FXML
    private TextField newnameId, newId, newAddressId, newPhonenrId, newAccountnoId;

    @FXML
    private TextField addressid, streetnoid, streetnameid, postalcodeid, postaltownid;

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


    public Customer newCustomer(){
        Customer customer = new Customer();
        customer.setCustomerId(Integer.parseInt(newId.getText()));
        customer.setBillingAccount(newAccountnoId.getText());
        customer.setPhoneNumber(newPhonenrId.getText());
        customer.setAddress(Integer.parseInt(newAddressId.getText()));
        customer.setCustomerName(newnameId.getText());
        return customer;
    }

    public Address newAddress(){
        Address address = new Address();
        address.setPostalTown(postaltownid.getText());
        address.setPostalCode(postalcodeid.getText());
        address.setStreetName(streetnameid.getText());
        address.setAddressId(Integer.parseInt(addressid.getText()));
        address.setStreetNumber(streetnoid.getText());
        return address;
    }

}
