/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HangDTO;
import DTO.NhaCCDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import DTO.KhoSanPhamDTO;
import java.time.LocalDate;

/**
 *
 * @author Sokny Pen
 */
public class TTHangDal {
    
    HangDTO hangDto=new HangDTO();
    DatabaseManager db =new DatabaseManager();
    ResultSet resultSet;
    KhoSanPhamDTO khoSPDto=new KhoSanPhamDTO();
    
    private ObservableList<HangDTO> data=FXCollections.observableArrayList();
    private ObservableList<String> dataMaNcc=FXCollections.observableArrayList();
    private ObservableList<String> dataTenNcc=FXCollections.observableArrayList();
    
    public ObservableList<String> loadDataMaNcc(){
        try{
            resultSet=db.loadData("SELECT maNCC FROM nhacungcap");
            while(resultSet.next()){
                dataMaNcc.add(resultSet.getString("maNCC"));
            }
            resultSet.close();
            
        }catch(SQLException ex){
            Logger.getLogger(TTHangDal.class.getName()).log(Level.SEVERE, null,ex);
        }
        return dataMaNcc;
    }
    
    public String loadDataTenNcc(NhaCCDTO nCCDto){
        
        String tenNCC=null;
        try{
            if(!nCCDto.getMaNcc().isEmpty()){
                String sqlCode="select maNCC from nhacungcap where maNCC='"+nCCDto.getMaNcc()+"'";
                resultSet=db.loadData(sqlCode);
                if(resultSet.next()){
                    tenNCC=resultSet.getString("maNCC");
                }
            }
            resultSet.close();
            
        }catch(SQLException ex){
            Logger.getLogger(TTHangDal.class.getName()).log(Level.SEVERE, null,ex);
        }
        return tenNCC;
    }
    
    public ObservableList<HangDTO> loadData(ResultSet resultSet){
        try{
            String maHang;
            String tenHang;
            //String tenNcc;
            String maNCC;
            String ghiChu;
            String donViTinh;
            double giaBan;
            double giaNhap;
            String ngayHSD;
            
            while(resultSet.next()){
                maHang=resultSet.getString("maHang");
                tenHang=resultSet.getString("tenHang");
                maNCC=resultSet.getString("maNCC");
                donViTinh=resultSet.getString("donViTinh");
                giaNhap=Double.parseDouble(resultSet.getString("giaNhap"));
                giaBan=Double.parseDouble(resultSet.getString("giaBan"));
                ghiChu=resultSet.getString("ghiChu");
                ngayHSD = resultSet.getString("ngayHSD");
                
               data.add(new HangDTO(maHang,tenHang, maNCC, donViTinh, giaNhap, giaBan, ghiChu, ngayHSD));
    
            }
            resultSet.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return data;
    }
    
    public ResultSet getHang(){
        resultSet=db.loadData("select* from hang");
        return resultSet;
    }
    
    public ResultSet timHangTheoMa(HangDTO  hangDto)
    {
        resultSet=db.loadData("SELECT * FROM hang WHERE maHang='"+hangDto.getMaHang()+"'");
        return resultSet;
    }
    
    public ResultSet timHangTheoTen(HangDTO hangDto)
    {
        resultSet=db.loadData("SELECT * FROM hang WHERE tenHang like '%"+hangDto.getTenHang()+"%'");
        return resultSet;
    }
    
    public int saveKhoSanPham(KhoSanPhamDTO khoSPDto)
     {
      
        String sql="INSERT INTO khosanpham VALUES('"+khoSPDto.getMaHang()+"',"+khoSPDto.getSoLuong()+")";
        int result=db.executeData(sql);
        return result;
    }
    
    public int saveData(HangDTO hangDto)
    {
      
            String sql="INSERT INTO hang(maHang, tenHang, maNCC, donViTinh, giaNhap, giaBan, ghiChu, ngayHSD) VALUES("
                + "'"+ hangDto.getMaHang()+"',"
                + "'"+ hangDto.getTenHang()+"',"          
                + "'"+ hangDto.getMaNCC()+ "',"
                + "'"+hangDto.getDonViTinh() +"',"
                + hangDto.getGiaNhap() +","
                + hangDto.getGiaBan() + ","
                + "'"+ hangDto.getGhiChu() +  "',"
                + "'"+ hangDto.getNgayHSD() + "')";
        
        int result=db.executeData(sql);
        return result;
    }
    
     public int deleteData(HangDTO hangDto)
    {
      String sql="DELETE FROM hang WHERE maHang='"+hangDto.getMaHang()+"'";
      int result=db.executeData(sql);
      return result;
    }
    
   public int updateData(HangDTO hangDto, String ma)
   {
      String sql="UPDATE hang SET maHang = '" +hangDto.getMaHang() + "',tenHang='"+ hangDto.getTenHang() +
                                           "',maNCC ='"+hangDto.getMaNCC() +"',donViTinh='"+hangDto.getDonViTinh() + "',giaNhap ='" + hangDto.getGiaNhap()  + 
                                           "',giaBan ='" + hangDto.getGiaBan()  + "',ghiChu ='" + hangDto.getGhiChu()  +  "',ngayHSD ='" + hangDto.getNgayHSD()  + "' WHERE maHang=  '" + ma + "'" ;
      int result=db.executeData(sql);
      return result;
   }
            
}
