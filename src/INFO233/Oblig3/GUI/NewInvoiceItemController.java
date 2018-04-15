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
import javafx.stage.Stage;


import java.io.IOException;

public class NewInvoiceItemController {

    @FXML
    private TextArea systemText;

    @FXML
    private TextField productid, invoiceid;

    @FXML
    private Parent parent;

    private InvoiceItemsDAOImpl invoiceItemsDAO = new InvoiceItemsDAOImpl();

    // TODO:

    public InvoiceItems newInvoiceItem(){
        InvoiceItems invoiceItems = new InvoiceItems();
        invoiceItems.setInvoice(Integer.parseInt(invoiceid.getText()));
        invoiceItems.setProduct(Integer.parseInt(productid.getText()));
        return invoiceItems;

    }

    public void buttonAdd(){
        invoiceItemsDAO.addInvoiceItems(newInvoiceItem());
        systemText.setText("Product with ProductID " + newInvoiceItem().getProduct() + "added and InvoiceID " + newInvoiceItem().getInvoice() + " added" );
    }

    public void buttonBack(){
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
