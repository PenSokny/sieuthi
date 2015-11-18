/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.ResultSet;
import DTO.LoginDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ERROR 405
 */
public class LoginDAL {
    
    DatabaseManager db=new DatabaseManager();
    ResultSet resultSet;
    LoginDTO loginDTO=new LoginDTO();
    
    public String loadRole(ResultSet resultSet,LoginDTO loginDTO){
        String role="";
        try{
                while(resultSet.next()){
                loginDTO.setRole(resultSet.getString(role));
                role=loginDTO.getRole();
                JOptionPane.showMessageDialog(null, role);
            }
                resultSet.close();
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return role;
    }
   
    public ResultSet setRole(LoginDTO loginDTO){
        String sqlCode="select role from taikhoan where username='"+loginDTO.getUsername()+"' AND password='"+loginDTO.getPassword()+"'";
        JOptionPane.showMessageDialog(null, loginDTO.getUsername());
        resultSet=db.loadData(sqlCode);
        return resultSet;
    }
    
    public int kiemTraLogin(LoginDTO loginDTO){
        try{
           String sqlCode="select * from taikhoan where username='"+loginDTO.getUsername()+"' AND password='"+loginDTO.getPassword()+"'";
           resultSet=db.loadData(sqlCode);
           if(resultSet.next())
               return 1;
        }catch(SQLException ex){
            Logger.getLogger(LoginDAL.class.getName()).log(Level.SEVERE, null,ex);
        }
     return 0;

    }
}