/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DTO.LoginDTO;
import DAL.LoginDAL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 *
 * @author Sokny Pen
 */
public class LoginController implements Initializable{
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    Button btnClose;
    @FXML
    AnchorPane achorPaneLogin;
    @FXML
    AnchorPane ParentControl;
    
    
    LoginDTO loginDTO=new LoginDTO();
    LoginDAL loginDAL=new LoginDAL();
    SceneController scene=new SceneController();
    MainController mainController=new MainController();
    @FXML
    private void handleButtonLogin(ActionEvent event){
        loginDTO.setUsername(txtUsername.getText());
        loginDTO.setPassword(txtPassword.getText());
        if(loginDAL.kiemTraLogin(loginDTO)>0){
            scene.openNewScence("/GUI/MainGui.fxml", false);
            scene.closeScence((Stage) btnClose.getScene().getWindow());
        }
        else{
            JOptionPane.showMessageDialog(null, "User name or password incorrect");
        }
    }
    @FXML
    private void handleButtonExit(ActionEvent event){
        scene.closeScence((Stage)ParentControl.getScene().getWindow());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
