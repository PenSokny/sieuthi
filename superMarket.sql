-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema superMarket
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema quanlysieuthi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `NhanVien`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NhanVien` (
  `maNhanVien` VARCHAR(15) NOT NULL COMMENT '',
  `tenNhanVien` VARCHAR(45) NOT NULL COMMENT '',
  `dienThoaiLH` CHAR(15) NULL COMMENT '',
  `moTa` VARCHAR(100) NULL COMMENT '',
  PRIMARY KEY (`maNhanVien`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NhaCungCap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NhaCungCap` (
  `maNCC` VARCHAR(15) NOT NULL COMMENT '',
  `tenNCC` VARCHAR(45) NOT NULL COMMENT '',
  `diaChi` VARCHAR(45) NULL COMMENT '',
  `dienThoia` INT NULL COMMENT '',
  PRIMARY KEY (`maNCC`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DonHang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DonHang` (
  `maDoHang` VARCHAR(15) NOT NULL COMMENT '',
  `ngayDatHang` DATETIME NOT NULL COMMENT '',
  `maNhanVien` VARCHAR(15) NOT NULL COMMENT '',
  `maNCC` VARCHAR(15) NOT NULL COMMENT '',
  `NhanVien_maNhanVien` VARCHAR(15) NOT NULL COMMENT '',
  `NhaCungCap_maNCC` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`maDoHang`)  COMMENT '',
  INDEX `fk_DonHang_NhanVien_idx` (`NhanVien_maNhanVien` ASC)  COMMENT '',
  INDEX `fk_DonHang_NhaCungCap1_idx` (`NhaCungCap_maNCC` ASC)  COMMENT '',
  CONSTRAINT `fk_DonHang_NhanVien`
    FOREIGN KEY (`NhanVien_maNhanVien`)
    REFERENCES `NhanVien` (`maNhanVien`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DonHang_NhaCungCap1`
    FOREIGN KEY (`NhaCungCap_maNCC`)
    REFERENCES `NhaCungCap` (`maNCC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hang` (
  `maHang` VARCHAR(15) NOT NULL COMMENT '',
  `tenHang` VARCHAR(45) NOT NULL COMMENT '',
  `maNCC` VARCHAR(15) NOT NULL COMMENT '',
  `doViTinh` VARCHAR(15) NOT NULL COMMENT '',
  `giaNhap` DOUBLE NOT NULL COMMENT '',
  `giaBan` DOUBLE NOT NULL COMMENT '',
  `ngayHSD` DATETIME NOT NULL COMMENT '',
  `ghiChu` VARCHAR(100) NULL COMMENT '',
  `NhaCungCap_maNCC` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`maHang`)  COMMENT '',
  INDEX `fk_Hang_NhaCungCap1_idx` (`NhaCungCap_maNCC` ASC)  COMMENT '',
  CONSTRAINT `fk_Hang_NhaCungCap1`
    FOREIGN KEY (`NhaCungCap_maNCC`)
    REFERENCES `NhaCungCap` (`maNCC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HangNhap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HangNhap` (
  `maDonHang` VARCHAR(15) NOT NULL COMMENT '',
  `maHang` VARCHAR(15) NOT NULL COMMENT '',
  `soLuong` DOUBLE NOT NULL COMMENT '',
  `DonHang_maDoHang` VARCHAR(15) NOT NULL COMMENT '',
  `Hang_maHang` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`maDonHang`, `maHang`, `DonHang_maDoHang`, `Hang_maHang`)  COMMENT '',
  INDEX `fk_HangNhap_DonHang1_idx` (`DonHang_maDoHang` ASC)  COMMENT '',
  INDEX `fk_HangNhap_Hang1_idx` (`Hang_maHang` ASC)  COMMENT '',
  CONSTRAINT `fk_HangNhap_DonHang1`
    FOREIGN KEY (`DonHang_maDoHang`)
    REFERENCES `DonHang` (`maDoHang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HangNhap_Hang1`
    FOREIGN KEY (`Hang_maHang`)
    REFERENCES `Hang` (`maHang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KhoSanPham`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KhoSanPham` (
  `maHang` VARCHAR(15) NOT NULL COMMENT '',
  `soLuong` DOUBLE NOT NULL COMMENT '',
  `Hang_maHang` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`maHang`, `Hang_maHang`)  COMMENT '',
  INDEX `fk_KhoSanPham_Hang1_idx` (`Hang_maHang` ASC)  COMMENT '',
  CONSTRAINT `fk_KhoSanPham_Hang1`
    FOREIGN KEY (`Hang_maHang`)
    REFERENCES `Hang` (`maHang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HoaDon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HoaDon` (
  `soHoaDon` VARCHAR(15) NOT NULL COMMENT '',
  `ngayLap` DATETIME NOT NULL COMMENT '',
  PRIMARY KEY (`soHoaDon`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HangBan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HangBan` (
  `soLuong` DOUBLE NOT NULL COMMENT '',
  `donGia` DECIMAL(10,0) NOT NULL COMMENT '',
  `maHang` VARCHAR(15) NOT NULL COMMENT '',
  `soHoaDon` VARCHAR(15) NOT NULL COMMENT '',
  `Hang_maHang` VARCHAR(15) NOT NULL COMMENT '',
  `HoaDon_soHoaDon` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`maHang`, `soHoaDon`, `Hang_maHang`, `HoaDon_soHoaDon`)  COMMENT '',
  INDEX `fk_HangBan_Hang1_idx` (`Hang_maHang` ASC)  COMMENT '',
  INDEX `fk_HangBan_HoaDon1_idx` (`HoaDon_soHoaDon` ASC)  COMMENT '',
  CONSTRAINT `fk_HangBan_Hang1`
    FOREIGN KEY (`Hang_maHang`)
    REFERENCES `Hang` (`maHang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HangBan_HoaDon1`
    FOREIGN KEY (`HoaDon_soHoaDon`)
    REFERENCES `HoaDon` (`soHoaDon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taiKhoan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taiKhoan` (
  `userName` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(25) NOT NULL COMMENT '',
  `role` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`userName`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NhanVien`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NhanVien` (
  `maNhanVien` CHAR(10) NOT NULL COMMENT '',
  `tenNhanVien` VARCHAR(25) NOT NULL COMMENT '',
  `dienThoaiLH` CHAR(15) NULL COMMENT '',
  `moTa` VARCHAR(100) NULL COMMENT '',
  PRIMARY KEY (`maNhanVien`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `NhaCungCap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `NhaCungCap` (
  `maNCC` CHAR(10) NOT NULL COMMENT '',
  `tenNCC` VARCHAR(45) NOT NULL COMMENT '',
  `diaChi` VARCHAR(100) NULL COMMENT '',
  `dienThoai` CHAR(15) NULL COMMENT '',
  PRIMARY KEY (`maNCC`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Hang` (
  `maHang` CHAR(10) NOT NULL COMMENT '',
  `tenHang` VARCHAR(45) NOT NULL COMMENT '',
  `donViTinh` VARCHAR(15) NOT NULL COMMENT '',
  `ghiChu` VARCHAR(100) NULL COMMENT '',
  `maNCC` CHAR(10) NOT NULL COMMENT '',
  `giaNhap` DOUBLE NOT NULL COMMENT '',
  `giaBan` DOUBLE NOT NULL COMMENT '',
  `ngayHSD` DATETIME NOT NULL COMMENT '',
  PRIMARY KEY (`maHang`)  COMMENT '',
  INDEX `fk_Hang_NhaCungCap1_idx` (`giaNhap` ASC)  COMMENT '',
  CONSTRAINT `fk_Hang_NhaCungCap1`
    FOREIGN KEY (`giaNhap`)
    REFERENCES `NhaCungCap` (`maNCC`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DonHang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DonHang` (
  `maDonHang` CHAR(10) NOT NULL COMMENT '',
  `ngayDatHang` DATE NOT NULL COMMENT '',
  `maNhanVien` CHAR(10) NOT NULL COMMENT '',
  `maNCC` CHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`maDonHang`)  COMMENT '',
  INDEX `fk_DonHang_NhanVien1_idx` (`maNhanVien` ASC)  COMMENT '',
  INDEX `fk_DonHang_NhaCungCap1_idx` (`maNCC` ASC)  COMMENT '',
  CONSTRAINT `fk_DonHang_NhanVien1`
    FOREIGN KEY (`maNhanVien`)
    REFERENCES `NhanVien` (`maNhanVien`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_DonHang_NhaCungCap1`
    FOREIGN KEY (`maNCC`)
    REFERENCES `NhaCungCap` (`maNCC`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HangNhap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HangNhap` (
  `maDonHang` CHAR(10) NOT NULL COMMENT '',
  `maHang` CHAR(10) NOT NULL COMMENT '',
  `soLuong` DOUBLE NOT NULL COMMENT '',
  INDEX `fk_HangNhap_DonHang1_idx` (`maDonHang` ASC)  COMMENT '',
  INDEX `fk_HangNhap_Hang1_idx` (`maHang` ASC)  COMMENT '',
  PRIMARY KEY (`maDonHang`, `maHang`)  COMMENT '',
  CONSTRAINT `fk_HangNhap_DonHang1`
    FOREIGN KEY (`maDonHang`)
    REFERENCES `DonHang` (`maDonHang`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_HangNhap_Hang1`
    FOREIGN KEY (`maHang`)
    REFERENCES `Hang` (`maHang`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HoaDon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HoaDon` (
  `soHoaDon` CHAR(10) NOT NULL COMMENT '',
  `ngayLap` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`soHoaDon`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HangBan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HangBan` (
  `soLuong` BIGINT(10) NOT NULL COMMENT '',
  `donGia` DECIMAL(10,0) NOT NULL COMMENT '',
  `maHang` CHAR(10) NOT NULL COMMENT '',
  `soHoaDon` CHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`maHang`, `soHoaDon`)  COMMENT '',
  INDEX `fk_HangBan_Hang1_idx` (`maHang` ASC)  COMMENT '',
  INDEX `fk_HangBan_HoaDon1_idx` (`soHoaDon` ASC)  COMMENT '',
  CONSTRAINT `fk_HangBan_Hang1`
    FOREIGN KEY (`maHang`)
    REFERENCES `Hang` (`maHang`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_HangBan_HoaDon1`
    FOREIGN KEY (`soHoaDon`)
    REFERENCES `HoaDon` (`soHoaDon`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TaiKhoan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TaiKhoan` (
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `role` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KhoSanPham`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KhoSanPham` (
  `maHang` CHAR(10) NULL COMMENT '',
  `soLuong` DOUBLE NOT NULL COMMENT '',
  PRIMARY KEY (`maHang`)  COMMENT '',
  INDEX `fk_KhoSamPham_Hang1_idx` (`maHang` ASC)  COMMENT '',
  CONSTRAINT `fk_KhoSamPham_Hang1`
    FOREIGN KEY (`maHang`)
    REFERENCES `Hang` (`maHang`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
