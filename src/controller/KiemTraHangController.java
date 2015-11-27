/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.KiemTraHangDAL;
import DTO.HangDTO;
import DTO.NhaCCDTO;
import DTO.TimHangDTO;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

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
    TextField txtMaHang;
    @FXML
    TextField txtTenHang;
    @FXML
    ComboBox cbTenNcc;
    @FXML
    DatePicker tuNgay;
    @FXML
    DatePicker denNgay;
    
    KiemTraHangDAL kiemTraHangDal=new KiemTraHangDAL();
    HangDTO hangDto=new HangDTO();
    NhaCCDTO nccDto=new NhaCCDTO();
    FormValidation form=new FormValidation();
    ResultSet resultSet;
    
     public void loadTable(ResultSet resultSet)
    {
        //Load Data vao TableView
        maHang.setCellValueFactory(new PropertyValueFactory("maHang"));
        tenHang.setCellValueFactory(new PropertyValueFactory("tenHang"));
        donViTinh.setCellValueFactory(new PropertyValueFactory("donViTinh"));
        ncc.setCellValueFactory(new PropertyValueFactory("ncc"));
        giaNhap.setCellValueFactory(new PropertyValueFactory("giaNhap"));
        giaBan.setCellValueFactory(new PropertyValueFactory("giaBan"));
        ngayHSD.setCellValueFactory(new PropertyValueFactory("ngayHSD"));
        soLuong.setCellValueFactory(new PropertyValueFactory("soLuong"));
        ghiChu.setCellValueFactory(new PropertyValueFactory("ghiChu"));
         
        tbHang.getItems().clear();
        tbHang.setItems(kiemTraHangDal.loadData(resultSet));
    
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load ten nha cung cap
        cbTenNcc.getItems().clear();
        cbTenNcc.setItems(kiemTraHangDal.loadTenNcc());
        
        
        //Datepicker tu ngay Format
        
        tuNgay.setValue(LocalDate.now());
        
        String pattern = "dd-MM-yyyy";

        tuNgay.setConverter(new StringConverter<LocalDate>() {
             DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

             @Override 
             public String toString(LocalDate date) {
                 if (date != null) {
                     return dateFormatter.format(date);
                 } else {
                     return "";
                 }
             }

             @Override 
             public LocalDate fromString(String string) {
                 if (string != null && !string.isEmpty()) {
                     return LocalDate.parse(string, dateFormatter);
                 } else {
                     return null;
                 }
             }
         });
        
        
        //Datepicker den ngay Format
        
        denNgay.setValue(LocalDate.now());
      
        denNgay.setConverter(new StringConverter<LocalDate>() {
             DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

             @Override 
             public String toString(LocalDate date) {
                 if (date != null) {
                     return dateFormatter.format(date);
                 } else {
                     return "";
                 }
             }

             @Override 
             public LocalDate fromString(String string) {
                 if (string != null && !string.isEmpty()) {
                     return LocalDate.parse(string, dateFormatter);
                 } else {
                     return null;
                 }
             }
         });
        
        
    }
    
    @FXML
    private void btnTimMaHang(ActionEvent event){
        boolean empty=form.textIsEmtpy(txtMaHang, "Nhập mã hàng");
        if(!empty){
            hangDto.setMaHang(txtMaHang.getText().trim());
            resultSet=kiemTraHangDal.timKiemTheoMaHang(hangDto);
            loadTable(resultSet);
        }
        //else JOptionPane.showMessageDialog(null, "Không tìm thầy!");
    }
    @FXML
    private void btnTimTenHang(ActionEvent event){
        boolean empty=form.textIsEmtpy(txtTenHang, "Nhập tên hàng");
        if(!empty){
            hangDto.setTenHang(txtTenHang.getText().trim());
            resultSet=kiemTraHangDal.timKiemTheoTenHang(hangDto);
            loadTable(resultSet);
        }
    }
    @FXML
    private void btnTimNCC(ActionEvent event){
        boolean empty=form.comboBoxIsEmtpy(cbTenNcc, "Chọn Tên Nhà cung cấp");
        if(!empty){
            hangDto.setTenNCC(cbTenNcc.getSelectionModel().getSelectedItem().toString());
            resultSet=kiemTraHangDal.timKiemTheoTenNcc(nccDto);
            loadTable(resultSet);
        }
        
    }
    @FXML
    private void btnTimHangTrongKho(ActionEvent event){
        resultSet=kiemTraHangDal.timKiemHangCon();
        loadTable(resultSet);
    }
    @FXML
    private void btnTimHangKoTrongKho(ActionEvent event){
        resultSet=kiemTraHangDal.timKiemHangHet();
        loadTable(resultSet);
    }
    
    @FXML
    private void btnTimNgay(ActionEvent event){
        LocalDate tu=tuNgay.getValue();
        LocalDate den=denNgay.getValue();
        resultSet=kiemTraHangDal.timKiemHangHetHan(tu, den);
        loadTable(resultSet);
                
    }
    
    
    
}
