/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.NhaCCDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sokny Pen
 */
public class NhaCCDal {
    
    NhaCCDTO nCCDto=new NhaCCDTO();
    DatabaseManager db=new DatabaseManager();
    ResultSet resultSet;
    
    private ObservableList<NhaCCDTO> data=FXCollections.observableArrayList();
    
    public ObservableList<NhaCCDTO> loadData(ResultSet resultSet){
        try{
            String maNcc;
            String tenNcc;
            String soDt;
            String diaChi;
            while(resultSet.next()){
                maNcc=resultSet.getString("maNCC");
                tenNcc=resultSet.getString("tenNCC"); 
                diaChi=resultSet.getString("diaChi");
                soDt=resultSet.getString("dienThoai");
                
                data.add(new NhaCCDTO(maNcc,tenNcc,diaChi,soDt));
            }
            resultSet.close(); 
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return data;                
    }
    
    public ResultSet getNcc(){
        resultSet=db.loadData("select * from nhacungcap");
        return resultSet;
        
    }
    
    public int saveData(NhaCCDTO nCCDto){
        String sqlCode="insert into nhacungcap values('"+nCCDto.getMaNcc()+"','"+nCCDto.getTenNcc()+"','"+nCCDto.getDiaChi()+"','"+nCCDto.getSoDt()+"')";
        int result=db.executeData(sqlCode);
        return result;
        
    }
    
    public int deleteData(NhaCCDTO nCCDto){
        String sqlCode="delete from nhacungcap where maNCC='"+nCCDto.getMaNcc()+"'";
        int result=db.executeData(sqlCode);
        return result;
    }
    
    public int updateData(NhaCCDTO nCCDto,String ma){
        String sqlCode="update nhacungcap set maNCC='"+nCCDto.getMaNcc()+"',tenNCC='"+nCCDto.getTenNcc()+"',diaChi='"+nCCDto.getDiaChi()+"',dienThoai='"+nCCDto.getSoDt()+"' where maNCC='"+ma+"'";
        int result=db.executeData(sqlCode);
        return result;
    }   
    
    public ResultSet timNccTheoMa(NhaCCDTO nccDto)
    {
        resultSet=db.loadData("SELECT * FROM nhacungcap WHERE maNCC='"+nccDto.getMaNcc()+"'");
        return resultSet;
    }
    
    public ResultSet timNccTheoTen(NhaCCDTO nccDto)
    {
        resultSet=db.loadData("SELECT * FROM nhacungcap WHERE tenNCC like '%"+nccDto.getTenNcc()+"%'");
        return resultSet;
    }
}
