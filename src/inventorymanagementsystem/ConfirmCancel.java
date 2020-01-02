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
public class ConfirmCancel extends ConfirmDialogController {
    
    TemplateControl _sourceOfCancel;
    
    public ConfirmCancel(TemplateControl sourceOfCancel)
    {
        _sourceOfCancel = sourceOfCancel;
        
        String headerText = "Are you sure you want to cancel?";
        
        super.setConfirmHeaderText(headerText);
    }

    @Override
    public void clickedOkay(ActionEvent e) {
        _sourceOfCancel.closeWindow();
        this.closeWindow();
    }

    @Override
    public void clickedCancel(ActionEvent e) {
        closeWindow();
    }
    
}
