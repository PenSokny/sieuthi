/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HangDTO;
import DTO.NhaCCDTO;
import DTO.TimHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sokny Pen
 */
public class KiemTraHangDAL {
    DatabaseManager db= new DatabaseManager();
    
    private ObservableList<TimHangDTO> data=FXCollections.observableArrayList();
    private ObservableList<String> dataNcc=FXCollections.observableArrayList();
    ResultSet resultSet;
    
    //Tim kiem theo maHang
    public ResultSet timKiemTheoMaHang(HangDTO hangDto){
        String sql="SELECT * FROM hang, nhacungcap, khosanpham "
                + "WHERE hang.maNCC=nhacungcap.maNCC "
                + "AND hang.maHang=khosanpham.maHang "
                + "AND hang.maHang='"+hangDto.getMaHang()+"'";
        
        resultSet=db.loadData(sql);
        return resultSet;
    }
    
     //Tim kiem theo tenHang
    public ResultSet timKiemTheoTenHang(HangDTO hangDto)
    {
       String sql="SELECT * FROM hang,nhacungcap,khosanpham "
               + "WHERE nhacungcap.maNCC=hang.maNCC "
               + "AND hang.maHang=khosanpham.maHang "
               + "AND hang.tenHang like '%"+hangDto.getTenHang()+"%'";          
       
       resultSet=db.loadData(sql);
       return resultSet;
       
    }
    
    //Tim kiem theo ten Nha cung cap
    public ResultSet timKiemTheoTenNcc(NhaCCDTO nccDto)
    {
       String sql="SELECT * FROM hang, nhacungcap, khosanpham "
               + "WHERE nhacungcap.maNCC=hang.maNCC "
               + "AND hang.maHang=khosanpham.maHang "
               + "AND nhacungcap.tenNCC like '%"+nccDto.getTenNcc()+"%'";
  
       resultSet=db.loadData(sql);
       return resultSet;
       
    }
    
    //Tim kiem hang con trong kho
    public ResultSet timKiemHangCon()
    {
       String sql="SELECT * FROM hang,nhacungcap,khosanpham "
               + "WHERE nhacungcap.maNCC=hang.maNCC "
               + "AND hang.maHang=khosanpham.maHang "
               + "AND khosanpham.soLuong<>0";
              
       
       resultSet=db.loadData(sql);
       return resultSet;
       
    }
    
     //Tim kiem hang khong con trong kho
    public ResultSet timKiemHangHet()
    {
       String sql="SELECT * FROM hang,nhacungcap,khosanpham "
               + "WHERE nhacungcap.maNCC=hang.maNCC "
               + "AND hang.maHang=khosanpham.maHang "
               + "AND khosanpham.soLuong=0";
              
       resultSet=db.loadData(sql);
       return resultSet;
       
    }
    
     //Tim kiem hang het han
    public ResultSet timKiemHangHetHan(LocalDate tuNgay, LocalDate denNgay)
    {
       String sql="SELECT * FROM hang,nhacungcap,khosanpham "
               + "WHERE nhacungcap.maNCC=hang.maNCC "
               + "AND hang.maHang=khosanpham.maHang "
               + "AND hang.ngayHSD>='"+tuNgay+"'"
               + "AND hang.ngayHSD<='"+denNgay+"'";
              
       resultSet=db.loadData(sql);
       return resultSet;
       
    }
    
    // Ket qua hien thi vao trong tabble
     public ObservableList<TimHangDTO> loadData(ResultSet resultSet)
     {
        try {
        
            while(resultSet.next())
            {
                SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                
                String maHang=resultSet.getString("maHang");
                String tenHang=resultSet.getString("tenHang");
                String donViTinh=resultSet.getString("donViTinh");
                String ncc=resultSet.getString("tenNCC");
                double giaNhap=Double.parseDouble(resultSet.getString("giaNhap"));
                double giaBan=Double.parseDouble(resultSet.getString("giaBan"));
                String ngayHSD=newFormat.format(oldFormat.parse(resultSet.getString("ngayHSD")));
                double soLuong=Double.parseDouble(resultSet.getString("soLuong"));
                String ghiChu=resultSet.getString("ghiChu");
   
                data.add(new TimHangDTO(maHang, tenHang, donViTinh, ncc, giaNhap, giaBan, ngayHSD, soLuong, ghiChu));
            }
            
            resultSet.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
     
     //Load ten nha cung cap
     
     public ObservableList<String> loadTenNcc()
     {
        try {
            resultSet=db.loadData("SELECT * FROM nhacungcap");
            while(resultSet.next())
            {
                dataNcc.add(resultSet.getString("tenNCC")); 
            }
            resultSet.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(KiemTraHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dataNcc;
     }
     
    
    
}
