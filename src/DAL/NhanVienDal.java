/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhanVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sokny Pen
 */
public class NhanVienDal {
    
    NhanVienDTO nvDto=new NhanVienDTO();
    DatabaseManager db=new DatabaseManager();
    ResultSet resultSet;
    
    private ObservableList<NhanVienDTO> data=FXCollections.observableArrayList();
    
    public ObservableList<NhanVienDTO> loadData(ResultSet resultSet){
        
        try{
            String maNhanVien;
            String tenNhanVien;
            String soDienThoai;
            String motaNV;
            
            while(resultSet.next()){
                maNhanVien=resultSet.getString("maNhanVien");
                tenNhanVien=resultSet.getString("tenNhanVien");
                soDienThoai=resultSet.getString("dienThoaiLH");
                motaNV=resultSet.getString("moTa");
                data.add(new NhanVienDTO(maNhanVien,tenNhanVien,soDienThoai,motaNV));
            }
            resultSet.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return data;
    }
    
    public int saveData(NhanVienDTO nvDto){
        String sqlCode="insert into nhanvien values('"+nvDto.getMaNhanVien()+"','"+nvDto.getTenNhanVien()+"','"+nvDto.getDienThoai()+"','"+nvDto.getMoTa()+"') ";
        int result=db.executeData(sqlCode);
        
        return result;
    }
    
    public int updateData(NhanVienDTO nvDto,String ma){
        String sqlCode="update nhanvien set maNhanVien='"+nvDto.getMaNhanVien()+"',tenNhanVien='"+nvDto.getTenNhanVien()+"',dienThoaiLH='"+nvDto.getDienThoai()+"',moTa='"+nvDto.getMoTa()+"' where maNhanVien='"+ma+"'";
        int result=db.executeData(sqlCode);
        
        return result;
    }
    
    public int deleteData(NhanVienDTO nvDto){
        String sql="delete from nhanvien where maNhanVien='"+nvDto.getMaNhanVien()+"'";
        int result=db.executeData(sql);
        
        return result;
                
    }
    
    public ResultSet getNhanVien(){
        resultSet=db.loadData("select * from nhanvien");
        return resultSet;
    }
    
    
}
