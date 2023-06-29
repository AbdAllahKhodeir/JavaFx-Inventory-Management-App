//  ModifyPartController.java
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215

package C482.Controllers;

/**
 *
 * @author AbdAllah Khodeir
 */

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Setting the ModifyProductController class.
 *
 */

public class ModifyProductController implements Initializable {

    private ObservableList<PartModel> assocPartList = FXCollections.observableArrayList();

    @FXML private TableView<ProductModel> mainScreenProductsTable;
    @FXML private TableView<PartModel> modifyProductTab;
    @FXML private TableColumn<?, ?> modifyProductPartIDCol;
    @FXML private TableColumn<?, ?> modifyPartNameCol;
    @FXML private TableColumn<?, ?> modifyProductInventoryCol;
    @FXML private TableColumn<?, ?> modifyProductPriceCol;
    @FXML private TableView<PartModel> assocProductTab;
    @FXML private TableColumn<?, ?> associatedProductIDCol;
    @FXML private TableColumn<?, ?> associatedPartNameCol;
    @FXML private TableColumn<?, ?> associatedInventoryCol;
    @FXML private TableColumn<?, ?> associatedPriceCol;
    @FXML private Button modifyProductCancel;
    @FXML private Button modifyProductSave;
    @FXML private Button removeAssociatedPart;
    @FXML private Button modifyProductButton;
    @FXML private TextField addProductMachineID;
    @FXML private TextField modifyProductSearchBox;
    @FXML private Button modifyProductSearchButton;
    @FXML private TextField modifyProductID;
    @FXML private TextField modifyProductName;
    @FXML private TextField modifyProductInv;
    @FXML private TextField modifyProductPrice;
    @FXML private TextField modifyProductMaximum;
    @FXML private TextField modifyProductMinimum;

    private int currentIndex = 0;

    /**
     *  Escape to Main Screen when Cancel.
     *
     * @param event
     */

    @FXML
    public void ModifyProductCancel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/MainScreen.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    /**
     * Catching Input from Main screen.
     *
     * @param selectedIndex
     * @param product
     */

    @FXML
    public void SelectedProduct(int selectedIndex, ProductModel product){

        currentIndex = selectedIndex;
        modifyProductID.setText(String.valueOf(product.getId()));
        modifyProductName.setText(String.valueOf(product.getName()));
        modifyProductInv.setText(String.valueOf(product.getStock()));
        modifyProductPrice.setText(String.valueOf(product.getPrice()));
        modifyProductMaximum.setText(String.valueOf(product.getMax()));
        modifyProductMinimum.setText(String.valueOf(product.getMin()));

        for (PartModel part: product.getAllAssociatedParts()) {
            assocPartList.add(part);}
    }

    /**
     * Populating the tables.
     *
     * @param url
     * @param resourceBundle
     */

    public void initialize(URL url, ResourceBundle resourceBundle) {

        modifyProductTab.setItems(InventoryModel.getAllParts());
        modifyProductPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //add parts to associated table (bottom)

        assocProductTab.setItems(assocPartList);
        associatedProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Adding selected products associated parts table.
     *
     * @param event
     */
    @FXML
    void AddingProductPartModify(ActionEvent event) {
        PartModel selectedPart = modifyProductTab.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setContentText("SELECT A PART FROM THE LIST");
            alert.showAndWait();
            return;
        }
        else if (!assocPartList.contains(selectedPart))
        {
            assocPartList.add(selectedPart);
            assocProductTab.setItems(assocPartList);
        }
    }

    /**
     * Remove the selected part to the associated parts table.
     *
     * @param event
     */
    @FXML
    void RemoveAssociatedPart(ActionEvent event) {
        PartModel selectedPart = assocProductTab.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setContentText("SELECT A PART FROM THE LIST");
            alert.showAndWait();
            return;
        }
        else if (assocPartList.contains(selectedPart))
        {
            ProductModel.deleteAssocdPart(selectedPart);
            assocPartList.remove(selectedPart);
            assocProductTab.setItems(assocPartList);
        }
    }

    /**
     * Saving the added Product.
     *
     * @param event`
     */

    @FXML
    void SavingAddedProduct(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(modifyProductID.getText());
            String name = modifyProductName.getText();
            int stock = Integer.parseInt(modifyProductInv.getText());
            double price = Double.parseDouble(modifyProductPrice.getText());
            int max = Integer.parseInt(modifyProductMaximum.getText());
            int min = Integer.parseInt(modifyProductMinimum.getText());

            if (stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be between Minimum and Maximum.");
                alert.showAndWait();
                return;
            } else if (min >= max) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum must be between Minimum and Maximum");
                alert.showAndWait();
                return;
            }
            ProductModel updatedProduct = new ProductModel(id, name, price, stock, min, max);
            if (updatedProduct != assocPartList) {
                InventoryModel.UpdateSelectedProduct(currentIndex, updatedProduct);
            }


            for (PartModel part: assocPartList) {
                if (part != assocPartList)
                    updatedProduct.addAssociatedParts(part);
            }

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
     * Find parts to add to the associated parts list.
     *
     * @param event
     */
    @FXML
    void ModifyProductAssocPartSearch(ActionEvent event) {
        String searchText = modifyProductSearchBox.getText();
        ObservableList<PartModel> results = InventoryModel.lookupPart(searchText);
        try {
            while (results.size() == 0 ) {
                int partID = Integer.parseInt(searchText);
                results.add(InventoryModel.lookupPart(partID));
            }
            modifyProductTab.setItems(results);
        } catch (NumberFormatException e) {
            Alert noParts = new Alert(Alert.AlertType.ERROR);
            noParts.setTitle("ERROR");
            noParts.setContentText("PART NOT FOUND");
            noParts.showAndWait();
        }
    }
}