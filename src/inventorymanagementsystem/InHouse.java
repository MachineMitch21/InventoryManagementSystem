/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mitch
 */
public class InHouse extends Part {
    
    private SimpleIntegerProperty machineID = new SimpleIntegerProperty();
    
    public void setMachineID(int machineID)
    {
        this.machineID.set(machineID);
    }
    
    public int getMachineID()
    {
        return machineID.get();
    }
}
