/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DTO.DonNhapHangDTO;
import DTO.HangNhapDTO;
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
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author ERROR 405
 */
public class DonNhapHangController implements Initializable{
    
    //TableView Data
    @FXML 
    TableView<DonNhapHangDTO> tbDonNhap;
    @FXML
    TableColumn<DonNhapHangDTO,String> maHoaDon;
    @FXML
    TableColumn<DonNhapHangDTO,String> ngayLap;
    @FXML
    TableColumn<DonNhapHangDTO,String> tenHang;
    @FXML
    TableColumn<DonNhapHangDTO,Double> soLuong;
    @FXML
    TableColumn<DonNhapHangDTO,String> donViTinh;
    @FXML
    TableColumn<DonNhapHangDTO,Double> giaNhap;
    @FXML
    TableColumn<DonNhapHangDTO,Double> thanhTien;
            
    //Hoa DOn
    @FXML
    TextField txtMaHoaDon;
    @FXML
    DatePicker ngayNhapHang;
    
    //Nha cung cap
    @FXML
    ComboBox cbMaNcc;
    @FXML
    TextField txtTenNcc;
    
    //Hang
    @FXML 
    ComboBox cbMaHang;
    @FXML
    TextField txtTenHang;
    @FXML
    TextField txtDonViTinh;
    @FXML
    TextField txtGiaNhap;
    @FXML
    TextField txtGiaBan;
    @FXML
    TextField txtTenNccHang;
    @FXML
    TextField txtSoLuong;
    
    //Nhan Vien
    @FXML 
    ComboBox cbMaNV;
    @FXML
    TextField txtTenNV;
    
    
    DonNhapHangDTO donHangDTO=new DonNhapHangDTO();
    HangNhapDTO hangNhapDTO=new HangNhapDTO();
    FormValidation form=new FormValidation();
    
    public boolean validate()
    {
        boolean maHoDonEmpty=form.textIsEmtpy(txtMaHoaDon, "chưa có thông tin");
        boolean maNccEmpty=form.comboBoxIsEmtpy(cbMaNcc, "chưa có thông tin");
        boolean maNvEmpty=form.comboBoxIsEmtpy(cbMaNV, "chưa có thông tin");
        boolean maHangEmpty=form.comboBoxIsEmtpy(cbMaHang, "chưa có thông tin");
        boolean soLuongEmpty=form.textIsEmtpy(txtSoLuong, "chưa có thông tin");
        boolean soLuongIsNumber=form.textIsNumberic(txtSoLuong, "phải là số");
        
        if(!maHoDonEmpty && !maNccEmpty && !maNvEmpty && !maHangEmpty &&  !soLuongEmpty && soLuongIsNumber) 
            return true;
        return false;
        
    }
    
    public void loadTabble()
    {
        //Load Data vao TableView
        maHoaDon.setCellValueFactory(new PropertyValueFactory("maHoaDon"));
        ngayLap.setCellValueFactory(new PropertyValueFactory("ngayDatHang"));
        tenHang.setCellValueFactory(new PropertyValueFactory("tenHang"));
        soLuong.setCellValueFactory(new PropertyValueFactory("soLuong"));
        donViTinh.setCellValueFactory(new PropertyValueFactory("donViTinh"));
        giaNhap.setCellValueFactory(new PropertyValueFactory("giaNhap"));
        thanhTien.setCellValueFactory(new PropertyValueFactory("thanhTien"));
        tbDonNhap.getItems().clear();
    
    
    }
    
    
    @FXML
    private void handleBtnThemMoi(ActionEvent event){
        
    }
    @FXML
    private void handleButtonLuu(ActionEvent event){
        if(validate())
         {
               donHangDTO.setMaHoaDon(txtMaHoaDon.getText());
               //donHangDTO.setNgayNhaptHang(ngayNhapHang.getValue());
               donHangDTO.setMaNV(cbMaNV.getSelectionModel().getSelectedItem().toString());
               donHangDTO.setMaNcc(cbMaNcc.getSelectionModel().getSelectedItem().toString());

               hangNhapDTO.setMaDonHang(txtMaHoaDon.getText());
               hangNhapDTO.setMaHang(cbMaHang.getSelectionModel().getSelectedItem().toString());
               hangNhapDTO.setSoLuong(Double.parseDouble(txtSoLuong.getText()));
         }
        
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTabble();
    }
    
}
