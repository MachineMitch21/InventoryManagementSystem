/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.util.ArrayList;
import java.util.Comparator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mitch
 */
public class Product implements Comparator<Product>, Comparable<Product>{
    
    private ArrayList<Part> associatedParts   = new ArrayList<Part>();
    private SimpleStringProperty  name        = new SimpleStringProperty();
    private SimpleDoubleProperty  price       = new SimpleDoubleProperty();
    private SimpleIntegerProperty productID   = new SimpleIntegerProperty();
    private SimpleIntegerProperty inStock     = new SimpleIntegerProperty();
    private SimpleIntegerProperty min         = new SimpleIntegerProperty();
    private SimpleIntegerProperty max         = new SimpleIntegerProperty();
    
    public void setName(String name)
    {
        this.name.set(name);
    }
    
    public void setPrice(double price)
    {
        this.price.set(price);
    }
    
    public void setInStock(int inStock)
    {
        this.inStock.set(inStock);
    }
    
    public void setMin(int min)
    {
        this.min.set(min);
    }
    
    public void setMax(int max)
    {
        this.max.set(max);
    }
    
    public void setProductID(int productID)
    {
        this.productID.set(productID);
    }
    
    
    public String getName()
    {
        return name.get();
    }
    
    public double getPrice()
    {
        return price.get();
    }
    
    public int getInStock()
    {
        return inStock.get();
    }
    
    public int getMin()
    {
        return min.get();
    }
    
    public int getMax()
    {
        return max.get();
    }
    
    public int getProductID()
    {
        return productID.get();
    }
    
    public void addAssociatedPart(Part part)
    {
        if (!associatedParts.contains(part))
        {
            associatedParts.add(part);
        }
        else 
        {
            System.out.println("Product " + getProductID() + " already contains Part " + part.getPartID());
        }
    }
    
    public boolean removeAssociatedPart(int index)
    {
        boolean removalSuccessful = false;
        
        if (!(index > associatedParts.size() - 1) && !associatedParts.isEmpty())
        {            
            Part part = associatedParts.get(index);
            
            associatedParts.remove(index);
            removalSuccessful = true;
        }
        else
        {
            removalSuccessful = false;
        }
        
        return removalSuccessful;
    }
    
    public boolean removeAssociatedPart(Part part)
    {
        boolean removeSuccessful = false;
        
        removeSuccessful = associatedParts.remove(part);
        
        return removeSuccessful;
    }
    
    public Part lookupAssociatedPart(int index)
    {
        Part p = null;
        
        try 
        {
            p = associatedParts.get(index);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe.getMessage());
        }
        
        return p;
    }
    
    public int numberOfAssociatedParts()
    {
        return associatedParts.size();
    }

    @Override
    public int compare(Product product1, Product product2) {
        return (int)(product1.getPrice() - product2.getPrice());
    }

    @Override
    public int compareTo(Product product) {
        return (this.getName()).compareTo(product.getName());
    }
    
}
