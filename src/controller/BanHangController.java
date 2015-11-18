/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DTO.BanHangDTO;
import DTO.LoginDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author ERROR 405
 */
public class BanHangController implements Initializable{
    @FXML
    TableView<BanHangDTO> tblBanHang;
    @FXML
    TableColumn<BanHangDTO,String> colMa;
    @FXML
    TableColumn<BanHangDTO,String> colTenSanPham;
    @FXML
    TableColumn<BanHangDTO,String> colSoLuong;
    @FXML
    TableColumn<BanHangDTO,String> colDonGia;
    @FXML
    TableColumn<BanHangDTO,String> colDonViTinh;
    @FXML
    TableColumn<BanHangDTO,String> colThanhTien;
        
    @FXML
    TextField maSanPham;
    @FXML
    TextField tenSanPham;
    @FXML
    TextField donViTinh;
    @FXML
    TextField soLuong;
    @FXML
    TextField donGia;
    @FXML
    TextField tongSoTien;
    @FXML
    TextField tienKhachTra;
    @FXML
    TextField tienTraLai;
    @FXML
    DatePicker ngayBanHang;
    
    @FXML
    private Button btnThanhTien;
    @FXML
    private Button inHoaDon;
    
    BanHangDTO banhangDTO=new BanHangDTO();
    FormValidation validation=new FormValidation();
    
    
    @FXML
    private void handleEnterPressed(javafx.scene.input.KeyEvent event){
        
    }
    @FXML
    public void handleThanhTien(ActionEvent event){
        
    }
    @FXML
    public void handleThem(ActionEvent event){
        
    }
    @FXML
    public void handleXoa(ActionEvent event){
        
    }
    @FXML
    private void handlePress(javafx.scene.input.KeyEvent event) {
        
    }
    
    
    
    
            
    
            
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
