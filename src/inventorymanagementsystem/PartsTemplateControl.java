/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;


/**
 *
 * @author (Erin) Mitch Schutt
 */
public abstract class PartsTemplateControl extends TemplateControl implements InventoryListener {
    
    @FXML
    protected Label lblHeader;
    
    @FXML 
    protected RadioButton btnInHouse;
    
    @FXML 
    protected RadioButton btnOutsourced;
    
    @FXML
    protected GridPane inputGrid;
    
    @FXML
    protected Label lblCNameMachID;
    
    @FXML
    protected TextField txtID;
    
    @FXML 
    protected TextField txtName;
    
    @FXML
    protected TextField txtInv;
    
    @FXML 
    protected TextField txtPriceCost;
    
    @FXML
    protected TextField txtMax;
    
    @FXML 
    protected TextField txtMin;
    
    @FXML
    protected TextField txtCNameMachID;
    
    /**
     * 
     * @param event 
     */
    @FXML 
    public void toggleInHouse(ActionEvent event)
    {
        // If we've called this method without an event and set the parameter to null
        if (event == null)
        {
            btnInHouse.setSelected(true);
        }
        
        btnOutsourced.setSelected(false);
        lblCNameMachID.setText("Machine ID");
    }
    
    /**
     * 
     * @param event 
     */
    @FXML 
    public void toggleOutsourced(ActionEvent event)
    {
        // Same setup as toggleInHouse
        if (event == null)
        {
            btnOutsourced.setSelected(true);
        }
        
        btnInHouse.setSelected(false);
        lblCNameMachID.setText("Company Name");
    }
    
    /**
     * 
     * @param event 
     */
    @FXML 
    public abstract void clickedSave(ActionEvent event);
    
    /**
     * 
     * @param event 
     */
    @FXML
    public void clickedCancel(ActionEvent event)
    {
        ConfirmCancel cc = new ConfirmCancel(this);
    }
    
    public PartsTemplateControl() {
        
        super("PartsTemplateFXML.fxml", "style.css", 475, 400);
        
        initRadioButtons();
        initGrids();
        
        Inventory inventory = InventoryManagementSystem.getInventory();
        
        inventory.addListener(this);
    }
    
    /**
     * Checks all the text fields on the Parts screens for empty string inputs
     * @return true or false
     */
    @Override
    public boolean allFieldsFilled()
    {
        return !("".equals(txtID.getText())         ||
                 "".equals(txtName.getText())       ||
                 "".equals(txtInv.getText())        ||
                 "".equals(txtPriceCost.getText())  ||
                 "".equals(txtMax.getText())        ||
                 "".equals(txtMin.getText())        ||
                 "".equals(txtCNameMachID.getText()));
    }
    
    public boolean numericFieldsValid()
    {
        return (InputValidater.isAllDigits(txtInv.getText()) &&
                InputValidater.isAllDigits(txtMax.getText()) &&
                InputValidater.isAllDigits(txtMin.getText()) &&
                InputValidater.isPrice(txtPriceCost.getText()));
    }
    
    /**
     * 
     */
    private void initRadioButtons()
    {
        Tooltip inhouseTip = new Tooltip("This reveals the 'MachineID' field");
        Tooltip outsourcedTip = new Tooltip("This reveals the 'Company Name' field");
                
        btnInHouse.setTooltip(inhouseTip);
        btnOutsourced.setTooltip(outsourcedTip);   
        
        toggleInHouse(null);
    }
    
    /**
     * 
     */
    private void initGrids()
    {
        inputGrid.setMaxHeight(600);
        inputGrid.setMaxWidth(600);
    }
    
    /**
     * 
     */
    @Override
    public void closeWindow()
    {
        super.closeWindow();
        
        Inventory inventory = InventoryManagementSystem.getInventory();
        inventory.removeListener(this);
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void partAdded(InventoryEvent e) {
        System.out.println("Part window recieved part added event");
    }

    @Override
    public void partDeleted(InventoryEvent e) {
        System.out.println("Part window recieved part deleted event");
    }

    @Override
    public void partModified(InventoryEvent e) {
        System.out.println("Part window recieved part modified event");
    }

    @Override
    public void productAdded(InventoryEvent e) {
        System.out.println("Part window recieved product added event");
    }

    @Override
    public void productDeleted(InventoryEvent e) {
        System.out.println("Part window recieved product deleted event");
    }

    @Override
    public void productModified(InventoryEvent e) {
        System.out.println("Part window recieved product modified event");
    }
}