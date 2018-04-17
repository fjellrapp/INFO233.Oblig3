package INFO233.Oblig3.GUI;

import DAO.CategoryDAOImpl;
import Entities.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class EditCategoryPrestageController {

    @FXML
    private TextField categoryid;

    @FXML
    private Text alertid;

    @FXML
    private Parent parent;

    private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();

    public void onNext() {
        int collectedId = Integer.parseInt(categoryid.getText());
        if (!doesIDExist()) {
            alertid.setText("Not a valid ID. Create a new one or input a valid ID");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EditCategoryFXML.fxml"));
                AnchorPane anchor = loader.load();
                EditCategoryController controller = loader.getController();
                controller.setId(collectedId);
                Scene scene = new Scene(anchor);
                Stage stage = (Stage) parent.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean doesIDExist() {
        Category category = categoryDAO.accessCategory(Integer.parseInt(categoryid.getText()));
        return category.getCategoryName() != null;
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
