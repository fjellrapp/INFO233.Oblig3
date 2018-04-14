package INFO233.Oblig3.GUI;


import DAO.*;
import Entities.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    List<InvoiceItems> invoiceItemslist = invoiceItemsDAO.getAllInvoiceItems();
    private int currentIndex;

    @FXML
    private Text navnId, addresseId, postId, fakuturaid, datoid,
            telefonid, billingid, sumid;

    @FXML
    private VBox pris, type, pbeskrivelse;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentIndex = 0;
        invoiceDAO = new InvoiceDAOImpl();
        displayInfo(currentIndex);

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

     private void itemsDisplay(){
        float sumItems = 0;
        for (InvoiceItems item : invoiceItemslist) {
                Product product = productDAO.accessProduct(item.getProduct());
                Text itemName = new Text(product.getProductName());
                Text itemPrice = new Text(String.valueOf(product.getPrice()));
                Text itemDescription = new Text(product.getDescription());

                sumItems += product.getPrice();
                type.getChildren().add(itemName);
                pris.getChildren().add(itemPrice);
                pbeskrivelse.getChildren().add(itemDescription);


        }
        sumid.setText(String.valueOf(sumItems));
    }

        public void displayInfo ( int index){
            currentIndex = index;
            insertInfoFromDb(invoiceList.get(index));
        }
        }




