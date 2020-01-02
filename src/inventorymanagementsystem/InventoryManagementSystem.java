/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author mitch
 */
public class InventoryManagementSystem extends Application {
    
    private static Inventory _inventory = null;
    private static PartSearchMonitor _partSearchMonitor = null;
    private static ProductSearchMonitor _productSearchMonitor = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        if (_inventory == null)
            _inventory = new Inventory();
        
        if (_partSearchMonitor == null)
        {
            _partSearchMonitor = new PartSearchMonitor();
            _partSearchMonitor.start();
        }
        
        if (_productSearchMonitor == null)
        {
            _productSearchMonitor = new ProductSearchMonitor();
            _productSearchMonitor.start();
        }
        
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());        
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.setMinHeight(500);
        stage.setMinWidth(1050);
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent we) -> {
            we.consume();
            ConfirmExitApplication cea = new ConfirmExitApplication();
        });  
    }
    
    public static Inventory getInventory()
    {
        return _inventory;
    }
    
    public static PartSearchMonitor getPartSearchMonitor()
    {
        return _partSearchMonitor;
    }
    
    public static ProductSearchMonitor getProductSearchMonitor()
    {
        return _productSearchMonitor;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
