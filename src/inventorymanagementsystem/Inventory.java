/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mitch
 */
public class Inventory {
    
    // All of the objects that are subscribing to inventory events
    private Set<InventoryListener> _inventoryListeners;
    
    private ArrayList<Product>   _products = new ArrayList<Product>();
    private ArrayList<Part>      _allParts = new ArrayList<Part>();
    
    private static int nextPartID = 0;
    private static int nextProductID = 0;
    
    
    public Inventory()
    {
        _inventoryListeners = new HashSet<InventoryListener>();
        
        int partsToCreate = 10000;
        
        for (int i = 0; i < partsToCreate; i++)
        {
            InHouse part = new InHouse();
            
            part.setName(Integer.toString(i));
            part.setInStock(25);
            part.setMax(50);
            part.setMin(0);
            part.setPrice(25);
            
            addPart(part);
        }
    }
    
    public synchronized void addListener(InventoryListener il)
    {
        _inventoryListeners.add(il);
    }
    
    public synchronized void removeListener(InventoryListener il)
    {
        _inventoryListeners.remove(il);
    }
    
    protected synchronized void firePartAddedEvent(Part p)
    {
        InventoryEvent ie = new InventoryEvent(this, p);
        
        for (InventoryListener il : _inventoryListeners)
        {
            il.partAdded(ie);
        }
    }
    
    protected synchronized void firePartDeletedEvent(Part p)
    {
        InventoryEvent ie = new InventoryEvent(this, p);
        
        for (InventoryListener il : _inventoryListeners)
        {
            il.partDeleted(ie);
        }
    }
    
    protected synchronized void firePartModifiedEvent(Part p)
    {
        InventoryEvent ie = new InventoryEvent(this, p);
        
        for (InventoryListener il : _inventoryListeners)
        {
            il.partModified(ie);
        }
    }
    
    protected synchronized void fireProductAddedEvent(Product p)
    {
        InventoryEvent ie = new InventoryEvent(this, p);
        
        for (InventoryListener il : _inventoryListeners)
        {
            il.productAdded(ie);
        }
    }
    
    protected synchronized void fireProductDeletedEvent(Product p)
    {
        InventoryEvent ie = new InventoryEvent(this, p);
        
        for (InventoryListener il : _inventoryListeners)
        {
            il.productDeleted(ie);
        }
    }
    
    protected synchronized void fireProductModifiedEvent(Product p)
    {
        InventoryEvent ie = new InventoryEvent(this, p);
        
        for (InventoryListener il : _inventoryListeners)
        {
            il.productModified(ie);
        }
    }
    
    public void addProduct(Product p)
    {
        p.setProductID(nextProductID);
        nextProductID++;
        
        /*
            Don't do this currently.  It is not a part of the requirements
        
        for (int i = 0; i < this.numberOfProducts(); i++)
        {
            Product currentProduct = _products.get(i);
            
            String newProductName = p.getName();
            String currentProductName = currentProduct.getName();
            
            if (newProductName.equals(currentProductName))
            {
                ErrorDialogController edc = new ErrorDialogController("A product with the name " + newProductName + " already exists");
            }
            else 
            {
                _products.add(p);
                fireProductAddedEvent(p);                
            }
        }
        */

        _products.add(p);
        fireProductAddedEvent(p);      
    }
   
    public boolean removeProduct(int index)
    {
        boolean removeSuccessful = false;
        
        try 
        {
            Product p = _products.get(index);
            
            _products.remove(index);
            removeSuccessful = true;
            
            fireProductDeletedEvent(p);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe.getMessage());
        }
        
        return removeSuccessful;
    }
    
    public boolean removeProduct(Product product)
    {
        boolean removeSuccessful = false;
        
        removeSuccessful = _products.remove(product);
        
        if (removeSuccessful)
        {
            fireProductDeletedEvent(product);
        }
        
        return removeSuccessful;
    }
    
    public Product lookupProduct(int index)
    {
        Product p = null;
        
        try 
        {
            p = _products.get(index);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe.getMessage());
        }
        
        return p;
    }
    
    public void updateProduct(int i)
    {
        // Actual updates are handled in the other updateProduct method
    }
    
    public void updateProduct(Product p)
    {
        try 
        {
            for (int i = 0; i < _products.size(); i++)
            {
                Product temp = _products.get(i);
                
                if (temp.getProductID() == p.getProductID())
                {
                    _products.set(i, p);
                    
                    fireProductModifiedEvent(_products.get(i));
                }
            }
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe.getMessage());
        }
    }
    
    public void addPart(Part p)
    {
        p.setPartID(nextPartID);
        nextPartID++;
        
        _allParts.add(p);
        
        firePartAddedEvent(p);
    }
    
    public boolean deletePart(Part p)
    {
        boolean partRemoved = false;
        
        if (_allParts.contains(p))
        {
            _allParts.remove(p);
            
            firePartDeletedEvent(p);
            
            partRemoved = true;
        }
        else 
        {
            partRemoved = false;
        }
        
        return partRemoved;
    }
    
    public Part lookupPart(int index)
    {
        Part p = null;
        
        try 
        {
            p = _allParts.get(index);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe.getMessage());
        }
        
        return p;
    }
    
    public void updatePart(int i)
    {
        // Actual updates are handled using the other updatePart method
    }
    
    public void updatePart(Part p)
    {
        try 
        {
            for (int i = 0; i < _allParts.size(); i++)
            {
                Part temp = _allParts.get(i);
                
                if (temp.getPartID() == p.getPartID())
                {
                    _allParts.set(i, p);
                    
                    firePartModifiedEvent(_allParts.get(i));
                }
            }
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe.getMessage());
        }
    }
    
    public boolean hasParts()
    {
        return !_allParts.isEmpty();
    }
    
    public boolean hasProducts()
    {
        return !_products.isEmpty();
    }
    
    public int numberOfParts()
    {
        return _allParts.size();
    }
    
    public int numberOfProducts()
    {
        return _products.size();
    }
}
