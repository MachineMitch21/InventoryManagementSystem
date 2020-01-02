/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author mitch
 */
public abstract class ConfirmDialogController extends TemplateControl {
       
    
    @FXML
    private Label lblConfirmHeader;
    
    @FXML
    public abstract void clickedOkay(ActionEvent e);
    
    @FXML
    public abstract void clickedCancel(ActionEvent e);
    
    
    public ConfirmDialogController()
    {
        super("ConfirmDialogFXML.fxml", "confirmDialogStyle.css", 250, 150);        
    }

    @Override
    public boolean allFieldsFilled() {
        System.out.println("allFieldsFilled() not used for ConfirmDialogController");
        return false;
    }
    
    public void setConfirmHeaderText(String headerText)
    {
        lblConfirmHeader.setText(headerText);
    }
}
