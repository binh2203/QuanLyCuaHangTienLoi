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
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.NhaCungCapDao;
import dao.NhaSanXuatDao;
import dao.SanPhamDao;
//import dao.SanPhamDao;
//import entity.SanPham;
import entity.NhaCungCap;
import entity.NhaSanXuat;
import entity.SanPham;
//import other.DatePicker;
import other.RandomMa;
//import other.XuLyNgay;

public class Pnl_TKSanPham extends JPanel implements ActionListener{
	private String cl_green = "#00c691";
	private String cl_greyblue = "#f0f6fb";
	private String cl_greenbold = "#015a06";
	private String cl_yellow = "#fcbe00";
	private String cl_black = "#222222";
	private Font font_btn16 = new Font("Arial", Font.BOLD, 16);
	private Font font_btn13 = new Font("Arial", Font.BOLD, 13);
	private Font font_btnita = new Font("Arial", Font.ITALIC, 13);

	private static final long serialVersionUID = 1L;

	private JComboBox<String> cboLoaiSanPham;
	private JComboBox<String> cboThuongHieu;
	private JComboBox<String> cboNSX;

	private SanPhamDao sp_dao;
	private NhaCungCapDao ncc_dao;
	private List<NhaCungCap> listNCC;
	private NhaSanXuatDao nsx_dao;
	private List<NhaSanXuat> listNSX;

	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JTextField txtSoLuongTon;
	private JTextField txtBaoHanh;
	private JTextField txtgiaNhap;
	private JTextField txtTim;

	private DefaultTableModel model;
	private JTable table;

	private JTextField txtTrang;
	private RandomMa rd;
	private JTextArea txtMoTa;
	private SimpleDateFormat formatter;
	private String dateFormat;

	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnTrangDau;
	private JButton btnTrangSau;
	private JButton btnTrangTruoc;
	private JButton btnGiam;
	private JButton btnTang;
	private JButton btnTim;
	private AbstractButton btnTrangCuoi;

