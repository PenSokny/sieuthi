/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.UserDal;
import DTO.LoginDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author ERROR 405
 */
public class UserController implements Initializable{
    
    @FXML
    TableView<LoginDTO> tbUser;
    @FXML
    TableColumn<LoginDTO,String> colUser;
    @FXML
    TableColumn<LoginDTO,String> colRole;
    
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    ComboBox cbRole;
    
    LoginDTO loginDTO=new LoginDTO();
    UserDal userDal=new UserDal();
    FormValidation form=new FormValidation();
    
    ObservableList<String> data=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        data.add("Admin");
        data.add("Accounting");
        data.add("Seller");
        cbRole.setItems(data);
        loadData(); 
    }
    
    public void themMoi(){
        txtUsername.setText("");
        txtPassword.setText("");
        cbRole.getSelectionModel().select("");
     }
    
    public boolean validate(){
        boolean usernameEmpty=form.textIsEmtpy(txtUsername, "Nhập username");
        boolean passEmpty=form.textIsEmtpy(txtPassword, "Nhập Password");
        boolean roleEmpty=form.comboBoxIsEmtpy(cbRole, "Chọn Role");
        if(!usernameEmpty && !passEmpty && !roleEmpty){
            return true;
        }
        return false;
    }
    public void loadData(){
        colUser.setCellValueFactory(new PropertyValueFactory("username"));
        colRole.setCellValueFactory(new PropertyValueFactory("role"));
        tbUser.getItems().clear();
        tbUser.setItems(userDal.loadData());
        
    }
    
    @FXML
    private void handleButtonLuu(ActionEvent event){
        
        if(validate()){
            loginDTO.setUsername(txtUsername.getText().trim());
            loginDTO.setPassword(txtPassword.getText().trim());
            loginDTO.setRole(cbRole.getSelectionModel().getSelectedItem().toString());
            int result=userDal.themUser(loginDTO);
            if(result>0){
                loadData();
                JOptionPane.showMessageDialog(null, "Luu Thanh Cong.");
            }
            else JOptionPane.showMessageDialog(null, "Khong Luu Duoc.");
        }
                  
    }
    
    @FXML
    private void handleButtonXoa(ActionEvent event){
        int i=tbUser.getSelectionModel().getSelectedIndex();
        if(i>0){
            loginDTO.setUsername(tbUser.getSelectionModel().getSelectedItem().getUsername());
            if(userDal.xoaUser(loginDTO)>0){
                loadData();
                JOptionPane.showMessageDialog(null, "Xoa Thanh Cong.");
            }
            else JOptionPane.showMessageDialog(null, "Khong the xoa duoc.");
        }
    }
    
}
