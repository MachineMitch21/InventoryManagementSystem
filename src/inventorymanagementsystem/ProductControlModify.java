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
public class ProductControlModify extends ProductTemplateControl {
    
    protected Product _product;
    
    ProductControlModify(Product productToModify)
    {
        _product = productToModify;
        super.lblHeader.setText("Modify Product");
        
        String name     = _product.getName();
        int productID   = _product.getProductID();
        int inStock     = _product.getInStock();
        int max         = _product.getMax();
        int min         = _product.getMin();
        double price    = _product.getPrice();
        
        super.txtID.setDisable(true);
        super.txtID.setText(Integer.toString(productID));
        super.txtInv.setText(Integer.toString(inStock));
        super.txtMax.setText(Integer.toString(max));
        super.txtMin.setText(Integer.toString(min));
        super.txtName.setText(name);
        super.txtPrice.setText(Double.toString(price));
        
        int amountOfPartsInProduct = _product.numberOfAssociatedParts();
        
        for (int i = 0; i < amountOfPartsInProduct; i++)
        {
            Part part = _product.lookupAssociatedPart(i);
            super.partsAddedData.add(part);
        }
    }

    @Override
    public void clickedSave() 
    {
        if (super.allFieldsFilled())
        {
            if (super.numericFieldsValid())
            {
                Inventory inventory = InventoryManagementSystem.getInventory();

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
                        _product.setInStock(inStock);
                        _product.setMax(max);
                        _product.setMin(min);
                        _product.setName(name);
                        _product.setPrice(price);

                        int numberOfParts = super.partsAddedData.size();

                        if (numberOfParts >= 1)
                        {
                            for (int i = 0; i < numberOfParts; i++)
                            {
                                Part part = partsAddedData.get(i);
                                _product.addAssociatedPart(part);
                            }
                            
                            inventory.updateProduct(_product);
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
                ErrorDialogController edc = new ErrorDialogController("Make sure all numeric fields do not contain letters or symbols and that the price field contains a valid price (ex. 45.75)");
            }
        }
        else
        {
            ErrorDialogController edc = new ErrorDialogController("All fields must be filled out");        
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
            ErrorDialogController edc = new ErrorDialogController("You haven't selected a part to remove from the product");
        }
    }
}
