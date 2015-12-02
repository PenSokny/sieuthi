/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.time.LocalDate;

/**
 *
 * @author Sokny Pen
 */
public class DonHangDTO {
    
    private String maDonHang;
    private String ngayNhapHang;
    private String maNhanVien;
    private String maNCC;
    
    public DonHangDTO() {
    }

    
    
    public DonHangDTO(String maDonHang, String ngayNhapHang, String maNv, String maNcc) {
        this.maDonHang = maDonHang;
        this.ngayNhapHang = ngayNhapHang;
        this.maNhanVien = maNv;
        this.maNCC = maNcc;
    }


    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getNgayNhapHang() {
        return ngayNhapHang;
    }

    public void setNgayNhapHang(String ngayNhapHang) {
        this.ngayNhapHang = ngayNhapHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    
}
