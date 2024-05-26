package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.DBConnection;
import entity.ChiTietPhieuDat;


public class ChiTietPhieuDatDao {
	private Connection con;
	private PreparedStatement ps = null;



	public  ChiTietPhieuDatDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}
	public int TaoCTPDH(ChiTietPhieuDat ctpdh) throws SQLException {
		String query = "INSERT INTO CT_PhieuDatHang\r\n"
				+ "VALUES ('" + ctpdh.getPhieuDat().getMaPDH()
				+ "','"+ ctpdh.getSanPham().getMaSanPham() +"', +"+ ctpdh.getSoLuong() +")";
		ps = con.prepareStatement(query);
		return ps.executeUpdate();
	}

}
