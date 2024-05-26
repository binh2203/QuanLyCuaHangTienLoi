package other;

import java.util.List;

import dao.KhachHangDao;
import dao.NhanVienDao;
//import dao.HoaDon_Dao;
//import dao.KhachHang_Dao;
import dao.SanPhamDao;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class RandomMa {
	/*
	public String taoMaHoaDon() {
		long ma = 10000000;
		HoaDon_Dao hd = new HoaDon_Dao();
		List<HoaDon> list = hd.getHoaDon();
		for (int i = 0; i < list.size(); i++) {
			if (i == (list.size() - 1)) {
				String chuoi[] = list.get(i).getMaHoaDon().split("D");
				ma = Long.parseLong(chuoi[1]) + 1;
			}
		}
		return "HD" + ma;
	}*/

	public String taoMaSanPham() {
		long ma = 10000000;
		SanPhamDao sp = new SanPhamDao();
		List<SanPham> list = sp.getSP();
		for (int i = 0; i < list.size(); i++) {
			if (i == (list.size() - 1)) {
				String chuoi[] = list.get(i).getMaSanPham().trim().split("P");
				ma = Long.parseLong(chuoi[1]) + 1;
			}
		}
		return "SP" + ma;
	}

	public String taoMaKhachHang() {
		long ma = 10000000;
		KhachHangDao kh = new KhachHangDao();
		List<KhachHang> list = kh.getKhachHang();
		for (int i = 0; i < list.size(); i++) {
			if (i == (list.size() - 1)) {
				String chuoi[] = list.get(i).getMaKH().trim().split("H");
				ma = Long.parseLong(chuoi[1]) + 1;
			}
		}
		return "KH" + ma;
	}

	public String taoMaNhanVien() {

		long ma = 10000000;
		NhanVienDao nv = new NhanVienDao();
		List<NhanVien> list = nv.getNhanVien();
		for (int i = 0; i < list.size(); i++) {
			if (i == (list.size() - 1)) {
				String chuoi[] = list.get(i).getMaNV().trim().split("V");
				ma = Long.parseLong(chuoi[1]) + 1;
			}
		}
		return "NV" + ma;
	}
}
