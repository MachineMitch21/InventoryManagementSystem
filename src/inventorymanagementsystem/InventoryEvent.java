/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.util.ArrayList;

/**
 *
 * @author mitch
 */
public class InventoryEvent {
    
    private Inventory   _source;
    private Part        _part;
    private Product     _product;

    public InventoryEvent(Inventory source)
    {
        _source     = source;
        _part       = null;
        _product    = null;        
    }    
    
    public InventoryEvent(Inventory source, Part part)
    {
        _source     = source;
        _part       = part;
        _product    = null;
    }
    
    public InventoryEvent(Inventory source, Product product)
    {
        _source     = source;
        _part       = null;
        _product    = product;        
    }
    
    public InventoryEvent(Inventory source, Product product, Part part)
    {
        _source     = source;
        _part       = part;
        _product    = product;        
    }
    
    public Inventory getSource()
    {
        return _source;
    }
    
    public Part getPart()
    {
        return _part;
    }
    
    public Product getProduct()
    {
        return _product;
    }
}
