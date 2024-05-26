package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietPhieuDatDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import dao.PhieuDatHangDao;
import dao.SanPhamDao;
import entity.ChiTietPhieuDat;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatHang;
import entity.SanPham;
import entity.TaiKhoan;
import javaswingdev.FontAwesomeIcon;


public class Pnl_PhieuDatHang extends JPanel implements ActionListener, MouseListener, KeyListener{

	private JTextField txt_masp;
	private JTextField txt_tensp;
	private JTextField txt_donVi;
	private JTextField txt_giaBan;
	private JTextField txt_soLuong;
	private JButton btn_cong;
	private JButton btn_tru;
	private JButton btn_timsp;
	private JButton btn_lamMoi;
	private JButton btn_them;
	private DefaultTableModel tbl_modelDSSP;
	private JTable tbl_DSSP;
	private JTextField txt_maPDH;
	private JTextField txt_nhanVien;
	private JTextField txt_SDT;
	private JTextField txt_ngayLap;
	private JDateChooser dcNgayLayHang;
	private JTextField txt_khachHang;
	private JTextField txt_soLuongPDH;
	private JButton btn_truSL;
	private DefaultTableModel tbl_modelPDH;
	private JTable tbl_PDH;
	private JTextField txt_thanhTien;
	private JButton btn_Xoa;
	private JTextField txt_tienKhachDua;
	private JButton btn_thanhToan;
	private JButton btn_lapPDH;
	private JTextField txt_tienThua;
	private List<SanPham> dsSP;
	private SanPhamDao spDao;
	private DecimalFormat decimalFormat = new DecimalFormat("#");
	private boolean trungMaSP;
	private KhachHang khachHang;
	private LocalDate today;
	private TaiKhoan taiKhoan;
	private NhanVien nhanVien;

