/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DTO.NhanVienDTO;
import DAL.NhanVienDal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author ERROR 405
 */
public class NhanVienController implements Initializable{
    
    @FXML
    TableView<NhanVienDTO> tblNhanVien;
    @FXML
    TableColumn<NhanVienDTO,String> maNhanVien;
    @FXML
    TableColumn<NhanVienDTO,String> tenNhanVien;
    @FXML
    TableColumn<NhanVienDTO,String> soDt;
    @FXML
    TableColumn<NhanVienDTO,String> moTa;
    @FXML
    TextField txtMota;
    @FXML
    TextField txtSoDt;
    @FXML
    TextField txtTen;
    @FXML
    TextField txtMa;
    @FXML
    Label soNhanVien;

    NhanVienDTO nhanVienDTO=new NhanVienDTO();
    FormValidation form=new FormValidation();
    NhanVienDal nvDal=new NhanVienDal();
    
    public void themMoi(){
        txtMa.setText("");
        txtTen.setText("");
        txtSoDt.setText("");
        txtMota.setText("");
    }
    
    
    public void loadData(){
         maNhanVien.setCellValueFactory(new PropertyValueFactory("maNhanVien"));
         tenNhanVien.setCellValueFactory(new PropertyValueFactory("tenNhanVien"));
         soDt.setCellValueFactory(new PropertyValueFactory("dienThoai"));
         moTa.setCellValueFactory(new PropertyValueFactory("moTa"));
         tblNhanVien.getItems().clear();
         tblNhanVien.setItems(nvDal.loadData(nvDal.getNhanVien()));
    }
    
    public boolean validate()
    {
        boolean maNccEmpty=form.textIsEmtpy(txtMa, "Vui lòng nhập Mã nhân viên");
        boolean tenNccEmpty=form.textIsEmtpy(txtTen, "Vui lòng nhập tên nhân viên");
        boolean soDtEmpty=form.textIsEmtpy(txtSoDt, "Vui lòng nhập số điện thoại");
        boolean soDtCorrect=form.textIsPhoneNumber(txtSoDt, "Số điện thoại không đúng.");
        if(!maNccEmpty && !tenNccEmpty && !soDtEmpty && soDtCorrect)
          return true;
        return false;
    }
    
    @FXML
    private void handleButtonCapNhat(ActionEvent event){
        
        int i=tblNhanVien.getSelectionModel().getSelectedIndex();
        if(i>=0){
            if(validate()){
                nhanVienDTO.setMaNhanVien(txtMa.getText());
                nhanVienDTO.setTenNhanVien(txtTen.getText());
                nhanVienDTO.setDienThoai(txtSoDt.getText());
                nhanVienDTO.setMoTa(txtMota.getText());
                String ma=tblNhanVien.getSelectionModel().getSelectedItem().getMaNhanVien();
                
                if(nvDal.updateData(nhanVienDTO,ma)>0){
                    loadData();
                    themMoi();
                    JOptionPane.showMessageDialog(null, "update thanh cong.");
                }
                
            }
            else  JOptionPane.showMessageDialog(null, "khong the update duoc.");
        }
        
    }
    @FXML
    private void handleButtonXoa(ActionEvent event){
        int i=tblNhanVien.getSelectionModel().getSelectedIndex();
        if(i>=0){
            nhanVienDTO.setMaNhanVien(tblNhanVien.getSelectionModel().getSelectedItem().getMaNhanVien());
            if(nvDal.deleteData(nhanVienDTO)>0){
                loadData();
                themMoi();
                JOptionPane.showMessageDialog(null,"Xoa thanh cong.");
            }
         
        }
        else JOptionPane.showMessageDialog(null,"Khong the xoa duoc");
        
    }
    

    
    @FXML
    private void handleButtonLuu(ActionEvent event){
        
        if(validate())
            {
                nhanVienDTO.setMaNhanVien(txtMa.getText());
                nhanVienDTO.setTenNhanVien(txtTen.getText());
                nhanVienDTO.setDienThoai(txtSoDt.getText());
                nhanVienDTO.setMoTa(txtMota.getText());
                if(nvDal.saveData(nhanVienDTO)>0){
                    loadData();
                    themMoi();
                }
                
            }
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        loadData();
       
        tblNhanVien.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(tblNhanVien.getSelectionModel().getSelectedIndex()>=0)
                {
                    txtMa.setText(newValue.getMaNhanVien());
                    txtTen.setText(newValue.getTenNhanVien());
                    txtSoDt.setText(newValue.getDienThoai());
                    txtMota.setText(newValue.getMoTa());
                }
            
         });
         
    }
    
    
}
