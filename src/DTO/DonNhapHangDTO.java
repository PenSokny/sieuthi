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
public class DonNhapHangDTO {
    private String maHoaDon;
    private String ngayNhapHang;
    private String tenHang;
    private double soLuong;
    private String donViTinh;
    private double giaNhap;
    private double thanhTien;
    
    private String maNcc;
    private String maNV;
    private String maHang;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayNhapHang() {
        return ngayNhapHang;
    }

    public void setNgayNhaptHang(String ngayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }


    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }
    
    
    

    public DonNhapHangDTO() {
    }

    public DonNhapHangDTO(String maHoaDon, String ngayNhapHang, String tenHang, double soLuong, String donViTinh, double giaNhap, double thanhTien, String maNcc, String maNV, String maHang) {
        this.maHoaDon = maHoaDon;
        this.ngayNhapHang = ngayNhapHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.giaNhap = giaNhap;
        this.thanhTien = thanhTien;
        this.maNcc = maNcc;
        this.maNV = maNV;
        this.maHang = maHang;
    }

     public DonNhapHangDTO(String maHoaDon, String tenHang, double soLuong, String donViTinh, double giaNhap, double thanhTien, String maNcc, String maNV, String maHang) {
        this.maHoaDon = maHoaDon;
     
        this.tenHang = tenHang;
       
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.giaNhap = giaNhap;
        this.thanhTien = thanhTien;
        this.maNcc = maNcc;
        this.maNV = maNV;
        this.maHang = maHang;
    }
    
}
