/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DTO.KhoSanPhamDTO;
import DTO.HangDTO;
import DAL.TTHangDal;
import DTO.NhaCCDTO; 
import DAL.NhaCCDal;
import java.net.URL; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 *
 * @author ERROR 405
 */



public class QuanLyThongTinHangController implements Initializable{
    
    @FXML
    TableView<HangDTO> tbTTHang;
    @FXML
    TableColumn<HangDTO,String> maHang;
    @FXML
    TableColumn<HangDTO,String> tenHang;
    @FXML
    TableColumn<HangDTO,String> maNCC;
    @FXML
    TableColumn<HangDTO,String> tenNCC;
    @FXML
    TableColumn<HangDTO,String> giaNhap;
    @FXML
    TableColumn<HangDTO,String> giaBan;
    @FXML
    TableColumn<HangDTO,String> donViTinh;
    @FXML
    TableColumn<HangDTO,String> ghiChu;
    @FXML
    TableColumn<HangDTO,String> ngayHSD;
    
    
    @FXML
    TextField txtMaHang;

    @FXML
    TextField txtTenHang;

    @FXML
    ComboBox cbMaNhaCungCap;
    
    @FXML
    TextField txtMaNCC;

    @FXML
    TextField txtDonViTinh;

    @FXML
    TextField txtGiaNhap;

    @FXML
    TextField txtGiaBan;

    @FXML
    TextField txtGhiChu;
   
    @FXML
    DatePicker ngayhsd;
    
    HangDTO hangDTO=new HangDTO();
    TTHangDal ttHangDal=new TTHangDal();
    NhaCCDTO nCCDto=new NhaCCDTO();
    NhaCCDal nCCDal=new NhaCCDal();
    KhoSanPhamDTO khoSPDto=new KhoSanPhamDTO();
    FormValidation form=new FormValidation();
    
    
     public void loadData(){
         maHang.setCellValueFactory(new PropertyValueFactory("maHang"));
         tenHang.setCellValueFactory(new PropertyValueFactory("tenHang"));
         maNCC.setCellValueFactory(new PropertyValueFactory("maNCC"));
         donViTinh.setCellValueFactory(new PropertyValueFactory("donviTinh"));
         giaNhap.setCellValueFactory(new PropertyValueFactory("giaNhap"));
         giaBan.setCellValueFactory(new PropertyValueFactory("giaBan"));
         ghiChu.setCellValueFactory(new PropertyValueFactory("ghiChu"));
         ngayHSD.setCellValueFactory(new PropertyValueFactory("ngayHSD"));
         
         
          tbTTHang.getItems().clear();
          
        }
     
    public void themMoi(){
       txtMaHang.setText("");
       txtTenHang.setText("");
       cbMaNhaCungCap.getSelectionModel().select("");
       txtMaNCC.setText("");
       txtDonViTinh.setText("");
       txtGiaNhap.setText("");
       txtGiaBan.setText("");
       txtGhiChu.setText("");
       ngayhsd.setValue(LocalDate.now());
          
        
    }
    public boolean validate(){
        boolean maTTHangEmpty=form.textIsEmtpy(txtMaHang, "Vui lòng nhập Mã Hàng");
        boolean tenTTHangEmpty=form.textIsEmtpy(txtTenHang, "Vui lòng nhập tên hàng");
        boolean maNCC=form.comboBoxIsEmtpy(cbMaNhaCungCap, "Vui lòng lựa chọn");
        boolean donViTinhEmpty=form.textIsEmtpy(txtDonViTinh, "Vui lòng nhập đơn vị tính");
        boolean giaNhapIsNumber=form.textIsNumberic(txtGiaNhap, "Giá nhập phải là số");
        boolean giaNhapEmpty=form.textIsEmtpy(txtGiaNhap, "Vui lòng nhập giá nhập");
        boolean giaBanIsNumber=form.textIsNumberic(txtGiaBan, "Giá bán phải là số");
        boolean giaBanEmpty=form.textIsEmtpy(txtGiaBan, "Vui lòng nhập giá bán");
           
        if(!maTTHangEmpty && !tenTTHangEmpty && !maNCC && !donViTinhEmpty && !giaNhapEmpty && !giaBanEmpty && giaNhapIsNumber && giaBanIsNumber)
          return true;
        return false;    
    }
    
    
    @FXML
    private void handleButtonThemMoi(ActionEvent even){
        themMoi();
    }
    
    @FXML
    private void handleButtonLuu(ActionEvent even){
        
        if(validate()){   
            
               hangDTO.setMaHang(txtMaHang.getText());
               hangDTO.setTenHang(txtTenHang.getText());    
               hangDTO.setMaNCC(cbMaNhaCungCap.getSelectionModel().getSelectedItem().toString());
               hangDTO.setDonViTinh(txtDonViTinh.getText());
               hangDTO.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
               hangDTO.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
               hangDTO.setGhiChu(txtGhiChu.getText());
               hangDTO.setNgayHSD(ngayhsd.getValue().toString());
               int result=ttHangDal.saveData(hangDTO);
               khoSPDto.setMaHang(txtMaHang.getText());
               int resultKho=ttHangDal.saveKhoSanPham(khoSPDto);
               
               if(result>0 && resultKho>0)
              {    
                 loadData();
                 themMoi();
                 JOptionPane.showMessageDialog(null, "Luu thanh cong"); 
                 
               
               }
               else
                  JOptionPane.showMessageDialog(null, "Chua luu duoc."); 
         }
        
    }
    @FXML
    private void handleButtonUpdate(ActionEvent even){
        
    }
    @FXML
    private void handleButtonXoa(ActionEvent even){
        
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        
                     loadData();
             
            // Handle ListView selection changes.
             
            SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        
           tbTTHang.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends HangDTO> observable, HangDTO oldValue, HangDTO newValue) -> {
                try {
                        int i=tbTTHang.getSelectionModel().getSelectedIndex();
                        if(i>=0){
                            txtMaHang.setText(newValue.getMaHang());
                            txtTenHang.setText(newValue.getTenHang());
                    
                            cbMaNhaCungCap.getSelectionModel().select(newValue.getMaNCC());
                            txtDonViTinh.setText(newValue.getDonViTinh());
                            txtGiaNhap.setText(String.format("%.0f",newValue.getGiaNhap()));
                            txtGiaBan.setText(String.format("%.0f", newValue.getGiaBan()));
                            txtGhiChu.setText( newValue.getGhiChu());
                            ngayhsd.setValue(LocalDate.parse(newFormat.format(oldFormat.parse(newValue.getNgayHSD()))));
                   
                        } 
                    }catch(ParseException ex){
                     Logger.getLogger(QuanLyThongTinHangController.class.getName()).log(Level.SEVERE, null, ex);
                }
     
        });
        //Datepicker ngayHSD Format

        ngayhsd.setValue(LocalDate.now());

        String pattern = "dd-MM-yyyy";

         ngayhsd.setConverter(new StringConverter<LocalDate>() {
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
         
         

        
        cbMaNhaCungCap.getItems().clear();
        cbMaNhaCungCap.setItems(ttHangDal.loadDataMaNcc());
        
        cbMaNhaCungCap.setOnAction((event) -> {
        nCCDto.setMaNcc(cbMaNhaCungCap.getSelectionModel().getSelectedItem().toString());
        txtMaNCC.setText(ttHangDal.loadDataTenNcc(nCCDto)); 
        } ); 
     
        
    }
              
}
