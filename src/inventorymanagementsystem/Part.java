/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.util.Comparator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mitch
 */
public abstract class Part implements Comparator<Part>, Comparable<Part>{
    
    private final SimpleIntegerProperty partID   = new SimpleIntegerProperty();
    private final SimpleStringProperty  name     = new SimpleStringProperty();
    private final SimpleDoubleProperty  price    = new SimpleDoubleProperty();
    private final SimpleIntegerProperty inStock  = new SimpleIntegerProperty();
    private final SimpleIntegerProperty min      = new SimpleIntegerProperty();
    private final SimpleIntegerProperty max      = new SimpleIntegerProperty();
    
    @Override
    public int compareTo(Part part)
    {
        return (this.getName()).compareTo(part.getName());
    }

    @Override
    public int compare(Part part1, Part part2)
    {
        return (int)(part1.getPrice() - part2.getPrice());
    }
    
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
    
    public void setPartID(int partID)
    {
        this.partID.set(partID); 
    }
    
    
    public String getName()
    {
        return this.name.get();
    }
    
    public double getPrice()
    {
        return this.price.get();
    }
    
    public int getInStock()
    {
        return this.inStock.get();
    }
    
    public int getMin()
    {
        return this.min.get();
    }
    
    public int getMax()
    {
        return this.max.get();
    }
    
    public int getPartID()
    {
        return this.partID.get();
    }
}
