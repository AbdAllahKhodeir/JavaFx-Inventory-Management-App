//  AddPartController.java
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215


package C482.Controllers;

/**
 *
 * @author AbdAllah Khodeir
 */

import C482.Model.InHouseModel;
import C482.Model.InventoryModel;
import C482.Model.OutsourcedModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddPartController {

    @FXML private Label machineIDorCompanyID;

    @FXML private TextField addPartMaximum;
    @FXML private TextField addPartMinimum;
    @FXML private TextField addPartInventoryNum;
    @FXML private TextField addPartPricing;
    @FXML private TextField addPartMachineID;
    @FXML private RadioButton addPartOutsourced;
    @FXML private Button addPartSave;
    @FXML private Button addPartCancel;
    @FXML private TextField newPartName;
    @FXML private RadioButton InHousePartRadio;
    @FXML private RadioButton OutsourcedPartRadio;

    /**
     * Setting Default as Machine ID when Adding Parts
     *
     * @param event
     */

    @FXML
    void AddInHousePart(ActionEvent event) {

        machineIDorCompanyID.setText("Machine ID");
    }


    /**
     * Switching to Company Name When Adding Outsourced Parts
     *
     * @param event
     */
    @FXML
    void AddOutsourcedPart(ActionEvent event) {

        machineIDorCompanyID.setText("Company Name");
    }


    /**
     * Escape to Main Screen When Cancel Add Part
     *
     * @param event
     */

    @FXML
    public void addPartCancel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/MainScreen.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage MainScreenReturn = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainScreenReturn.setScene(scene);
        MainScreenReturn.show();
    }

    @FXML
    void AddPartSave(ActionEvent event) throws IOException {

        try {

            // Using a random number and multiplying it by 100 will prevent it from overlapping with products.

            int uniqueID = (int) (Math.random() * 100);
            String name = newPartName.getText();
            int inStock = Integer.parseInt(addPartInventoryNum.getText());
            double price = Double.parseDouble(addPartPricing.getText());
            int min = Integer.parseInt(addPartMinimum.getText());
            int max = Integer.parseInt(addPartMaximum.getText());
            int machineID = 0;
            String companyName;

            // Error Message that Minimum should be less than Maximum.

            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum Must Be Greater Than Minimum.");
                alert.showAndWait();
                return;
            }

            // Inventory should be between the Minimum and Maximum.

            else if (inStock < min || max < inStock) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory Must be Within Minimum and Maximum.");
                alert.showAndWait();
                return;
            }

            System.out.println(OutsourcedPartRadio.isSelected() + " OutSourced");
            System.out.println(InHousePartRadio.isSelected() + " InHouse");
            if (OutsourcedPartRadio.isSelected()) {
                companyName = addPartMachineID.getText();
                OutsourcedModel addPart = new OutsourcedModel(uniqueID, name, price, inStock, min, max, companyName);
                InventoryModel.AddNewPart(addPart);
            }

            if (InHousePartRadio.isSelected()) {
                machineID = Integer.parseInt(addPartMachineID.getText());
                InHouseModel addPart = new InHouseModel(uniqueID, name, price, inStock, min, max, machineID);
                InventoryModel.AddNewPart(addPart);
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

}