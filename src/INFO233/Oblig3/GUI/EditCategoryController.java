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

public class EditCategoryController {

    CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
    @FXML
    private TextField categoryid, name;
    @FXML
    private Parent parent;
    @FXML
    private TextArea systemText;

    /**
     * Setter ID
     * @param inheritedID
     */

    public void setId(Integer inheritedID) {
        {
            categoryid.setText(Integer.toString(inheritedID));
        }
    }

    public void editCategory() {
        Category category = categoryDAO.accessCategory(Integer.parseInt(categoryid.getText()));
        if (!name.getText().trim().isEmpty()) {
            category.setCategoryName(name.getText());
        }

        categoryDAO.editCategory(category);
    }

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


