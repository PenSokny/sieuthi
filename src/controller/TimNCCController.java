/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.NhaCCDal;
import DTO.NhaCCDTO;
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
public class TimNCCController implements Initializable {
    @FXML
    TableView<NhaCCDTO> tblNcc;
    @FXML 
    TableColumn<NhaCCDTO,String> maNcc;
    @FXML 
    TableColumn<NhaCCDTO,String> tenNcc;
    @FXML 
    TableColumn<NhaCCDTO,String> soDt;
    @FXML 
    TableColumn<NhaCCDTO,String> diaChi;
    @FXML
    TextField txtTenNcc;
    @FXML
    TextField txtMaNcc;
    
    NhaCCDal nccDal=new NhaCCDal();
    NhaCCDTO nccDto=new NhaCCDTO();
    FormValidation form=new FormValidation();
    ResultSet resultSet;
    
    public void loadData(ResultSet resultSet)
    {
         maNcc.setCellValueFactory(new PropertyValueFactory("maNcc"));
         tenNcc.setCellValueFactory(new PropertyValueFactory("tenNcc"));
         soDt.setCellValueFactory(new PropertyValueFactory("soDt"));
         diaChi.setCellValueFactory(new PropertyValueFactory("diaChi"));
         tblNcc.getItems().clear();
         tblNcc.setItems(nccDal.loadData(resultSet));
    }
    
    @FXML
    private void handleTimMa(ActionEvent event){
       boolean isTenEmpty=form.textIsEmtpy(txtMaNcc, "Nhập mã nhà cung cấp");
       if(!isTenEmpty){
           nccDto.setMaNcc(txtMaNcc.getText());
           resultSet=nccDal.timNccTheoMa(nccDto);
           loadData(resultSet);
           
       }
    }
    
    @FXML
    private void handleTimTen(ActionEvent event){
       boolean isTenEmpty=form.textIsEmtpy(txtTenNcc, "Nhập tên nhà cung cấp");
       if(!isTenEmpty){
           nccDto.setTenNcc(txtTenNcc.getText());
           resultSet=nccDal.timNccTheoTen(nccDto);
           loadData(resultSet);
       }
    }
    
    @FXML
    private void handleTimTat(ActionEvent event){
        resultSet=nccDal.getNcc();
        loadData(resultSet);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resultSet=nccDal.getNcc();
        loadData(resultSet);
        
    }
    
}
