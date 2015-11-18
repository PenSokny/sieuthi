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
public class NhanVienDTO {

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    private String maNhanVien;
    private String tenNhanVien;
    private String dienThoai;
    private String moTa;
    
    public NhanVienDTO() {
    }
    
    public NhanVienDTO(String maNhanVien, String tenNhanVien, String dienThoai, String moTa) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.dienThoai = dienThoai;
        this.moTa = moTa;
    }
    
}
