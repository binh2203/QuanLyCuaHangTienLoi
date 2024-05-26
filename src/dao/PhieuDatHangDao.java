package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DBConnection;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatHang;

public class PhieuDatHangDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;


	public  PhieuDatHangDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public int getSLPDH() throws SQLException {
		String query = "SELECT COUNT(*) AS row_count FROM PhieuDatHang";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		int i = 0;
		while (rs.next()) {
			i = rs.getInt("row_count");
		}
		return i;
	}

	public List<PhieuDatHang> getDSPhieuDatHang() throws SQLException {
		List<PhieuDatHang> dsPDH = new ArrayList<>();
		String query = "Select * from PhieuDatHang";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			KhachHangDao kh = new KhachHangDao();
			NhanVienDao nv = new NhanVienDao();
			PhieuDatHang pdh = new PhieuDatHang();
			pdh.setMaPDH(rs.getString("maPDH"));
			pdh.setNhanVien(nv.timNhanVienTheoMa(rs.getString("maNV")));
			pdh.setKhachHang(kh.timKhachHangTheoMa(rs.getString("maKH")));
			pdh.setNgayLapPDH(rs.getDate("ngayLapPDH").toLocalDate());
			pdh.setNgayLayHang(rs.getDate("ngayLayHang").toLocalDate());
			pdh.setTrangThai(rs.getBoolean("trangThai"));
			dsPDH.add(pdh);
		}
		return dsPDH;
	}

	public List<PhieuDatHang> timDSPhieuDatHang(PhieuDatHang pdh) throws SQLException {
		List<PhieuDatHang> dsPDH = new ArrayList<>();
		int tt = 0;
		if(pdh.isTrangThai()) {
			tt = 1;
		}
		String query = "select maPDH, NhanVien.tenNV, KhachHang.tenKH, KhachHang.phone, ngayLapPDH, ngayLayHang, trangThai\r\n"
				+ "from PhieuDatHang  \r\n"
				+ "	join NhanVien on NhanVien.maNV = PhieuDatHang.maNV\r\n"
				+ "	join KhachHang on KhachHang.maKH = PhieuDatHang.maKH\r\n"
				+ "where  maPDH  like concat('%', ? ,'%') \r\n"
				+ "	and NhanVien.tenNV like concat('%', ? ,'%')\r\n"
				+ "	and KhachHang.tenKH like concat('%', ? ,'%')\r\n"
				+ "	and KhachHang.phone like concat('%', ?,'%')\r\n"
				+ "	and ngayLapPDH like concat('%', ? ,'%') \r\n"
				+ "	and ngayLayHang like concat('%', ? ,'%')\r\n"
				+ "	and trangThai = ?\r\n"
				+ "group by PhieuDatHang.maPDH, NhanVien.tenNV, KhachHang.tenKH, KhachHang.phone, ngayLapPDH, ngayLayHang, trangThai\r\n";
		ps = con.prepareStatement(query);
		ps.setString(1, pdh.getMaPDH());
		ps.setString(2, pdh.getNhanVien().getHoTenNV());
		ps.setString(3, pdh.getKhachHang().getHoTenKH());
		ps.setString(4, pdh.getKhachHang().getSDT());
		if(pdh.getNgayLapPDH() == null) {
			ps.setString(5, null);
		} else {
			int day = pdh.getNgayLapPDH().getDayOfMonth();
			String dayString;
			String monthString;
			if(day < 10) {
				dayString = "0" + day;
			} else {
				dayString = day + "";
			}
			int month = pdh.getNgayLapPDH().getMonthValue();
			if(month < 10) {
				monthString = "0" + month;
			} else {
				monthString = month + "";
			}
			int year = pdh.getNgayLapPDH().getYear();
			ps.setString(5, year + "-" + monthString + "-" + dayString);
		}
		if(pdh.getNgayLayHang() == null) {
			ps.setString(6, null);
		} else {
			int day = pdh.getNgayLayHang().getDayOfMonth();
			String dayString;
			String monthString;
			if(day < 10) {
				dayString = "0" + day;
			} else {
				dayString = day + "";
			}
			int month = pdh.getNgayLayHang().getMonthValue();
			if(month < 10) {
				monthString = "0" + month;
			} else {
				monthString = month + "";
			}
			int year = pdh.getNgayLayHang().getYear();
			ps.setString(6, year + "-" + monthString + "-" + dayString);
		}
		ps.setString(7, tt + "");
		rs = ps.executeQuery();
		while (rs.next()) {
			PhieuDatHang pdh1 = new PhieuDatHang();
			pdh1.setMaPDH(rs.getString("maPDH"));
			pdh1.setNhanVien(new NhanVien(null, rs.getString("tenNV")));
			pdh1.setKhachHang(new KhachHang(null, rs.getString("tenKH"), rs.getString("phone")));
			pdh1.setNgayLapPDH(rs.getDate("ngayLapPDH").toLocalDate());
			pdh1.setNgayLayHang(rs.getDate("ngayLayHang").toLocalDate());
			pdh1.setTrangThai(rs.getBoolean("trangThai"));
			dsPDH.add(pdh1);
		}
		return dsPDH;
	}
	public int TaoPDH(PhieuDatHang pdh) throws SQLException {
	    int trangThai = pdh.isTrangThai() ? 1 : 0;
	    String maKH = pdh.getKhachHang() != null ? pdh.getKhachHang().getMaKH() : null;

	    String query = "INSERT INTO PhieuDatHang (maPDH, maNV, maKH, ngayLapPDH, ngayLayHang, trangThai, thanhTien,tienKhachDua) "
	                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    ps = con.prepareStatement(query);
	    ps.setString(1, pdh.getMaPDH());
	    ps.setString(2, pdh.getNhanVien().getMaNV());
	    ps.setString(3, maKH);
	    ps.setDate(4, java.sql.Date.valueOf(pdh.getNgayLapPDH()));
	    ps.setDate(5, java.sql.Date.valueOf(pdh.getNgayLayHang()));
	    ps.setInt(6, trangThai);
	    ps.setDouble(7, pdh.getThanhTien());
	    ps.setDouble(8, pdh.getTienKhachDua());
	    return ps.executeUpdate();
	}



	public double GetTongTien(String maPDH) throws SQLException {
		String query = "SELECT * FROM PhieuDatHang WHERE maPDH = '"+ maPDH +"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next()) {
			return rs.getDouble("thanhTien");
		}
		return -1;
	}
	public int ThanhToanPDH(String maPDH, Double tienKhachDua) throws SQLException {
		String query = "UPDATE PHIEUDATHANG\r\n"
				+ "SET tienKhachDua = "+tienKhachDua+", trangThai = 1\r\n"
				+ "WHERE maPDH = '"+maPDH+"'";
		ps = con.prepareStatement(query);
		return ps.executeUpdate();
	}
}
