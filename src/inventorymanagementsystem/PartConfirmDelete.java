/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.event.ActionEvent;

/**
 *
 * @author mitch
 */
public class PartConfirmDelete extends ConfirmDialogController{
    
    Part _partToDelete;
    
    public PartConfirmDelete(Part part)
    {
        _partToDelete = part;
        
        String headerText = "Are you sure you want to delete Part "
                            + "(ID " + _partToDelete.getPartID() 
                            + ", NAME " + _partToDelete.getName() + ")??"; 
        
        super.setConfirmHeaderText(headerText);
    }

    @Override
    public void clickedOkay(ActionEvent e) {
        Inventory inventory = InventoryManagementSystem.getInventory();
        
        inventory.deletePart(_partToDelete);
        
        closeWindow();
    }

    @Override
    public void clickedCancel(ActionEvent e) {
        closeWindow();
    }
    
}
