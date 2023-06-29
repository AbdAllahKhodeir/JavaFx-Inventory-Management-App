//  AddProductController.java
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215

package C482.Controllers;

/**
 *
 * @author AbdAllah Khodeir
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import C482.Model.InventoryModel;
import C482.Model.PartModel;
import C482.Model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class AddProductController implements Initializable {

    // Observable List holds the associated parts list.
    private ObservableList<PartModel> AssocPartList = FXCollections.observableArrayList();

    @FXML private TextField addProductSearchField;
    @FXML private Button addProductSearch;
    @FXML private Button addProductCancel;
    @FXML private TextField addProductID;
    @FXML private TableColumn<?, ?> assocProductIDCol;
    @FXML private TableColumn<?, ?> assocProductNameCol;
    @FXML private TableColumn<?, ?> assocProductInventoryCol;
    @FXML private TableColumn<?, ?> assocPriceCol;
    @FXML private TableView<PartModel> assocProductTab;
    @FXML private TextField addProductName;
    @FXML private TextField addProductInv;
    @FXML private TextField addProductPrice;
    @FXML private TextField addProductMaximum;
    @FXML private TextField addProductMinimum;
    @FXML private TableView<PartModel> addProductTab;
    @FXML private TableColumn<?, ?> addProductPartIDCol;
    @FXML private TableColumn<?, ?> addPartNameCol;
    @FXML private TableColumn<?, ?> addProductInventoryCol;
    @FXML private TableColumn<?, ?> addProductPriceCol;
    @FXML private Button addProductSave;
    @FXML private Button removeAssociatedPartButton;
    @FXML private Button addProductAddButton;
    @FXML private Button removeAssocPartButton;

    /**
     * Escape to Main Screen when Cancel
     *
     * @param event
     */

    @FXML
    public void CancelProductAdd(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/MainScreen.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }


    /**
     * Saving New Added Product
     *
     * @param event
     */
    @FXML
    void SaveProduct(ActionEvent event) throws IOException {
        try {

            // Multiplying by 10000 to avoid overlapping

            int uniqueID = (int) (Math.random() * 10000);

            String name = addProductName.getText();
            int stock = Integer.parseInt(addProductInv.getText());
            double price = Double.parseDouble(addProductPrice.getText());
            int max = Integer.parseInt(addProductMaximum.getText());
            int min = Integer.parseInt(addProductMinimum.getText());

            if (min > stock || stock < max) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be between the minimum and maximum values.");
                alert.showAndWait();
            } else if (min >= max) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum must be greater than Minimum.");
                alert.showAndWait();
            }

            ProductModel product = new ProductModel(uniqueID, name, price, stock, min, max);

            for (PartModel part: AssocPartList) {
                if (part != AssocPartList)
                    product.addAssociatedParts(part);
            }

            InventoryModel.getAllProducts().add(product);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(new File("/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/MainScreen.fxml").toURI().toURL());
            stage.setScene(new Scene((Parent) scene));
            stage.show();


        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setContentText("WRONG VALUE");
            alert.showAndWait();
            return;
        }
    }

    /**
     * Adding the part to the observable list.
     *
     * @param event
     */

    @FXML
    void addProductAdd(ActionEvent event) {
        PartModel selectedPart = addProductTab.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setContentText("SELECT A PART FROM THE LIST");
            alert.showAndWait();
            return;
        }
        else if (!AssocPartList.contains(selectedPart))
        {
            AssocPartList.add(selectedPart);
            assocProductTab.setItems(AssocPartList);
        }
    }

    /**
     * Removing the part to the observable list.
     *
     * @param event
     */
    @FXML
    void removeAssocPartButton(ActionEvent event) {
        PartModel selectedPart = assocProductTab.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setContentText("SELECT A PART FROM THE LIST");
            alert.showAndWait();
            return;
        }
        else if (AssocPartList.contains(selectedPart))
        {
            AssocPartList.remove(selectedPart);
            assocProductTab.setItems(AssocPartList);
        }
    }

    /**
     *  Populate the table with the Products and its associated parts.
     *
     * @param resourceBundle
     * @param url
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addProductTab.setItems(InventoryModel.getAllParts());
        addProductPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        assocProductTab.setItems(AssocPartList);
        assocProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     *  Search based on input text
     *
     * @param event
     */
    @FXML
    void AddedProductSearch(ActionEvent event) {

        String searchText = addProductSearchField.getText();
        ObservableList<PartModel> results = InventoryModel.lookupPart(searchText);

        try {
            while (results.size() == 0 ) {
                int partID = Integer.parseInt(searchText);
                results.add(InventoryModel.lookupPart(partID));
            }
            addProductTab.setItems(results);

        } catch (NumberFormatException e) {
            Alert noParts = new Alert(Alert.AlertType.ERROR);
            noParts.setTitle("ERROR");
            noParts.setContentText("Part Model Not Found");
            noParts.showAndWait();
        }
    }

}