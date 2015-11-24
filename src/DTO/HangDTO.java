/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author ERROR 405
 */
public class HangDTO {
    private String tenHang;
    private String maHang;
    private String tenNCC;
    private String maNCC;
    private String ghiChu;
    private double giaNhap;
    private double giaBan;
    private String donViTinh;
    private String ngayHSD;
    
    public HangDTO() {
        
    }
/*
    public HangDTO(String maHang, String tenHang, String donViTinh, String ghiChu, String maNCC, double giaNhap, double giaBan, LocalDate ngayHSD) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.donViTinh = donViTinh;
        this.ghiChu = ghiChu;
        this.maNCC = maNCC;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.ngayHSD = ngayHSD;
    }
*/

    public HangDTO(String maHang, String tenHang, String maNcc, String donViTinh, double giaNhap, double giaBan, String ghiChu, String ngayHSD) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.donViTinh = donViTinh;
        this.ghiChu = ghiChu;
        this.maNCC = maNcc;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.ngayHSD = ngayHSD;
    }


    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public void setNgayHSD(String ngayHSD) {
        this.ngayHSD = ngayHSD;
    }
    
    public String getNgayHSD() {
        return ngayHSD;
    }
}
