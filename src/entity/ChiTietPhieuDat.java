package entity;

public class ChiTietPhieuDat {
	private PhieuDatHang phieuDat;
	private SanPham sanPham;
	private int soLuong;
	public PhieuDatHang getPhieuDat() {
		return phieuDat;
	}
	public void setPhieuDat(PhieuDatHang phieuDat) {
		this.phieuDat = phieuDat;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public ChiTietPhieuDat(PhieuDatHang phieuDat, SanPham sanPham, int soLuong) {
		super();
		this.phieuDat = phieuDat;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}
	public ChiTietPhieuDat() {
	}
	@Override
	public String toString() {
		return "ChiTietPhieuDat [phieuDat=" + phieuDat + ", sanPham=" + sanPham + ", soLuong=" + soLuong + "]";
	}

}
