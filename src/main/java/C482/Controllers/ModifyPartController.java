//  ModifyPartController.java
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
import C482.Model.PartModel;

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

/**
 * Setting the ModifyPartControllerClass.
 *
 */

public class ModifyPartController  {

    // label for swapping between machine id and outsourced

    @FXML private Label machineOrCompanyID;
    @FXML private TextField modifyPartID;
    @FXML private TextField modifyPartName;
    @FXML private TextField modifyPartInv;
    @FXML private TextField modifyPartPrice;
    @FXML private TextField modifyPartMaximum;
    @FXML private TextField modifyPartMinimum;
    @FXML private TextField addPartMachineID;
    @FXML private RadioButton ModifyPartInHouseRadio;
    @FXML private RadioButton ModifyPartOutsourcedRadio;
    @FXML private Button modifyPartCancelButton;

    private int currentIndex = 0;

    /**
     *  Escape to Main Screen when Cancel.
     *
     * @param event
     */

    @FXML
    public void ModifyPartCancel(ActionEvent event) throws IOException {
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
     * @param part
     * @param selectedIndex
     */
    public void SelectedPart(int selectedIndex, PartModel part){

        currentIndex = selectedIndex;

        if (part instanceof InHouseModel) {
            ModifyPartInHouseRadio.setSelected(true);
            addPartMachineID.setText(String.valueOf(((InHouseModel) part).GetMachineID()));
        }
        else {
            ModifyPartOutsourcedRadio.setSelected(true);
            addPartMachineID.setText(((OutsourcedModel) part).getCompanyName());
        }

        modifyPartID.setText(String.valueOf(part.getId()));
        modifyPartName.setText(String.valueOf(part.getName()));
        modifyPartInv.setText(String.valueOf(part.getStock()));
        modifyPartPrice.setText(String.valueOf(part.getPrice()));
        modifyPartMaximum.setText(String.valueOf(part.getMax()));
        modifyPartMinimum.setText(String.valueOf(part.getMin()));
    }

    /**
     * Changing Part Type to In House.
     *
     * @param event
     */

    @FXML
    public void AddingPartInHouse(ActionEvent event) {

        machineOrCompanyID.setText("Machine ID");
    }
    /**
     *  Changing Part Type to OutSourced.
     *
     * @param event
     */
    @FXML
    public void AddingPartOutsourced(ActionEvent event) {

        machineOrCompanyID.setText("Company Name");
    }


    /**
     * Saving Modified Part.
     *
     * @param event
     */
    @FXML
    void SaveModifiedPart(ActionEvent event) throws IOException {

        try {
            int partID = Integer.parseInt(modifyPartID.getText());
            String name = modifyPartName.getText();
            int inStock = Integer.parseInt(modifyPartInv.getText());
            double price = Double.parseDouble(modifyPartPrice.getText());
            int min = Integer.parseInt(modifyPartMinimum.getText());
            int max = Integer.parseInt(modifyPartMaximum.getText());
            int machineID;
            String companyName;

            // Minimum Should be Less than Maximum.

            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Maximum must be greater than Minimum.");
                alert.showAndWait();
                return;
            }
            //Inventory should be between minimum and maximum.

            else if (inStock < min || max < inStock) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be between Minimum and Maximum.");
                alert.showAndWait();
                return;
            }


            System.out.println(ModifyPartOutsourcedRadio.isSelected() + " OutSourced");
            System.out.println(ModifyPartInHouseRadio.isSelected() + " InHouse");
            if (ModifyPartInHouseRadio.isSelected()) {
                machineID = Integer.parseInt(addPartMachineID.getText());
                InHouseModel updatedPart = new InHouseModel(partID, name, price, inStock, min, max, machineID);
                InventoryModel.UpdateSelectedPart(currentIndex, updatedPart);
            }
            if (ModifyPartOutsourcedRadio.isSelected()) {
                companyName = addPartMachineID.getText();
                OutsourcedModel updatedPart = new OutsourcedModel(partID, name, price, inStock, min, max, companyName);
                InventoryModel.UpdateSelectedPart(currentIndex, updatedPart);
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