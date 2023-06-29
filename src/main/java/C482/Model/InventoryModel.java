//  InventoryModel.java
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215

package C482.Model;

/**
 *
 * @author AbdAllah Khodeir
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * Setting InventoryModel Class to store Products and Parts in the Observable Lists.
 *
 */
public class InventoryModel {

    private static ObservableList<PartModel> allParts = FXCollections.observableArrayList();
    private static ObservableList<ProductModel> allProducts = FXCollections.observableArrayList();

    /**
     * Adding a new part to the Obeservable List.
     *
     * @param newPart
     */
    public static void AddNewPart(PartModel newPart) {

        allParts.add(newPart);
    }

    /**
     * Adding a new Product to the Obeservable List.
     *
     * @param newProduct
     */
    public static void AddNewProduct(ProductModel newProduct) {

        allProducts.add(newProduct);
    }

    /**
     * Searching for part using user input.
     *
     * @param partID
     * @return part if found (not exact match) and null if no match has been found.
     *
     */
    public static PartModel lookupPart(int partID) {
        for(PartModel part: InventoryModel.getAllParts()) {
            while (part.getId() == partID) {
                return part;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No item found");
        alert.show();
        return null;
    }

    /**
     * Searching for part using user input.
     *
     * @param productID
     * @return product if found (not an exact match) and null if no match has been found.
     */
    public static ProductModel lookupProduct(int productID) {
        for(ProductModel product: InventoryModel.getAllProducts()){
            while (product.getId() == productID)
                return product;
        }
        return null;
    }

    /**
     * Returning a list of matched parts.
     *
     * @param partName
     * @return filtered parts
     */
    public static ObservableList<PartModel> lookupPart(String partName) {
        ObservableList<PartModel> PartName = FXCollections.observableArrayList();

        for (PartModel part: allParts) {
            if (part.getName().contains(partName)) {
                PartName.add(part);
            }
        }
        return PartName;
    }

    /**
     * Returning a list of matched products.
     *
     * @param productName
     * @return filteredProduct
     */
    public static ObservableList<ProductModel> lookupProduct(String productName) {
        ObservableList<ProductModel> ProductName = FXCollections.observableArrayList();

        for (ProductModel product: allProducts) {
            if (product.getName().contains(productName)) {
                ProductName.add(product);
            }
        }
        return ProductName;
    }

    /**
     * Update matched part.
     *
     * @param index
     * @param selectedPart
     */
    public static void UpdateSelectedPart(int index, PartModel selectedPart) {

        allParts.set(index, selectedPart);
    }

    /**
     * Update matched product.
     *
     * @param index
     * @param selectedProduct
     */

    public static void UpdateSelectedProduct(int index, ProductModel selectedProduct) {

        allProducts.set(index, selectedProduct);
    }


    /**
     * Delete the selected part.
     *
     * @param selectedPart
     */

    public static boolean DeleteSelectedPart(PartModel selectedPart) {
        if(allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete the selected product.
     *
     * @param selectedProduct
     */
    public static boolean DeleteSelectedProduct(ProductModel selectedProduct) {
        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    public static ObservableList<ProductModel> getAllProducts(){

        return allProducts;
    }

    public static ObservableList<PartModel> getAllParts() {

        return allParts;
    }
}