package INFO233.Oblig3.GUI;


import DAO.*;
import Entities.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    AddressDAOImpl addressDAO = new AddressDAOImpl();
    ProductDAOImpl productDAO = new ProductDAOImpl();
    InvoiceItemsDAOImpl invoiceItemsDAO = new InvoiceItemsDAOImpl();
    List<Invoice> invoiceList = invoiceDAO.getAllInvoices();
    List<InvoiceItems> invoiceItemsList = invoiceItemsDAO.getAllInvoiceItems();


    private int currentIndex;

    @FXML
    private Text navnId, addresseId, postId, fakuturaid, datoid,
            telefonid, billingid, sumid;

    @FXML
    private VBox pris, type, pbeskrivelse;


    @FXML
    private Parent parent;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentIndex = 0;
        invoiceDAO = new InvoiceDAOImpl();
        displayInvoice(currentIndex);

    }


    public void insertInfoFromDb(Invoice invoices) {

        Customer customer = customerDAO.accessCustomer(invoices.getCustomer());
        Address address = addressDAO.accessAddress(invoices.getCustomer());
        Invoice invoice = invoiceDAO.accessInvoice(invoices.getCustomer());


        navnId.setText(customer.getCustomerName());
        addresseId.setText(address.getStreetName() + " " + address.getStreetNumber());
        postId.setText(address.getPostalCode() + " " + address.getPostalTown());
        fakuturaid.setText(String.valueOf(invoice.getInvoiceId()));
        datoid.setText(invoice.getDato());
        telefonid.setText(customer.getPhoneNumber());
        billingid.setText(customer.getBillingAccount());

        itemsDisplay();


    }

    private void itemsDisplay() {

        float sumItems = 0;

        for (InvoiceItems item : invoiceItemsList) {
            Product product = productDAO.accessProduct(item.getProduct());
            Text itemName = new Text(product.getProductName());
            Text itemPrice = new Text(String.valueOf(product.getPrice()));
            Text itemDescription = new Text(product.getDescription());

            type.getChildren().add(itemName);
            pris.getChildren().add(itemPrice);
            pbeskrivelse.getChildren().add(itemDescription);

            sumItems += product.getPrice();
        }
        sumid.setText(String.valueOf(sumItems));
    }


    public void displayInvoice(int index) {
        currentIndex = index;
        insertInfoFromDb(invoiceList.get(currentIndex));
    }

    public void onNext() {
        if (currentIndex == 0) {
            displayInvoice(currentIndex + 1);
        } else {
            displayInvoice(currentIndex++);
        }
    }


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




