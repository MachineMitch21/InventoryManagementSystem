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
public class ProductControlAdd extends ProductTemplateControl {
    
    Product _productToAdd = new Product();
    
    ProductControlAdd()
    {
        super.lblHeader.setText("Add Product");
        super.txtID.setDisable(true);
        super.txtID.setText("Auto Gen - Disabled");
    }

    @Override
    public void clickedSave() 
    {
        if (super.allFieldsFilled())
        {
            Inventory inventory = InventoryManagementSystem.getInventory();
            
            if (super.numericFieldsValid())
            {
                String name     = super.txtName.getText();
                int inStock     = Integer.parseInt(super.txtInv.getText());
                int max         = Integer.parseInt(super.txtMax.getText());
                int min         = Integer.parseInt(super.txtMin.getText());
                double price    = Double.parseDouble(super.txtPrice.getText());

                String errorStr = InputValidater.validateMinMaxInStock(min, max, inStock);

                if ((errorStr.equals("")))
                {
                    double combinedPartsPrice = super.getAddedPartsPrice();

                    if (price > combinedPartsPrice)
                    {
                        _productToAdd.setInStock(inStock);
                        _productToAdd.setMax(max);
                        _productToAdd.setMin(min);
                        _productToAdd.setName(name);
                        _productToAdd.setPrice(price);

                        int numberOfParts = partsAddedData.size();

                        if (numberOfParts >= 1)
                        {
                            for (int i = 0; i < numberOfParts; i++)
                            {
                                Part part = partsAddedData.get(i);
                                _productToAdd.addAssociatedPart(part);
                            }
                            
                            inventory.addProduct(_productToAdd);
                            closeWindow();
                        }
                        else 
                        {
                            ErrorDialogController edc = new ErrorDialogController("Products must always have at least one part");
                        }                                    
                    }
                    else 
                    {
                        ErrorDialogController edc = new ErrorDialogController("The price of a product must always be greater than the price of all associated parts \n\n" 
                                                                                + "Associated Parts Combined Price: " + combinedPartsPrice);
                    }
                }
                else 
                {
                    ErrorDialogController edc = new ErrorDialogController(errorStr);
                }
            }
            else 
            {
                ErrorDialogController edc = new ErrorDialogController("Make sure InvLevel, Max, Min and Price fields contain only numerical entries and the Price field contains a valid price (ex. 14.52)");
            }
        }
        else 
        {
            ErrorDialogController edc = new ErrorDialogController("Not all fields are filled");
        }
    }
    
    @Override
    public void addAssociatedPart(ActionEvent e)
    {
        Part part = (Part) super.tblPartsToAdd.getSelectionModel().getSelectedItem();
        
        if (part != null)
        {
            if (!partsAddedData.contains(part))
                partsAddedData.add(part);
        }
        else 
        {
            ErrorDialogController edc = new ErrorDialogController("You haven't selected a part to add to the product");
        }
    }
    
    @Override
    public void deleteAssociatedPart(ActionEvent e)
    {
        Part part = (Part) super.tblAddedParts.getSelectionModel().getSelectedItem();
        
        if (part != null)
        {
            partsAddedData.remove(part);
        }
        else 
        {
            ErrorDialogController edc = new ErrorDialogController("You have not selected an associated part to delete from the product");
        }
    }
}
