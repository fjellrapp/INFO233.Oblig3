package INFO233.Oblig3.InvoiceOppg2;


import INFO233.Oblig3.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Laster database");
        Main main = new Main();
        try {
            main.queryInvoice();
        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}

