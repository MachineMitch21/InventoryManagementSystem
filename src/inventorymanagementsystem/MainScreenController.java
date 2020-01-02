/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * The MainScreenController controls the home screen of the application.
 * 
 * @author (Erin) Mitch Schutt
 */
public class MainScreenController implements Initializable, InventoryListener, SearchListener {
    
    
    private boolean quickSearchEnabled = true;
    
    
    private ObservableList<Part> partData = FXCollections.observableArrayList();
    private ObservableList<Product> productData = FXCollections.observableArrayList();
    
    
    @FXML
    TableView tblParts;
    
    @FXML 
    TableView tblProducts;
    
    @FXML
    TextField txtPartSearch;
    
    @FXML
    TextField txtProductSearch;
    
    @FXML
    Button btnPartSearch;
    
    @FXML 
    Button btnProductSearch;
    
    /**
     * Executes when the Exit button is pressed on the home screen.
     * Exits the application with code 0.
     */
    @FXML
    public void exitApplication()
    {
        ConfirmExitApplication cea = new ConfirmExitApplication();
    }
    
    /**
     * Opens the AddPart screen when the user presses the Add button under the Parts table
     * @param event 
     */
    @FXML 
    public void openAddPartDialog(ActionEvent event)
    {
        try
        {
            PartControlAdd ptc = new PartControlAdd();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Opens the ModifyPart screen when the user presses the Modify button under the Parts table
     * @param event 
     */
    @FXML
    public void openModifyPartDialog(ActionEvent event)
    {
        try
        {                        
            Part part = (Part) tblParts.getSelectionModel().getSelectedItem();
            
            if (part != null)
            {
                PartControlModify ptm = new PartControlModify(part);
            }
            else 
            {
                ErrorDialogController edc = new ErrorDialogController("You have not selected a part to modify");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Deletes the selected Part in the Parts table from the Inventory when the 
     * user presses the delete button under the Parts table.
     * @param event 
     */
    @FXML
    public void deletePart(ActionEvent event)
    {
        Part part = (Part) tblParts.getSelectionModel().getSelectedItem();
               
        
        if (part != null)
        {
            Inventory inventory = InventoryManagementSystem.getInventory();
            boolean deleteOkay = true;
            
            int numberOfProducts = inventory.numberOfProducts();
            
            ArrayList<Product> productsWithPart = new ArrayList<>();
            
            for (int i = 0; i < numberOfProducts; i++)
            {
                Product currentProduct = inventory.lookupProduct(i);
                
                int numberOfParts = currentProduct.numberOfAssociatedParts();
                
                for (int j = 0; j < numberOfParts; j++)
                {
                    Part currentPart = currentProduct.lookupAssociatedPart(j);
                    
                    if (part == currentPart)
                    {
                        deleteOkay = false;
                        productsWithPart.add(currentProduct);
                        break;
                    }
                }
            }
            
            if (deleteOkay)
            {
                PartConfirmDelete cpd = new PartConfirmDelete(part);
            }
            else 
            {
                String productIDs = "[ ";
                
                for (int i = 0; i < productsWithPart.size(); i++)
                {
                    Product product = productsWithPart.get(i);
                    productIDs += "{(-ID " + product.getProductID() + ", NAME " + product.getName() + "-)}";
                    
                    // If this isn't the last product with the part that we are
                    // currently attempting to delete, then add a comma to seperate
                    // the product IDs in the productIDs string
                    if (!(i == productsWithPart.size() - 1))
                    {
                        productIDs += ", ";
                    }
                }
                
                productIDs += " ]";
                
                ErrorDialogController edc = new ErrorDialogController("Part (ID " + part.getPartID() + ", NAME " + part.getName() 
                                                                               + ") could not be deleted. Product(s) " 
                                                                               + productIDs + " contain it", 350, 250);
            }
        }
        else 
        {
            ErrorDialogController edc = new ErrorDialogController("You have not selected a part to delete");
        }
    }
    
    /**
     * Opens the AddProduct screen when the user presses the Add button under the Products table
     * @param event 
     */
    @FXML
    public void openAddProductDialog(ActionEvent event)
    {
        Inventory inventory = InventoryManagementSystem.getInventory();
        
        boolean invHasParts = inventory.hasParts();
        
        try 
        {
            if (invHasParts)
            {
                ProductControlAdd pta = new ProductControlAdd();
            }
            else 
            {
                ErrorDialogController edc = new ErrorDialogController("Please add parts to the inventory before creating any products");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Opens the ModifyProduct screen when the user presses the Modify button under the Products table
     * @param event 
     */
    @FXML
    public void openModifyProductDialog(ActionEvent event)
    {
        try 
        {
            Inventory inventory = InventoryManagementSystem.getInventory();
            
            Product product = (Product) tblProducts.getSelectionModel().getSelectedItem();
            
            if (product != null)
            {
                ProductControlModify pcm = new ProductControlModify(product);
            }
            else 
            {
                ErrorDialogController edc = new ErrorDialogController("You have not selected a product to modify");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * 
     * @param event 
     */
    public void deleteProduct(ActionEvent event)
    {
        Product product = (Product) tblProducts.getSelectionModel().getSelectedItem();
        
        if (product != null)
        {
            if (!(product.numberOfAssociatedParts() > 0))
            {
                ProductConfirmDelete pcd = new ProductConfirmDelete(product);
            }
            else 
            {
                ErrorDialogController edc = new ErrorDialogController("Products having a part associated to them cannot be deleted");
            }
        }
        else 
        {
            ErrorDialogController edc = new ErrorDialogController("You have not selected a product to delete");
        }
    }
    
    /**
     * 
     * @param e 
     */
    private synchronized void updatePartSearch(Event e)
    {
        String searchStr = txtPartSearch.getText();
        
        Inventory inventory = InventoryManagementSystem.getInventory();
        int numberOfParts = inventory.numberOfParts();
        
        ArrayList<Part> tempParts   = new ArrayList<Part>(partData);

        for (int i = 0; i < numberOfParts; i++)
        {
            Part part = inventory.lookupPart(i);

            if (!part.getName().contains(searchStr) && !searchStr.isEmpty())
            {
                tempParts.remove(part);
            }
            else 
            {
                if (!tempParts.contains(part))
                {
                    tempParts.add(part);                  
                }
            }
        }
        
        Platform.runLater(() -> {
            partData = FXCollections.observableArrayList(tempParts);
            tblParts.setItems(partData);
        });
    }
    
    /**
     * 
     * @param e 
     */
    private synchronized void updateProductSearch(Event e)
    {
        String searchStr = txtProductSearch.getText();
        
        Inventory inventory = InventoryManagementSystem.getInventory();
        int numberOfProducts = inventory.numberOfProducts();
        
        ArrayList<Product> tempProducts = new ArrayList<Product>(productData);
        
        for (int i = 0; i < numberOfProducts; i++)
        {
            Product product = inventory.lookupProduct(i);
            
            if (!product.getName().contains(searchStr) && !searchStr.isEmpty())
            {
                tempProducts.remove(product);
            }
            else 
            {
                if (!tempProducts.contains(product))
                {
                    tempProducts.add(product);
                }
            }
        }
        
        Platform.runLater(() -> {
            productData = FXCollections.observableArrayList(tempProducts);
            tblProducts.setItems(productData);
        });
    }
    
    /**
     * 
     */
    private void toggleQuickSearch()
    {
        quickSearchEnabled = !quickSearchEnabled;
        
        if (quickSearchEnabled)
        {
            txtPartSearch.setOnKeyReleased((KeyEvent e) -> updatePartSearch(e));
            txtProductSearch.setOnKeyReleased((KeyEvent e) -> updateProductSearch(e));
        }
        else 
        {
            txtPartSearch.setOnKeyReleased(null);
            txtProductSearch.setOnKeyReleased(null);
        }
    }
    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inventory inventory = InventoryManagementSystem.getInventory();
        SearchMonitorThread partSearchMonitor = InventoryManagementSystem.getPartSearchMonitor();
        SearchMonitorThread productSearchMonitor = InventoryManagementSystem.getProductSearchMonitor();
        
        inventory.addListener(this);
        partSearchMonitor.addListener(this);
        productSearchMonitor.addListener(this);
        partSearchMonitor.setSearchSource(txtPartSearch);
        productSearchMonitor.setSearchSource(txtProductSearch);
        
        /*
        * Comment this out to stick with automatic quick search as the user types
        *
        // Temporary hack to prevent null pointer exception in getScene() method call
        Platform.runLater(() -> {
            btnPartSearch.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN), () -> {
                this.toggleQuickSearch();
            });  
        });
        
        toggleQuickSearch();
        */
        
        btnPartSearch.setOnAction((ActionEvent e) -> updatePartSearch(e));
        btnProductSearch.setOnAction((ActionEvent e) -> updateProductSearch(e));
        
        tblParts.setItems(partData);
        tblProducts.setItems(productData);
        
        
        // If the inventory happens to already have data in it, go ahead and 
        // process it and display it in the tables
        int numberOfParts = inventory.numberOfParts();
        int numberOfProducts = inventory.numberOfProducts();
        
        for (int i = 0; i < numberOfParts; i++)
        {
            Part part = inventory.lookupPart(i);
            partData.add(part);
        }
        
        for (int i = 0; i < numberOfProducts; i++)
        {
            Product product = inventory.lookupProduct(i);
            productData.add(product);
        }
    }    

    /**
     * 
     * @param e 
     */
    @Override
    public void partAdded(InventoryEvent e) {                
        partData.add(e.getPart());
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void partDeleted(InventoryEvent e) {        
        partData.remove(e.getPart());
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void partModified(InventoryEvent e) {        
        Part part = e.getPart();
        
        int indexOfPart = partData.indexOf(part);
        partData.set(indexOfPart, part);
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void productAdded(InventoryEvent e) {        
        productData.add(e.getProduct());
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void productDeleted(InventoryEvent e) {        
        productData.remove(e.getProduct());
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void productModified(InventoryEvent e) {        
        Product product = e.getProduct();
        
        int indexOfProduct = productData.indexOf(product);
        productData.set(indexOfProduct, product);
    }

    @Override
    public void searchInitiated(SearchEvent event) {
        
        SearchType type = event.getSearchType();
        
        if (type == SearchType.PART)
        {
            updatePartSearch(null);
        }
        else if (type == SearchType.PRODUCT)
        {
            updateProductSearch(null);
        }
    }
}
