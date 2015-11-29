/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DonHangDTO;
import DTO.DonNhapHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sokny Pen
 */
public class TimDonNhapHangDAL {
    
    DatabaseManager db=new DatabaseManager();
    ResultSet resultSet;
    
    private ObservableList<DonNhapHangDTO> data=FXCollections.observableArrayList();
    
    //Tim theo maHoaDon
    public ResultSet timTheoMaHoaDon(DonHangDTO donHangDto)
    {
         String sql="SELECT * FROM donhang,nhaphang,hang,nhanvien,nhacungcap "
                    + "WHERE donhang.maDonHang=nhaphang.maDonHang "
                    + "AND hang.maHang=nhaphang.maHang "
                    + "AND nhanvien.maNhanVien=donhang.maNhanVien "
                    + "AND nhacungcap.maNCC=donhang.maNCC "
                    + "AND donhang.maDonHang='"+donHangDto.getMaDonHang()+"'";
        
          resultSet=db.loadData(sql);
          return resultSet;
    }
    
    // Tim theo ngayNhapHang
    public ResultSet timTheoNgayNhapHang(DonHangDTO donHangDto)
    {
         String sql="SELECT * FROM donhang,nhaphang,hang,nhanvien,nhacungcap "
                    + "WHERE donhang.maDonHang=nhaphang.maDonHang "
                    + "AND hang.maHang=nhaphang.maHang "
                    + "AND nhanvien.maNhanVien=donhang.maNhanVien "
                    + "AND nhacungcap.maNCC=donhang.maNCC "
                    + "AND donhang.ngayDatHang='"+donHangDto.getNgayNhapHang()+"'";
        
          resultSet=db.loadData(sql);
          return resultSet;
    }
    
     //Tim theo maHoaDon
    public ResultSet hienThiTatCa()
    {
         String sql="SELECT * FROM donhang,nhaphang,hang,nhanvien,nhacungcap "
                    + "WHERE donhang.maDonHang=nhaphang.maDonHang "
                    + "AND hang.maHang=nhaphang.maHang "
                    + "AND nhanvien.maNhanVien=donhang.maNhanVien "
                    + "AND nhacungcap.maNCC=donhang.maNCC ";
        
          resultSet=db.loadData(sql);
          return resultSet;
    }
    
    // Ket qua hien thi vao trong tabble
     public ObservableList<DonNhapHangDTO> loadData(ResultSet resultSet)
     {
        try {
        
            while(resultSet.next())
            {
                SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
                
                String maHoaDon=resultSet.getString("maDonHang");
                String ngayNhap=newFormat.format(oldFormat.parse(resultSet.getString("ngayDatHang")));
                String tenHang=resultSet.getString("tenHang");
                double soLuong=Double.parseDouble(resultSet.getString("soLuong"));
                String donViTinh=resultSet.getString("donViTinh");
                double giaNhap=Double.parseDouble(resultSet.getString("giaNhap"));
                double thanhTien=soLuong*giaNhap;
                String maNcc=resultSet.getString("maNCC");
                String maNv=resultSet.getString("maNhanVien");
                String maHang=resultSet.getString("maHang");
                
                data.add(new DonNhapHangDTO(maHoaDon, ngayNhap, tenHang, soLuong, donViTinh, giaNhap, thanhTien,maNcc,maNv,maHang));
                
            }
            
            resultSet.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    
}
