package INFO233.Oblig3.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSceneController {

    @FXML
    private Button newCustomer, newAddress, newInvoice, newInvoiceItem, newCategory;

    @FXML
    private Button editCustomer, editAddress, editInvoice, editInvoiceItem, editCategory;

    @FXML
    private Button showInvoice;

    @FXML
    private Parent parent;

    @FXML
    public void onNewCustomer(){
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("NewCustomerFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onShowInvoice(){
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("InvoiceFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onNewInvoice(){
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("NewInvoiceFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onNewCategory(){
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("NewCategoryFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onNewInvoiceItem(){
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("NewInvoiceItemFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
