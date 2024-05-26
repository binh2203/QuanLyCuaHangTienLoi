package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.DBConnection;
import entity.NhaCungCap;
import entity.NhaSanXuat;
import entity.SanPham;

public class SanPhamDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;


	public SanPhamDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}
	public List<SanPham> getDSSanPham() throws SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		List<SanPham> dssp = new ArrayList<>();
		String query = "Select * from SanPham";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			SanPham sp = new SanPham();
			sp.setMaSanPham(rs.getString("maSP"));
			sp.setTenSanPham(rs.getString("tenSP"));
			String maNCC = rs.getString("maNCC");
			sp.setNhaCungCap(new NhaCungCap(maNCC));
			sp.setNhaSanXuat(new NhaSanXuat(rs.getString("maNSX")));
			sp.setSoLuongTon(rs.getInt("soLuongTon"));
			sp.setDonVi(rs.getString("donVi"));
			sp.setGiaNhap(rs.getDouble("giaNhap"));
			
			
			dssp.add(sp);
		}
		return dssp;
	}
	public int soLuongSP() {
		// Kết nối database
		Connection con = DBConnection.getInstance().getConnection();
		int soluong = 0;
		try {
			PreparedStatement statement = con.prepareStatement("select count(maSP) as Dem from SanPham");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				soluong = rs.getInt("Dem");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soluong;
	}
	
	public List<SanPham> getSP() {
		List<SanPham> dsSP = new ArrayList<SanPham>();
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("select * from SanPham join NhaCungCap on SanPham.maNCC=NhaCungCap.maNCC");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				dsSP.add(new SanPham(rs.getString("maSP"), rs.getString("tenSP"), new NhaCungCap(rs.getString("maNCC")), 
						new NhaSanXuat(rs.getString("maNSX")), rs.getInt("soLuongTon"),	rs.getString("donVi"), rs.getDouble("giaNhap")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSP;
	}
	
	public List<SanPham> phanTrangSanPham(int fn, int ln) {
		Connection con = DBConnection.getInstance().getConnection();
		List<SanPham> dsSP = new ArrayList<SanPham>();
		PreparedStatement statement = null;

		String sql = "select *from(select ROW_NUMBER() over (order by maSP) as STT, ncc.maNCC, nsx.maNSX, sp.maSP,"
				+ " sp.tenSP, sp.soLuongTon, sp.donVi, sp.giaNhap, ncc.tenNCC, ncc.email, ncc.phone, nsx.tenNSX from SanPham sp "
				+ "join NhaCungCap ncc on sp.maNCC=ncc.maNCC join NhaSanXuat nsx on sp.maNSX = nsx.maNSX) as PhanTrang where PhanTrang.STT Between " + fn + " and "
				+ ln;
		try {
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				dsSP.add(new SanPham(rs.getString("maSP"), rs.getString("tenSP"), new NhaCungCap(rs.getString("tenNCC")), 
						new NhaSanXuat(rs.getString("tenNSX")), rs.getInt("soLuongTon"), rs.getString("donVi"), rs.getDouble("giaNhap")));

			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return dsSP;

	}

	public boolean ThemSP(SanPham sp) {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("insert into SanPham values(?,?,?,?,?,?,?)");
			statement.setString(1, sp.getMaSanPham());
			statement.setString(2, sp.getTenSanPham());
			statement.setString(3, sp.getNhaCungCap().getMaNhaCungCap());
			statement.setInt(4, sp.getSoLuongTon());
			statement.setString(5, sp.getDonVi());
			statement.setDouble(6, sp.getGiaNhap());
			statement.setString(7, sp.getNhaSanXuat().getMaNhaSX());
			statement.executeUpdate();
			con.close();
			statement.close();
			
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean XoaSP(String ma) {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("delete SanPham where maSP=?");
			statement.setString(1, ma);
			statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean updateSoLuong(int soLuong, String ma) {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("update SanPham set soLuongTon=? where maSP=?");
			statement.setInt(1, soLuong);
			statement.setString(2, ma);
			statement.executeUpdate();
			con.close();
			statement.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean updateSanPham(SanPham sp) {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = con.prepareStatement(
					"update SanPham set tenSP=?,maNCC=?,soLuongTon=?,donVi=?,giaNhap=?, maNSX=? where maSP=?");
			statement.setString(1, sp.getTenSanPham());
			statement.setString(2, sp.getNhaCungCap().getMaNhaCungCap());
			statement.setInt(3, sp.getSoLuongTon());
			statement.setString(4, sp.getDonVi());
			statement.setDouble(5, sp.getGiaNhap());
			statement.setString(6, sp.getNhaSanXuat().getMaNhaSX());
			statement.setString(7, sp.getMaSanPham());
			statement.executeUpdate();
			con.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // Kiểm tra chuỗi có chứa số nguyên hoặc số thực
    }
	
	public List<SanPham> timSanPham(String searchValue) {
		List<SanPham> dsSP = new ArrayList<>();
	    Connection con = DBConnection.getInstance().getConnection();
	    try {
	        String thuongHieusp = "or tenNCC like N'%" + searchValue + "%' ";
	        String donvisp = "or donVi like N'%" + searchValue + "%' ";
	        String tenNSXsp = "or tenNSX like N'%" + searchValue + "%'";
	        String sql = "select * from SanPham join NhaCungCap on SanPham.maNCC=NhaCungCap.maNCC " +
	        			"join NhaSanXuat on SanPham.maNSX = NhaSanXuat.maNSX "
	                     + "where tenSP like N'%" + searchValue + "%' "
	                     + donvisp + thuongHieusp + tenNSXsp;
	        
	        if(isNumeric(searchValue)) {
	        	sql += " or soLuongTon = " + Integer.parseInt(searchValue) + " or giaNhap = " + Double.parseDouble(searchValue);
	        }
	        
	        PreparedStatement statement = con.prepareStatement(sql); 

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				dsSP.add(new SanPham(rs.getString("maSP"), rs.getString("tenSP"), new NhaCungCap(rs.getString("maNCC")), 
						new NhaSanXuat(rs.getString("maNSX")), rs.getInt("soLuongTon"),	rs.getString("donVi"), rs.getDouble("giaNhap")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSP;
	}
	public SanPham timSanPhamTheoMa(String maSP) throws SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		SanPham sanPham = null;
	    String query = "select * from SanPham join NhaCungCap on SanPham.maNCC = NhaCungCap.maNCC where maSP = ?";
	    try (PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, maSP);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                sanPham = new SanPham(rs.getString("maSP"), rs.getString("tenSP"),
	                        new NhaCungCap(rs.getString("maNCC")), new NhaSanXuat(),
	                        rs.getInt("soLuongTon"), rs.getString("donVi"), rs.getDouble("giaNhap"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return sanPham;
	}
	public List<SanPham> docSanPhamGiaNhapTu0Den20000() {
	    List<SanPham> dsSanPham = new ArrayList<>();
	    Connection con = DBConnection.getInstance().getConnection();
	    try {
	        String sql = "SELECT * FROM SanPham WHERE giaNhap >= 0 AND giaNhap <= 20000";
	        PreparedStatement statement = con.prepareStatement(sql);
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            String maSP = rs.getString("maSP");
	            String tenSP = rs.getString("tenSP");
	            String maNCC = rs.getString("maNCC");
	            int soLuongTon = rs.getInt("soLuongTon");
	            String donVi = rs.getString("donVi");
	            double giaNhap = rs.getDouble("giaNhap");
	            String maNSX = rs.getString("maNSX");

	            // Tạo đối tượng sản phẩm và thêm vào danh sách
	            SanPham sanPham = new SanPham(maSP, tenSP, new NhaCungCap(maNCC), new NhaSanXuat(maNSX), soLuongTon, donVi, giaNhap);
	            dsSanPham.add(sanPham);
	        }
	        return dsSanPham.subList(0, Math.min(dsSanPham.size(), 10)); 
	        // Đóng kết nối và các tài nguyên
	      
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsSanPham;
	}
	public List<SanPham> docSanPhamGiaNhapTu20000Den70000() {
	    List<SanPham> dsSanPham2 = new ArrayList<>();
	    Connection con = DBConnection.getInstance().getConnection();
	    try {
	        String sql = "SELECT * FROM SanPham WHERE giaNhap >= 20000 AND giaNhap <= 70000";
	        PreparedStatement statement = con.prepareStatement(sql);
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            String maSP = rs.getString("maSP");
	            String tenSP = rs.getString("tenSP");
	            String maNCC = rs.getString("maNCC");
	            int soLuongTon = rs.getInt("soLuongTon");
	            String donVi = rs.getString("donVi");
	            double giaNhap = rs.getDouble("giaNhap");
	            String maNSX = rs.getString("maNSX");

	            // Tạo đối tượng sản phẩm và thêm vào danh sách
	            SanPham sanPham = new SanPham(maSP, tenSP, new NhaCungCap(maNCC), new NhaSanXuat(maNSX), soLuongTon, donVi, giaNhap);
	            dsSanPham2.add(sanPham);
	        }
	        // Đóng kết nối và các tài nguyên
	        return dsSanPham2.subList(0, Math.min(dsSanPham2.size(), 20));
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsSanPham2;
	}
	
	public List<SanPham> TimSPTheoMaHoacTen(SanPham sp) throws SQLException {
		List<SanPham> dsSP = new ArrayList<>();
		String query = "select * from SanPham where maSP like concat('%', ? ,'%') "
				+ "and tenSP like concat('%', ? ,'%')";
		ps = con.prepareStatement(query);
		ps.setString(1, sp.getMaSanPham());
		ps.setString(2, sp.getTenSanPham());
		rs = ps.executeQuery();
		while(rs.next()) {
			SanPham s = new SanPham();
			s.setMaSanPham(rs.getString("maSP"));
			s.setTenSanPham(rs.getString("tenSP"));
			String maNCC = rs.getString("maNCC");
			s.setNhaCungCap(new NhaCungCap(maNCC));
			s.setNhaSanXuat(new NhaSanXuat(rs.getString("maNSX")));
			s.setSoLuongTon(rs.getInt("soLuongTon"));
			s.setDonVi(rs.getString("donVi"));
			s.setGiaNhap(rs.getDouble("giaNhap"));

			dsSP.add(s);
		}
		return dsSP;
	}
	public double giaBan(double giaNhap) {
		double giaban = giaNhap*0.1+giaNhap+1000;
		return giaban;
	}

}
