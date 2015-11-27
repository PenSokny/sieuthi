/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import DTO.NhaCCDTO;
import DAL.NhaCCDal;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Sokny Pen
 */
public class NhaCCController implements Initializable{
    
    @FXML
    TableView<NhaCCDTO> tblNcc;
    @FXML
    TableColumn<NhaCCDTO,String> maNCC;
    @FXML
    TableColumn<NhaCCDTO,String> tenNCC;
    @FXML
    TableColumn<NhaCCDTO,String> SDD;
    @FXML
    TableColumn<NhaCCDTO,String> diaChi;
    
    @FXML
    TextField txtmaNCC;
    @FXML
    TextField txtTenNCC;
    @FXML
    TextField txtDiaChi;
    @FXML
    TextField txtSDD;

    
    NhaCCDTO nCCDto=new NhaCCDTO();
    NhaCCDal nCCDal=new NhaCCDal();
    FormValidation form=new  FormValidation();
    
    public void loadData()
    {
         maNCC.setCellValueFactory(new PropertyValueFactory("maNcc"));
         tenNCC.setCellValueFactory(new PropertyValueFactory("tenNcc"));
         diaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
         SDD.setCellValueFactory(new PropertyValueFactory("soDt"));
         
         tblNcc.getItems().clear();
         tblNcc.setItems(nCCDal.loadData(nCCDal.getNcc()));
        
    }
    
    public void themMoi(){
        txtmaNCC.setText("");
        txtTenNCC.setText("");  
        txtSDD.setText("");
        txtDiaChi.setText("");
          
    }
    
    public boolean validate(){
        boolean maNccEmpty=form.textIsEmtpy(txtmaNCC, "Vui lòng nhập Mã nhà cung cấp");
        boolean tenNccEmpty=form.textIsEmtpy(txtTenNCC, "Vui lòng nhập tên nhà cung cấp");
        boolean soDtEmpty=form.textIsEmtpy(txtSDD, "Vui lòng nhập số điện thoại");
        boolean soDtCorrect=form.textIsPhoneNumber(txtSDD, "Số điện thoại không đúng."); 
        if(!maNccEmpty && !tenNccEmpty && !soDtEmpty && soDtCorrect )
          return true;
        return false;
    }
    
    @FXML
    private void handleButtonLuu(ActionEvent event){
        if(validate()){
            nCCDto.setMaNcc(txtmaNCC.getText());
            nCCDto.setTenNcc(txtTenNCC.getText());        
            nCCDto.setSoDt(txtSDD.getText());
            nCCDto.setDiaChi(txtDiaChi.getText());
            
            if(nCCDal.saveData(nCCDto)>0){
                loadData();
                themMoi();
                JOptionPane.showMessageDialog(null, "Lưu thành công");
            }
        }
        else JOptionPane.showMessageDialog(null, "Không thể lưu được.");
    }
    
    @FXML
    private void handleButtonSua(ActionEvent event){
        int i=tblNcc.getSelectionModel().getSelectedIndex();
        if(i>=0){
            if(validate()){
                nCCDto.setMaNcc(txtmaNCC.getText());
                nCCDto.setTenNcc(txtTenNCC.getText());
                nCCDto.setDiaChi(txtDiaChi.getText());
                nCCDto.setSoDt(txtSDD.getText());
                
                String ma=tblNcc.getSelectionModel().getSelectedItem().getMaNcc();
                if(nCCDal.updateData(nCCDto, ma)>0){
                    loadData();
                    themMoi();
                    JOptionPane.showMessageDialog(null, "Update thành công");
                }
            }
        }
        else JOptionPane.showMessageDialog(null, "Không thể update được.");
    }
    
    @FXML
    private void handleButtonXoa(ActionEvent event){
        
        int i=tblNcc.getSelectionModel().getSelectedIndex();
        if(i>=0){
            nCCDto.setMaNcc(tblNcc.getSelectionModel().getSelectedItem().getMaNcc());
            if(nCCDal.deleteData(nCCDto)>0){
                loadData();
                themMoi();
                JOptionPane.showMessageDialog(null, "Delete thành công"); 
            }
        }
        else JOptionPane.showMessageDialog(null, "Không thể delete được.");
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
             loadData();
             
            // Handle ListView selection changes.
        
            tblNcc.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(tblNcc.getSelectionModel().getSelectedIndex()>=0)
                {
                    txtmaNCC.setText(newValue.getMaNcc());
                    txtTenNCC.setText(newValue.getTenNcc());
                    txtSDD.setText(newValue.getSoDt());
                    txtDiaChi.setText(newValue.getDiaChi());
                }
            
         });
    }
    
}
