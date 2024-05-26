package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.KhachHangDao;
import entity.KhachHang;
import other.RandomMa;

public class Pnl_KhachHang extends JPanel implements ActionListener {

	private String cl_green = "#00c691";
	private String cl_greyblue = "#f0f6fb";
	private String cl_yellow = "#fcbe00";
	private Font font_btn13 = new Font("Arial", Font.BOLD, 13);
	private static final long serialVersionUID = 1L;
	private KhachHangDao kh_dao;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtTim;
	private JComboBox<String> cboTim;
	private JLabel lblTmKimTheo;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btnTrangDau;
	private JButton btnTrangTruoc;
	private JTextField txtTrang;
	private JButton btnTrangSau;
	private JButton btnTrangCuoi;
	private JLabel lblMKhchHng_4;
	private JRadioButton rbnNam;
	private JRadioButton rbnNu;
	private JLabel lblMKhchHng_5;
	private JTextField txtNgaySinh;


	/**
	 * Create the panel.
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1400, 800);
	}

	public Pnl_KhachHang() {

		// Lấy data từ table KhachHang
		kh_dao = new KhachHangDao();

		setSize(1400, 683);
		setLayout(null);
		setBackground(Color.decode(cl_greyblue));

		// add Tiêu đề cho KhachHang_JPanel
		panel_2 = new JPanel();
		panel_2.setBackground(Color.decode(cl_green));
		panel_2.setBounds(5, 70, 1400, 55);
		add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel = new JLabel("Thông Tin Khách Hàng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 10, 1400, 35);
		panel_2.add(lblNewLabel);

		// Tạo Tiêu đề cho table
		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Danh Sách Khách Hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 380, 1360, 330);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);

		// Tạo table, table model
		String row[] = { "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính", "Ngày Sinh", "SĐT", "Địa chỉ", "Email" };
		model = new DefaultTableModel(row, 0);
		table = new JTable(model);
		// Setting cho header table
		JTableHeader a = table.getTableHeader();
		a.setBackground(Color.decode(cl_green));
		a.setForeground(Color.white);
		a.setFont(new Font("Tahoma", Font.BOLD, 10));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 20, 1340, 300);
		panel.add(scrollPane);

		// Panel Nhập thông tin KH
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 136, 1360, 170);
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		panel_1.setLayout(null);

		// Lbl, txt của mã KH
		JLabel lblMKhchHng = new JLabel("Mã khách hàng :");
		lblMKhchHng.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMKhchHng.setBounds(31, 21, 111, 23);
		panel_1.add(lblMKhchHng);

		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEditable(false);
		txtMaKhachHang.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMaKhachHang.setBounds(152, 21, 244, 23);
		panel_1.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);

		// Lbl, txt của tên KH
		JLabel lblMKhchHng_1 = new JLabel("Tên khách hàng :");
		lblMKhchHng_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMKhchHng_1.setBounds(31, 54, 108, 23);
		panel_1.add(lblMKhchHng_1);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(152, 54, 244, 23);
		panel_1.add(txtTenKhachHang);

		// Lbl, txt của SĐT
		JLabel lblMKhchHng_3 = new JLabel("SDT :");
		lblMKhchHng_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMKhchHng_3.setBounds(449, 54, 46, 23);
		panel_1.add(lblMKhchHng_3);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Arial", Font.PLAIN, 13));
		txtSDT.setColumns(10);
		txtSDT.setBounds(516, 54, 206, 23);
		panel_1.add(txtSDT);

		// Lbl, txt của Email
		JLabel lblMKhchHng_2 = new JLabel("Email :");
		lblMKhchHng_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMKhchHng_2.setBounds(450, 87, 45, 23);
		panel_1.add(lblMKhchHng_2);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(516, 87, 206, 23);
		panel_1.add(txtEmail);

		// Lbl, txt của Địa chỉ
		JLabel lblMKhchHng_1_1 = new JLabel("Địa chỉ :");
		lblMKhchHng_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMKhchHng_1_1.setBounds(31, 125, 96, 23);
		panel_1.add(lblMKhchHng_1_1);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 13));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(152, 125, 570, 23);
		panel_1.add(txtDiaChi);

		lblMKhchHng_4 = new JLabel("Giới tính:");
		lblMKhchHng_4.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMKhchHng_4.setBounds(449, 21, 63, 23);
		panel_1.add(lblMKhchHng_4);

		//
		rbnNam = new JRadioButton("Nam");
		rbnNam.setSelected(true);
		rbnNam.setFont(new Font("Arial", Font.PLAIN, 13));
		rbnNam.setFocusPainted(false);
		rbnNam.setBounds(516, 22, 52, 21);
		panel_1.add(rbnNam);

		rbnNu = new JRadioButton("Nữ");
		rbnNu.setFont(new Font("Arial", Font.PLAIN, 13));
		rbnNu.setFocusPainted(false);
		rbnNu.setBounds(583, 22, 62, 21);
		panel_1.add(rbnNu);
		ButtonGroup g = new ButtonGroup();
		g.add(rbnNu);
		g.add(rbnNam);

		// BTN Thêm
		btnThem = new JButton("Thêm");
		btnThem.setBounds(950, 100, 112, 36);
		panel_1.add(btnThem);
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThem.setIcon(new
				ImageIcon(Pnl_KhachHang.class.getResource("/image/add_25px.png")));

		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(new Color(34, 139, 34));
		btnThem.setBorder(null);
		btnThem.setFocusPainted(false);
		btnThem.setFont(font_btn13);

		// BTN Cập nhật
		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setBounds(1100, 100, 112, 36);
		panel_1.add(btnCapNhat);
		btnCapNhat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCapNhat.setIcon(new
		ImageIcon(Pnl_KhachHang.class.getResource("/image/settings_25px.png")));
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.setBackground(Color.decode(cl_yellow));
		btnCapNhat.setBorder(null);
		btnCapNhat.setFocusPainted(false);
		btnCapNhat.setFont(font_btn13);

		// BTN Xóa trắng
		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setBounds(950, 30, 112, 36);
		panel_1.add(btnXoaTrang);
		btnXoaTrang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaTrang.setIcon(new
		ImageIcon(Pnl_KhachHang.class.getResource("/image/create_25px.png")));
		btnXoaTrang.setForeground(new Color(255, 255, 255));
		btnXoaTrang.setBackground(new Color(34, 139, 34));
		btnXoaTrang.setBorder(null);
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 13));

		// BTN Xóa
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(1100, 30, 112, 36);
		panel_1.add(btnXoa);
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.setIcon(new
		ImageIcon(Pnl_KhachHang.class.getResource("/image/delete_bin_25px.png")));
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setBorder(null);
		btnXoa.setFocusPainted(false);
		btnXoa.setFont(font_btn13);

		lblMKhchHng_5 = new JLabel("Ngày sinh :");
		lblMKhchHng_5.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMKhchHng_5.setBounds(31, 87, 108, 23);
		panel_1.add(lblMKhchHng_5);

		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Arial", Font.PLAIN, 13));
		//txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(152, 87, 244, 23);
		panel_1.add(txtNgaySinh);

//		dcNgaySinh = new JDateChooser();
//		dcNgaySinh.setBounds(152, 87, 244, 23);
//		panel_1.add(dcNgaySinh);

		// Txt tim kiem
		txtTim = new JTextField();
		txtTim.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTim.setBounds(281, 319, 246, 25);
		add(txtTim);
		txtTim.setColumns(10);

		// ComoBox Tim kiem
		cboTim = new JComboBox<>();
		cboTim.setBackground(Color.WHITE);
		cboTim.setFont(new Font("Arial", Font.PLAIN, 13));
		cboTim.setBounds(127, 319, 144, 25);
		add(cboTim);
		// Thêm các dạng tìm kiếm cho combobox
		cboTim.addItem("Tên khách hàng");
		cboTim.addItem("SDT khách hàng");
		cboTim.addItem("Mã khách hàng");

		// Label Tim kiem
		lblTmKimTheo = new JLabel("Tìm kiếm theo :");
		lblTmKimTheo.setFont(font_btn13);
		lblTmKimTheo.setBounds(10, 319, 119, 25);
		add(lblTmKimTheo);

		// BTN Trang Đầu
		btnTrangDau = new JButton("");
		btnTrangDau.setIcon(new
		ImageIcon(Pnl_KhachHang.class.getResource("/image/skip_to_start_20px.png")));
		btnTrangDau.setFocusPainted(false);
		btnTrangDau.setBorder(null);
		btnTrangDau.setBackground(Color.decode(cl_green));
		btnTrangDau.setBounds(603, 720, 30, 25);
		add(btnTrangDau);

		// BTN Trang Trước
		btnTrangTruoc = new JButton("");
		 btnTrangTruoc.setIcon(new
		 ImageIcon(Pnl_KhachHang.class.getResource("/image/fast_forward_20px.png")));
		btnTrangTruoc.setFocusPainted(false);
		btnTrangTruoc.setBorder(null);
		btnTrangTruoc.setBackground(Color.decode(cl_green));
		btnTrangTruoc.setBounds(722, 720, 30, 25);
		add(btnTrangTruoc);

		// TXT Số trang
		txtTrang = new JTextField();
		txtTrang.setEditable(false);
		txtTrang.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrang.setColumns(10);
		txtTrang.setBounds(682, 720, 30, 25);
		add(txtTrang);

		// BTN Trang Sau
		btnTrangSau = new JButton("");
		btnTrangSau.setIcon(new
		ImageIcon(Pnl_KhachHang.class.getResource("/image/rewind_20px.png")));
		btnTrangSau.setFocusPainted(false);
		btnTrangSau.setBorder(null);
		btnTrangSau.setBackground(Color.decode(cl_green));
		btnTrangSau.setBounds(640, 720, 30, 25);
		add(btnTrangSau);

		// BTN Trang Cuối
		btnTrangCuoi = new JButton("");
		btnTrangCuoi.setIcon(new
		ImageIcon(Pnl_KhachHang.class.getResource("/image/end_20px.png")));
		btnTrangCuoi.setFocusPainted(false);
		btnTrangCuoi.setBorder(null);
		btnTrangCuoi.setBackground(Color.decode(cl_green));
		btnTrangCuoi.setBounds(759, 720, 30, 25);
		add(btnTrangCuoi);

		// Random mã KH
		RandomMa rd = new RandomMa();
		txtMaKhachHang.setText(rd.taoMaKhachHang());
		// Đọc DL
		docDL(1, 15);
		txtTrang.setText("1");

		// Add event
		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);

		btnTrangCuoi.addActionListener(this);
		btnTrangDau.addActionListener(this);
		btnTrangSau.addActionListener(this);
		btnTrangTruoc.addActionListener(this);
		// Event xử lý khi click vào một dòng trong table thả dữ liệu vào các ô nhập
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int i = table.getSelectedRow();
				txtMaKhachHang.setText(model.getValueAt(i, 0).toString());
				txtTenKhachHang.setText(model.getValueAt(i, 1).toString());
				if (table.getValueAt(i, 2).toString().equals("Nam")) {
					rbnNam.setSelected(true);
				} else {
					rbnNu.setSelected(true);
				}
				txtNgaySinh.setText(model.getValueAt(i, 3).toString());
				txtSDT.setText(model.getValueAt(i, 4).toString());
				txtDiaChi.setText(model.getValueAt(i, 5).toString());
				txtEmail.setText(model.getValueAt(i, 6).toString());

			}
		});
		// Event tìm kiếm khách theo: tên, sđt, mã
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Tìm theo Tên
				if (cboTim.getSelectedIndex() == 0) {
					List<KhachHang> list1 = kh_dao.timKHTheoTen(txtTim.getText());
					xoaDL();
					list1.forEach(i -> {
						String gt;
						if (i.isGioiTinh()) {
							gt = "Nữ";
						} else {
							gt = "Nam";
						}
						model.addRow(new Object[] { i.getMaKH(), i.getHoTenKH(), gt, i.getNgaySinh(),
								i.getSDT(),  i.getDiaChi(), i.getEmail() });
					});
				}
				// Tìm theo SDT
				else if (cboTim.getSelectedIndex() == 1) {
					List<KhachHang> list1 = kh_dao.timKHTheoSDT(txtTim.getText());
					xoaDL();
					list1.forEach(i -> {
						String gt;
						if (i.isGioiTinh()) {
							gt = "Nữ";
						} else {
							gt = "Nam";
						}
						model.addRow(new Object[] { i.getMaKH(), i.getHoTenKH(), gt, i.getNgaySinh(),
								i.getSDT(), i.getDiaChi(), i.getEmail() });
					});
				}
				// Tìm theo Mã
				else {
					List<KhachHang> list1 = kh_dao.timKHTheoMa(txtTim.getText());
					xoaDL();
					list1.forEach(i -> {
						String gt;
						if (i.isGioiTinh()) {
							gt = "Nữ";
						} else {
							gt = "Nam";
						}
						model.addRow(new Object[] { i.getMaKH(), i.getHoTenKH(), gt, i.getNgaySinh(),
								i.getSDT(), i.getDiaChi(), i.getEmail() });
					});
				}
			}
		});

	}

	// Hàm xóa DL table model
	public void xoaDL() {
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}

	// Hàm đọc DL table model
	public void docDL(int fn, int ln) {
		List<KhachHang> listKH = kh_dao.phanTrangKhachHang(fn, ln);
		listKH.forEach(e -> {
			String gt;
			if (e.isGioiTinh()) {
				gt = "Nữ";
			} else {
				gt = "Nam";
			}
			model.addRow(new Object[] { e.getMaKH(), e.getHoTenKH(), gt, e.getNgaySinh(), e.getSDT(),
					 e.getDiaChi(), e.getEmail() });

		});
	}

	// Hàm reset dữ liệu trên txt
	public void xoaTrang() {
		RandomMa rd = new RandomMa();
		String a = rd.taoMaKhachHang();
		txtMaKhachHang.setText(a);
		txtTenKhachHang.setText("");
		rbnNam.setSelected(true);
		txtNgaySinh.setText("");
		txtSDT.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
		txtMaKhachHang.requestFocus();
	}

	// Hàm đến trang sau
	public void denTrangSau() {
		int soLuong = kh_dao.soLuongKH();
		int soTrang = Integer.parseInt(txtTrang.getText());
		int trangLonNhat;
		if (soLuong % 15 == 0) {
			trangLonNhat = soLuong / 15;
		} else {
			trangLonNhat = soLuong / 15 + 1;
		}
		if (soTrang < trangLonNhat) {
			txtTrang.setText(String.valueOf(soTrang + 1));
			int fn = 15 * soTrang + 1;
			int ln = fn + 14;
			xoaDL();
			docDL(fn, ln);
			table.clearSelection();

		}
	}

	// Hàm đến trang trước
	public void denTrangTruoc() {
		int trang = Integer.parseInt(txtTrang.getText());
		if (trang > 1) {
			txtTrang.setText(String.valueOf(trang - 1));
			int fn = 15 * (trang - 2) + 1;
			int ln = fn + 14;
			xoaDL();
			docDL(fn, ln);
			table.clearSelection();
		}
	}

	// Hàm đến trang đầu
	public void denTrangDau() {
		txtTrang.setText("1");
		xoaDL();
		docDL(1, 15);
		table.clearSelection();
	}

	// Hàm đến trang cuối
	public void denTrangCuoi() {
		int soLuong = kh_dao.soLuongKH();
		int trangCuoi;
		if (soLuong % 15 == 0) {
			trangCuoi = soLuong / 15;
		} else {
			trangCuoi = soLuong / 15 + 1;
		}
		int fn = (trangCuoi - 1) * 15 + 1;
		int ln = fn + 14;
		xoaDL();
		docDL(fn, ln);
		txtTrang.setText(String.valueOf(trangCuoi));
		table.clearSelection();

	}

	// Hàm lấy số trang hiện tại
	public void trangHienTai() {
		int trang = Integer.parseInt(txtTrang.getText());
		int ln = trang * 15;
		int fn = ln - 14;
		xoaDL();
		docDL(fn, ln);
	}

	// Kiểm tra kiểu dữ liệu nhập vào
	public boolean validateKhachHang() {
		String tenKhachHang = txtTenKhachHang.getText();
		String diaChi = txtDiaChi.getText();
		String sDT = txtSDT.getText();
		String email = txtEmail.getText();
		// Kt tên
		if (tenKhachHang.length() == 0) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng không được để trống !");
			txtTenKhachHang.requestFocus();
			txtTenKhachHang.selectAll();
			return false;
		} else {
			if (!tenKhachHang.matches("[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ\r\n"
					+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu\r\n"
					+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
				JOptionPane.showMessageDialog(null, "Tên không hợp lệ !");
				txtTenKhachHang.requestFocus();
				txtTenKhachHang.selectAll();
				return false;
			}
		}
		// Kt ĐC
		if (diaChi.length() == 0) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống !");
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		}
		// Kt SĐT
		if (sDT.length() == 0) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống !");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		} else {
			if (!sDT.matches("[0-9]{10}")) {
				JOptionPane.showMessageDialog(null, "Số điện thoại phải là chữ số và gồm 10 số !");
				txtSDT.requestFocus();
				txtSDT.selectAll();
				return false;
			}
		}
		// Kt email
		if (email.length() != 0) {
			if (!email.matches("[A-Za-z0-9.]+@[A-Za-z]+.[A-Za-z]+")) {
				JOptionPane.showMessageDialog(null, "Email không hợp lệ !");
				txtEmail.requestFocus();
				txtEmail.selectAll();
				return false;
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		// Button cập nhật
		if (o.equals(btnCapNhat)) {

			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần cập nhật !");
			} else {
				if (validateKhachHang()) {
					String maKH = txtMaKhachHang.getText();
					String tenKH = txtTenKhachHang.getText();
					boolean gioiTinh = rbnNu.isSelected();
					LocalDate ngaySinh = LocalDate.parse(txtNgaySinh.getText());
					String sDT = txtSDT.getText();
					String diaChi = txtDiaChi.getText();
					String email = txtEmail.getText();

					KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, ngaySinh, sDT, diaChi, email);

					int t = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật ?", "Cập nhật",
							JOptionPane.YES_NO_OPTION);
					if (t == JOptionPane.YES_OPTION) {
						if (kh_dao.updateKhachHang(kh)) {
							trangHienTai();
							table.clearSelection();
							JOptionPane.showMessageDialog(null, "Cập nhật thành công !");

						} else {
						JOptionPane.showMessageDialog(null, "Cập nhật thất bại !");
					}
					}
				}
			}
		}
		// Button thêm
		if (o.equals(btnThem)) {
			if (validateKhachHang()) {
				String maKH = txtMaKhachHang.getText();
				String tenKH = txtTenKhachHang.getText();
				boolean gioiTinh = rbnNam.isSelected();
				LocalDate ngaySinh = LocalDate.parse(txtNgaySinh.getText());
				String sDT = txtSDT.getText();
				String email = txtEmail.getText();
				String diaChi = txtDiaChi.getText();
				KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, ngaySinh, sDT, diaChi, email);
				if (kh_dao.themKhachHang(kh)) {
					JOptionPane.showMessageDialog(null, "Thêm thành công !");
					xoaDL();
					denTrangCuoi();
					xoaTrang();
				} else {
					JOptionPane.showMessageDialog(null, "Thêm thất bại !");
				}
			}
		}


		// Button trang cuối
		if (o.equals(btnTrangCuoi)) {

			denTrangCuoi();
		}
		// Button trang đầu
		if (o.equals(btnTrangDau)) {

			denTrangDau();
		}
		// Button trang sau
		if (o.equals(btnTrangSau)) {

			denTrangTruoc();
		}
		// Button trang trư
		if (o.equals(btnTrangTruoc)) {
			denTrangSau();
		}

		// Button xóa trắng
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		// Button xóa
		if (o.equals(btnXoa)) {

			int row = table.getSelectedRow();
			String maKH = txtMaKhachHang.getText();

			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng cần xóa !");
			} else {

				int t = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa ?", "Xóa", JOptionPane.YES_NO_OPTION);


				if (t == JOptionPane.YES_OPTION) {
					if (kh_dao.XoaKH(maKH)) {
						JOptionPane.showMessageDialog(null, "Xóa thành công !");
						xoaDL();
						trangHienTai();
						xoaTrang();
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại !");
					}
				}
			}
		}
	}
}
