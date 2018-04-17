package INFO233.Oblig3.GUI;

import DAO.CategoryDAOImpl;
import Entities.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewCategoryController {

    @FXML
    private TextField categoryid, nameid;

    @FXML
    private Parent parent;

    @FXML
    private TextArea systemText;

    private CategoryDAOImpl categoryDAO = new CategoryDAOImpl();


    /**
     * Oppretter en ny kategori
     * @return kategori-objekt
     */
    public Category newCategory() {
        Category category = new Category();
        category.setCategoryName(nameid.getText());
        category.setCategoryId(Integer.parseInt(categoryid.getText()));
        return category;
    }

    /**
     * Håndterer insert-knappen. Legger til objektet til databasen.
     */
    public void onInsertButton() {
        categoryDAO.addCategory(newCategory());
        systemText.setText(newCategory().getCategoryName() + " inserted");

    }

    /**
     * Fører brukerern tilbake til main
     */

    public void onBackButton() {
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
