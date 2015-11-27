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
public class TimHangDTO {
    private String maHang;
    private String tenHang;
    private String donViTinh;
    private String ncc;
    private double giaNhap;
    private double giaBan;
    
    
    public TimHangDTO() {
    }

    public TimHangDTO(String maHang, String tenHang, String donViTinh, String ncc, double giaNhap, double giaBan, String ngayHSD, double soLuong, String ghiChu) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.donViTinh = donViTinh;
        this.ncc = ncc;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.ngayHSD = ngayHSD;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getNcc() {
        return ncc;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getNgayHSD() {
        return ngayHSD;
    }

    public void setNgayHSD(String ngayHSD) {
        this.ngayHSD = ngayHSD;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    private String ngayHSD;
    private double soLuong;
    private String ghiChu;

    

    
}
