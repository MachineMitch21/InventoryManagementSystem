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
public class PartControlModify extends PartsTemplateControl {
    
    protected Part  _part;
    
    public PartControlModify(Part partToModify)
    {
        _part = partToModify;
        
        try 
        {
            setupPartData();
        }
        catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void setupPartData()
    {
        super.lblHeader.setText("Modify Part");
        
        super.txtID.setDisable(true);
        
        if ((_part != null))
        {
            super.txtID.setText(Integer.toString(_part.getPartID()));
            super.txtName.setText(_part.getName());
            super.txtInv.setText(Integer.toString(_part.getInStock()));
            super.txtMax.setText(Integer.toString(_part.getMax()));
            super.txtMin.setText(Integer.toString(_part.getMin()));
            super.txtPriceCost.setText(Double.toString(_part.getPrice()));

            super.btnInHouse.setDisable(false);
            super.btnOutsourced.setDisable(false);

            if (_part instanceof InHouse)
            {
                super.btnInHouse.setSelected(true);
                super.btnOutsourced.setSelected(false);

                InHouse ih = (InHouse)_part;
                super.txtCNameMachID.setText(Integer.toString(ih.getMachineID()));
            }
            else if (_part instanceof OutSourced)
            {
                super.btnInHouse.setSelected(false);
                super.btnOutsourced.setSelected(true);

                OutSourced o = (OutSourced)_part;
                super.txtCNameMachID.setText(o.getCompanyName());
            }               
        }  
        else 
        {
            super.closeWindow();
            throw new NullPointerException("Part is null");
        }
    }

    @Override
    public void clickedSave(ActionEvent event) {
        
        if (super.allFieldsFilled())
        {
            Inventory inventory = InventoryManagementSystem.getInventory();
            
            if (super.numericFieldsValid())
            {
                String name     = super.txtName.getText();
                int inStock     = Integer.parseInt(super.txtInv.getText());
                int max         = Integer.parseInt(super.txtMax.getText());
                int min         = Integer.parseInt(super.txtMin.getText());
                double price    = Double.parseDouble(super.txtPriceCost.getText());

                String errorStr = InputValidater.validateMinMaxInStock(min, max, inStock);

                if ((errorStr.equals("")))
                {
                    _part.setName(name);
                    _part.setInStock(inStock);
                    _part.setMax(max);
                    _part.setMin(min);
                    _part.setPrice(price);

                    if (_part instanceof InHouse)
                    {
                        InHouse ih = (InHouse)_part;
                        ih.setMachineID(Integer.parseInt(super.txtCNameMachID.getText()));
                    }
                    else if (_part instanceof OutSourced)
                    {
                        OutSourced os = (OutSourced)_part;
                        os.setCompanyName(super.txtCNameMachID.getText());
                    }

                    inventory.updatePart(_part);
                    closeWindow();                
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
            ErrorDialogController edc = new ErrorDialogController("Not all fields are filled out");
        }
    }
}
