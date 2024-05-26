package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DBConnection;
import entity.KhachHang;

public class KhachHangDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private ArrayList<KhachHang> khachhang = new ArrayList<>();

	public KhachHangDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}
    public KhachHang timKhachHangTheoSDT(String sDT) throws SQLException {
    	KhachHang kh = new KhachHang();
    	String query = "Select * from KhachHang where phone LIKE CONCAT('%', ?, '%')";
    	PreparedStatement stmt = con.prepareCall(query);
    	stmt.setString(1, sDT);
    	ResultSet rs = stmt.executeQuery();
    	while (rs.next()) {
                kh = new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getString("phone"));
    	}
    	return kh;
    }

    public KhachHang timKhachHangTheoMa(String maKH) throws SQLException {
    	KhachHang kh = new KhachHang();
    	String query = "Select * from KhachHang where maKH = ?";
    	ps = con.prepareStatement(query);
    	ps.setString(1, maKH);
    	rs = ps.executeQuery();
    	while (rs.next()) {
                kh = new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getBoolean("gioiTinh"),
    					rs.getDate("ngaySinh").toLocalDate(),rs.getString("phone"), rs.getString("diaChi"), rs.getString("email"));
                return kh;
    	}
    	return null;
    }
	//// ==============================================

	public boolean themKhachHang(KhachHang kh) {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("insert into KhachHang values(?,?,?,?,?,?,?)");
			statement.setString(1, kh.getMaKH());
			statement.setString(2, kh.getHoTenKH());
			statement.setBoolean(3, kh.isGioiTinh());
			statement.setDate(4, java.sql.Date.valueOf(kh.getNgaySinh()));
			statement.setString(5, kh.getSDT());
			statement.setString(6, kh.getDiaChi());
			statement.setString(7, kh.getEmail());
			statement.executeUpdate();
			con.close();
			statement.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public List<KhachHang> getKhachHang() {

		List<KhachHang> list = new ArrayList<>();
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("select * from KhachHang");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getBoolean("gioiTinh"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"),
						rs.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<KhachHang> phanTrangKhachHang(int fn, int ln) {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		List<KhachHang> list = new ArrayList<>();
		PreparedStatement statement = null;

		String sql = "select *from(select ROW_NUMBER() over (order by maKH) as STT,maKH,tenKH,gioiTinh,ngaySinh,phone,diaChi,email from KhachHang) as PhanTrang where PhanTrang.STT Between ? and ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, fn);
			statement.setInt(2, ln);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getBoolean("gioiTinh"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"),
						rs.getString("email")));

			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int soLuongKH() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		int dem = 0;
		try {
			PreparedStatement statement = con.prepareStatement("select count(maKH) as Dem from KhachHang");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				dem = rs.getInt("Dem");
			}
		} catch (SQLException e) {

		}
		return dem;
	}

	public boolean XoaKH(String ma) {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("delete KhachHang where maKH=?");
			statement.setString(1, ma);
			statement.executeUpdate();
			con.close();
			statement.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean updateKhachHang(KhachHang kh) {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		try {
			PreparedStatement statement = con
					.prepareStatement("update KhachHang set tenKH=?, gioiTinh=?, ngaySinh=?, phone=?, diaChi=?, email=? where maKH=?");
			statement.setString(1, kh.getHoTenKH());
			statement.setBoolean(2, kh.isGioiTinh());
			statement.setDate(3, java.sql.Date.valueOf(kh.getNgaySinh()));
			statement.setString(4, kh.getSDT());
			statement.setString(5, kh.getDiaChi());
			statement.setString(6, kh.getEmail());
			statement.setString(7, kh.getMaKH());

			statement.executeUpdate();
			con.close();
			statement.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	// select *from KhachHang join HoaDon on
	// KhachHang.maKhachHang=HoaDon.maKhachHang where sDT='a'
//	public KhachHang timKhachHangTheoSDT(String sdt) {
//		KhachHang kh = null;
//		DBConnection connection = DBConnection.getInstance();
//		con = connection.getConnection();
//		try {
//			PreparedStatement statement = con.prepareStatement("select * from KhachHang where phone=?");
//			statement.setString(1, sdt);
//			ResultSet rs = statement.executeQuery();
//			rs.next();
//			kh = new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getBoolean("gioiTinh"),
//					rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"),
//					rs.getString("email"));
//			con.close();
//		} catch (Exception e) {
//
//		}
//		return kh;
//	}

	public List<KhachHang> timKHTheoTen(String ten) {
		List<KhachHang> list = new ArrayList<>();
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		try {
			String sql = "select * from KhachHang where tenKH like N'%" + ten + "%'";

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getBoolean("gioiTinh"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"),
						rs.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<KhachHang> timKHTheoSDT(String sdt) {
		List<KhachHang> list = new ArrayList<>();
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		try {
			String sql = "select * from KhachHang where phone like N'%" + sdt + "%'";

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getBoolean("gioiTinh"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"),
						rs.getString("email")));
			}
		} catch (SQLException e) {

		}
		return list;
	}

	public List<KhachHang> timKHTheoMa(String ma) {
		List<KhachHang> list = new ArrayList<>();
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		try {
			String sql = "select * from KhachHang where maKH like N'%" + ma + "%'";

			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getBoolean("gioiTinh"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"), rs.getString("diaChi"),
						rs.getString("email")));
			}
		} catch (SQLException e) {

		}
		return list;
	}

}
