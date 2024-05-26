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
import java.awt.event.MouseEvent;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.NhanVienDao;
import entity.NhanVien;
import other.RandomMa;

public class Pnl_QLNV extends JPanel implements ActionListener {

	private String cl_green = "#00c691";
	private String cl_greyblue = "#f0f6fb";
	private String cl_yellow = "#fcbe00";
	private Font font_btn13 = new Font("Arial", Font.BOLD, 13);
	private Font font_pln13 = new Font("Arial", Font.PLAIN, 13);
	private Font font_btn20 = new Font("Arial", Font.BOLD, 20);

	private NhanVienDao nv_dao;
	private JTextField txtMaNhanVien;
	private JTextField txtTenNhanVien;
	private JTextField txtNgaySinh;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JComboBox<String> cboChucVu;
	private JRadioButton rbnNam;
	private JRadioButton rbnNu;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private RandomMa rd;
	private static final long serialVersionUID = 1L;
	private JButton btnTim;
	private JTextField txtTim;
	private JTextField txtEmail;
	private JComboBox<String> cboTim;
	private JLabel lblTmKimTheo;

	/**
	 * Create the panel.
	 */
	// Tạo panel
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1400, 800);
	}

	public Pnl_QLNV() {
		nv_dao = new NhanVienDao();


		setSize(1400, 700);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1400, 700);
		panel.setBackground(Color.decode(cl_greyblue));
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.decode(cl_green));
		panel_1.setBounds(5, 70, 1400, 55);
		panel.add(panel_1);

		JLabel lblThngTinNhn = new JLabel("Thông Tin Nhân Viên");
		lblThngTinNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngTinNhn.setForeground(Color.WHITE);
		lblThngTinNhn.setFont(font_btn20);
		lblThngTinNhn.setBounds(0, 10, 1400, 35);
		panel_1.add(lblThngTinNhn);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng Tin Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_3.setBounds(10, 136, 1360, 189);
		panel_3.setBackground(Color.white);
		panel.add(panel_3);

		JLabel lblMNhnVin = new JLabel("Mã nhân viên :");
		lblMNhnVin.setFont(font_pln13);
		lblMNhnVin.setBounds(100, 35, 94, 25);
		panel_3.add(lblMNhnVin);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setColumns(10);
		txtMaNhanVien.setBounds(192, 35, 205, 23);
		panel_3.add(txtMaNhanVien);

		JLabel lblTnNhnVin = new JLabel("Tên nhân viên :");
		lblTnNhnVin.setFont(font_pln13);
		lblTnNhnVin.setBounds(100, 70, 94, 25);
		panel_3.add(lblTnNhnVin);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(192, 70, 205, 23);
		panel_3.add(txtTenNhanVien);

		JLabel lblChcV = new JLabel("Chức vụ :");
		lblChcV.setFont(font_pln13);
		lblChcV.setBounds(533, 35, 94, 25);
		//panel_3.add(lblChcV);

		JLabel lblMNhnVin_1_1 = new JLabel("Giới tính :");
		lblMNhnVin_1_1.setFont(font_pln13);
		lblMNhnVin_1_1.setBounds(513, 35, 94, 25);
		panel_3.add(lblMNhnVin_1_1);

		txtNgaySinh = new JTextField();
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(192, 105, 205, 23);
		panel_3.add(txtNgaySinh);

		JLabel lblNgaySinh = new JLabel("Ngày sinh :");
		lblNgaySinh.setFont(font_pln13);
		lblNgaySinh.setBounds(100, 105, 94, 25);
		panel_3.add(lblNgaySinh);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(610, 71, 164, 23);
		panel_3.add(txtSDT);

		JLabel lblMNhnVin_1_3 = new JLabel("Số điện thoại :");
		lblMNhnVin_1_3.setFont(font_pln13);
		lblMNhnVin_1_3.setBounds(513, 70, 94, 25);
		panel_3.add(lblMNhnVin_1_3);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(192, 140, 320, 23);
		panel_3.add(txtDiaChi);

		JLabel lblMNhnVin_1_4 = new JLabel("Địa chỉ :");
		lblMNhnVin_1_4.setFont(font_pln13);
		lblMNhnVin_1_4.setBounds(100, 138, 94, 25);
		panel_3.add(lblMNhnVin_1_4);

		cboChucVu = new JComboBox<>();
		cboChucVu.setBounds(625, 37, 149, 21);
		//panel_3.add(cboChucVu);
		cboChucVu.addItem("Bán Hàng");
		cboChucVu.addItem("Quản Lý");

		rbnNam = new JRadioButton("Nam");
		rbnNam.setSelected(true);
		rbnNam.setFont(font_pln13);
		rbnNam.setFocusPainted(false);
		rbnNam.setBounds(625, 37, 52, 21);
		panel_3.add(rbnNam);

		rbnNu = new JRadioButton("Nữ");
		rbnNu.setFont(font_pln13);
		rbnNu.setFocusPainted(false);
		rbnNu.setBounds(679, 37, 62, 21);
		panel_3.add(rbnNu);
		ButtonGroup g = new ButtonGroup();
		g.add(rbnNu);
		g.add(rbnNam);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "Danh S\u00E1ch Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 380, 1360, 330);
		panel_4.setBackground(Color.white);
		panel.add(panel_4);

		String row[] = { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ",
				"Email" };
		model = new DefaultTableModel(row, 0);
		table = new JTable(model);
		JTableHeader a = table.getTableHeader();
		a.setBackground(Color.decode(cl_green));
		a.setForeground(Color.white);
		a.setFont(new Font("Tahoma", Font.BOLD, 10));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollPane.setBounds(10, 20, 1340, 300);
		panel_4.add(scrollPane);


		docDL();
		rd = new RandomMa();
		txtMaNhanVien.setText(rd.taoMaNhanVien());

		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setBounds(948, 29, 112, 36);
		panel_3.add(btnXoaTrang);
		btnXoaTrang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaTrang.setIcon(new ImageIcon(Pnl_QLNV.class.getResource("/image/create_25px.png")));
		btnXoaTrang.setForeground(Color.WHITE);
		btnXoaTrang.setFont(font_btn13);
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setBorder(null);
		btnXoaTrang.setBackground(new Color(34, 139, 34));

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(1097, 29, 112, 36);
		panel_3.add(btnXoa);
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.setIcon(new ImageIcon(Pnl_QLNV.class.getResource("/image/delete_bin_25px.png")));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(font_btn13);
		btnXoa.setFocusPainted(false);
		btnXoa.setBorder(null);
		btnXoa.setBackground(Color.RED);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(948, 98, 112, 36);
		panel_3.add(btnThem);
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThem.setIcon(new ImageIcon(Pnl_QLNV.class.getResource("/image/add_25px.png")));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(font_btn13);
		btnThem.setFocusPainted(false);
		btnThem.setBorder(null);
		btnThem.setBackground(new Color(34, 139, 34));

		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setBounds(1097, 98, 112, 36);
		panel_3.add(btnCapNhat);
		btnCapNhat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCapNhat.setIcon(new ImageIcon(Pnl_QLNV.class.getResource("/image/settings_25px.png")));
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(font_btn13);
		btnCapNhat.setFocusPainted(false);
		btnCapNhat.setBorder(null);
		btnCapNhat.setBackground(Color.decode(cl_yellow));

		JLabel lblMNhnVin_1_3_1 = new JLabel("Email :");
		lblMNhnVin_1_3_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMNhnVin_1_3_1.setBounds(513, 104, 94, 25);
		panel_3.add(lblMNhnVin_1_3_1);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(610, 105, 164, 23);
		panel_3.add(txtEmail);

		// btnTim
		btnTim = new JButton("");
		btnTim.setFocusPainted(false);
		btnTim.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/search_15px.png")));
		btnTim.setBorder(null);
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setFocusPainted(false);
		btnTim.setBorder(null);
		btnTim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTim.setBackground(Color.decode(cl_green));
		btnTim.setBounds(1220, 330, 40, 30);
		//panel.add(btnTim);

		// Txt tim kiem
		txtTim = new JTextField();
		txtTim.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTim.setBounds(291, 335, 246, 25);
		panel.add(txtTim);
		txtTim.setColumns(10);

		// ComoBox Tim kiem
		cboTim = new JComboBox<>();
		cboTim.setBackground(Color.WHITE);
		cboTim.setFont(new Font("Arial", Font.PLAIN, 13));
		cboTim.setBounds(137, 335, 144, 25);
		panel.add(cboTim);
		// Thêm các dạng tìm kiếm cho combobox
		cboTim.addItem("Tên Nhân viên");
		cboTim.addItem("Mã Nhân viên");

		// Label Tim kiem
		lblTmKimTheo = new JLabel("Tìm kiếm theo :");
		lblTmKimTheo.setFont(font_btn13);
		lblTmKimTheo.setBounds(20, 335, 119, 25);
		panel.add(lblTmKimTheo);

		// ADD EVENT
		btnCapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				txtMaNhanVien.setText(table.getValueAt(row, 0).toString());
				txtTenNhanVien.setText(table.getValueAt(row, 1).toString());
				//cboChucVu.setSelectedItem(table.getValueAt(row, 2).toString());
				if (table.getValueAt(row, 2).toString().equals("Nam")) {
					rbnNam.setSelected(true);
				} else {
					rbnNu.setSelected(true);
				}
				txtNgaySinh.setText(table.getValueAt(row, 3).toString());
				txtSDT.setText(table.getValueAt(row, 4).toString());
				txtDiaChi.setText(table.getValueAt(row, 5).toString());
				txtEmail.setText(table.getValueAt(row, 6).toString());
			}
		});

	// Event tìm kiếm nhân viên
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Tìm theo Tên
				if (cboTim.getSelectedIndex() == 0) {
					List<NhanVien> list1 = nv_dao.timDSNhanVienTheoTen(txtTim.getText());
					xoaDL();
					list1.forEach(i -> {
						String gt;
						if (i.isGioiTinh()) {
							gt = "Nữ";
						} else {
							gt = "Nam";
						}
						model.addRow(new Object[] { i.getMaNV(), i.getHoTenNV(), gt, i.getNgaySinhNV(),
								i.getSoDienThoaiNV(), i.getDiaChiNV(), i.getEmailNV() });
					});
				}
				// Tìm theo Mã
				else if (cboTim.getSelectedIndex() == 1){
					List<NhanVien> list1 = nv_dao.timDSNhanVienTheoMa(txtTim.getText());
					xoaDL();
					list1.forEach(i -> {
						String gt;
						if (i.isGioiTinh()) {
							gt = "Nữ";
						} else {
							gt = "Nam";
						}
						model.addRow(new Object[] { i.getMaNV(), i.getHoTenNV(), gt, i.getNgaySinhNV(),
								i.getSoDienThoaiNV(), i.getDiaChiNV(), i.getEmailNV() });
					});
				}
			}
		});

	}

	public void xoaDL() {
		model.getDataVector().removeAllElements();
	}

	public void docDL() {
		List<NhanVien> list = nv_dao.getNhanVien();
		list.forEach(e -> {
			String gt;
			if (e.isGioiTinh()) {
				gt = "Nữ";
			} else {
				gt = "Nam";
			}
			model.addRow(new Object[] { e.getMaNV(), e.getHoTenNV(), gt, e.getNgaySinhNV(), e.getSoDienThoaiNV(),
					e.getDiaChiNV(), e.getEmailNV() });
		});
	}

	public boolean thongTinNhanVien() {
		String tenNhanVien = txtTenNhanVien.getText();
		//double heSoLuong = 0;
		//String matKhau = txtMatKhau.getText();
		String diaChi = txtDiaChi.getText();
		String sDT = txtSDT.getText();
		//String cMND = txtCMND.getText();

		// validate cho nhan vien
		if (tenNhanVien.length() == 0) {
			JOptionPane.showMessageDialog(null, "Tên không được để trống !");
			txtTenNhanVien.requestFocus();
			txtTenNhanVien.selectAll();
			return false;
		}

		else {
			// validate cho ten nhan vien
			if (!tenNhanVien.matches("[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ\r\n"
					+ "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu\r\n"
					+ "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ ]+")) {
				JOptionPane.showMessageDialog(null, "Tên không hợp lệ !");
				txtTenNhanVien.requestFocus();
				txtTenNhanVien.selectAll();
				return false;
			}
		}
//		try {
//			// validate he so luong
//			heSoLuong = Double.parseDouble(txtNgaySinh.getText());
//			if (heSoLuong <= 0) {
//				JOptionPane.showMessageDialog(null, "Hệ số lương phải lớn hơn 0 !");
//				txtNgaySinh.requestFocus();
//				txtNgaySinh.selectAll();
//				return false;
//			}
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "Hệ số lương phải là chử số !");
//			txtNgaySinh.requestFocus();
//			txtNgaySinh.selectAll();
//			return false;
//		}
//		if (matKhau.length() == 0) {
//			JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống !");
//			txtMatKhau.requestFocus();
//			txtMatKhau.selectAll();
//			return false;
//		} else {
//			if (!matKhau.matches("[0-9a-zA-Z]{6}")) {
//				JOptionPane.showMessageDialog(null, "Mật khẩu phải gồm 6 kí tự !");
//				txtMatKhau.requestFocus();
//				txtMatKhau.selectAll();
//				return false;
//			}
//		}
		if (diaChi.length() == 0) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống !");
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		}
		if (sDT.length() == 0) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống !");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		} else {
			if (!sDT.matches("[0-9]{10}")) {
				JOptionPane.showMessageDialog(null, "Số điện thoại phải là chử số và gồm 10 số !");
				txtSDT.requestFocus();
				txtSDT.selectAll();
				return false;
			}
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnXoaTrang)) {
			txtMaNhanVien.setText(rd.taoMaNhanVien());
			txtTenNhanVien.setText("");
			txtNgaySinh.setText("");
			txtSDT.setText("");
			txtDiaChi.setText("");
			txtEmail.setText("");
		}

		// Button thêm
		if (obj.equals(btnThem)) {
			if (thongTinNhanVien()) {
				String maNV = txtMaNhanVien.getText();
				String tenNV = txtTenNhanVien.getText();
				LocalDate ngaySinh = LocalDate.parse(txtNgaySinh.getText());
				String sDT = txtSDT.getText();
				String diaChi = txtDiaChi.getText();
				String email = txtEmail.getText();
				boolean gioiTinh = rbnNu.isSelected();
				//String matKhau = txtMatKhau.getText();
				//String cMND = txtCMND.getText();
				//String chucVu = cboChucVu.getSelectedItem().toString();

				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sDT, diaChi, email);

				if (nv_dao.themNhanvien(nv)) {
					JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công !");
					xoaDL();
					docDL();
				} else {
					JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại !");
				}

			}
		}

		// Button cập nhật
		if (obj.equals(btnCapNhat)) {
			if (thongTinNhanVien()) {
				String maNV = txtMaNhanVien.getText();
				String tenNV = txtTenNhanVien.getText();
				LocalDate ngaySinh = LocalDate.parse(txtNgaySinh.getText());
				String sDT = txtSDT.getText();
				String diaChi = txtDiaChi.getText();
				String email = txtEmail.getText();
				boolean gioiTinh = rbnNu.isSelected();
				//String matKhau = txtMatKhau.getText();
				//String chucVu = cboChucVu.getSelectedItem().toString();
				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, sDT, diaChi, email);
				if (nv_dao.capNhatNhanVien(nv)) {
					JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công !");
					xoaDL();
					docDL();
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thất bại !");
				}
			}
		}
		// Button xóa
		if (obj.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa !");
			} else {
				String maNV = table.getValueAt(row, 0).toString();
				int chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhân viên này không ?",
						"Chú ý", JOptionPane.YES_NO_OPTION);
				if (chon == JOptionPane.YES_OPTION) {
						if (nv_dao.xoaNhanVien(maNV) == 1) {
						JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công !");
						xoaDL();
						docDL();
						table.clearSelection();
				} else {
					JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại !");
				}
			}
		}
		}
	}


}
