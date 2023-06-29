//  InHouseModel.java
//  C482
//  Created by AbdAllah M AbdAllah Khodeir
// Student ID: 010734215

package C482.Model;

/**
 *
 * @author AbdAllah Khodeir
 */

/**
 * Setting the InHouseModel Class.
 *
 */

public class InHouseModel extends PartModel {
    private int machineID;

    public InHouseModel(int id, String Name, double Price, int InventoryCount, int Min, int Max, int MachineID) {
        super(id, Name, Price, InventoryCount, Min, Max);
        this.machineID = MachineID;
    }

    public void SetMachineID(int MachineID) {
        this.machineID = MachineID;
    }

    public int GetMachineID() {

        return machineID;
    }

}