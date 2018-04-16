package INFO233.Oblig3.GUI;

import DAO.InvoiceDAOImpl;
import DAO.ProductDAOImpl;
import Entities.Invoice;
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

public class EditProductController {

    @FXML
    private TextField productid, name, description, price, category;

    @FXML
    private TextArea systemText;

    @FXML
    private Parent parent;

    private ProductDAOImpl productDAO = new ProductDAOImpl();

    public void setId(Integer inheritedID){
        productid.setText(Integer.toString(inheritedID));
    }

    public void editProduct() {
        Product product = productDAO.accessProduct(Integer.parseInt(productid.getText()));
        if (!name.getText().trim().isEmpty()) {
            product.setProductName(name.getText());
        }if(!description.getText().trim().isEmpty()){
            product.setDescription(description.getText());
        }if(!price.getText().trim().isEmpty()){
            product.setPrice(Float.parseFloat(price.getText()));
        }
        productDAO.editProduct(product);
    }


    public void onDisplay(){

        try {
            Product p = productDAO.accessProduct(Integer.parseInt(productid.getText()));

            systemText.setText(
                    "Currently registered data about this product: " + " \n" +
                            "ID: " + p.getProductId() + " \n" +
                            "Name: " + p.getProductName() + " \n" +
                            "Description: " + p.getDescription() + " \n" +
                            "Price: " + p.getPrice() + " \n" +
                            "Category: " + p.getCategory()
            );
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onBack() {
        try {
            AnchorPane anchor = FXMLLoader.load(getClass().getResource("EditProductPrestageFXML.fxml"));
            Scene scene = new Scene(anchor);
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
