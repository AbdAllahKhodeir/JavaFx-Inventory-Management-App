//  ProductModel.java
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215

package C482.Model;

/**
 *
 * @author AbdAllah Khodeir
 */

import C482.Model.PartModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductModel {
    public static ObservableList<PartModel> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public ProductModel(int id, String name, double price, int stock, int min, int max) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @param id
     */
    public void setId(int id) { this.id = id; }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * Adds a part to the associated parts observablelist.
     * @param part
     */
    public void addAssociatedParts(PartModel part) {
        associatedParts.add(part);
    }

    /**
     * Remove a part from the observablelist.
     * @param selectedAssociatedPart
     * @return true
     */
    public boolean deleteAssociatedPart(PartModel selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**
     * Returns all associated parts for selected product.
     * @return associatedParts
     */
    public ObservableList<PartModel> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * Remove a part from the observablelist.
     * @param selectedAssociatedPart
     * @return true
     */
    public static boolean deleteAssocdPart(PartModel selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }
}