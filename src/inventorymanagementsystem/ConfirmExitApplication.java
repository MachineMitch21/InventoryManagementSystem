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
public class ConfirmExitApplication extends ConfirmDialogController {
    
    public ConfirmExitApplication()
    {
        super.setConfirmHeaderText("Are you sure you want to exit the application?  You will lose any unsaved changes.");
    }

    @Override
    public void clickedOkay(ActionEvent e) {
        System.exit(0);
    }

    @Override
    public void clickedCancel(ActionEvent e) {
        closeWindow();
    }
    
}
