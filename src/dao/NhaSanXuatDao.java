package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DBConnection;
import entity.NhaSanXuat;

public class NhaSanXuatDao {
	public List<NhaSanXuat> getNhaSanXuat() {
		List<NhaSanXuat> list = new ArrayList<>();
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("select *from NhaSanXuat");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				list.add(new NhaSanXuat(rs.getString("maNSX"), rs.getString("tenNSX")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
