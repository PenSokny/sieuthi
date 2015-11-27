/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DTO.LoginDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 *
 * @author ERROR 405
 */
public class MainController implements Initializable{
    
    @FXML
    AnchorPane ParentControl;
    @FXML
    Menu edit;
    @FXML
    Menu timkiem;
    @FXML
    MenuItem login;
    @FXML
    Label labelLogin;
    
    SceneController scence=new SceneController();
    
    @FXML
    private void handleMenuNhaCungCap(ActionEvent event) 
    {
        scence.openNewWindow("/GUI/NccGui.fxml", ParentControl); 
    }
    @FXML
    private void handleMenuItemNhanVien(ActionEvent event){
        
        scence.openNewWindow("/GUI/NhanVienGui.fxml", ParentControl);
    }

    @FXML
    private void handleMenuItemTimNCC(ActionEvent event){
       
        scence.openNewWindow("/GUI/TimNhaCC.fxml", ParentControl);
    }
    @FXML
    private void handleMenuItemTimNhanVien(ActionEvent event){
      
        scence.openNewWindow("/GUI/TimNhanVien.fxml", ParentControl);
    }
    @FXML
    private void handleMenuItemTimDonNhapHang(ActionEvent event){
      
        scence.openNewWindow("/GUI/TimDonNhapHangGui.fxml", ParentControl);
    }
    @FXML
    private void handleMenuItemTimHang(ActionEvent event){
     
        scence.openNewWindow("/GUI/KiemTraHangGUI.fxml", ParentControl);
    }
    @FXML
    private void handleMenuLogin(ActionEvent event){
        scence.openNewScence("/GUI/LoginGui.fxml", false);
        scence.closeScence((Stage)ParentControl.getScene().getWindow());
    }
    @FXML
    private void handleMenuClose(ActionEvent event){
        scence.closeScence((Stage)ParentControl.getScene().getWindow());
    }
    
    @FXML
    private void handleMenuItemHang(ActionEvent event){
        scence.openNewWindow("/GUI/QuanLyThongTinHang.fxml", ParentControl);
    }
    
    @FXML
    private void handleMenuItemDonNhapHang(ActionEvent event){
        scence.openNewWindow("/GUI/DonNhapHangGui.fxml", ParentControl);
    }
    
    @FXML
    private void handleMenuItemRpNcc(ActionEvent event){
        
    }
    
    @FXML
    private void handleMenuItemRpDonHang(ActionEvent event){
        scence.openNewWindow("/GUI/ReportDonNhapHang.fxml", ParentControl);
    }
    
    @FXML
    private void handleMenuItemRpHang(ActionEvent event){
       
    }
    
    @FXML
    private void handleMenuItemAbout(ActionEvent event){
        scence.openNewScence("/GUI/AboutUsGui.fxml", false);
    }
    
    @FXML
    private void handleMenuItemUser(ActionEvent event){
        scence.openNewWindow("/GUI/UserGui.fxml", ParentControl);
    }
    
    @FXML
    private void handleMenuBanHang(ActionEvent event){
        scence.openNewWindow("/GUI/BanHangGui.fxml", ParentControl);
    }
    
    @FXML
    private void handleMenuItemRpNv(ActionEvent event){
       
    }
    
    @FXML
    private void handleMenuItemRpBanHang(ActionEvent event){
        scence.openNewWindow("/GUI/BanHangReport.fxml", ParentControl);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
