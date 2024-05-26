package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DBConnection;
import entity.NhaCungCap;

public class NhaCungCapDao {
	public List<NhaCungCap> getNhaCungCap() {
		List<NhaCungCap> list = new ArrayList<>();
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("select *from NhaCungCap");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				list.add(new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"), rs.getString("email"), rs.getString("phone")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
