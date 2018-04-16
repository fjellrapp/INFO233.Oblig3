package INFO233.Oblig3.GUI;

import DAO.CustomerDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class EditCustomerStage1Controller {

    @FXML
    private Parent parent;

    @FXML
    private TextField id;

    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();





    public void onNext(){
        int collectedId = Integer.parseInt(id.getText());
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("EditCustomerFXML.fxml"));
            AnchorPane anchor = loader.load();
            EditCustomerController controller = loader.getController();
            controller.setTextId(collectedId);
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }



}
