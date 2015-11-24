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
public class NhaCCDTO {
    private String maNcc;
    private String tenNcc;
    private String soDt;
    private String diaChi;
    
    public NhaCCDTO() {
    }

    
    
    public NhaCCDTO(String maNcc, String tenNcc, String diaChi, String soDt) {
        this.maNcc = maNcc;
        this.tenNcc = tenNcc;
        this.soDt = soDt;
        this.diaChi = diaChi;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }

    public String getSoDt() {
        return soDt;
    }

    public void setSoDt(String soDt) {
        this.soDt = soDt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    

}
