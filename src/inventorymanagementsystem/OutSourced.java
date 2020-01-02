/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mitch
 */
public class OutSourced extends Part {
    
    private SimpleStringProperty companyName = new SimpleStringProperty();
    
    public void setCompanyName(String companyName)
    {
        this.companyName.set(companyName);
    }
    
    public String getCompanyName()
    {
        return companyName.get();
    }
}
