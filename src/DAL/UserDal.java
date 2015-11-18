/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.LoginDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ERROR 405
 */
public class UserDal {
    
    DatabaseManager db=new DatabaseManager();
    ObservableList<LoginDTO> data=FXCollections.observableArrayList();
    ResultSet resultSet;
    
    public int themUser(LoginDTO loginDTO){
        String sqlCode="insert into taikhoan values('"+loginDTO.getUsername()+"','"+loginDTO.getPassword()+"','"+loginDTO.getRole()+"')";
        return db.executeData(sqlCode);
    }
    
    public int xoaUser(LoginDTO loginDTO){
        String sqlCode="delete from taikhoan where username='"+loginDTO.getUsername()+"'";
        return db.executeData(sqlCode);
    }
    
    public ObservableList<LoginDTO> loadData(){
        try{
            resultSet=db.loadData("select* from taikhoan");
            while(resultSet.next()){
                String username=resultSet.getString("username");
                String pass=resultSet.getString("password");
                String role=resultSet.getString("role");
                data.add(new LoginDTO(username,pass,role));
            }
            resultSet.close();
        }catch(SQLException ex){
            Logger.getLogger(UserDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
}
