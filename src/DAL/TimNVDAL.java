/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhanVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sokny Pen
 */
public class TimNVDAL {
    DatabaseManager db = new DatabaseManager();
    ResultSet resultSet;

    private ObservableList<NhanVienDTO> data = FXCollections.observableArrayList();

    //Tim theo maNhanVien
    public ResultSet timTheoMaNhanVien(NhanVienDTO nvDto) {
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien = '" + nvDto.getMaNhanVien() + "' ";
        resultSet = db.loadData(sql);
        
        return resultSet;
    }

    public ResultSet timTheoTenNhanVien(NhanVienDTO nvDto) {
        String sql = "SELECT * FROM NhanVien WHERE tenNhanVien like '%" + nvDto.getTenNhanVien() + "%' ";
        resultSet = db.loadData(sql);
        return resultSet;
    }

    
    public ResultSet hienThiTatCa() {
        String sql = "SELECT * FROM NhanVien  ";
        resultSet = db.loadData(sql);
        return resultSet;
    }

    // Ket qua hien thi vao trong tabble
    public ObservableList<NhanVienDTO> loadData(ResultSet resultSet) {
        try {

            while (resultSet.next()) {
                String maNhanVien = resultSet.getString("maNV");
                JOptionPane.showMessageDialog(null, "thanh cong");
                String tenNhanVien = resultSet.getString("tenNV");
   
                String soDth = resultSet.getString("soDth");
          
                String moTa = resultSet.getString("moTa");
          

                data.add(new NhanVienDTO(maNhanVien, tenNhanVien, soDth, moTa));

            }

            resultSet.close();

        } catch (SQLException ex) {
            Logger.getLogger(DonNhapHangDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
}
