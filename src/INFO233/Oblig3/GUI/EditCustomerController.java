package INFO233.Oblig3.GUI;

import DAO.CustomerDAOImpl;
import Entities.Customer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EditCustomerController{

    @FXML
    private TextField id, name, address, phonenr, account;

    @FXML
    private TextArea systemText;

    @FXML
    private Parent parent;

    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();



    public void setTextId(Integer inheritedID){
        id.setText(Integer.toString(inheritedID));
    }



    @FXML
    public void editCustomer(){

        Customer customer = customerDAO.accessCustomer(Integer.parseInt(id.getText()));
        if (!name.getText().trim().isEmpty()){
            customer.setCustomerName(name.getText());
        }else if(!address.getText().trim().isEmpty()){
            customer.setAddress(Integer.parseInt(address.getText()));
        }else if(!phonenr.getText().trim().isEmpty()){
            customer.setPhoneNumber(phonenr.getText());
        }else if(!account.getText().trim().isEmpty()){
            customer.setBillingAccount(account.getText());
        }

        customerDAO.editCustomer(customer);
    }

    @FXML
    public void displayCustomer(){
        try {
            Customer c = customerDAO.accessCustomer(Integer.parseInt(id.getText()));

            systemText.setText(
                    "Currently registered data on this customer: " + " \n" +
                            "Name: " + c.getCustomerName() + " \n" +
                            "Address: " + c.getAddress() + " \n" +
                            "Phonenr: " + c.getPhoneNumber() + " \n" +
                            "Account: " + c.getBillingAccount() + " \n"
            );
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        }

      @FXML
    public void onBack(){
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("MainSceneFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
