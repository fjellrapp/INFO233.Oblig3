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

    /**
     * Henter ID fra fra tekstfeltet og kaller metoden doesIDExist() som sjekker om IDen eksisterer.
     * Går videre til try-blokken hvis den eksisterer og fører brukeren inn til
     * EditCategory
     */

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

    /**
     * Sjekker om den gitte IDen eksisterer
     * @return true eller false
     */
    private boolean doesIDExist() {
        Category category = categoryDAO.accessCategory(Integer.parseInt(categoryid.getText()));
        return category.getCategoryName() != null;
    }

    /**
     * Spesifiserer hva som skal skje når brukeren trykker "back"
     */
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
