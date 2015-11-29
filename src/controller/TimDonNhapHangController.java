/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.TimDonNhapHangDAL;
import DTO.DonHangDTO;
import DTO.DonNhapHangDTO;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Sokny Pen
 */
public class TimDonNhapHangController implements Initializable {
    
    //TableView Data
    @FXML 
    TableView<DonNhapHangDTO> tbTimDonNhap;
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
    TableColumn<DonNhapHangDTO,Double> thanhToan;
            
    //Hoa DOn
    @FXML
    TextField txtMaHoaDon;
    @FXML
    DatePicker ngayNhapHang;
    
    
    FormValidation form=new FormValidation();
    TimDonNhapHangDAL timDonNhapHangDal=new TimDonNhapHangDAL();
    DonHangDTO donHangDto=new DonHangDTO();
    ResultSet resultSet;
    
    public boolean validate(){
        boolean maHoDonEmpty=form.textIsEmtpy(txtMaHoaDon, "chưa có thông tin");
        
        if(!maHoDonEmpty) 
            return true;
        return false;
        
    }
    
    public void loadTable(ResultSet resultSet)
    {
        //Load Data vao TableView
        maHoaDon.setCellValueFactory(new PropertyValueFactory("maHoaDon"));
        ngayLap.setCellValueFactory(new PropertyValueFactory("ngayDatHang"));
        tenHang.setCellValueFactory(new PropertyValueFactory("tenHang"));
        soLuong.setCellValueFactory(new PropertyValueFactory("soLuong"));
        donViTinh.setCellValueFactory(new PropertyValueFactory("donViTinh"));
        giaNhap.setCellValueFactory(new PropertyValueFactory("giaNhap"));
        thanhToan.setCellValueFactory(new PropertyValueFactory("thanhTien"));
        
        tbTimDonNhap.getItems().clear();
        tbTimDonNhap.setItems(timDonNhapHangDal.loadData(resultSet));
    
    }
    
    @FXML
    public void handleTimKiemTheoMaHoaDon(ActionEvent e){
        if(validate())
        {
            donHangDto.setMaDonHang(txtMaHoaDon.getText().trim());
            resultSet=timDonNhapHangDal.timTheoMaHoaDon(donHangDto);
            loadTable(resultSet);

        }
 
    }
    
    @FXML
    public void handleTimKiemTheoNgayNhap(ActionEvent e){
        
            donHangDto.setNgayNhapHang(ngayNhapHang.getValue());
            resultSet=timDonNhapHangDal.timTheoNgayNhapHang(donHangDto);
            loadTable(resultSet);
    }
    
    @FXML
    public void handleHienThiAll(ActionEvent e){
         resultSet=timDonNhapHangDal.hienThiTatCa();
         loadTable(resultSet);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       resultSet=timDonNhapHangDal.hienThiTatCa();
         loadTable(resultSet);
        
    }
}
