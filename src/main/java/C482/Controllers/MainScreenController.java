//  MainScreenController.java
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215

package C482.Controllers;

/**
 *
 * @author AbdAllah Khodeir
 */

import C482.Model.InventoryModel;
import C482.Model.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;



public class MainScreenController implements Initializable {

    @FXML private Button addProduct;
    @FXML private Button modifyProduct;
    @FXML private Button deleteProduct;
    @FXML private Button modifyPart;
    @FXML private Button addPart;
    @FXML private Button deletePart;
    @FXML private Button exitMain;
    @FXML private TableColumn<PartModel, Integer> partsTablePartID;
    @FXML private TableColumn<PartModel, String> partsTablePartName;
    @FXML private TableColumn<PartModel, Integer> partsTableInventoryAmount;
    @FXML private TableColumn<PartModel, Integer> partsTablePrice;
    @FXML private TableColumn<ProductModel, Integer> productsTablePartID;
    @FXML private TableColumn<ProductModel, String> productsTablePartName;
    @FXML private TableColumn<ProductModel, Integer> productsTableInventoryAmount;
    @FXML private TableColumn<ProductModel, Integer> productsTablePrice;
    @FXML private TableColumn<ProductModel, Integer> productIDCol;
    @FXML private TableColumn<ProductModel, String> productNameCol;
    @FXML private TableColumn<ProductModel, Integer> productInventoryCol;
    @FXML private TableColumn<ProductModel, Double> productPriceCol;
    @FXML private TableColumn<PartModel, Integer> partIDCol;
    @FXML private TableColumn<PartModel, String> partNameCol;
    @FXML private TableColumn<PartModel, Integer> partInventoryCol;
    @FXML private TableColumn<PartModel, Double> partPriceCol;
    @FXML private TableView<ProductModel> productTable;
    @FXML private TableView<PartModel> partsTableView;
    @FXML private TableView<ProductModel> mainScreenProductsTab;
    @FXML private TableView<PartModel> mainScreenPartsTab;
    @FXML private TableView<PartModel> modifyProductTab;
    @FXML private TextField productSearch;
    @FXML private TextField partSearch;

    Stage stage;

    /**
     * Loading AddPart View
     *
     * @param event
     */
    @FXML

    void AddPartMainscreen(ActionEvent event) throws IOException {

        String absolutePath = "/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/AddPart.fxml";
        File file = new File(absolutePath);
        Parent addParts = FXMLLoader.load(file.toURI().toURL());
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Loading ModifyPartController
     *
     * @param event
     */

    @FXML
    void ModifyPartMainscreen(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(new File("/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/ModifyPart.fxml").toURI().toURL());

            ModifyPartController MPController = loader.getController();
            MPController.SelectedPart(mainScreenPartsTab.getSelectionModel().getSelectedIndex(), mainScreenPartsTab.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Select A Part First");
            alert.show();
        }
    }

    /**
     * Loading AddProduct Controller.
     *
     * @param event
     */
    @FXML
    void AddProductMainscreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/AddProduct.fxml").toURI().toURL());
        Parent addParts = fxmlLoader.load();
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


    /**
     * Exit Button to Close App
     * @param ExitButton
     */
    public void MainscreenExitButton(ActionEvent ExitButton) {
        Stage stage = (Stage) ((Node) ExitButton.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Loading the ModifyProduct controller.
     *
     * @param event
     */
    @FXML
    void ModifyProductMainscreen(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            File file = new File("/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/ModifyProduct.fxml");
            loader.setLocation(file.toURI().toURL());
            loader.load();

            ModifyProductController MPController = loader.getController();
            MPController.SelectedProduct(mainScreenProductsTab.getSelectionModel().getSelectedIndex(), mainScreenProductsTab.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Select A Product First");
            alert.show();
        }
    }

    /**
     * Populating tables
     * .
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainScreenPartsTab.setItems(InventoryModel.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        mainScreenProductsTab.setItems(InventoryModel.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Deleting selected part
     *
     * @param event
     */
    @FXML
    void DeletePartMainscreen(ActionEvent event) {
        PartModel selectedPart = mainScreenPartsTab.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRM");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            InventoryModel.DeleteSelectedPart(selectedPart);
        }
    }

    /**
     * Deleting selected product.
     *
     * @param event
     */

    @FXML
    void DeleteProductMainscreen(ActionEvent event) {
        ProductModel selectedProduct = mainScreenProductsTab.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRM");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to delete this product?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            ProductModel selectedDeleteProduct = mainScreenProductsTab.getSelectionModel().getSelectedItem();
            if (selectedDeleteProduct.getAllAssociatedParts().size() > 0) {
                Alert cantDelete = new Alert(Alert.AlertType.ERROR);
                cantDelete.setTitle("ERROR");
                cantDelete.setContentText("Remove associated parts before deleting this product.");
                cantDelete.showAndWait();
                return;
            }
            InventoryModel.DeleteSelectedProduct(selectedProduct);
        }
    }

    /**
     * Search for matched input text in the parts list.
     *
     * @param event
     */
    @FXML
    void PartSearchMainscreen(ActionEvent event) {
        String searchText = partSearch.getText();
        ObservableList<PartModel> results = InventoryModel.lookupPart(searchText);
        try {
            while (results.size() == 0 ) {
                int partID = Integer.parseInt(searchText);
                results.add(InventoryModel.lookupPart(partID));
            }
            mainScreenPartsTab.setItems(results);
        } catch (NumberFormatException e) {
            Alert noParts = new Alert(Alert.AlertType.ERROR);
            noParts.setTitle("ERROR");
            noParts.setContentText("Part Not Found");
            noParts.showAndWait();
        }
    }

    /**
     * Search for matched input text in the products list.
     *
     * @param event
     */
    @FXML
    void ProductSearchMainscreen(ActionEvent event) {
        String searchText = productSearch.getText();
        ObservableList<ProductModel> results = InventoryModel.lookupProduct(searchText);
        try {
            while (results.size() == 0 ) {
                int productID = Integer.parseInt(searchText);
                results.add(InventoryModel.lookupProduct(productID));
            }
            mainScreenProductsTab.setItems(results);
        } catch (NumberFormatException e) {
            Alert noParts = new Alert(Alert.AlertType.ERROR);
            noParts.setTitle("ERROR");
            noParts.setContentText("Product Not Found");
            noParts.showAndWait();
        }
    }
}