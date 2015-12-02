/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.BanHangDTO;
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
public class BanHangDAL {
    
    DatabaseManager db=new DatabaseManager();
    BanHangDTO banHangDto=new BanHangDTO();
    ResultSet resultSet;
    
    private ObservableList<BanHangDTO> data=FXCollections.observableArrayList();
    
    public void loadMaHoaDon(ResultSet resultSet,BanHangDTO banHangDto){
        try{
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    banHangDto.setMaHoaDon(resultSet.getString("soHoaDon"));
                }
            } else{
                banHangDto.setMaHoaDon("0");
            }
            //resultSet.close();
        }catch(SQLException ex){
            Logger.getLogger(BanHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean loadDataToTextField(ResultSet resultSet,BanHangDTO banHangDto){
        boolean i=false;
        try{
            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    banHangDto.setTenSanPham(resultSet.getString("tenHang"));
                    banHangDto.setThanhTien(resultSet.getDouble("giaBan"));
                    banHangDto.setDonViTinh(resultSet.getString("donViTinh"));
                    i=true;
                }
            }else {
                JOptionPane.showMessageDialog(null,"Hàng không tìm thấy");
                i=false;
            }
            resultSet.close();
        }catch(SQLException ex){
            Logger.getLogger(BanHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    } 
    
    public void loadSoLuongSP(ResultSet resultSet,BanHangDTO banHangDto){
        try{
            while(resultSet.next()){
                banHangDto.setSoLuong(resultSet.getDouble("soLuong"));
            }
            resultSet.close();
        }catch(SQLException ex){
            
        }
    }
    
    public boolean kiemTraHangTrongTable(ResultSet resultSet,BanHangDTO banHangDto){
        boolean i=true;
        try{
            while(resultSet.next()){
                if(banHangDto.getMaSanPham().equals(resultSet.getString("maHang")));
                i=false;
                break;
            }
        }catch(SQLException ex){
            
        }
        return i;
    }
    
    public ResultSet getTableData(BanHangDTO banHangDto){
        String sql="SELECT hangban.maHang, tenHang, soLuong, giaBan, donViTinh, soLuong*donGia AS thanhTien FROM hoadon, "
                + "hangban, hang WHERE hoadon.soHoaDon=hangban.soHoaDon "
                + "AND hangban.maHang=hang.maHang AND hangban.soHoaDon=0 ";
        resultSet=db.loadData(sql);
        return resultSet;
    }
    
    public ResultSet getSoLuongSP(BanHangDTO banHangDto){
        String sql="SELECT soLuong FROM khosanpham WHERE maHang="+banHangDto.getMaSanPham()+"";
        resultSet=db.loadData(sql);
        return resultSet;
    }
    
    public int themHangVaoHangBan(BanHangDTO banHangDto){
        int result;
        String sql="INSERT INTO hangban VALUES('"+banHangDto.getMaHoaDon()+"','"+banHangDto.getNgayLapHoaDon()+"')";
        result=db.executeData(sql);
        return result;
    }
    
        public int themHangVaoHoaDon(BanHangDTO banHangDto){
        int result;
        String sql="INSERT INTO hoadon  VALUES('"+banHangDto.getMaHoaDon()+"' ,'"+ banHangDto.getNgayLapHoaDon()+"' )";       
        result=db.executeData(sql);       
        return result;
    }
    
    public int updateKhoSP(BanHangDTO banHangDto){
        
        String sql="UPDATE khosanpham SET soLuong=soLuong -"+banHangDto.getSoLuong()+" WHERE maHang="+banHangDto.getMaSanPham()+"";
        int result=db.executeData(sql);
        return result;
    }
    
    public ResultSet setData(BanHangDTO banHangDto){
        String sql="SELECT tenHang,giaBan,donViTinh FROM hang WHERE maHang='"+banHangDto.getMaSanPham()+"'";
        resultSet=db.loadData(sql);
        return resultSet;
    }
    
    public int themHangVaoKhoSP(BanHangDTO banHangDto){
        String sql="UPDATE khosanpham SET soLuong= soLuong+ "+banHangDto.getSoLuong()+" WHERE maHang='"+banHangDto.getMaSanPham()+"'";
        int result=db.executeData(sql);
        return result;
    }
    
    public ResultSet getSoHoaDon(){
        String sql="SELECT soHoaDon FROM hoadon";
        resultSet=db.loadData(sql);
        return resultSet;
     }
}
