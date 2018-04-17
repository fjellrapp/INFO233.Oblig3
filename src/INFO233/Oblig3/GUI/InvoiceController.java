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

    /**
     * Gjemmer elementene fra den forrige fakturaen når man skal vise en ny faktura
     */
    public void hidePrevious(){
        pris.getChildren().clear();
        type.getChildren().clear();
        pbeskrivelse.getChildren().clear();
    }

    /**
     * Henter informasjon fra en kunde, addresse og item til en gitt faktura.
     * @param invoices Fakturaen som skal vises.
     */

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

    /**
     * Henter inn en liste med items som tilhører en faktura. Går gjennom disse
     * og genererer et Text-element for hver av den.
     * @param list, listen med items som tilhører en faktura.
     */
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


    /**
     * Viser en faktura ved en gitt index.
     * @param index
     */
    public void displayInvoice(int index) {
        currentIndex = index;
        insertInfoFromDb(invoiceList.get(index));
    }

    /**
     * Viser en ny faktura når brukeren trykket next
     */
    public void onNext() {
        if (currentIndex >= 0 && currentIndex < invoiceList.size() - 1) {
            hidePrevious();
            displayInvoice(currentIndex + 1);
        }
    }

    /**
     * Går tilbake til forrige faktura, eller ut til hovedmenyen om den er på første index.
     */
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




