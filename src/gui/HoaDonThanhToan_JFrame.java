package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class HoaDonThanhToan_JFrame extends JFrame implements ActionListener{

	private String cl_green = "#00c691";
	private String cl_greyblue = "#f0f6fb";
	private String cl_greenbold = "#015a06";
	private String cl_yellow = "#fcbe00";
	private String cl_black = "#222222";
	private Font font_btn16 = new Font("Arial", Font.BOLD, 16);
	private Font font_btn13 = new Font("Arial", Font.BOLD, 13);
	private Font font_btnita = new Font("Arial", Font.ITALIC, 13);
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	@SuppressWarnings("unused")
	private HoaDon hoaDon;
	private HoaDonDAO hd_dao;
	private ChiTietHoaDonDAO cthd_dao;

	public HoaDonThanhToan_JFrame(HoaDon hoaDon) throws SQLException {
		super();
		this.hoaDon = hoaDon;

		cthd_dao = new ChiTietHoaDonDAO();
		hd_dao = new HoaDonDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("HÓA ĐƠN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(241, 22, 168, 37);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn :");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(57, 100, 91, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Ngày lập HĐ :");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(411, 100, 108, 25);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Mã khách hàng :");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(57, 140, 108, 25);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Ngày giao hàng :");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_1_1.setBounds(411, 140, 108, 25);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_2_1 = new JLabel("Tên khách hàng :");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_2_1.setBounds(57, 185, 117, 25);
		contentPane.add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Địa chỉ :");
		lblNewLabel_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_2_1_1.setBounds(57, 230, 117, 25);
		contentPane.add(lblNewLabel_1_2_1_1);

		JLabel txtTenKhachHang = new JLabel("Trần Thị A");
		txtTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTenKhachHang.setBounds(171, 185, 130, 25);
		contentPane.add(txtTenKhachHang);

		JLabel txtMaKhachHang = new JLabel("KH10000000");
		txtMaKhachHang.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMaKhachHang.setBounds(171, 140, 130, 25);
		contentPane.add(txtMaKhachHang);

		JLabel txtDiaChi = new JLabel("1084/51, Quang Trung, Gò Vấp, TPHCM");
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDiaChi.setBounds(120, 230, 496, 25);
		contentPane.add(txtDiaChi);

		JLabel txtMaHoaDon = new JLabel("HD10000000");
		txtMaHoaDon.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMaHoaDon.setBounds(147, 100, 154, 25);
		contentPane.add(txtMaHoaDon);

		JLabel txtLapHoaDon = new JLabel("20-11-2020");
		txtLapHoaDon.setFont(new Font("Arial", Font.PLAIN, 13));
		txtLapHoaDon.setBounds(507, 100, 130, 25);
		contentPane.add(txtLapHoaDon);

		JLabel txtGiaoHang = new JLabel("20-11-2020");
		txtGiaoHang.setFont(new Font("Arial", Font.PLAIN, 13));
		txtGiaoHang.setBounds(529, 140, 108, 25);
		contentPane.add(txtGiaoHang);

		String row[] = { "STT", "Tên linh kiện", "Số lượng", "Giá bán", "Thành tiền" };
		model = new DefaultTableModel(row, 0);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, EventObject e) {
				return false;
			};
		};
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(57, 265, 539, 237);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		contentPane.add(scrollPane);

		JLabel lblNewLabel_2 = new JLabel("Tổng tiền :");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_2.setBounds(385, 571, 98, 25);
		contentPane.add(lblNewLabel_2);

		JLabel txtTongTien = new JLabel("500000");
		txtTongTien.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTongTien.setBounds(483, 571, 154, 25);
		contentPane.add(txtTongTien);

		JButton btnQuayLai = new JButton("Quay Lại");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setFont(new Font("Arial", Font.BOLD, 13));
		btnQuayLai.setFocusPainted(false);
		btnQuayLai.setBorder(null);
		btnQuayLai.setBackground(new Color(65, 105, 225));
		btnQuayLai.setBounds(35, 604, 112, 36);
		contentPane.add(btnQuayLai);

		txtMaHoaDon.setText(hoaDon.getMaHD());
		List<HoaDon> listHD = hd_dao.getListHD();
		listHD.forEach(e -> {
			if (e.getMaHD().equals(hoaDon.getMaHD())) {
				txtMaKhachHang.setText(e.getKhachHang().getMaKH());
				txtTenKhachHang.setText(e.getKhachHang().getHoTenKH());
				txtDiaChi.setText(e.getKhachHang().getDiaChi());
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				LocalDate date1 = e.getNgayLapHD();
				txtLapHoaDon.setText(formatter.format(date1));
				LocalDate date2 = e.getNgayLapHD();
				txtGiaoHang.setText(formatter.format(date2));
			}
		});
		List<ChiTietHoaDon> listCTHD = cthd_dao.getCTHDByID(hoaDon.getMaHD());
		int stt = 0;
		int mucGiamGia = 0;
		for (int i = 0; i < listCTHD.size(); i++) {
			stt++;
			model.addRow(new Object[] { stt, listCTHD.get(i).getSanPham().getTenSanPham(),
					listCTHD.get(i).getSoLuong(), listCTHD.get(i).getSanPham().getGiaBan(),
					listCTHD.get(i).getSoLuong() * listCTHD.get(i).getSanPham().getGiaBan() });
		}

		double tongTien = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			tongTien = tongTien + Double.parseDouble(table.getValueAt(i, 4).toString());
		}
		DecimalFormat df = new DecimalFormat("#,##0.00");

		tongTien = tongTien - ((tongTien * mucGiamGia) / 100);
		txtTongTien.setText(String.valueOf(df.format(tongTien)) + " VNĐ");

		JLabel lblNewLabel_1_2_1_2 = new JLabel("Phương thức thanh toán :");
		lblNewLabel_1_2_1_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_2_1_2.setBounds(57, 512, 178, 25);
		contentPane.add(lblNewLabel_1_2_1_2);

		JLabel txtTenKhachHang_1 = new JLabel("Tiền mặt");
		txtTenKhachHang_1.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTenKhachHang_1.setBounds(224, 512, 78, 25);
		contentPane.add(txtTenKhachHang_1);

		JLabel lblNewLabel_1_2_1_3 = new JLabel("Mức giảm giá :");
		lblNewLabel_1_2_1_3.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_2_1_3.setBounds(411, 512, 108, 25);
		contentPane.add(lblNewLabel_1_2_1_3);

		JLabel txtMucGiamGia = new JLabel("0 %");
		txtMucGiamGia.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMucGiamGia.setBounds(512, 512, 66, 25);
		contentPane.add(txtMucGiamGia);

		txtMucGiamGia.setText(String.valueOf(mucGiamGia) + " %");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
