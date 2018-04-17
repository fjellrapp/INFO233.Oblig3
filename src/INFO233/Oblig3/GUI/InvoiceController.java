package INFO233.Oblig3.GUI;


import DAO.*;
import Entities.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    AddressDAOImpl addressDAO = new AddressDAOImpl();
    ProductDAOImpl productDAO = new ProductDAOImpl();
    InvoiceItemsDAOImpl invoiceItemsDAO = new InvoiceItemsDAOImpl();
    List<Invoice> invoiceList = invoiceDAO.getAllInvoices();



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

    public void hidePrevious(){
        pris.getChildren().clear();
        type.getChildren().clear();
        pbeskrivelse.getChildren().clear();
    }


    public void insertInfoFromDb(Invoice invoices) {

        Customer customer = customerDAO.accessCustomer(invoices.getCustomer());
        Address address = addressDAO.accessAddress(customer.getAddress());
        List<InvoiceItems> invoiceItemsList = invoiceItemsDAO.getInvoiceItemsById(invoices.getInvoiceId());


        navnId.setText(customer.getCustomerName());
        addresseId.setText(address.getStreetName() + " " + address.getStreetNumber());
        postId.setText(address.getPostalCode() + " " + address.getPostalTown());
        fakuturaid.setText(String.valueOf(invoices.getInvoiceId()));
        datoid.setText(invoices.getDato());
        telefonid.setText(customer.getPhoneNumber());
        billingid.setText(customer.getBillingAccount());

        itemsDisplay(invoiceItemsList);


    }

    private void itemsDisplay(List<InvoiceItems> list) {

        float sumItems = 0;

        for (InvoiceItems item : list) {
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
        insertInfoFromDb(invoiceList.get(index));
    }

    public void onNext() {
        if (currentIndex >= 0 && currentIndex < invoiceList.size() - 1) {
            hidePrevious();
            displayInvoice(currentIndex + 1);
        }
    }


    public void onBack() {
        if (currentIndex != 0){
            currentIndex -= 1;
            hidePrevious();
            displayInvoice(currentIndex);
        }else {
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
}




