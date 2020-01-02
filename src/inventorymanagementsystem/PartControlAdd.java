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
public class PartControlAdd extends PartsTemplateControl {
    
    PartControlAdd()
    {
        super.lblHeader.setText("Add Part");
        
        super.txtID.setDisable(true);
        super.txtID.setText("Auto Gen - Disabled");
    }

    @Override
    public void clickedSave(ActionEvent event) 
    {
        if (super.allFieldsFilled())
        {
            Inventory inventory = InventoryManagementSystem.getInventory();
            
            Part part = null;
            
            if (super.btnInHouse.isSelected())
            {
                part = new InHouse();
            }
            else if (super.btnOutsourced.isSelected())
            {
                part = new OutSourced();
            }
                    
            if (part != null)
            {
                
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
                        part.setName(name);
                        part.setInStock(inStock);
                        part.setMax(max);
                        part.setMin(min);
                        part.setPrice(price);

                        if (part instanceof InHouse)
                        {
                            InHouse ih = (InHouse)part;
                            int machineID = Integer.parseInt(super.txtCNameMachID.getText());
                            ih.setMachineID(machineID);
                        }
                        else if (part instanceof OutSourced)
                        {
                            OutSourced os = (OutSourced)part;
                            String companyName = super.txtCNameMachID.getText();
                            os.setCompanyName(companyName);
                        }           

                        inventory.addPart(part);
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
                ErrorDialogController edc = new ErrorDialogController("Part is NULL, either InHouse or OutSourced buttons should be selected");
            }
        }
        else 
        {
            ErrorDialogController edc = new ErrorDialogController("Not all fields are filled out");
        }
    }
}
