/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.DonNhapHangDAL;
import DTO.DonHangDTO;
import DTO.DonNhapHangDTO;
import DTO.HangDTO;
import DTO.HangNhapDTO;
import DTO.NhaCCDTO;
import DTO.NhanVienDTO;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    HangDTO hangDto=new HangDTO();
    DonNhapHangDTO donNhapHangDto=new DonNhapHangDTO();
    HangNhapDTO hangNhapDTO=new HangNhapDTO();
    DonNhapHangDAL donNhapHangDal=new DonNhapHangDAL();
    NhaCCDTO nccDto= new NhaCCDTO();
    DonHangDTO donHangDto=new DonHangDTO();
    NhanVienDTO nvDto=new NhanVienDTO();
    FormValidation form=new FormValidation();
    
    public void xoaThongTinHang()
    {
        txtTenHang.setText("");
        txtDonViTinh.setText("");
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        txtSoLuong.setText("");
    
    }
    
    public void themMoi()
    {
       xoaThongTinHang();
       txtMaHoaDon.setText("");
       ngayNhapHang.setValue(LocalDate.now());
       cbMaNcc.getSelectionModel().select("");
       cbMaNV.getSelectionModel().select("");
                  
    }
    
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
    
    public void loadTable(){
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
  
    public void addTableView()
    {
         //Load Data vao TableView
        ObservableList<DonNhapHangDTO> dataTableView=FXCollections.observableArrayList(); 
        
        maHoaDon.setCellValueFactory(new PropertyValueFactory("maHoaDon"));
        ngayLap.setCellValueFactory(new PropertyValueFactory("ngayDatHang"));
        tenHang.setCellValueFactory(new PropertyValueFactory("tenHang"));
        soLuong.setCellValueFactory(new PropertyValueFactory("soLuong"));
        donViTinh.setCellValueFactory(new PropertyValueFactory("donViTinh"));
        giaNhap.setCellValueFactory(new PropertyValueFactory("giaNhap"));
        thanhTien.setCellValueFactory(new PropertyValueFactory("thanhTien"));
        
        dataTableView=donNhapHangDal.loadDataTbView(hangDto, donNhapHangDto,hangNhapDTO, donHangDto, nccDto, nvDto);
        tbDonNhap.setItems(dataTableView);
    }
    

    @FXML
    private void handleButtonLuu(ActionEvent event){
        if(validate())
         {
               donHangDto.setMaDonHang(txtMaHoaDon.getText());
               donHangDto.setNgayNhapHang(ngayNhapHang.getValue());
               donHangDto.setMaNhanVien(cbMaNV.getSelectionModel().getSelectedItem().toString());
               donHangDto.setMaNCC(cbMaNcc.getSelectionModel().getSelectedItem().toString());

               hangNhapDTO.setMaDonHang(txtMaHoaDon.getText());
               hangNhapDTO.setMaHang(cbMaHang.getSelectionModel().getSelectedItem().toString());
               hangNhapDTO.setSoLuong(Double.parseDouble(txtSoLuong.getText()));
               
               int result=donNhapHangDal.saveData(donHangDto, hangNhapDTO);
               System.out.println(result);
               if(result>0)
               {
                 loadTable();
                 JOptionPane.showMessageDialog(null, "Luu thanh cong"); 
                 themMoi();
               }
               else
                   JOptionPane.showMessageDialog(null, "Chua luu duoc.");
         }
        
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTable();
        
        // Handle ListView selection changes.
        
            SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");   
        
            tbDonNhap.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                
            try {
                int i=tbDonNhap.getSelectionModel().getSelectedIndex();
                if(i>=0)
                {
                    txtMaHoaDon.setText(newValue.getMaHoaDon());
                    ngayNhapHang.setValue(LocalDate.parse(newFormat.format(oldFormat.parse(newValue.getNgayNhapHang()))));
                    cbMaNcc.getSelectionModel().select(newValue.getMaNcc());
                    cbMaNV.getSelectionModel().select(newValue.getMaNV());
                    cbMaHang.getSelectionModel().select(newValue.getMaHang());
                    txtSoLuong.setText(String.format("%.0f" ,newValue.getSoLuong()));
                }
                
            } catch (ParseException ex) {
                Logger.getLogger(DonNhapHangController.class.getName()).log(Level.SEVERE, null, ex);
            }       
           
         });
        
        
        //Data nha cung cap
        cbMaNcc.getItems().clear();
        cbMaNcc.setItems(donNhapHangDal.loadDataNCC());
        
            cbMaNcc.setOnAction((event) -> {
           nccDto.setMaNcc(cbMaNcc.getSelectionModel().getSelectedItem().toString());
           txtTenNcc.setText(donNhapHangDal.getTenNCC(nccDto)); 
           
           //Load Data Hang theo maNCC
           xoaThongTinHang();
           cbMaHang.getItems().clear();
           cbMaHang.setItems(donNhapHangDal.loadDataHang(nccDto));
           
        });
        
        //Data Hang
      
        cbMaHang.setOnAction((event) -> {
            
            try {
                if(cbMaHang.getSelectionModel().getSelectedIndex()>=0)
                {
                    hangDto.setMaHang(cbMaHang.getSelectionModel().getSelectedItem().toString());
                
                    ResultSet resultSet=donNhapHangDal.getTTHang(hangDto);
                    if(resultSet.next())
                    {
                        txtTenHang.setText(resultSet.getString("tenHang"));
                        txtDonViTinh.setText(resultSet.getString("donViTinh"));
                        txtGiaBan.setText(resultSet.getString("giaBan"));
                        txtGiaNhap.setText(resultSet.getString("giaNhap"));

                    }

                    resultSet.close();
                }
                    
                   
    
            } catch (SQLException ex) {
                Logger.getLogger(DonNhapHangController.class.getName()).log(Level.SEVERE, null, ex);
            }
              
           
        });
        
        //Data Nhan Vien
        cbMaNV.getItems().clear();
        cbMaNV.setItems(donNhapHangDal.loadDataNV());
        
        cbMaNV.setOnAction((event) -> {
            
            nvDto.setMaNhanVien(cbMaNV.getSelectionModel().getSelectedItem().toString());
            txtTenNV.setText(donNhapHangDal.getTenNV(nvDto));
            
        });
        
       //Datepicker ngayNhapHang Format
        
        ngayNhapHang.setValue(LocalDate.now());
        
        String pattern = "dd-MM-yyyy";

        ngayNhapHang.setConverter(new StringConverter<LocalDate>() {
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
    
}
