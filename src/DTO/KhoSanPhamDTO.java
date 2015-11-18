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
public class KhoSanPhamDTO {
    
    private String maHang;
    private double soLuong;
    
    public KhoSanPhamDTO() {
    }

    
    
    public KhoSanPhamDTO(String maHang, double soLuong) {
        this.maHang = maHang;
        this.soLuong = soLuong;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }
    

    
}