	// Tạo panel
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(1400, 800);
	}
	public Pnl_TKSanPham() {
		sp_dao = new SanPhamDao();
		ncc_dao = new NhaCungCapDao();

		setSize(1400, 683);
		setLayout(null);
		setBackground(Color.decode(cl_greyblue));

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new TitledBorder(null, "Thông Tin Sản Phẩm", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(10, 136, 1360, 170);
		add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.decode(cl_greyblue));

		// Ma SanPham
		JLabel lblNewLabel = new JLabel("Mã sản phẩm :");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(62, 28, 90, 25);
		panel.add(lblNewLabel);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setEditable(false);
		txtMaSanPham.setFont(new Font("Arial", Font.PLAIN, 13));
		txtMaSanPham.setBounds(154, 28, 316, 23);
		panel.add(txtMaSanPham);
		txtMaSanPham.setColumns(10);

		// Ten SanPham
		JLabel lblNewLabel_2 = new JLabel("Tên sản phẩm :");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(62, 63, 90, 25);
		panel.add(lblNewLabel_2);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Arial", Font.PLAIN, 13));
		txtTenSanPham.setColumns(10);
		txtTenSanPham.setBounds(154, 63, 316, 23);
		panel.add(txtTenSanPham);

		// Thuong hieu SanPham
		JLabel lblNewLabel_3 = new JLabel("Thương hiệu :");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(573, 63, 90, 25);
		panel.add(lblNewLabel_3);

		// Đơn vị SanPham
		JLabel lblNewLabel_6 = new JLabel("Đơn vị :");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(573, 28, 90, 25);
		panel.add(lblNewLabel_6);

		// SoLuongTon SanPham
		JLabel lblNewLabel_2_1 = new JLabel("Số lượng tồn :");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(280, 98, 90, 25);
		panel.add(lblNewLabel_2_1);

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuongTon.setFont(new Font("Arial", Font.PLAIN, 13));
		txtSoLuongTon.setColumns(10);
		txtSoLuongTon.setBounds(380, 98, 70, 23);
		txtSoLuongTon.setText("0");
		panel.add(txtSoLuongTon);

		// cbo loai SanPham
		cboLoaiSanPham = new JComboBox<>();
		cboLoaiSanPham.setBackground(Color.WHITE);
		cboLoaiSanPham.setFont(new Font("Arial", Font.PLAIN, 13));
		cboLoaiSanPham.setBounds(667, 28, 185, 23);
		panel.add(cboLoaiSanPham);

		cboLoaiSanPham.addItem("Chai");
		cboLoaiSanPham.addItem("Lon");
		cboLoaiSanPham.addItem("Gói");
		cboLoaiSanPham.addItem("Cốc");
		cboLoaiSanPham.addItem("Tách");
		cboLoaiSanPham.addItem("Chiếc");
		cboLoaiSanPham.addItem("Cây");
		cboLoaiSanPham.addItem("Bộ");

		// Gia nhap SanPham
		JLabel lblNewLabel_2_2 = new JLabel("Giá nhập :");
		lblNewLabel_2_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2_2.setBounds(62, 98, 90, 25);
		panel.add(lblNewLabel_2_2);

		txtgiaNhap = new JTextField();
		txtgiaNhap.setFont(new Font("Arial", Font.PLAIN, 13));
		txtgiaNhap.setColumns(10);
		txtgiaNhap.setBounds(154, 98, 95, 23);
		panel.add(txtgiaNhap);

		// Nhà sản xuất
		JLabel lblNewLabel_2_2_1 = new JLabel("Nhà Sản Xuất :");
		lblNewLabel_2_2_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2_2_1.setBounds(573, 98, 90, 25);
		panel.add(lblNewLabel_2_2_1);

		// cboThuongHieu SanPham
		cboThuongHieu = new JComboBox<>();
		cboThuongHieu.setBackground(Color.WHITE);
		cboThuongHieu.setFont(new Font("Arial", Font.PLAIN, 13));
		cboThuongHieu.setBounds(667, 63, 185, 23);

		listNCC = ncc_dao.getNhaCungCap();
		cboThuongHieu.addItem("");
		listNCC.forEach(e -> {
			cboThuongHieu.addItem(e.getTenNhaCungCap());
		});

		panel.add(cboThuongHieu);


		// Tạo JPanel cho tiêu đề và bảng
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.decode(cl_green));
		panel_1.setBounds(5, 70, 1400, 55);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thông Tin Sản Phẩm");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(0, 10, 1400, 35);
		panel_1.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Danh Sách Sản Phẩm", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_2.setBounds(10, 380, 1360, 330);
		add(panel_2);
		panel_2.setLayout(null);

		// cbo nha san xuat SanPham
		cboNSX = new JComboBox<>();
		cboNSX.setBackground(Color.WHITE);
		cboNSX.setFont(new Font("Arial", Font.PLAIN, 13));
		cboNSX.setBounds(667, 98, 185, 23);

		nsx_dao = new NhaSanXuatDao();
		listNSX = nsx_dao.getNhaSanXuat();
		cboNSX.addItem("");
		listNSX.forEach(e -> {
			cboNSX.addItem(e.getTenNhaSX());
		});

		panel.add(cboNSX);

		// Table SanPham
		String cols[] = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Thương Hiệu", "Số Lượng Tồn", "Đơn Vị", "Giá Nhập", "Giá Bán", "Nhà Sản Xuất"};
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);

		JTableHeader td = table.getTableHeader();
		td.setBackground(Color.decode(cl_green));
		td.setForeground(Color.white);
		td.setFont(new Font("Tahoma", Font.BOLD, 10));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 20, 1340, 300);
		panel_2.add(scrollPane);

		// BtnCapNhat
		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/settings_25px.png")));
		btnCapNhat.setFont(new Font("Arial", Font.BOLD, 13));
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.setFocusPainted(false);
		btnCapNhat.setBorder(null);
		btnCapNhat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCapNhat.setBackground(Color.decode(cl_yellow));
		btnCapNhat.setBounds(1100, 100, 112, 36);
		panel.add(btnCapNhat);

		// btnXoaTrang
		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/create_25px.png")));
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoaTrang.setForeground(new Color(255, 255, 255));
		btnXoaTrang.setFocusPainted(false);
		btnXoaTrang.setBorder(null);
		btnXoaTrang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaTrang.setBackground(new Color(34, 139, 34));
		btnXoaTrang.setBounds(950, 30, 112, 36);
		panel.add(btnXoaTrang);

		// btnXoa
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/delete_bin_25px.png")));
		btnXoa.setFont(new Font("Arial", Font.BOLD, 13));
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setFocusPainted(false);
		btnXoa.setBorder(null);
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setBounds(1100, 30, 112, 36);
		panel.add(btnXoa);

		// BtnThem
		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/add_25px.png")));
		btnThem.setFont(new Font("Arial", Font.BOLD, 13));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFocusPainted(false);
		btnThem.setBorder(null);
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThem.setBackground(new Color(34, 139, 34));
		btnThem.setBounds(950, 100, 112, 36);
		panel.add(btnThem);

		// btnTrangDau
		btnTrangDau = new JButton("");
		btnTrangDau.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/skip_to_start_20px.png")));
		btnTrangDau.setFocusPainted(false);
		btnTrangDau.setBorder(null);
		btnTrangDau.setBackground(Color.decode(cl_green));
		btnTrangDau.setBounds(603, 720, 30, 25);
		add(btnTrangDau);

		// btnTrangSau
		btnTrangSau = new JButton("");
		btnTrangSau.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/rewind_20px.png")));
		btnTrangSau.setFocusPainted(false);
		btnTrangSau.setBorder(null);
		btnTrangSau.setBackground(Color.decode(cl_green));
		btnTrangSau.setBounds(640, 720, 30, 25);
		add(btnTrangSau);

		// btnTrang Hientai
		txtTrang = new JTextField();
		txtTrang.setEditable(false);
		txtTrang.setHorizontalAlignment(SwingConstants.CENTER);
		txtTrang.setColumns(10);
		txtTrang.setBounds(682, 720, 30, 25);
		add(txtTrang);

		// btnTrangTruoc
		btnTrangTruoc = new JButton("");
		btnTrangTruoc.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/fast_forward_20px.png")));
		btnTrangTruoc.setFocusPainted(false);
		btnTrangTruoc.setBorder(null);
		btnTrangTruoc.setBackground(Color.decode(cl_green));
		btnTrangTruoc.setBounds(722, 720, 30, 25);
		add(btnTrangTruoc);

		// btnTrangCuoi
		btnTrangCuoi = new JButton("");
		btnTrangCuoi.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/end_20px.png")));
		btnTrangCuoi.setFocusPainted(false);
		btnTrangCuoi.setBorder(null);
		btnTrangCuoi.setBackground(Color.decode(cl_green));
		btnTrangCuoi.setBounds(759, 720, 30, 25);
		add(btnTrangCuoi);

		DocDL(1, 15);
		txtTrang.setText("1");
		rd = new RandomMa();
		txtMaSanPham.setText(rd.taoMaSanPham());

		// btnGiam
		btnGiam = new JButton("");
		btnGiam.setFocusPainted(false);
		btnGiam.setBorder(null);
		btnGiam.setBackground(Color.decode(cl_green));
		btnGiam.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/chevron_down_9px.png")));
		btnGiam.setBounds(448, 109, 20, 11);
		panel.add(btnGiam);

		// btnTang
		btnTang = new JButton("");
		btnTang.setFocusPainted(false);
		btnTang.setIcon(new ImageIcon(Pnl_TKSanPham.class.getResource("/image/chevron_up_9px.png")));
		btnTang.setBorder(null);
		btnTang.setBackground(Color.decode(cl_green));
		btnTang.setBounds(448, 98, 20, 11);
		panel.add(btnTang);

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
		add(btnTim);

		// Tìm kiếm
		txtTim = new JTextField();
		txtTim.setBounds(960, 330, 260, 30);
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTim.setColumns(10);

		add(txtTim);

		// EVENT SanPham
		btnCapNhat.addActionListener(this);
		btnGiam.addActionListener(this);
		btnTang.addActionListener(this);
		btnTrangCuoi.addActionListener(this);
		btnTrangDau.addActionListener(this);
		btnTrangSau.addActionListener(this);
		btnTrangTruoc.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = table.getSelectedRow();

				txtMaSanPham.setText(table.getValueAt(row, 0).toString());
				txtTenSanPham.setText(table.getValueAt(row, 1).toString());
				cboThuongHieu.setSelectedItem(table.getValueAt(row, 2).toString());
				txtSoLuongTon.setText(table.getValueAt(row, 3).toString());
				cboLoaiSanPham.setSelectedItem(table.getValueAt(row, 4).toString());
				txtgiaNhap.setText(table.getValueAt(row, 5).toString());
				cboNSX.setSelectedItem(table.getValueAt(row, 7).toString());

			}
		});

		txtTim.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            String timString = txtTim.getText().trim();

		            if (!timString.isEmpty()) {
		                List<SanPham> l = sp_dao.timSanPham(timString);
		                XoaDL();
		                l.forEach(sp -> {
		                    model.addRow(new Object[] {
		                            sp.getMaSanPham(), sp.getTenSanPham(), sp.getNhaCungCap().getMaNhaCungCap(), sp.getSoLuongTon(),
		                            sp.getDonVi(), sp.getGiaNhap(), sp_dao.giaBan(sp.getGiaNhap()), sp.getNhaSanXuat().getMaNhaSX()});
		                });
		            }
		        }
		    }
		});
	}
	// Các hàm bổ sung
	public void XoaDL() {

		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
	}

	public void DocDL(int fn, int ln) {

		List<SanPham> listsp = sp_dao.phanTrangSanPham(fn, ln);
		listsp.forEach(e -> {
			try {
				model.addRow(
						new Object[] { e.getMaSanPham(), e.getTenSanPham(), e.getNhaCungCap().getMaNhaCungCap(), e.getSoLuongTon(),
								e.getDonVi(), e.getGiaNhap(), sp_dao.giaBan(e.getGiaNhap()),e.getNhaSanXuat().getMaNhaSX()});
			} catch (Exception e2) {

			}

		});

	}

	public void xoaTrang() {

		RandomMa rd = new RandomMa();
		String a = rd.taoMaSanPham();
		txtMaSanPham.setText(a);
		txtTenSanPham.setText("");
		txtgiaNhap.setText("");
		cboLoaiSanPham.setSelectedIndex(0);
		cboThuongHieu.setSelectedIndex(0);
		cboNSX.setSelectedIndex(0);
		txtSoLuongTon.setText("0");
		txtMaSanPham.requestFocus();

	}

	public void denTrangTruoc() {

		int soLuong = sp_dao.soLuongSP();
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
			XoaDL();
			DocDL(fn, ln);
			table.clearSelection();

		}
	}

	public void denTrangSau() {

		int trang = Integer.parseInt(txtTrang.getText());
		if (trang > 1) {
			txtTrang.setText(String.valueOf(trang - 1));
			int fn = 15 * (trang - 2) + 1;
			int ln = fn + 14;
			XoaDL();
			DocDL(fn, ln);
			table.clearSelection();
		}

	}

	public void denTrangDau() {

		txtTrang.setText("1");
		XoaDL();
		DocDL(1, 15);
		table.clearSelection();

	}

	public void denTrangCuoi() {

		int soLuong = sp_dao.soLuongSP();
		int trangCuoi;
		if (soLuong % 15 == 0) {
			trangCuoi = soLuong / 15;
		} else {
			trangCuoi = soLuong / 15 + 1;
		}
		int fn = (trangCuoi - 1) * 15 + 1;
		int ln = fn + 14;
		XoaDL();
		DocDL(fn, ln);
		txtTrang.setText(String.valueOf(trangCuoi));
		table.clearSelection();

	}

	public void trangHienTai() {

		int trang = Integer.parseInt(txtTrang.getText());
		int ln = trang * 15;
		int fn = ln - 14;
		XoaDL();
		DocDL(fn, ln);

	}

	public boolean regexSanPham() {

		String tenSanPham = txtTenSanPham.getText();
		int soLuongTon;
		double giaNhap;
		try {

			giaNhap = Double.parseDouble(txtgiaNhap.getText());
			if (giaNhap < 0) {
				JOptionPane.showMessageDialog(null, "Giá nhập phải lớn hơn -1 (chỉ bằng -1 khi bạn không muốn tìm theo giá nhập) !!!");
				txtgiaNhap.selectAll();
				txtgiaNhap.requestFocus();
				return false;
			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Giá nhập phải là chữ số và không được để trống !!!");
			txtgiaNhap.selectAll();
			txtgiaNhap.requestFocus();
			return false;
		}
		try {
			soLuongTon = Integer.parseInt(txtSoLuongTon.getText());

			if (soLuongTon <= 0) {

				JOptionPane.showMessageDialog(null, "Số lượng tồn phải lớn hơn -1 (chỉ bằng -1 khi bạn không muốn tìm theo số lượng tồn)  !!!");
				txtSoLuongTon.selectAll();
				txtSoLuongTon.requestFocus();
				return false;

			}
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Số lượng tồn phải là số nguyên và không được để trống !!!");
			txtSoLuongTon.selectAll();
			txtSoLuongTon.requestFocus();
			return false;

		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnCapNhat)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Phải chọn Sản Phẩm cần sửa !!!");
			} else {
				if (regexSanPham()) {
					String maNCC = "";
					String maNSX = "";

					String nsx = (String) cboNSX.getSelectedItem();
					String thuongHieu = (String) cboThuongHieu.getSelectedItem();

					List<NhaSanXuat> listNSX = nsx_dao.getNhaSanXuat();
					List<NhaCungCap> listNCC = ncc_dao.getNhaCungCap();
					for (NhaCungCap element : listNCC) {
						if (element.getTenNhaCungCap().equals(thuongHieu)) {
							maNCC = element.getMaNhaCungCap();
						}
					}
					for (NhaSanXuat element : listNSX) {
						if(element.getTenNhaSX().equals(nsx)) {
							maNSX = element.getMaNhaSX();
						}
					}

					String maSanPham = txtMaSanPham.getText();
					String tenSanPham = txtTenSanPham.getText();
					String loaiSanPham = (String) cboLoaiSanPham.getSelectedItem();
					int soLuongTon = Integer.parseInt(txtSoLuongTon.getText());
					double giaNhap = Double.parseDouble(txtgiaNhap.getText());
					SanPham sp = new SanPham(maSanPham, tenSanPham, new NhaCungCap(maNCC), new NhaSanXuat(maNSX), soLuongTon,
							loaiSanPham, giaNhap);

					int t = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thay đổi !!!", "Cập nhật",
							JOptionPane.YES_NO_OPTION);
					if (t == JOptionPane.YES_OPTION) {
						if (sp_dao.updateSanPham(sp)) {
							trangHienTai();
							table.clearSelection();
							JOptionPane.showMessageDialog(null, "Cập nhật thành công !!!");

						} else {
							JOptionPane.showMessageDialog(null, "Cập nhật thất bại !!!");
						}
					}
				}
			}
		}
		if (o.equals(btnGiam)) {

			int sl = Integer.parseInt(txtSoLuongTon.getText());
			if (sl > 0) {
				txtSoLuongTon.setText(String.valueOf(sl - 1));
			}

		}
		if (o.equals(btnTang)) {

			int sl = Integer.parseInt(txtSoLuongTon.getText());
			txtSoLuongTon.setText(String.valueOf(sl + 1));

		}

		if (o.equals(btnThem)) {

			if (regexSanPham()) {
				String tenNCC = (String) cboThuongHieu.getSelectedItem();
				String tenNSX = (String) cboNSX.getSelectedItem();
				String maNCC = "";
				String maNSX = "";

				List<NhaSanXuat> listNSX = nsx_dao.getNhaSanXuat();
				List<NhaCungCap> listNCC = ncc_dao.getNhaCungCap();

				for (NhaCungCap element : listNCC) {
					if (element.getTenNhaCungCap().equals(tenNCC)) {
						maNCC = element.getMaNhaCungCap();
					}
				}
				for (NhaSanXuat element : listNSX) {
					if (element.getTenNhaSX().equals(tenNSX)) {
						maNSX = element.getMaNhaSX();
					}
				}

				String maSanPham = txtMaSanPham.getText();
				String tenSanPham = txtTenSanPham.getText();
				String loaiSanPham = (String) cboLoaiSanPham.getSelectedItem();
				int soLuongTon = Integer.parseInt(txtSoLuongTon.getText());
				double giaNhap = Double.parseDouble(txtgiaNhap.getText());

				SanPham sp = new SanPham(maSanPham, tenSanPham, new NhaCungCap(maNCC), new NhaSanXuat(maNSX), soLuongTon,
						loaiSanPham, giaNhap);
				if (sp_dao.ThemSP(sp)) {
					JOptionPane.showMessageDialog(null, "Thêm thành công !!!");
					XoaDL();
					denTrangCuoi();
					xoaTrang();
				} else {
					JOptionPane.showMessageDialog(null, "Trùng mã Sản Phẩm !!!");
				}

			}

		}
		if (o.equals(btnTrangDau)) {
			denTrangDau();
		}
		if (o.equals(btnTrangSau)) {
			denTrangSau();
		}
		if (o.equals(btnTrangTruoc)) {
			denTrangTruoc();
		}
		if (o.equals(btnTrangCuoi)) {
			denTrangCuoi();
		}
		if (o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			String ma = txtMaSanPham.getText();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Phải chọn dòng cần xóa !!!");
			} else {
				int t = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không !!!", "Xóa",
						JOptionPane.YES_NO_OPTION);
				if (t == JOptionPane.YES_OPTION) {
					if (sp_dao.XoaSP(ma)) {

						xoaTrang();
						if (table.getRowCount() != 1) {
							trangHienTai();
							JOptionPane.showMessageDialog(null, "Xóa thành công !!!!!");
						} else {
							denTrangTruoc();
							JOptionPane.showMessageDialog(null, "Xóa thành công !!!!!");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Xóa thất bại !!!!!");
					}

				}

			}
		}
		if (o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		if (o.equals(btnTim)) {
			String timString = txtTim.getText().trim();

            if (!timString.isEmpty()) {
                List<SanPham> l = sp_dao.timSanPham(timString);
                XoaDL();
                l.forEach(sp -> {
                    model.addRow(new Object[] {
                            sp.getMaSanPham(), sp.getTenSanPham(), sp.getNhaCungCap().getMaNhaCungCap(), sp.getSoLuongTon(),
                            sp.getDonVi(), sp.getGiaNhap(), sp_dao.giaBan(sp.getGiaNhap()), sp.getNhaSanXuat().getMaNhaSX()});
                });
            }
		}
	}
}
