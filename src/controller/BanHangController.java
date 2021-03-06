/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.BanHangDAL;
import DTO.BanHangDTO;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 *
 * @author ERROR 405
 */
public class BanHangController implements Initializable{
    @FXML
    TableView<BanHangDTO> tbBanHang;
    @FXML
    TableColumn<BanHangDTO,String> maSP;
    @FXML
    TableColumn<BanHangDTO,String> tenSP;
    @FXML
    TableColumn<BanHangDTO,String> soLuong;
    @FXML
    TableColumn<BanHangDTO,String> donGia;
    @FXML
    TableColumn<BanHangDTO,String> donViTinh;
    @FXML
    TableColumn<BanHangDTO,String> thanhTien;
        
    @FXML
    TextField txtMaSP;
    @FXML
    TextField txtTenSP;
    @FXML
    TextField txtDonViTinh;
    @FXML
    TextField txtSoLuong;
    @FXML
    TextField txtDonGia;
    @FXML
    TextField txtTongSoTien;
    @FXML
    TextField txtTienKhachTra;
    @FXML
    TextField txtTienTraLai;
    @FXML
    DatePicker ngayBanHang;
 
    
    private double soTien=0;
    
    BanHangDTO banHangDto=new BanHangDTO();
    BanHangDAL banHangDal=new BanHangDAL();
    FormValidation validation=new FormValidation();
    ResultSet resultSet;
    
    ArrayList <BanHangDTO> arr=new ArrayList<>();
    
    public boolean setValidation() {
        boolean maIsEmty = validation.textIsEmtpy(txtMaSP, "Vui lòng nhập mã sản phẩm ");
        boolean soLuongIsNumber = validation.textIsNumberic(txtSoLuong, "Số lượng phải là số");
        boolean soLuongIsEmty = validation.textIsEmtpy(txtSoLuong, "Vui lòng nhập vào số lượng");
        
        if (maIsEmty || soLuongIsEmty || !soLuongIsNumber) {
            return false;
        } else {
            return true;
        }

    }

    public boolean setValidationTienKhachTra() {
        boolean tienKhacTraIsNumber = validation.textIsNumberic(txtTienKhachTra, "Tiền khách tra phải la số");
        boolean tienKhachTraIsEmty=validation.textIsEmtpy(txtTienKhachTra, "Hãy nhập vào tiền khách trả");
        if (tienKhacTraIsNumber && !tienKhachTraIsEmty) {
            return true;
        } else {
            return false;
        }
    }
    
    private void themMoi() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtDonViTinh.setText("");
        //ngayBanHang.setValue(LocalDate.now());

    }
    
    //load data vao table
    public void loadData() {
         maSP.setCellValueFactory(new PropertyValueFactory("maSanPham"));
        tenSP.setCellValueFactory(new PropertyValueFactory("tenSanPham"));
        soLuong.setCellValueFactory(new PropertyValueFactory("soLuong"));
        donGia.setCellValueFactory(new PropertyValueFactory("donGia"));
        thanhTien.setCellValueFactory(new PropertyValueFactory("thanhTien"));
        donViTinh.setCellValueFactory(new PropertyValueFactory("donViTinh"));       
        tbBanHang.getItems().clear();
        
        ObservableList<BanHangDTO> loadData=FXCollections.observableArrayList(arr);
        tbBanHang.setItems(loadData);
    }
    
     public void setData(BanHangDTO banHangDto) {

        if (!txtMaSP.getText().equals("")) {

            txtMaSP.setText(banHangDto.getMaSanPham());
            txtTenSP.setText(banHangDto.getTenSanPham());
            txtDonViTinh.setText(banHangDto.getDonViTinh());
            txtDonGia.setText("" + banHangDto.getThanhTien() + "");
        } else {
            
        }
    }
     
     public void them() {
        
        banHangDto.setMaSanPham(txtMaSP.getText());
        if (banHangDal.loadDataToTextField(banHangDal.setData(banHangDto), banHangDto) == true) {
            banHangDal.loadSoLuongSP(banHangDal.getSoLuongSP(banHangDto), banHangDto);
            double so = Double.valueOf(txtSoLuong.getText());
            if (banHangDto.getSoLuong() >= so) {
                setData(banHangDto);
                String ma = txtMaSP.getText();
                String ten = txtTenSP.getText();
                double soluong = Double.valueOf(txtSoLuong.getText());
                double dongia = Double.valueOf(txtDonGia.getText());
                String donVi = txtDonViTinh.getText();
                soTien = soTien + soluong * dongia;
                txtTongSoTien.setText("" + soTien);
                
                arr.add(new BanHangDTO(ma, ten, soluong, dongia, donVi, soluong * dongia));
                loadData();
                //kiemTraHangTrongTable();
            } else {
                JOptionPane.showMessageDialog(null, "Số lượng trong kho không đủ");
                
            }
           
        }
        else{
            themMoi();
            
        }
    }

    @FXML
    private void handleEnterPressed(javafx.scene.input.KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
            txtSoLuong.setText("1");
            banHangDto.setMaSanPham(txtMaSP.getText().trim());
            if(banHangDal.loadDataToTextField(banHangDal.setData(banHangDto), banHangDto)==true){
                setData(banHangDto);
            }
            else{
                themMoi();
            }
        }
    }
   

    @FXML
    public void handleThem(ActionEvent event){
       boolean i = true;
        if(txtSoLuong.getText().equals("")){
            txtSoLuong.setText("1");
        }
        if (setValidation() == true) {
           //banHangDal.themHangVaoHangBan(banHangDto);
        
            if (arr.isEmpty()) {
                them();
            } else {
                for (BanHangDTO ban : arr) {
                    if (ban.getMaSanPham().equals(txtMaSP.getText())) {
                        i = false;
                        break;
                    }
                }
                if (i == true) {
                    them();
                } else {
                    JOptionPane.showMessageDialog(null, "Hàng đã có trong bảng");
                }
            }
        }
        themMoi(); 
    }
    
    @FXML
    public void handleXoa(ActionEvent event){
        int i=tbBanHang.getSelectionModel().getSelectedIndex();
        if(i>= 0){
                arr.remove(tbBanHang.getSelectionModel().getSelectedIndex());
                loadData();
        }
        else{
            JOptionPane.showMessageDialog(null, "Hãy chọn hàng để xóa");
        }
    }
    @FXML
    private void handlePress(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (Double.parseDouble(txtTienKhachTra.getText()) >= Double.parseDouble(txtTongSoTien.getText())) {
                double tienCon = Double.parseDouble(txtTienKhachTra.getText()) - Double.parseDouble(txtTongSoTien.getText());
                txtTienTraLai.setText("" + tienCon);
            }
            else{
                JOptionPane.showMessageDialog(null, "Tiền khách trả không đủ");
            }
        }
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //loadData();
        
        SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        tbBanHang.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (tbBanHang.getSelectionModel().getSelectedIndex() >= 0) {

                    txtMaSP.setText(newValue.getMaSanPham());
                    txtTenSP.setText(newValue.getTenSanPham());
                    txtSoLuong.setText("" + newValue.getSoLuong() + "");
                    txtDonGia.setText("" + newValue.getDonGia() + "");
                    txtDonViTinh.setText(newValue.getDonViTinh());


        }
        });
   
        ngayBanHang.setValue(LocalDate.now());

        ngayBanHang.setValue(LocalDate.now());
        
        String pattern = "dd-MM-yyyy";

        ngayBanHang.setConverter(new StringConverter<LocalDate>() {
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
