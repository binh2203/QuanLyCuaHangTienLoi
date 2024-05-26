package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.DBConnection;
import entity.NhanVien;

public class NhanVienDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;


	public NhanVienDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public boolean themNhanvien(NhanVien nv)  {
		String insert = "Insert into NhanVien values (?, ?, ?, ?, ?, ?, ?)";
		try {
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, nv.getMaNV());

		int day = nv.getNgaySinhNV().getDayOfMonth();
		int month = nv.getNgaySinhNV().getMonthValue();
		int year = nv.getNgaySinhNV().getYear();

		stmt.setString(4, year + "-" + month + "-" + day);
		stmt.setString(2, nv.getHoTenNV());
		stmt.setBoolean(3, nv.isGioiTinh());
		stmt.setString(5, nv.getSoDienThoaiNV());
		stmt.setString(6, nv.getDiaChiNV());
		stmt.setString(7, nv.getEmailNV());
		stmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public List<NhanVien> getDSNhanVien() throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "Select * from NhanVien";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien();
			nv.setMaNV(rs.getString("manv"));
			nv.setHoTenNV(rs.getString("tenNV"));
			nv.setGioiTinh(rs.getBoolean("gioiTinh"));
			nv.setNgaySinhNV(rs.getDate("ngaySinh").toLocalDate());
			nv.setSoDienThoaiNV(rs.getString("phone"));
			nv.setDiaChiNV(rs.getString("diaChi"));
			nv.setEmailNV(rs.getString("email"));

			dsnv.add(nv);
		}
		return dsnv;
	}

	public NhanVien timNhanVienTheoMa(String maNV) throws SQLException {
		// System.out.println(maNV);
		String query = "Select * from NhanVien where maNV = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, maNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("manv"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"),
					rs.getDate("ngaySinh").toLocalDate(),rs.getString("phone"), rs.getString("diaChi"), rs.getString("email"));
			return nv;
		}
		return null;
	}

	public List<NhanVien> timDSNhanVienTheoMa(String maNV) {
		List<NhanVien> list = new ArrayList<>();
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
		try {
			PreparedStatement statement = con.prepareStatement("select * from NhanVien where maNV like N'%" + maNV + "%'");

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"),
						rs.getDate("ngaySinh").toLocalDate(),rs.getString("phone"), rs.getString("diaChi"), rs.getString("email")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	public NhanVien timNhanVienTheoTen(String ten) throws SQLException {

		String query = "Select * from NhanVien where tenNV = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, ten);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("manv"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"),
					rs.getDate("ngaySinh").toLocalDate(),rs.getString("phone"), rs.getString("diaChi"), rs.getString("email"));
			return nv;
		}
		return null;
	}
	public List<NhanVien> timDSNhanVienTheoTen(String tenNV)  {
		List<NhanVien> dsnv = new ArrayList<>();

		try {
		String query = "select * from NhanVien where tenNV LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, tenNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("manv"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"),
					rs.getDate("ngaySinh").toLocalDate(),rs.getString("phone"), rs.getString("diaChi"), rs.getString("email"));
			dsnv.add(nv);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	public List<NhanVien> timDSNhanVien(NhanVien nv) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		int gt = 0;
		if(nv.isGioiTinh()) {
			gt = 1;
		}
		String query = "select * from NhanVien where maNV LIKE CONCAT('%', ?, '%')"
				+ " and tenNV LIKE CONCAT('%', ?, '%') "
				+ " and gioiTinh = ? "
				+ " and phone LIKE CONCAT('%', ?, '%')"
				+ " and diaChi LIKE CONCAT('%', ?, '%')"
				+ " and email LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, nv.getMaNV());
		ps.setString(2, nv.getHoTenNV());
		ps.setString(3, gt +"");
		ps.setString(4, nv.getSoDienThoaiNV());
		ps.setString(5, nv.getDiaChiNV());
		ps.setString(6, nv.getEmailNV());
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv1 = new NhanVien(rs.getString("manv"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"),
					rs.getDate("ngaySinh").toLocalDate(),rs.getString("phone"), rs.getString("diaChi"), rs.getString("email"));
			dsnv.add(nv1);
		}
		return dsnv;
	}



	public int xoaNhanVien(String maNV) {
		String query = "delete from NhanVien where maNV = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, maNV);
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 1;
	}

	public boolean capNhatNhanVien(NhanVien nv) {
		String sql = "UPDATE NhanVien SET tenNV = ?, gioiTinh =?, ngaySinh = ?, phone = ?,diaChi =?, email = ? WHERE maNV =?";
		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, nv.getHoTenNV());

			int day = nv.getNgaySinhNV().getDayOfMonth();
			int month = nv.getNgaySinhNV().getMonthValue();
			int year = nv.getNgaySinhNV().getYear();

			ps.setString(3, year + "-" + month + "-" + day);
			ps.setBoolean(2, nv.isGioiTinh());
			ps.setString(4, nv.getSoDienThoaiNV());
			ps.setString(5, nv.getDiaChiNV());
			ps.setString(6, nv.getEmailNV());
			ps.setString(7, nv.getMaNV());
			// rs = ps.executeQuery();

			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

// Lĩnh:
	private ArrayList<NhanVien> dsNV1;
	public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc){
		dsNV1 = new ArrayList<>();
		try {
		String query= "SELECT  top(10)      NhanVien.maNV, sum(SanPham.giaBan*CT_HoaDon.soLuong)\r\n"
				+ "FROM            NhanVien INNER JOIN\r\n"
				+ "                         HoaDon ON NhanVien.maNV = HoaDon.maNV INNER JOIN\r\n"
				+ "                         CT_HoaDon ON HoaDon.maHD = CT_HoaDon.maHD INNER JOIN \r\n"
				+ "							SanPham ON [CT_HoaDon].maSP = SanPham.maSP \r\n"
				+ "WHERE  HoaDon.ngayLapHoaDon BETWEEN ? AND ?\r\n"
				+ "group by NhanVien.maNV\r\n"
				+ "order by sum (SanPham.giaBan*CT_HoaDon.soLuong) desc";

		ps = con.prepareStatement(query);
		int dayBD = ngayBatDau.getDayOfMonth();
		int monthBD = ngayBatDau.getMonthValue();
		int yearBD = ngayBatDau.getYear();

		ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

		int dayKT = ngayKetThuc.getDayOfMonth();
		int monthKT = ngayKetThuc.getMonthValue();
		int yearKT = ngayKetThuc.getYear();

		ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
		rs = ps.executeQuery();

		while (rs.next()) {
			String maNV = rs.getString(1);
			NhanVien nhanVien = new NhanVien(maNV);
			dsNV1.add(nhanVien);
		}
		return dsNV1;

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	public NhanVien getNhanVienByEmail(String email) {

		try {
			String query = "select *from NhanVien where email =?";

			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("phone"),   rs.getString("diaChi"),
						rs.getString("email"));
				return nv;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}
	//===========================================================
	public List<NhanVien> getNhanVien() {
		List<NhanVien> list = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("select *from NhanVien");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				list.add(new NhanVien(rs.getString("manv"), rs.getString("tenNV"), rs.getBoolean("gioiTinh"),
						rs.getDate("ngaySinh").toLocalDate(),rs.getString("phone"), rs.getString("diaChi"), rs.getString("email")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
