package entity;


import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoTenNV;
	private boolean gioiTinh;
	private LocalDate ngaySinhNV;
	private String soDienThoaiNV;
	private String diaChiNV;
	private String emailNV;
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoTenNV() {
		return hoTenNV;
	}
	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public LocalDate getNgaySinhNV() {
		return ngaySinhNV;
	}
	public void setNgaySinhNV(LocalDate ngaySinhNV) {
		this.ngaySinhNV = ngaySinhNV;
	}
	public String getSoDienThoaiNV() {
		return soDienThoaiNV;
	}
	public void setSoDienThoaiNV(String soDienThoaiNV) {
		this.soDienThoaiNV = soDienThoaiNV;
	}
	public String getDiaChiNV() {
		return diaChiNV;
	}
	public void setDiaChiNV(String diaChiNV) {
		this.diaChiNV = diaChiNV;
	}
	public String getEmailNV() {
		return emailNV;
	}
	public void setEmailNV(String emailNV) {
		this.emailNV = emailNV;
	}
	public NhanVien(String maNV, String hoTenNV, boolean gioiTinh, LocalDate ngaySinhNV, String soDienThoaiNV,
			String diaChiNV, String emailNV) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.gioiTinh = gioiTinh;
		this.ngaySinhNV = ngaySinhNV;
		this.soDienThoaiNV = soDienThoaiNV;
		this.diaChiNV = diaChiNV;
		this.emailNV = emailNV;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	public NhanVien(String maNV, String hoTenNV) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
	}

}
