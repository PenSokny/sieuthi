/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Sokny Pen
 */
public class SceneController {
    
    public void openNewScence(String fxml,boolean setMax)
    {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setMaximized(setMax);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void closeScence(Stage stage)
    {
        stage.close();
    
    }
    
     public void openNewWindow(String FXMLFile,AnchorPane ParentControl)
    {
     
        try {    

            URL url = getClass().getResource(FXMLFile);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 
            
            ParentControl.getChildren().clear();///name of pane where you want to put the fxml.
            ParentControl.getChildren().add(page);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
