package INFO233.Oblig3.InvoiceOppg2;


import DAO.AddressDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.InvoiceDAOImpl;
import Entities.Address;
import Entities.Customer;
import Entities.Invoice;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    AddressDAOImpl addressDAO = new AddressDAOImpl();
    private List<Invoice> invoiceList = invoiceDAO.getAllInvoices();
    private int currentIndex;

    @FXML
    Text navnId, addresseId, postId, fakuturaid, datoid;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentIndex = 0;
       invoiceDAO = new InvoiceDAOImpl();
       displayInfo(currentIndex);
     

    }


    public void insertInfoFromDb(Invoice invoices){

      Customer customer = customerDAO.accessCustomer(invoices.getCustomer());
      Address address = addressDAO.accessAddress(invoices.getCustomer());
      Invoice invoice = invoiceDAO.accessInvoice(invoices.getCustomer());

      navnId.setText(customer.getCustomerName());
      addresseId.setText(address.getStreetName() +  " " + address.getStreetNumber());
      postId.setText(address.getPostalCode() + " " + address.getPostalTown());
      fakuturaid.setText("" + invoice.getInvoiceId());
      datoid.setText(invoice.getDato());
    }

    public void displayInfo(int index){
        currentIndex = index;
        insertInfoFromDb(invoiceList.get(index));

    }




}

