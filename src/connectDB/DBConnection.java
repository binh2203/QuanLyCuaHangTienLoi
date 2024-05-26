package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection instance;
	private Connection cnt;

	private DBConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyCuaHangTienLoi;trustServerCertificate=true";
		String user = "sa";
		String pass = "123456";
		try {
			cnt = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
            // Kiểm tra trạng thái của kết nối và tạo một kết nối mới nếu cần
            if (cnt == null || cnt.isClosed()) {
                String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyCuaHangTienLoi;trustServerCertificate=true";
                String user = "sa";
                String pass = "123456";
                cnt = DriverManager.getConnection(url, user, pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return cnt;
	}


}