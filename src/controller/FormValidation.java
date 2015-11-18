/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.control.*;

/**
 *
 * @author ERROR 405
 */
public class FormValidation {
    
    public boolean textIsEmtpy(TextField textField,String message)
    {
        if(textField.getText().trim().isEmpty())
        {
            textField.clear();
            textField.setPromptText(message);
            textField.getStyleClass().add("error");
            return true;
           
        } 
           textField.getStyleClass().removeAll("error"); 
           textField.setPromptText("");
            return false; 
        
    }
    
    public boolean comboBoxIsEmtpy(ComboBox cb,String message)
    {
        if(cb.getSelectionModel().isEmpty())
        {
            
            cb.setPromptText(message);
            cb.getStyleClass().add("error");
            return true;
           
        } 
           cb.getStyleClass().removeAll("error"); 
           cb.setPromptText("");
            return false; 
        
    }
    
    

    public boolean textIsNumberic(TextField textField,String message)
    {
        boolean isNumberic=false;
        
        if(!textField.getText().trim().isEmpty())
        {
            
            if(textField.getText().trim().matches("^?\\d+(\\.\\d+)?$"))
            {
                textField.getStyleClass().removeAll("error"); 
                textField.setPromptText("");
                isNumberic=true;
            }
            
            else
            {
                textField.clear();
                textField.setPromptText(message);
                textField.getStyleClass().add("error");
                isNumberic=false;
            }
            
            
        }
        
        return isNumberic;
    }
    
    
    public boolean textIsPhoneNumber(TextField textField,String message)
    {
        boolean isPhoneNumber=false;
        
        if(!textField.getText().trim().isEmpty())
        {
            if(textField.getText().trim().matches("^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{3,5})$"))
            {
                textField.getStyleClass().removeAll("error"); 
                textField.setPromptText("");
                isPhoneNumber=true;
            }
             else
            {
                textField.clear();
                textField.setPromptText(message);
                textField.getStyleClass().add("error");
                isPhoneNumber=false;
            }
        }
        return isPhoneNumber;
    }
    
    public boolean textIsEmail(TextField textField,String message)
    {
        boolean isEmail=false;
        
        if(!textField.getText().trim().isEmpty())
        {
            if(textField.getText().trim().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$"))
            {
                textField.getStyleClass().removeAll("error"); 
                textField.setPromptText("");
                isEmail=true;
            }
             else
            {
                textField.clear();
                textField.setPromptText(message);
                textField.getStyleClass().add("error");
                isEmail=false;
            }
        }
        return isEmail;
    }
    
    
    public boolean textIsDate(TextField textField,String message)
    {
        boolean isDate=false;
        
        if(!textField.getText().trim().isEmpty())
        {
            if(textField.getText().trim().matches("(0?[1-9]|[12][0-9]|3[01])[-/](0?[1-9]|1[012])[-/]((19|20)\\d\\d)"))  //Date Format (dd-mm-yyyy) or (dd/mm/yyyy)
            {
                textField.getStyleClass().removeAll("error"); 
                textField.setPromptText("");
                isDate=true;
            }
             else
            {
                textField.clear();
                textField.setPromptText(message);
                textField.getStyleClass().add("error");
                isDate=false;
            }
        }
        return isDate;
    }
    
    
}