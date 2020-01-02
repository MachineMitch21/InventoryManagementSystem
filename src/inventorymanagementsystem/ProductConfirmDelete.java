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
public class ProductConfirmDelete extends ConfirmDialogController {
    
    Product _productToDelete;
    
    public ProductConfirmDelete(Product product)
    {
        _productToDelete = product;
        
        String headerText = "Are you sure you want to delete Product "
                            + "(ID " + _productToDelete.getProductID() 
                            + ", NAME " + _productToDelete.getName() + ")??";
        
        super.setConfirmHeaderText(headerText);
    }

    @Override
    public void clickedOkay(ActionEvent e) {
        Inventory inventory = InventoryManagementSystem.getInventory();
        
        inventory.removeProduct(_productToDelete);
        
        closeWindow();
    }

    @Override
    public void clickedCancel(ActionEvent e) {
        closeWindow();
    }
    
}
