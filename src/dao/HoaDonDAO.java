	package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import connectDB.DBConnection;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatHang;

public class HoaDonDAO {
	ArrayList<HoaDon> listHD;
	
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;


	public HoaDonDAO() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}
	
	public List<PhieuDatHang> getDSPhieuDatHang() throws SQLException {
		Connection con = DBConnection.getInstance().getConnection();
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
	
	
	public ArrayList<HoaDon> getListHD() {
		return listHD;
	}

	public List<HoaDon> docTuBang() throws SQLException{
		Connection con = DBConnection.getInstance().getConnection();
		List<HoaDon> list  = new ArrayList<HoaDon>();
		String query = "select * from HoaDon join KhachHang on HoaDon.maKH = KhachHang.maKH join NhanVien on HoaDon.maNV = NhanVien.maNV";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new HoaDon(rs.getString("maHD"), new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"), 
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"), 
					rs.getString("email")), new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), 
					rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), 
					rs.getString("diaChi"), rs.getString("email")), 
					rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getDouble("tienKhachDua")));
		}
		return list;
	}
	 public List<HoaDon> docTuBangTop10() throws SQLException {
		 Connection con = DBConnection.getInstance().getConnection();
	        List<HoaDon> list = new ArrayList<>();
	        String query = "select * from HoaDon join KhachHang on HoaDon.maKH = KhachHang.maKH join NhanVien on HoaDon.maNV = NhanVien.maNV";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new HoaDon(rs.getString("maHD"), new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"), 
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"), 
						rs.getString("email")), new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), 
						rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), 
						rs.getString("diaChi"), rs.getString("email")), 
						rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getDouble("tienKhachDua")));
			}
			return list.subList(0, Math.min(list.size(), 10));    
	    }
	 public HoaDon timHoaDonTheoMa(String maHoaDon) throws SQLException {
		 Connection con = DBConnection.getInstance().getConnection();
	        HoaDon hoaDon = null; // Khởi tạo biến hoaDon là null
	        String query = "SELECT * FROM HoaDon "
                    + "JOIN KhachHang ON HoaDon.maKH = KhachHang.maKH "
                    + "JOIN NhanVien ON HoaDon.maNV = NhanVien.maNV "
                    + "WHERE maHD = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maHoaDon);
			rs = ps.executeQuery();
			while (rs.next()) {
				hoaDon = new HoaDon(rs.getString("maHD"), new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"), 
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"), 
						rs.getString("email")), new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), 
						rs.getBoolean("gioiTinh"), rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), 
						rs.getString("diaChi"), rs.getString("email")), 
						rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getDouble("tienKhachDua"));
			}	
	        return hoaDon;
	    }
	 public boolean themHoaDon(HoaDon hd) {
		 Connection con = DBConnection.getInstance().getConnection();
			try {
				LocalDate ngayLapHD = hd.getNgayLapHD();
				java.sql.Date ngayLapHDSql = java.sql.Date.valueOf(ngayLapHD);
				PreparedStatement statement = con.prepareStatement("insert into HoaDon values(?,?,?,?,?,?,?)");
				statement.setString(1, hd.getMaHD());
				statement.setString(2, hd.getKhachHang().getMaKH());
				statement.setString(3, hd.getNhanVien().getMaNV());
				statement.setDate(4, ngayLapHDSql);
				statement.executeUpdate();
				con.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	 
}
