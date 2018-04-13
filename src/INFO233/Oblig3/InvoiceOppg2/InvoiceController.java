package INFO233.Oblig3.InvoiceOppg2;


import DAO.InvoiceDAOImpl;
import Entities.Customer;
import Entities.Invoice;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    InvoiceDAOImpl invoiceDAO = new InvoiceDAOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       invoiceDAO = new InvoiceDAOImpl();
    }


    public void displayName(){

        invoiceDAO.accessInvoice(1).getCustomer();

    }

    public int showInvoiceNo(){
        int test = invoiceDAO.accessInvoice(1).getInvoiceId();
        return test;
    }

}

