/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Sokny Pen
 */
public class LoginDTO {
    private String username;
    private String password;
    private String role;
    
    public LoginDTO(){
        
    }
    public LoginDTO(String username,String pass,String role){
        this.username=username;
        this.password=pass;
        this.role=role;            
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
