/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author mitch
 */
public abstract class ProductTemplateControl extends TemplateControl implements InventoryListener {
    
    
    protected ObservableList<Part> partsToAddData = FXCollections.observableArrayList();
    protected ObservableList<Part> partsAddedData = FXCollections.observableArrayList();
    
    
    @FXML
    protected Label lblHeader;
    
    @FXML 
    protected Button btnSearch;
    
    @FXML
    protected TextField txtSearch;
    
    @FXML 
    protected TextField txtID;
    
    @FXML
    protected TextField txtName;
    
    @FXML
    protected TextField txtInv;    
    
    @FXML
    protected TextField txtPrice;

    @FXML
    protected TextField txtMax;

    @FXML
    protected TextField txtMin;
    
    @FXML
    protected TableView tblPartsToAdd;
    
    @FXML
    protected TableView tblAddedParts;
    
    @FXML
    protected Button btnAddAssociatedPart;
    
    @FXML
    protected Button btnDeleteAssociatedPart;
    
    @FXML
    public void clickedSave()
    {
        
    }
    
    private synchronized void updateAssociatedPartSearch(Event e)
    {
        System.out.println("Updating search results");
        String searchStr = txtSearch.getText();
        
        Inventory inventory = InventoryManagementSystem.getInventory();
        int numberOfParts = inventory.numberOfParts();
        

        for (int i = 0; i < numberOfParts; i++)
        {
            Part part = inventory.lookupPart(i);

            if (!part.getName().contains(searchStr) && !searchStr.isEmpty())
            {
                partsToAddData.remove(part);
            }
            else
            {
                if (!partsToAddData.contains(part))
                {
                    partsToAddData.add(part);
                }
            }
        }
    }
    
    @FXML
    public void clickedCancel()
    {
        ConfirmCancel cc = new ConfirmCancel(this);
    }
    
    @FXML
    public abstract void addAssociatedPart(ActionEvent e);
    
    @FXML
    public abstract void deleteAssociatedPart(ActionEvent e);
    
    @Override
    public boolean allFieldsFilled()
    {
        return !("".equals(txtID.getText())     ||
                 "".equals(txtName.getText())   ||
                 "".equals(txtInv.getText())    ||
                 "".equals(txtPrice.getText())  ||
                 "".equals(txtMax.getText())    ||
                 "".equals(txtMin.getText())    );
    }
    
    public boolean numericFieldsValid()
    {
        return (InputValidater.isAllDigits(txtInv.getText()) &&
                InputValidater.isAllDigits(txtMax.getText()) &&
                InputValidater.isAllDigits(txtMin.getText()) &&
                InputValidater.isPrice(txtPrice.getText()));
    }
    
    public ProductTemplateControl()
    {
        super("ProductTemplateFXML.fxml", "style.css", 950, 550);
        
        Inventory inventory = InventoryManagementSystem.getInventory();
        
        inventory.addListener(this);
        
        tblPartsToAdd.setItems(partsToAddData);
        tblAddedParts.setItems(partsAddedData);
        
        int amountOfParts = inventory.numberOfParts();
        
        for (int i = 0; i < amountOfParts; i++)
        {
            Part part = inventory.lookupPart(i);
            
            partsToAddData.add(part);
        }
        
        txtSearch.setOnKeyReleased((KeyEvent e) -> updateAssociatedPartSearch(e));
        
        initButtons();
    }
    
    private void initButtons()
    {
        Tooltip addTooltip = new Tooltip("Adds the selected part to associated parts but makes no permanent changes until you click Save");
        Tooltip deleteTooltip = new Tooltip("Deletes the selected part from associated parts but makes no permanent changes until you click Save");
        
        btnAddAssociatedPart.setTooltip(addTooltip);
        btnDeleteAssociatedPart.setTooltip(deleteTooltip);
    }
    
    protected double getAddedPartsPrice()
    {
        double combinedPrice = 0;
        
        for (int i = 0; i < partsAddedData.size(); i++)
        {
            Part part = partsAddedData.get(i);
            combinedPrice += part.getPrice();
        }
        
        return combinedPrice;
    }
    
    @Override
    public void closeWindow()
    {
        super.closeWindow();
        
        Inventory inventory = InventoryManagementSystem.getInventory();
        inventory.removeListener(this);
    }

    @Override
    public void partAdded(InventoryEvent e) {        
        partsToAddData.add(e.getPart());
    }

    @Override
    public void partDeleted(InventoryEvent e) {        
        partsToAddData.remove(e.getPart());
    }

    @Override
    public void partModified(InventoryEvent e) {        
        Part part = e.getPart();
        
        int indexOfPart = partsToAddData.indexOf(part);
        partsToAddData.set(indexOfPart, part);
    }

    @Override
    public void productAdded(InventoryEvent e) {
       
    }

    @Override
    public void productDeleted(InventoryEvent e) {
       
    }

    @Override
    public void productModified(InventoryEvent e) {
        
    }
}
