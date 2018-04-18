package GUI;

import DAO.ProductDAOImpl;
import Entities.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewProductController {

    @FXML
    private TextField productid, name, description, price, category;

    @FXML
    private TextArea systemText;

    private ProductDAOImpl productDAO = new ProductDAOImpl();

    @FXML
    private Parent parent;

    /**
     * Oppretter et nytt product-objekt.
     * @return objektet.
     */
    public Product newProduct() {
        Product product = new Product();

        product.setProductId(Integer.parseInt(productid.getText()));
        product.setProductName(name.getText());
        product.setDescription(description.getText());
        product.setPrice(Float.parseFloat(price.getText()));
        product.setCategory(Integer.parseInt(category.getText()));
        return product;
    }

    /**
     * Hånderer create-knappen. Legger til objektet som kommer inn med newProduct().
     */
    @FXML
    public void addProduct() {
        productDAO.addProduct(newProduct());
        systemText.setText(newProduct().getProductName() + " was added");
    }

    /**
     * Returnerer brukeren til main.
     */
    @FXML
    public void returnToMain() {
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
