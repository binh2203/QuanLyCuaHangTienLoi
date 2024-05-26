package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DBConnection;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhaSanXuat;
import entity.NhanVien;
import entity.SanPham;


public class ChiTietHoaDonDAO {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	public ChiTietHoaDonDAO() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}
	
	public List<ChiTietHoaDon> getCTHDByID(String ma) throws SQLException{
		Connection con = DBConnection.getInstance().getConnection();
		List<ChiTietHoaDon> list = new ArrayList<ChiTietHoaDon>();
		String query = "select * from CT_HoaDon join SanPham on CT_HoaDon.maSP=SanPham.maSP join HoaDon on "
				+ "CT_HoaDon.maHD=HoaDon.maHD where HoaDon.maHD =?";
		ps = con.prepareStatement(query);
		ps.setString(1, ma);
		rs = ps.executeQuery();
		while(rs.next()) {
			list.add(new ChiTietHoaDon(new HoaDon(rs.getString("maHD"), 
					new NhanVien(), 
					new KhachHang(),
					rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getDouble("tienKhachDua")), 
					new SanPham(rs.getString("maSP"), rs.getString("tenSP"), 
					new NhaCungCap(), new NhaSanXuat(), rs.getInt("soLuongTon"), rs.getString("donVi"), 
					rs.getDouble("giaNhap")),rs.getInt("soLuong")));
		}
		return list;
	}
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?)");
			statement.setString(1, cthd.getSanPham().getMaSanPham());
			statement.setString(2, cthd.getHoaDon().getMaHD());
			statement.setInt(3, cthd.getSoLuong());
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