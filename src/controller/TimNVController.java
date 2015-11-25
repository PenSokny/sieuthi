/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.NhanVienDal;
import DAL.TimNVDAL;
import DTO.NhanVienDTO;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ERROR 405
 */
public class TimNVController implements Initializable {
    @FXML
    TableView<NhanVienDTO> tblNhanVien;
    @FXML
    TableColumn<NhanVienDTO,String> ma;
    @FXML
    TableColumn<NhanVienDTO,String> ten;
    @FXML
    TableColumn<NhanVienDTO,String> sdth;
    @FXML
    TableColumn<NhanVienDTO,String> moTa;
    @FXML
    TextField txtMa;
    @FXML
    TextField txtTen;
    
    FormValidation form=new FormValidation();
    NhanVienDTO nvDto= new NhanVienDTO();
    NhanVienDal nvDal=new NhanVienDal();
    TimNVDAL timNVDal=new TimNVDAL();
    ResultSet resultSet;
       
    
     public boolean validateTen()
    {
         
        boolean tenNhanVien=form.textIsEmtpy(txtTen, "chưa có thông tin");
        if(!tenNhanVien ) 
            return true;
        return false;
        
    }
     public boolean validateMa(){
         boolean maNhanVien=form.textIsEmtpy(txtMa, "chưa có thông tin");
         if(!maNhanVien)
             return true;
         return false;
     }
     
    public void loadData(ResultSet resultSet)
    {
         ma.setCellValueFactory(new PropertyValueFactory("maNhanVien"));
         ten.setCellValueFactory(new PropertyValueFactory("tenNhanVien"));
         sdth.setCellValueFactory(new PropertyValueFactory("dienThoai"));
         moTa.setCellValueFactory(new PropertyValueFactory("moTa"));
         tblNhanVien.getItems().clear();
         tblNhanVien.setItems(nvDal.loadData(resultSet));
    }
    
    
    @FXML
    private void handleTimKiemTheoMaNhanVien(ActionEvent event){
         if(validateMa())
        {
           
                nvDto.setMaNhanVien(txtMa.getText().trim());

                ResultSet resultSet=timNVDal.timTheoMaNhanVien(nvDto);

                loadData(resultSet);

        }
    }
    
    @FXML
    private void handleTimTat(ActionEvent event){
         resultSet=nvDal.getNhanVien();
           loadData(resultSet);
    }
    
    @FXML
    private void handleTimKiemTheoTenNhanVien(ActionEvent event){
        if(validateTen()){
                nvDto.setTenNhanVien(txtTen.getText().trim());

                ResultSet resultSet=timNVDal.timTheoTenNhanVien(nvDto);

                loadData(resultSet);
        }
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resultSet=nvDal.getNhanVien();
        loadData(resultSet);
    }
    
}
