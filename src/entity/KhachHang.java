package entity;

import java.time.LocalDate;
import java.util.Objects;

public class KhachHang {
    private String maKH;
    private String hoTenKH;
    private boolean gioiTinh;
    private LocalDate ngaySinh;
    private String SDT;
    private String diaChi;
    private String email;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getHoTenKH() {
		return hoTenKH;
	}
	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public KhachHang(String maKH, String hoTenKH, boolean gioiTinh, LocalDate ngaySinh, String sDT, String diaChi,
			String email) {
		super();
		this.maKH = maKH;
		this.hoTenKH = hoTenKH;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		SDT = sDT;
		this.diaChi = diaChi;
		this.email = email;
	}


	public KhachHang(String maKH, String hoTenKH, String sDT) {
		super();
		this.maKH = maKH;
		this.hoTenKH = hoTenKH;
		SDT = sDT;
	}
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}
	

}
