//  InventoryProgram.java  // Main
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215

package C482.Main;

/**
 *
 * @author AbdAllah Khodeir
 */

import C482.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * The main class inserts sample data and provides an application for inventory management.
 */
public class InventoryProgram extends Application {

    /**
     * The Mainscreen.fxml file is initialized by the start method.
     *
     * @param stage
     */

    @Override

    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("/Users/abdallahkhodeir/Desktop/AbdAlah Khodeir Final/Software1/src/main/java/C482/Views/MainScreen.fxml").toURI().toURL());
            Scene scene = new Scene(fxmlLoader.load(), 850, 400);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Main Method to start the application
     *
     * @param args
     */

    public static void main(String[] args) {

        PartModel brakes = new InHouseModel(1, "Brakes", 12.99, 15, 5, 30, 1);
        InventoryModel.AddNewPart(brakes);

        PartModel tire = new InHouseModel(2, "Tire", 14.99, 15, 5, 50, 4);
        InventoryModel.AddNewPart(tire);

        PartModel rim = new InHouseModel(3, "Rim", 56.99, 15, 0, 150, 5);
        InventoryModel.AddNewPart(rim);

        PartModel light = new OutsourcedModel(4, "Light", 125.00, 20, 0, 200, "Gears");
        InventoryModel.AddNewPart(light);

        PartModel door = new OutsourcedModel(5, "Door", 945.00, 20, 0, 200, "DoorTech");
        InventoryModel.AddNewPart(door);

        ProductModel GiantBicycle = new ProductModel(1000, "Giant Bicycle", 299.99, 15, 5, 50);
        InventoryModel.AddNewProduct(GiantBicycle);

        ProductModel ScottBicycle = new ProductModel(1001, "Scott Bicycle", 199.99, 15, 1, 25);
        InventoryModel.AddNewProduct(ScottBicycle);

        ProductModel GtBike = new ProductModel(1002, "GT Bike", 99.99, 15, 1, 25);
        InventoryModel.AddNewProduct(GtBike);

        ProductModel scooter = new ProductModel(1003, "Scooter", 699.99, 15, 1, 25);
        InventoryModel.AddNewProduct(scooter);

        launch();
    }

}