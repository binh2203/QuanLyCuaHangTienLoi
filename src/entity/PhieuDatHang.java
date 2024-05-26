package entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhieuDatHang {
	private String maPDH;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private LocalDate ngayLapPDH;
	private LocalDate ngayLayHang;
	private boolean trangThai;
	private Double thanhTien;
	public Double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}
	private Double tienKhachDua;
	public String getMaPDH() {
		return maPDH;
	}
	public void setMaPDH(String maPDH) {
		this.maPDH = maPDH;
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
	public LocalDate getNgayLapPDH() {
		return ngayLapPDH;
	}
	public void setNgayLapPDH(LocalDate ngayLapPDH) {
		this.ngayLapPDH = ngayLapPDH;
	}
	public LocalDate getNgayLayHang() {
		return ngayLayHang;
	}
	public void setNgayLayHang(LocalDate ngayLayHang) {
		this.ngayLayHang = ngayLayHang;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public Double getTienKhachDua() {
		return tienKhachDua;
	}
	public void setTienKhachDua(Double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}
	public PhieuDatHang(String maPDH, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapPDH,
			LocalDate ngayLayHang, boolean trangThai, Double tienKhachDua) {
		super();
		this.maPDH = maPDH;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLapPDH = ngayLapPDH;
		this.ngayLayHang = ngayLayHang;
		this.trangThai = trangThai;
		this.tienKhachDua = tienKhachDua;
	}
	public PhieuDatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPDH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		PhieuDatHang other = (PhieuDatHang) obj;
		return Objects.equals(maPDH, other.maPDH);
	}


	public PhieuDatHang(String maPDH, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapPDH,
			LocalDate ngayLayHang, boolean trangThai, Double thanhTien, Double tienKhachDua) {
		super();
		this.maPDH = maPDH;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLapPDH = ngayLapPDH;
		this.ngayLayHang = ngayLayHang;
		this.trangThai = trangThai;
		this.thanhTien = thanhTien;
		this.tienKhachDua = tienKhachDua;
	}
	public PhieuDatHang(String maPDH, Double thanhTien) {
		super();
		this.maPDH = maPDH;
		this.thanhTien = thanhTien;
	}


}