	public Pnl_PhieuDatHang() {

		setLayout(new BorderLayout());
		JLabel lbl = new JLabel("LẬP PHIẾU ĐẶT HÀNG");
		lbl.setBorder(BorderFactory.createEmptyBorder(60, 0, 10, 0));
		lbl.setFont(new Font("Arial", Font.BOLD, 20));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel pnl_north = new JPanel();
		pnl_north.setBackground(Color.LIGHT_GRAY);

		pnl_north.add(lbl);

		JPanel pnl_west = new JPanel();
		JPanel pnl_ttsp = new JPanel();


		pnl_west.setLayout(new BoxLayout(pnl_west, BoxLayout.Y_AXIS));
		pnl_west.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

		pnl_ttsp.setLayout(new BoxLayout(pnl_ttsp, BoxLayout.Y_AXIS));
		pnl_ttsp.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		Font font = new Font("Arial", Font.PLAIN, 16);

		JLabel lbl_masp = new JLabel("Mã sản phẩm:");
		JLabel lbl_tensp = new JLabel("Tên sản phẩm:");
		JLabel lbl_donVi = new JLabel("Đơn vị:");
		JLabel lbl_giaBan = new JLabel("Giá Bán:");
		JLabel lbl_soLuong = new JLabel("Số lượng:");

		lbl_masp.setFont(font);
		lbl_tensp.setFont(font);
		lbl_donVi.setFont(font);
		lbl_giaBan.setFont(font);
		lbl_soLuong.setFont(font);


		txt_masp = new JTextField();
		txt_tensp = new JTextField();
		txt_donVi = new JTextField();
		txt_giaBan = new JTextField();
		txt_soLuong = new JTextField();

		txt_giaBan.setEditable(false);
		txt_donVi.setEditable(false);

		txt_masp.setFont(font);
		txt_tensp.setFont(font);
		txt_donVi.setFont(font);
		txt_giaBan.setFont(font);
		txt_soLuong.setFont(font);

		txt_masp.setPreferredSize(new Dimension(80, 30));
		txt_tensp.setPreferredSize(new Dimension(285, 30));
		txt_donVi.setPreferredSize(new Dimension(110, 30));
		txt_giaBan.setPreferredSize(new Dimension(150, 30));
		txt_soLuong.setPreferredSize(new Dimension(50, 30));

        FontAwesomeIcon icoCong = new FontAwesomeIcon();
        icoCong.setColor1(new Color(153, 204, 0));
        icoCong.setColor2(new Color(153, 204, 0));
        icoCong.setIcon(javaswingdev.FontAwesome.PLUS);


        FontAwesomeIcon icoTru = new FontAwesomeIcon();
        icoTru.setColor1(Color.BLACK);
        icoTru.setColor2(Color.BLACK);
        icoTru.setIcon(javaswingdev.FontAwesome.MINUS);


		btn_cong = new JButton();
		btn_tru = new JButton();

		btn_cong.setIcon(icoCong.toIcon());
		btn_tru.setIcon(icoTru.toIcon());

		btn_cong.setPreferredSize(new Dimension(30, 30));
		btn_tru.setPreferredSize(new Dimension(30, 30));

		JPanel pnl_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pnl_1.add(lbl_masp);
		pnl_1.add(txt_masp);
		pnl_1.add(lbl_tensp);
		pnl_1.add(txt_tensp);




		JPanel pnl_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		pnl_2.add(lbl_giaBan);
		pnl_2.add(txt_giaBan);
		pnl_2.add(lbl_donVi);
		pnl_2.add(txt_donVi);
		pnl_2.add(lbl_soLuong);
		pnl_2.add(btn_cong);
		pnl_2.add(txt_soLuong);
		pnl_2.add(btn_tru);


		pnl_ttsp.add(pnl_1);
		pnl_ttsp.add(pnl_2);
        pnl_ttsp.setPreferredSize(new Dimension(620, 110));

		pnl_west.add(pnl_ttsp);

		// pnl chức năng

		JPanel pnl_cn = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(60);
        layout.setVgap(15);
        pnl_cn.setLayout(layout);

		btn_timsp = new JButton("  Tìm SP");
		btn_lamMoi = new JButton("  Làm mới");
		btn_them = new JButton("Thêm SP vào PDH");

        FontAwesomeIcon icotim = new FontAwesomeIcon();
        icotim.setColor1(Color.BLUE);
        icotim.setColor2(Color.BLUE);
        icotim.setIcon(javaswingdev.FontAwesome.SEARCH);

        FontAwesomeIcon icoLamMoi = new FontAwesomeIcon();
        icoLamMoi.setColor1(Color.BLUE);
        icoLamMoi.setColor2(Color.BLUE);
        icoLamMoi.setIcon(javaswingdev.FontAwesome.REFRESH);

        btn_timsp.setIcon(icotim.toIcon());
        btn_lamMoi.setIcon(icoLamMoi.toIcon());
		btn_them.setIcon(icoCong.toIcon());

		btn_timsp.setFont(font);
		btn_lamMoi.setFont(font);
		btn_them.setFont(font);


        pnl_cn.add(btn_timsp);
        pnl_cn.add(btn_lamMoi);
        pnl_cn.add(btn_them);

        pnl_cn.setPreferredSize(new Dimension(620, 50));

		pnl_west.add(pnl_cn);

		// Bảng sản phẩm
		String col[] = { "STT", "Mã sản phẩm", "Tên sản phẩm",  "Đơn vị", "Giá bán", "Số lượng tồn"};
		tbl_modelDSSP = new DefaultTableModel(col, 0);
		dsSP = new ArrayList<>();
		spDao = new SanPhamDao();
		try {
			dsSP = spDao.getDSSanPham();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DocDanhSachSanPham();
		tbl_DSSP = new JTable(tbl_modelDSSP);
		TableColumn column = tbl_DSSP.getColumnModel().getColumn(2);
		column.setPreferredWidth(180);

		JScrollPane pnl_DSSP = new JScrollPane(tbl_DSSP);

		pnl_west.add(pnl_DSSP);

		JPanel pnl_cen = new JPanel(new BorderLayout());
		pnl_cen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel pnl_ncen = new JPanel(new BorderLayout());

		JLabel lbl_pdh = new JLabel("PHIẾU ĐẶT HÀNG");
		lbl_pdh.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_pdh.setBorder(new EmptyBorder(20, 0, 20, 0));
		lbl_pdh.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_ncen.add(lbl_pdh, BorderLayout.NORTH);


		Font font1 = new Font("Arial", Font.PLAIN, 14);

		JPanel pnl_ttpdh = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        Dimension sizetxt1 = new Dimension(150, 25);
        Dimension sizetxt = new Dimension(200, 25);

        //thành phần 1
        JPanel pnl1 = new JPanel();
        JLabel lbl1 = new JLabel("Mã PDH:");
        lbl1.setFont(font1);
        pnl1.add(lbl1);
        txt_maPDH = new JTextField();
        txt_maPDH.setFont(font1);
        txt_maPDH.setPreferredSize(sizetxt1);
        txt_maPDH.setEditable(false);
        pnl1.add(txt_maPDH);

        pnl_ttpdh.add(pnl1, gbc);

        // thành phần 2
        gbc.gridx = 1;
        JPanel pnl2 = new JPanel();
        JLabel lbl2 = new JLabel("Nhân viên:");
        lbl2.setFont(font1);
        pnl2.add(lbl2);

        txt_nhanVien = new JTextField();
        txt_nhanVien.setPreferredSize(sizetxt);
        txt_nhanVien.setFont(font1);
        txt_nhanVien.setEditable(false);
        pnl2.add(txt_nhanVien);
        pnl_ttpdh.add(pnl2, gbc);

        // thành phần 3
        gbc.gridy = 1;
        JPanel pnl3 = new JPanel();
        JLabel lbl3 = new JLabel("SDT khách hàng:");
        lbl3.setFont(font1);
        pnl3.add(lbl3);
        txt_SDT = new JTextField();
        txt_SDT.setFont(font1);
        txt_SDT.setPreferredSize(sizetxt);
        pnl3.add(txt_SDT);
        pnl_ttpdh.add(pnl3, gbc);

        // thành phần 4
        gbc.gridx = 0;
        JPanel pnl4 = new JPanel();
        JLabel lbl4 = new JLabel("Ngày lập phiếu:");
        lbl4.setFont(font1);
        pnl4.add(lbl4);
        txt_ngayLap = new JTextField();
        txt_ngayLap.setFont(font1);
        txt_ngayLap.setPreferredSize(sizetxt1);
        txt_ngayLap.setEditable(false);
        pnl4.add(txt_ngayLap);
        pnl_ttpdh.add(pnl4, gbc);

     // thành phần 5
        gbc.gridy = 2;
        JPanel pnl5 = new JPanel();
        JLabel lbl5 = new JLabel("Ngày lấy hàng:");
        lbl5.setFont(font1);
        pnl5.add(lbl5);

        Locale locale = new Locale("vi", "VN");
        Locale.setDefault(locale);
        dcNgayLayHang = new JDateChooser();
        dcNgayLayHang.setLocale(locale);
        dcNgayLayHang.setDateFormatString("yyyy-MM-dd");
        dcNgayLayHang.setFont(font1);
        dcNgayLayHang.setPreferredSize(sizetxt1);
        pnl5.add(dcNgayLayHang);
        pnl_ttpdh.add(pnl5, gbc);

        // thành phần 6
        gbc.gridx = 1;
        JPanel pnl6 = new JPanel();
        JLabel lbl6 = new JLabel("Khách hàng:");
        lbl6.setFont(font1);
        pnl6.add(lbl6);
        txt_khachHang = new JTextField();
        txt_khachHang.setEditable(false);
        txt_khachHang.setFont(font1);
        txt_khachHang.setPreferredSize(sizetxt);
        pnl6.add(txt_khachHang);
        pnl_ttpdh.add(pnl6, gbc);

        // thành phần 7
        gbc.gridx = 0;
        gbc.gridy = 3;
        JPanel pnl7 = new JPanel();
        JLabel lbl7 = new JLabel("Số lượng:");
        lbl7.setFont(font1);
        pnl7.add(lbl7);
        txt_soLuongPDH = new JTextField();
        txt_soLuongPDH.setFont(font1);
        txt_soLuongPDH.setPreferredSize(new Dimension(57, 25));
        txt_soLuongPDH.setEditable(false);
        btn_truSL = new JButton();
        btn_truSL.setIcon(icoTru.toIcon());
        btn_truSL.setPreferredSize(new Dimension(25, 25));

        btn_Xoa = new JButton("Xóa");
        btn_Xoa.setFont(font1);
        btn_Xoa.setPreferredSize(new Dimension(60, 25));

        pnl7.add(txt_soLuongPDH);
        pnl7.add(btn_truSL);
        pnl7.add(btn_Xoa);
        pnl_ttpdh.add(pnl7, gbc);
        pnl_ncen.add(pnl_ttpdh, BorderLayout.CENTER);
        // Bảng PDH

        String header_pdh[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Đơn vị","Số lượng", "Đơn giá"};

        tbl_modelPDH = new DefaultTableModel(header_pdh, 0);
        tbl_PDH = new JTable(tbl_modelPDH);
		TableColumn column1 = tbl_PDH.getColumnModel().getColumn(2);
		column1.setPreferredWidth(175);

        JScrollPane pnl_PDH = new JScrollPane(tbl_PDH);




        JPanel pnl_sc = new JPanel();
        pnl_sc.setLayout(new BoxLayout(pnl_sc, BoxLayout.Y_AXIS));
        pnl_sc.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel lbl_thanhTien = new JLabel("Thành tiền:");
        lbl_thanhTien.setFont(font);
        txt_thanhTien = new JTextField();
        txt_thanhTien.setFont(font);
        txt_thanhTien.setPreferredSize(sizetxt1);
        txt_thanhTien.setEditable(false);

        JPanel pnl8 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnl8.add(lbl_thanhTien);
        pnl8.add(txt_thanhTien);

        JLabel lbl_tkd = new JLabel("Tiền khách đưa:");
        lbl_tkd.setFont(font);
        txt_tienKhachDua = new JTextField();
        txt_tienKhachDua.setFont(font);
        txt_tienKhachDua.setPreferredSize(sizetxt1);

        JPanel pnl9 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnl9.add(lbl_tkd);
        pnl9.add(txt_tienKhachDua);

        JLabel lbl_tt = new JLabel("Tiền thừa:");
        lbl_tt.setFont(font);
        txt_tienThua = new JTextField();
        txt_tienThua.setEditable(false);
        txt_tienThua.setFont(font);
        txt_tienThua.setPreferredSize(sizetxt1);

        JPanel pnl10 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnl10.add(lbl_tt);
        pnl10.add(txt_tienThua);


        JPanel pnl_tt = new JPanel();
		FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER);
        layout1.setHgap(60);
        layout1.setVgap(15);
        pnl_tt.setLayout(layout1);

		btn_thanhToan = new JButton(" Thanh toán");
		btn_lapPDH = new JButton(" Lập phiếu đặt hàng");


        FontAwesomeIcon icoThanhToan = new FontAwesomeIcon();
        icoThanhToan.setColor1(Color.GREEN);
        icoThanhToan.setColor2(Color.GREEN);
        icoThanhToan.setIcon(javaswingdev.FontAwesome.MONEY);

        FontAwesomeIcon icoLapPDH= new FontAwesomeIcon();
        icoLapPDH.setIcon(javaswingdev.FontAwesome.PRINT);

        btn_thanhToan.setIcon(icoThanhToan.toIcon());
        btn_lapPDH.setIcon(icoLapPDH.toIcon());


		btn_thanhToan.setFont(font);
		btn_lapPDH.setFont(font);


        pnl_tt.add(btn_thanhToan);
        pnl_tt.add(btn_lapPDH);


        pnl_sc.add(pnl8);
        pnl_sc.add(pnl9);
        pnl_sc.add(pnl10);
        pnl_sc.add(pnl_tt);

        pnl_cen.add(pnl_sc, BorderLayout.SOUTH);
        pnl_cen.add(pnl_ncen, BorderLayout.NORTH);
        pnl_cen.add(pnl_PDH, BorderLayout.CENTER);

        // Ngày lập phiếu đặt
        today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = today.format(formatter);
        txt_ngayLap.setText(dateString);

        CapNhatTxt_MaPDH();

        Frm_DangNhap frm_DangNhap = new Frm_DangNhap();
        taiKhoan = frm_DangNhap.getTaiKhoan();
        NhanVienDao nvDao = new NhanVienDao();
        try {
			nhanVien = nvDao.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNV());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        txt_nhanVien.setText(nhanVien.getHoTenNV());

        // sự kiện của các button và table
        tbl_DSSP.addMouseListener(this);
        tbl_PDH.addMouseListener(this);
        btn_cong.addActionListener(this);
        btn_tru.addActionListener(this);
        btn_timsp.addActionListener(this);
        btn_lamMoi.addActionListener(this);
        btn_them.addActionListener(this);
        btn_truSL.addActionListener(this);
        btn_Xoa.addActionListener(this);
        btn_thanhToan.addActionListener(this);
        btn_lapPDH.addActionListener(this);
        txt_SDT.addKeyListener(this);
        txt_tienKhachDua.addKeyListener(this);
        txt_soLuong.addKeyListener(this);
        txt_masp.addKeyListener(this);
        txt_tensp.addKeyListener(this);

        add(pnl_north, BorderLayout.NORTH);
        add(pnl_west, BorderLayout.WEST);
        add(pnl_cen, BorderLayout.CENTER);
	}

	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(1400, 800);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(tbl_DSSP)) {
			ChuyenDuLieuTuBangVaoTextField();
		}else if(o.equals(tbl_PDH)) {
			int i = tbl_PDH.getSelectedRow();
			txt_soLuongPDH.setText(tbl_modelPDH.getValueAt(i, 4)+"");
			for (int j = 0; j < tbl_DSSP.getRowCount(); j++) {
				if(tbl_modelDSSP.getValueAt(j, 1).equals(tbl_modelPDH.getValueAt(i, 1))) {
					tbl_DSSP.setRowSelectionInterval(j, j);
					ChuyenDuLieuTuBangVaoTextField();
				}
			}

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btn_lamMoi)) {
			XoaRong();
		}else if(o.equals(btn_them)) {
			ThemSPVaoPDH();
		}else if(o.equals(btn_Xoa)) {
			XoaSPKhoiPDH();
		}else if(o.equals(btn_cong)) {
			if(KiemTraSoLuong()) {
				int soLuong = Integer.parseInt(txt_soLuong.getText()) + 1;
				int i = tbl_DSSP.getSelectedRow();
				int soLuongTon = Integer.parseInt(tbl_DSSP.getValueAt(i, 5)+"");
				if(soLuong > soLuongTon) {
					JOptionPane.showMessageDialog(this, "Số lượng phải bé hơn hoặc bằng số lượng tồn!");
					txt_soLuong.requestFocus();
					return;
				}
				txt_soLuong.setText(soLuong + "");
			} else {
				return;
			}
		}else if(o.equals(btn_tru)) {
			if(KiemTraSoLuong()) {
				int soLuong = Integer.parseInt(txt_soLuong.getText()) - 1;
				if(soLuong <= 0) {
					JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
					txt_soLuong.requestFocus();
					return;
				}
				txt_soLuong.setText(soLuong + "");
			} else {
				return;
			}
		}else if(o.equals(btn_truSL)) {
			TruSoLuong();
		}else if(o.equals(btn_thanhToan)) {
			ThanhToan();
		}else if(o.equals(btn_lapPDH)) {
			LapPhieuDat();
		}else if(o.equals(btn_timsp)) {
			TimSP();
		}

	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			if(o.equals(txt_SDT)) {
				TimSDT();
			} else if(o.equals(txt_tienKhachDua)) {
				TienKhachDua();
			} else if(o.equals(txt_soLuong)) {
				if(!KiemTraSoLuong()) {
					return;
				} else {
					int i = tbl_DSSP.getSelectedRow();
					int soLuong = Integer.parseInt(txt_soLuong.getText());
					int soLuongTon = Integer.parseInt(tbl_DSSP.getValueAt(i, 5)+"");
					if(soLuong > soLuongTon || soLuong < 1) {
						JOptionPane.showMessageDialog(this, "Số lượng phải > 1 và <= số lượng tồn!");
						txt_soLuong.requestFocus();
						return;
					}
				}

			}else if(o.equals(txt_masp)) {
				TimSP();

			}else if(o.equals(txt_tensp)) {
				TimSP();
			}
		}

	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void XoaRong() {
		txt_masp.setText("");
		txt_tensp.setText("");
		txt_giaBan.setText("");
		txt_donVi.setText("");
		txt_soLuong.setText("");
		tbl_DSSP.clearSelection();
	}

	private void DocDanhSachSanPham() {
		int i = 0;
		for (SanPham s : dsSP) {
			String giaBan = decimalFormat.format(s.getGiaBan());
			Object[] row = {i++, s.getMaSanPham(), s.getTenSanPham(), s.getDonVi(), giaBan, s.getSoLuongTon()};
			tbl_modelDSSP.addRow(row);
		}
	}
	private void ChuyenDuLieuTuBangVaoTextField() {
		int i = tbl_DSSP.getSelectedRow();
		String maSP = tbl_modelDSSP.getValueAt(i, 1) + "";
		String tenSP = tbl_modelDSSP.getValueAt(i, 2) + "";
		String donVi = tbl_modelDSSP.getValueAt(i, 3) + "";
		String giaBan = tbl_modelDSSP.getValueAt(i, 4) + "";

		txt_masp.setText(maSP);
		txt_tensp.setText(tenSP);
		txt_donVi.setText(donVi);
		txt_giaBan.setText(giaBan);
		txt_soLuong.setText(1+"");
	}

	private void ThemSPVaoPDH() {
		if(txt_masp.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trước khi thêm!");
			return;
		}
		if (!KiemTraSoLuong()) {
			return;
		}
		String maSP = txt_masp.getText();
		Double giaBan = Double.parseDouble(txt_giaBan.getText());
		int soLuong = Integer.parseInt(txt_soLuong.getText());
		trungMaSP = false;
		for (int i = 0; i < tbl_modelPDH.getRowCount(); i++) {
			if(tbl_PDH.getValueAt(i, 1).equals(maSP)) {
				int soLuongNew = Integer.parseInt(tbl_modelPDH.getValueAt(i, 4)+"") + soLuong;
				tbl_modelPDH.setValueAt(soLuongNew, i, 4);
				tbl_modelPDH.setValueAt(decimalFormat.format(giaBan*soLuongNew), i, 5);
				trungMaSP = true;
				break;
			}
		}
		if(!trungMaSP) {
			Object[] row = {tbl_modelPDH.getRowCount() + 1, maSP, txt_tensp.getText(), txt_donVi.getText(), soLuong,
					decimalFormat.format(giaBan*soLuong)};
			tbl_modelPDH.addRow(row);
		}
		CapNhatThanhTien();
		CapNhatSLTonKhiThem();
	}

	private void XoaSPKhoiPDH() {
		int i = tbl_PDH.getSelectedRow();
		int t = tbl_PDH.getSelectedRow() + 1;
		if(i != -1) {
			int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa sản phẩm này "
			 		+ "ra khỏi phiếu đặt hàng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		    if (choice == JOptionPane.YES_OPTION) {
		    	CapNhatSLTonKhiXoa();
				tbl_modelPDH.removeRow(i);
				txt_soLuongPDH.setText("");
				for (int j = i; j < tbl_modelPDH.getRowCount(); j++) {
					tbl_modelPDH.setValueAt(t++, j, 0);
				}
		    } else {
				return;
			}
		}else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn xóa khỏi danh sách đặt hàng!");
			return;
		}
		CapNhatThanhTien();
	}
	private void CapNhatThanhTien() {
		Double thanhTien = 0.0;
		for (int i = 0; i < tbl_PDH.getRowCount(); i++) {
			Double t = Double.parseDouble(tbl_modelPDH.getValueAt(i, 5)+"");
			thanhTien += t;
		}
		txt_thanhTien.setText(decimalFormat.format(thanhTien));
	}
	private void CapNhatSLTonKhiThem() {
		int i = tbl_DSSP.getSelectedRow();
		int soLuong = Integer.parseInt(txt_soLuong.getText());
		int soLuongTon = Integer.parseInt(tbl_modelDSSP.getValueAt(i, 5)+"");
		if(soLuong > soLuongTon || soLuong < 1) {
			JOptionPane.showMessageDialog(this, "Số lượng phải > 0 và < số lượng tồn!");
			return;
		}
		tbl_modelDSSP.setValueAt(soLuongTon - soLuong, i, 5);
	}
	private boolean KiemTraSoLuong() {
		if(txt_soLuong.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Số lượng không được để trống!");
			txt_soLuong.requestFocus();
			return false;
		}else if(!txt_soLuong.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng của số!");
			txt_soLuong.requestFocus();
			return false;
		}
		return true;
	}
	private void CapNhatSLTonKhiXoa() {
		int i = tbl_PDH.getSelectedRow();
		String maSP = tbl_modelPDH.getValueAt(i, 1) +"";
		int soLuong = Integer.parseInt(tbl_modelPDH.getValueAt(i, 4)+"");
		for (int j = 0; j < tbl_modelDSSP.getRowCount(); j++) {
			if(tbl_modelDSSP.getValueAt(j, 1).equals(maSP)) {
				int soLuongTon = Integer.parseInt(tbl_modelDSSP.getValueAt(j, 5)+"");
				tbl_modelDSSP.setValueAt(soLuongTon + soLuong, j, 5);
			}
		}
	}
	private void TruSoLuong() {
		int i = tbl_PDH.getSelectedRow();
		if(i != -1) {
			String maSP = tbl_modelPDH.getValueAt(i, 1) +"";
			int soLuong = Integer.parseInt(tbl_modelPDH.getValueAt(i, 4)+"");
			if(soLuong - 1 == 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn xóa khi bớt số lượng bằng 0!");
				return;
			}
			Double giaBan = 0.0;
			txt_soLuongPDH.setText((soLuong - 1) + "");
			tbl_modelPDH.setValueAt(soLuong - 1, i, 4);
			for (int j = 0; j < tbl_modelDSSP.getRowCount(); j++) {
				if(tbl_modelDSSP.getValueAt(j, 1).equals(maSP)) {
					giaBan = Double.parseDouble(tbl_modelDSSP.getValueAt(j, 4)+"");
					int soLuongTon = Integer.parseInt(tbl_modelDSSP.getValueAt(j, 5)+"");
					tbl_modelDSSP.setValueAt(soLuongTon + 1, j, 5);
				}
			}
			Double donGia = giaBan*(soLuong - 1);
			tbl_modelPDH.setValueAt(decimalFormat.format(donGia), i, 5);
		}else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn bớt số lượng khỏi danh sách đặt hàng!");
			return;
		}
		CapNhatThanhTien();
	}

	private void TimSDT() {
		if(!txt_SDT.getText().trim().matches("^0\\d{9}$")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
			txt_SDT.requestFocus();
			return;
		}else {
			KhachHangDao KHDao = new KhachHangDao();
			khachHang = new KhachHang();
			try {
				khachHang = KHDao.timKhachHangTheoSDT(txt_SDT.getText()+"");
				if(khachHang.getHoTenKH() == null) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!");
					return;
				}
				txt_khachHang.setText(khachHang.getHoTenKH());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	private void TienKhachDua() {
		if(ValidTienKhachDua()) {
			Double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText());
			Double thanhTien = Double.parseDouble(txt_thanhTien.getText());
			if (tienKhachDua < thanhTien) {
				JOptionPane.showMessageDialog(this, "Tiền khách đưa không được nhỏ hơn thành tiền!");
				txt_tienKhachDua.requestFocus();
				return;
			}
			txt_tienThua.setText(decimalFormat.format(tienKhachDua - thanhTien));
		}

	}
	private boolean ValidTienKhachDua() {
		if (txt_tienKhachDua.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Tiền khách đưa không được để trống!");
			txt_tienKhachDua.requestFocus();
			return false;
		}else if(!txt_tienKhachDua.getText().matches("\\d+")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng tiền!");
			txt_tienKhachDua.requestFocus();
			return false;
		}
		return true;
	}
	private boolean ValidThanhToan() {
		if(dcNgayLayHang.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Ngày lấy hàng không được để trống!");
			dcNgayLayHang.requestFocus();
			return false;
		}else if(txt_SDT.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
			txt_SDT.requestFocus();
			return false;
		}else if(tbl_PDH.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng đặt hàng thêm sản phẩm!");
			return false;
		}else if(txt_tienKhachDua.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Tiền khách đưa không được để trống!");
			txt_tienKhachDua.requestFocus();
			return false;
		}
		return true;
	}
	private boolean ValidLapPhieuDat() {
		if(dcNgayLayHang.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Ngày lấy hàng không được để trống!");
			dcNgayLayHang.requestFocus();
			return false;
		}else if(txt_SDT.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
			txt_SDT.requestFocus();
			return false;
		}else if(tbl_PDH.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng đặt hàng thêm sản phẩm!");
			return false;
		}
		return true;
	}

	private void ThanhToan(){
		if(ValidThanhToan()) {
	        Date ngayLayHang = dcNgayLayHang.getDate();
	        Instant instant = ngayLayHang.toInstant();
	        ZoneId zoneId = ZoneId.systemDefault();
	        LocalDate ngayLH = instant.atZone(zoneId).toLocalDate();

	        PhieuDatHangDao pdhDao = new PhieuDatHangDao();
	        ChiTietPhieuDatDao cTPDHDao = new ChiTietPhieuDatDao();



	        if(khachHang != null) {
	        	TimSDT();
	        }
	        Double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText());
	        Double tongTien = Double.parseDouble(txt_thanhTien.getText());
	        String maPDH = txt_maPDH.getText().trim();
	        PhieuDatHang pdh = new PhieuDatHang(maPDH, nhanVien,
	        		khachHang, today, ngayLH, true, tongTien, tienKhachDua);

	        try {
				if(pdhDao.TaoPDH(pdh) != 0) {
					for (int i = 0; i < tbl_PDH.getRowCount(); i++) {
						SanPham s = new SanPham(tbl_modelPDH.getValueAt(i, 1) + "");
						int soLuong = Integer.parseInt(tbl_modelPDH.getValueAt(i, 4) + "");
						ChiTietPhieuDat ctpdh = new ChiTietPhieuDat(pdh, s, soLuong);
						if(cTPDHDao.TaoCTPDH(ctpdh)==0) {
							JOptionPane.showMessageDialog(this, "Thanh toán không thành công!");
							break;
						}
					}
					JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
					ReloadData();
				}
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			return;
		}
	}
	private void LapPhieuDat(){
		if(ValidLapPhieuDat()) {
			Date ngayLayHang = dcNgayLayHang.getDate();
			Instant instant = ngayLayHang.toInstant();
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDate ngayLH = instant.atZone(zoneId).toLocalDate();

			PhieuDatHangDao pdhDao = new PhieuDatHangDao();
	        ChiTietPhieuDatDao cTPDHDao = new ChiTietPhieuDatDao();
			if(khachHang != null) {
				TimSDT();
			}
			Double tongTien = Double.parseDouble(txt_thanhTien.getText());
			PhieuDatHang pdh = new PhieuDatHang(txt_maPDH.getText().trim(), nhanVien,
					khachHang, today, ngayLH, false, tongTien, 0.0);

			try {
				if(pdhDao.TaoPDH(pdh) != 0) {
					for (int i = 0; i < tbl_PDH.getRowCount(); i++) {
						SanPham s = new SanPham(tbl_modelPDH.getValueAt(i, 1) + "");
						int soLuong = Integer.parseInt(tbl_modelPDH.getValueAt(i, 4) + "");
						ChiTietPhieuDat ctpdh = new ChiTietPhieuDat(pdh, s, soLuong);
						if(cTPDHDao.TaoCTPDH(ctpdh)==0) {
							JOptionPane.showMessageDialog(this, "Lập phiếu đặt hàng không thành công!");
							break;
						}
					}
					JOptionPane.showMessageDialog(this, "Lập phiếu đặt hàng thành công!");
					ReloadData();
				}
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			return;
		}
	}
	private void ReloadData() {
		tbl_modelDSSP.setRowCount(0);
		XoaRong();
		tbl_modelPDH.setRowCount(0);
		dcNgayLayHang.setDate(null);
		txt_SDT.setText("");
		txt_khachHang.setText("");
		txt_thanhTien.setText("");
		txt_tienKhachDua.setText("");
		txt_tienThua.setText("");
		CapNhatTxt_MaPDH();
		DocDanhSachSanPham();
	}
	private void CapNhatTxt_MaPDH() {
        PhieuDatHangDao pdhDao = new PhieuDatHangDao();
        try {
			int sLPDH = pdhDao.getSLPDH() + 1;
			if(sLPDH < 100) {
				txt_maPDH.setText("PDH0" + sLPDH);
			} else {
				txt_maPDH.setText("PDH" + sLPDH);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void TimSP() {
		SanPham sp = new SanPham(txt_masp.getText().trim(), txt_tensp.getText().trim());
		dsSP = null;
		SanPhamDao spDao = new SanPhamDao();
		try {
			dsSP = spDao.TimSPTheoMaHoacTen(sp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(dsSP.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm nào!");
			txt_masp.requestFocus();
			return;
		}else {
			tbl_modelDSSP.setRowCount(0);
			DocDanhSachSanPham();
		}
	}

}
