/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ERROR 405
 */
public class HangNhapDTO {
    private String maDonHang;
    private String maHang;

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
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
    private double soLuong;

    public HangNhapDTO() {
    }

    
    
    public HangNhapDTO(String maDonHang, String maHang, double soLuong) {
        this.maDonHang = maDonHang;
        this.maHang = maHang;
        this.soLuong = soLuong;
    }
    
}
