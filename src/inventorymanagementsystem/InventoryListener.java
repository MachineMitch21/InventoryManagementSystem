/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

/**
 *
 * @author mitch
 */
public interface InventoryListener {
    
    public void partAdded(InventoryEvent e);
    public void partDeleted(InventoryEvent e);
    public void partModified(InventoryEvent e);
    public void productAdded(InventoryEvent e);
    public void productDeleted(InventoryEvent e);
    public void productModified(InventoryEvent e);
    
}
