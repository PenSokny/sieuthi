/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DTO.TimHangDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author ERROR 405
 */
public class KiemTraHangController implements Initializable{
    
    @FXML 
    TableView<TimHangDTO> tbHang;
    @FXML
    TableColumn<TimHangDTO,String> maHang;
    @FXML
    TableColumn<TimHangDTO,String> tenHang;
    @FXML
    TableColumn<TimHangDTO,String> donViTinh;
    @FXML
    TableColumn<TimHangDTO,String> ncc;
    @FXML
    TableColumn<TimHangDTO,Double> giaNhap;
    @FXML
    TableColumn<TimHangDTO,Double> giaBan;
    @FXML
    TableColumn<TimHangDTO,String> ngayHSD;
    @FXML
    TableColumn<TimHangDTO,Double> soLuong;
    @FXML
    TableColumn<TimHangDTO,String> ghiChu;
    
    @FXML
    TextField txtTimMaHang;
    @FXML
    TextField txtTimTen;
    @FXML
    ComboBox txtTimNCC;
    @FXML
    DatePicker ngayBatDau;
    @FXML
    DatePicker ngayKetThuc;
    
    @FXML
    private void btnTimMaHang(ActionEvent event){
        
    }
    @FXML
    private void btnTimTen(ActionEvent event){
        
    }
    @FXML
    private void btnTimNCC(ActionEvent event){
        
    }
    @FXML
    private void btnTimHangTrongKho(ActionEvent event){
        
    }
    @FXML
    private void btnTimHangKoTrongKho(ActionEvent event){
        
    }
    
    @FXML
    private void btnTimNgay(ActionEvent event){
        
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
