/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author mitch
 */
public abstract class TemplateControl extends BorderPane {

    protected Stage       _myStage;
    protected String      _fxmlDoc;
    protected String      _cssDoc;
    protected int         _width;
    protected int         _height;
    
    TemplateControl(String fxmlDoc, int width, int height)
    {
        _fxmlDoc    = fxmlDoc;
        _width      = width;
        _height     = height;
        _cssDoc     = null;
        
        setupStage();
    }
    
    TemplateControl(String fxmlDoc, String cssDoc, int width, int height)
    {
        _fxmlDoc    = fxmlDoc;
        _width      = width;
        _height     = height;
        _cssDoc     = cssDoc;
        
        setupStage();
    }
    
    public abstract boolean allFieldsFilled();
    
    private void setupStage()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(_fxmlDoc));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try 
        {
            fxmlLoader.load();
        } 
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        _myStage = new Stage();
        Scene scene = new Scene(this);
        
        if (_cssDoc != null)
        {
            scene.getStylesheets().add(getClass().getResource(_cssDoc).toExternalForm());        
        }
        
        _myStage.setScene(scene);
        _myStage.setWidth(_width);
        _myStage.setHeight(_height);
        _myStage.setMinWidth(_width);
        _myStage.setMinHeight(_height);
        _myStage.show();
    }    
    
    public void setWidth(int width)
    {
        _width = width;
        _myStage.setWidth(_width);
    }
    
    public void setHeight(int height)
    {
        _height = height;
        _myStage.setHeight(_height);
    }
    
    public void closeWindow()
    {
        _myStage.close();
    }
}
