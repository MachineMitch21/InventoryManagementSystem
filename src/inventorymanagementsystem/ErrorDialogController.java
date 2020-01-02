/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author mitch
 */
public class ErrorDialogController extends TemplateControl {
    
    @FXML
    private Label lblErrorMsg;
    
    public ErrorDialogController(String errorStr)
    {
        super("ErrorDialogFXML.fxml", "errorStyle.css", 475, 250);
        
        setErrorMessage(errorStr);
        
        _myStage.focusedProperty().addListener((ObservableValue<? extends Boolean> oldValue, Boolean hidden, Boolean shown) -> {
            if (hidden)
            {
                closeWindow();
            }
        });
    }
    
    public ErrorDialogController(String errorStr, int width, int height)
    {
        super("ErrorDialogFXML.fxml", "errorStyle.css", width, height);
        
        setErrorMessage(errorStr);
        
        _myStage.focusedProperty().addListener((ObservableValue<? extends Boolean> oldValue, Boolean hidden, Boolean shown) -> {
            if (hidden)
            {
                closeWindow();
            }
        });
    }

    @Override
    public boolean allFieldsFilled() {
        System.out.println("allFieldsFilled() Not used for error dialog");
        return false;
    }
    
    public void setErrorMessage(String errorStr)
    {
        lblErrorMsg.setText(errorStr);
    }
    
}
