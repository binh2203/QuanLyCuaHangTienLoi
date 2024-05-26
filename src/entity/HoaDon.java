/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private String maHD;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private LocalDate ngayLapHD;
    private Double tienKhachDua;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	public Double getTienKhachDua() {
		return tienKhachDua;
	}
	public void setTienKhachDua(Double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}
	public HoaDon(String maHD, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapHD, Double tienKhachDua) {
		super();
		this.maHD = maHD;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLapHD = ngayLapHD;
		this.tienKhachDua = tienKhachDua;
	}
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}





}
