package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.DBConnection;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoanDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;


	public TaiKhoanDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public TaiKhoan timTaiKhoanTheoTenDangNhap(String userName) throws SQLException {
		String query = "Select * from TaiKhoan where tenDangNhap = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, userName);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNV"));
			TaiKhoan tk = new TaiKhoan(rs.getString("tenDangNhap"), rs.getString("matKhau"),
					nv, rs.getBoolean("chucVu"));
			return tk;
		}
		return null;
	}
}
