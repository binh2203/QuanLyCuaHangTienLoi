package entity;

import java.text.DecimalFormat;
import java.util.Objects;

public class SanPham {

	private String maSanPham;
	private String tenSanPham;
	private NhaCungCap nhaCungCap;
	private NhaSanXuat nhaSanXuat;
	private int soLuongTon;
	private String donVi;
	private Double giaNhap;
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public NhaSanXuat getNhaSanXuat() {
		return nhaSanXuat;
	}
	public void setNhaSanXuat(NhaSanXuat nhaSanXuat) {
		this.nhaSanXuat = nhaSanXuat;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public Double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(Double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public Double getGiaBan() {
		DecimalFormat decimalFormat = new DecimalFormat("#");
	    String giaBan = decimalFormat.format(giaNhap*1.15);
		return Double.parseDouble(giaBan);
	}
	public SanPham(String maSanPham, String tenSanPham, NhaCungCap nhaCungCap, NhaSanXuat nhaSanXuat, int soLuongTon,
			String donVi, Double giaNhap) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.nhaCungCap = nhaCungCap;
		this.nhaSanXuat = nhaSanXuat;
		this.soLuongTon = soLuongTon;
		this.donVi = donVi;
		this.giaNhap = giaNhap;
	}
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}

	public SanPham(String maSanPham, NhaCungCap nhaCungCap) {
		super();
		this.maSanPham = maSanPham;
		this.nhaCungCap = nhaCungCap;
	}
	public SanPham(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}
	public SanPham(String maSanPham, String tenSanPham) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
	}



}
