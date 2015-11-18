-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema quanlysieuthi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quanlysieuthi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quanlysieuthi` DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci ;
USE `quanlysieuthi` ;

-- -----------------------------------------------------
-- Table `quanlysieuthi`.`NhanVien`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`NhanVien` (
  `maNhanVien` VARCHAR(10) NOT NULL COMMENT '',
  `tenNhanVien` VARCHAR(30) NULL COMMENT '',
  `dienThoaiLH` VARCHAR(15) NULL COMMENT '',
  `moTa` VARCHAR(100) NULL COMMENT '',
  PRIMARY KEY (`maNhanVien`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`NhaCungCap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`NhaCungCap` (
  `maNCC` VARCHAR(10) NOT NULL COMMENT '',
  `tenNCC` VARCHAR(100) NULL COMMENT '',
  `diaChi` VARCHAR(200) NULL COMMENT '',
  `dienThoai` VARCHAR(10) NULL COMMENT '',
  PRIMARY KEY (`maNCC`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`DonHang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`DonHang` (
  `maDonHang` VARCHAR(10) NOT NULL COMMENT '',
  `ngayDatHang` DATETIME NULL COMMENT '',
  `maNhanVien` VARCHAR(10) NULL COMMENT '',
  `maNCC` VARCHAR(10) NULL COMMENT '',
  `NhanVien_maNhanVien` VARCHAR(10) NOT NULL COMMENT '',
  `NhaCungCap_maNCC` VARCHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`maDonHang`)  COMMENT '',
  INDEX `fk_DonHang_NhanVien_idx` (`NhanVien_maNhanVien` ASC)  COMMENT '',
  INDEX `fk_DonHang_NhaCungCap1_idx` (`NhaCungCap_maNCC` ASC)  COMMENT '',
  CONSTRAINT `fk_DonHang_NhanVien`
    FOREIGN KEY (`NhanVien_maNhanVien`)
    REFERENCES `quanlysieuthi`.`NhanVien` (`maNhanVien`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DonHang_NhaCungCap1`
    FOREIGN KEY (`NhaCungCap_maNCC`)
    REFERENCES `quanlysieuthi`.`NhaCungCap` (`maNCC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`Hang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`Hang` (
  `maHang` VARCHAR(10) NOT NULL COMMENT '',
  `tenHang` VARCHAR(45) NOT NULL COMMENT '',
  `maNCC` VARCHAR(10) NOT NULL COMMENT '',
  `giaNhap` DOUBLE NOT NULL COMMENT '',
  `giaBan` DOUBLE NOT NULL COMMENT '',
  `donViTinh` VARCHAR(40) NOT NULL COMMENT '',
  `ghiChu` VARCHAR(45) NULL COMMENT '',
  `NhaCungCap_maNCC` VARCHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`maHang`)  COMMENT '',
  INDEX `fk_Hang_NhaCungCap1_idx` (`NhaCungCap_maNCC` ASC)  COMMENT '',
  CONSTRAINT `fk_Hang_NhaCungCap1`
    FOREIGN KEY (`NhaCungCap_maNCC`)
    REFERENCES `quanlysieuthi`.`NhaCungCap` (`maNCC`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`NhapHang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`NhapHang` (
  `maDonHang` VARCHAR(10) NOT NULL COMMENT '',
  `maHang` VARCHAR(10) NULL COMMENT '',
  `soLuong` DOUBLE NULL COMMENT '',
  `DonHang_maDonHang` VARCHAR(10) NOT NULL COMMENT '',
  `Hang_maHang` VARCHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`maDonHang`, `DonHang_maDonHang`, `Hang_maHang`)  COMMENT '',
  INDEX `fk_NhapHang_DonHang1_idx` (`DonHang_maDonHang` ASC)  COMMENT '',
  INDEX `fk_NhapHang_Hang1_idx` (`Hang_maHang` ASC)  COMMENT '',
  CONSTRAINT `fk_NhapHang_DonHang1`
    FOREIGN KEY (`DonHang_maDonHang`)
    REFERENCES `quanlysieuthi`.`DonHang` (`maDonHang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NhapHang_Hang1`
    FOREIGN KEY (`Hang_maHang`)
    REFERENCES `quanlysieuthi`.`Hang` (`maHang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`KhoSanPham`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`KhoSanPham` (
  `maHang` VARCHAR(10) NOT NULL COMMENT '',
  `soLuong` DOUBLE NULL COMMENT '',
  `Hang_maHang` VARCHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`maHang`, `Hang_maHang`)  COMMENT '',
  INDEX `fk_KhoSanPham_Hang1_idx` (`Hang_maHang` ASC)  COMMENT '',
  CONSTRAINT `fk_KhoSanPham_Hang1`
    FOREIGN KEY (`Hang_maHang`)
    REFERENCES `quanlysieuthi`.`Hang` (`maHang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`HoaDon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`HoaDon` (
  `soHoaDon` VARCHAR(10) NOT NULL COMMENT '',
  `ngayLap` DATETIME NULL COMMENT '',
  PRIMARY KEY (`soHoaDon`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`HangBan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`HangBan` (
  `soLuong` BIGINT(10) NOT NULL COMMENT '',
  `donGia` DECIMAL(10,0) NULL COMMENT '',
  `maHang` VARCHAR(10) NULL COMMENT '',
  `soHoaDon` VARCHAR(10) NULL COMMENT '',
  `HoaDon_soHoaDon` VARCHAR(10) NOT NULL COMMENT '',
  `Hang_maHang` VARCHAR(10) NOT NULL COMMENT '',
  PRIMARY KEY (`soLuong`, `HoaDon_soHoaDon`, `Hang_maHang`)  COMMENT '',
  INDEX `fk_HangBan_HoaDon1_idx` (`HoaDon_soHoaDon` ASC)  COMMENT '',
  INDEX `fk_HangBan_Hang1_idx` (`Hang_maHang` ASC)  COMMENT '',
  CONSTRAINT `fk_HangBan_HoaDon1`
    FOREIGN KEY (`HoaDon_soHoaDon`)
    REFERENCES `quanlysieuthi`.`HoaDon` (`soHoaDon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HangBan_Hang1`
    FOREIGN KEY (`Hang_maHang`)
    REFERENCES `quanlysieuthi`.`Hang` (`maHang`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`TaiKhoan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`TaiKhoan` (
  `userName` VARCHAR(100) NOT NULL COMMENT '',
  `password` VARCHAR(45) NULL COMMENT '',
  `role` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`userName`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `quanlysieuthi`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`user` (
  `username` VARCHAR(16) NOT NULL COMMENT '',
  `email` VARCHAR(255) NULL COMMENT '',
  `password` VARCHAR(32) NOT NULL COMMENT '',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '');

USE `quanlysieuthi` ;

-- -----------------------------------------------------
-- Placeholder table for view `quanlysieuthi`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quanlysieuthi`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `quanlysieuthi`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quanlysieuthi`.`view1`;
USE `quanlysieuthi`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
