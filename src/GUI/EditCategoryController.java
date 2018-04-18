package GUI;

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

public class EditCategoryController {

    CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
    @FXML
    private TextField categoryid, name;
    @FXML
    private Parent parent;
    @FXML
    private TextArea systemText;

    /**
     * Brukes av EditCategoryPrestageController for å hente brukerdefinert ID.
     * @param inheritedID IDen som kommer inn i den andre klassen
     */

    public void setId(Integer inheritedID) {
        {
            categoryid.setText(Integer.toString(inheritedID));
        }
    }

    /**
     * Endrer navn på en kategori.
     */
    public void editCategory() {
        Category category = categoryDAO.accessCategory(Integer.parseInt(categoryid.getText()));
        if (!name.getText().trim().isEmpty()) {
            category.setCategoryName(name.getText());
        }
        categoryDAO.editCategory(category);
    }

    /**
     * Viser brukeren hvilke data som er registert på en gitt kategori.
     */
    public void onDisplay() {
        try {
            Category c = categoryDAO.accessCategory(Integer.parseInt(categoryid.getText()));
            systemText.setText(
                    "Currently registered data in this category: " + " \n" +
                            "CategoryID: " + c.getCategoryId() + " \n" +
                            "Category name: " + c.getCategoryName() + " \n"
            );
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Brukes på "Back"-knappen i vinduet og fører brukeren tilbake til "Prestage" vinduet.
     */
    public void onBack() {
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("EditCategoryPrestageFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


