/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DonHangDTO;
import DTO.DonNhapHangDTO;
import DTO.HangDTO;
import DTO.HangNhapDTO;
import DTO.NhaCCDTO;
import DTO.NhanVienDTO;
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
public class DonNhapHangDAL {
    DatabaseManager db=new DatabaseManager();
    ResultSet resultSet;
    
    private ObservableList<String> dataNcc=FXCollections.observableArrayList();
    private ObservableList<String> dataHang=FXCollections.observableArrayList();
    private ObservableList<String> dataNV=FXCollections.observableArrayList();
    private ObservableList<DonNhapHangDTO> data=FXCollections.observableArrayList();
    private ObservableList<DonNhapHangDTO> dataTableView=FXCollections.observableArrayList();
    
    //Load Data Nha Cung cap
    public ObservableList<String> loadDataNCC(){
        try{
            resultSet=db.loadData("SELECT maNCC from nhacungcap");
            while(resultSet.next()){
                dataNcc.add(resultSet.getString("maNCC"));
            }
            resultSet.close();
        }catch(SQLException ex){
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataNcc;
    }
    //load ten nha cung cap theo maNCC
    public String getTenNCC(NhaCCDTO nccDto){
        String tenNcc=null;
        try{
            if(!nccDto.getMaNcc().isEmpty()){
                String sql="SELECT tenNCC FROM nhacungcap WHERE maNCC='"+nccDto.getMaNcc()+"'";
                resultSet=db.loadData(sql);
                if(resultSet.next()){
                    tenNcc=resultSet.getString("tenNCC");
                }
            }
            resultSet.close();
        }catch(SQLException ex){
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tenNcc;
    }
    
    //Load data Hang theo maNCC
    public ObservableList<String> loadDataHang(NhaCCDTO nccDto){
        try{
            if(!nccDto.getMaNcc().isEmpty()){
                resultSet=db.loadData("SELECT maHang FROM hang WHERE maNCC='"+nccDto.getMaNcc()+"'");
                while(resultSet.next()){
                    dataHang.add(resultSet.getString("maHang"));
                }
            }
            resultSet.close();
        }catch(SQLException ex){
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataHang;
    }
    
    //load thong tin hang theo maHang
    public ResultSet getTTHang(HangDTO hangDto){
        if(!hangDto.getMaHang().isEmpty()){
            String sql="SELECT * FROM hang WHERE maHang='"+hangDto.getMaHang()+"'";
                    
            resultSet=db.loadData(sql);
        }
        return resultSet;
    }
    //Load data nhan vien 
    public ObservableList<String> loadDataNV(){
        try{
            resultSet=db.loadData("SELECT maNhanVien FROM nhanvien");
            while(resultSet.next()){
                dataNV.add(resultSet.getString("maNhanVien"));
            }
            resultSet.close();
            
        }catch(SQLException ex){
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataNV;
    }
    
    //Load ten nhan vien theo maNV
    public String getTenNV(NhanVienDTO nvDto){
        String tenNV=null;
        try{
            if(!nvDto.getMaNhanVien().isEmpty()){
                String sql="SELECT tenNhanVien FROM nhanvien WHERE maNhanVien='"+nvDto.getMaNhanVien()+"' ";
                resultSet=db.loadData(sql);
                if(resultSet.next()){
                    tenNV=resultSet.getString("tenNhanVien");
                }
            }
            resultSet.close();
        }catch(SQLException ex){
            Logger.getLogger(DonNhapHangDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tenNV;
    }
    
    // Load thong tin vao TableView
    public ObservableList<DonNhapHangDTO> loadData()
    {
        try {
            String sql="SELECT * FROM donhang,hangnhap,hang,nhanvien,nhacungcap "
                    + "WHERE donhang.maDonHang=hangnhap.maDonHang "
                    + "AND hang.maHang=hangNhap.maHang "
                    + "AND nhanvien.maNV=donhang.maNV "
                    + "AND nhacungcap.maNCC=donhang.maNCC";
            resultSet=db.loadData(sql);
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
    
    // Luu data to tableView
    public ObservableList<DonNhapHangDTO> loadDataTbView(HangDTO hangDto, HangNhapDTO hangNhapDto, DonHangDTO donHangDto, NhaCCDTO nccDto, NhanVienDTO nhanVienDto)
    {
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        String maHoaDon=donHangDto.getMaDonHang();
        String tenHang=hangDto.getTenHang();
        double soLuong=hangNhapDto.getSoLuong();
        String donViTinh=hangDto.getDonViTinh();
        double giaNhap=hangDto.getGiaNhap();
        double thanhTien=soLuong*giaNhap;
        String maNcc=nccDto.getMaNcc();
        String maNv=nhanVienDto.getTenNhanVien();
        String maHang=hangDto.getMaHang();
        dataTableView.add(new DonNhapHangDTO(maHoaDon, tenHang, soLuong, donViTinh, giaNhap, thanhTien,maNcc,maNv,maHang));

        return dataTableView;
    }
    
    
    // Luu thong tin vao Bang DonHang, NhapHang va edit bang khosanpham
    public int saveData(DonHangDTO donHangDto,HangNhapDTO hangNhapDto)
    {
        String sqlDonHang="INSERT INTO donHang VALUES('"+donHangDto.getMaDonHang()+"','"+donHangDto.getNgayNhapHang()+"','"+donHangDto.getMaNhanVien()+"','"+donHangDto.getMaNCC()+"')";
        String sqlHangNhap="INSERT INTO hangnhap VALUES('"+hangNhapDto.getMaDonHang()+"','"+hangNhapDto.getMaHang()+"','"+hangNhapDto.getSoLuong()+"')";
        String sqlCapNhapKho="UPDATE khosanpham SET soLuong=("+hangNhapDto.getSoLuong()+"+soLuong) WHERE maHang='"+hangNhapDto.getMaHang()+"'";
        
        int resultDonHang=db.executeData(sqlDonHang);
        int resultHangNhap=db.executeData(sqlHangNhap);
        int resultCapNhatKho=db.executeData(sqlCapNhapKho);
        
       if(resultHangNhap>0 && resultDonHang>0 && resultCapNhatKho>0 )
        
          return 1;
        
        return 0;
            
    }
}
