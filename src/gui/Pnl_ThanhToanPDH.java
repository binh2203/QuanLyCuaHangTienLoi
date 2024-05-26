package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
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

import com.toedter.calendar.JDateChooser;

import dao.PhieuDatHangDao;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatHang;
import javaswingdev.FontAwesome;
import javaswingdev.FontAwesomeIcon;

public class Pnl_ThanhToanPDH extends JPanel implements ActionListener, MouseListener, KeyListener{

	private JTextField txt_maPDH;
	private JTextField txt_nhanVien;
	private JTextField txt_khachHang;
	private JTextField txt_soDT;
	private JDateChooser dc_ngayLapPhieu;
	private JDateChooser dc_ngayLayHang;
	private DefaultTableModel tbl_model;
	private JTable tbl;
	private JButton btn_timKiem;
	private JButton btn_lamMoi;
	private JButton btn_xemChiTiet;
	private JTextField txt_tongTien;
	private JTextField txt_tienKhachDua;
	private JTextField txt_tienThua;
	private JButton btn_thanhToan;
	private ArrayList<PhieuDatHang> dsPDH;
	private PhieuDatHangDao pDHDao;
	private DecimalFormat decimalFormat = new DecimalFormat("#");


	public Pnl_ThanhToanPDH(){
		setLayout(new BorderLayout());
		JLabel lbl = new JLabel("THANH TOÁN PHIẾU ĐẶT HÀNG");
		lbl.setBorder(BorderFactory.createEmptyBorder(60, 0, 10, 0));
		lbl.setFont(new Font("Arial", Font.BOLD, 20));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel pnl_north = new JPanel();
		pnl_north.setBackground(Color.LIGHT_GRAY);
		pnl_north.add(lbl);

		Font font = new Font("Arial", Font.PLAIN, 16);
		Dimension size = new Dimension(300, 30);
		Dimension size1 = new Dimension(200, 30);
		Dimension size2 = new Dimension(180, 30);

		JLabel lbl_maPDH = new JLabel("Mã PDH: ");
		JLabel lbl_nhanVien = new JLabel("Nhân viên lập phiếu: ");
		JLabel lbl_khachHang = new JLabel("Khách hàng: ");
		JLabel lbl_soDT = new JLabel("Số điện thoại: ");
		JLabel lbl_ngayLapPhieu = new JLabel("Ngày lập PDH: ");
		JLabel lbl_ngayLayHang = new JLabel("Ngày lấy hàng: ");
		JLabel lbl_trangThai = new JLabel("Trạng thái: ");

		lbl_maPDH.setFont(font);
		lbl_nhanVien.setFont(font);
		lbl_khachHang.setFont(font);
		lbl_soDT.setFont(font);
		lbl_ngayLapPhieu.setFont(font);
		lbl_ngayLayHang.setFont(font);
		lbl_trangThai.setFont(font);

        Locale locale = new Locale("vi", "VN");
        Locale.setDefault(locale);

		txt_maPDH = new JTextField();
		txt_nhanVien = new JTextField();
		txt_khachHang = new JTextField();
		txt_soDT = new JTextField();
		dc_ngayLapPhieu = new JDateChooser();
		dc_ngayLapPhieu.setLocale(locale);
		dc_ngayLapPhieu.setDateFormatString("yyyy-MM-dd");
		dc_ngayLayHang = new JDateChooser();
		dc_ngayLayHang.setLocale(locale);
		dc_ngayLayHang.setDateFormatString("yyyy-MM-dd");

		txt_maPDH.setPreferredSize(size1);
		txt_nhanVien.setPreferredSize(size);
		txt_khachHang.setPreferredSize(size);
		txt_soDT.setPreferredSize(size);
		dc_ngayLapPhieu.setPreferredSize(size1);
		dc_ngayLayHang.setPreferredSize(size1);


		txt_maPDH.setFont(font);
		txt_nhanVien.setFont(font);
		txt_khachHang.setFont(font);
		txt_soDT.setFont(font);
		dc_ngayLapPhieu.setFont(font);
		dc_ngayLayHang.setFont(font);


		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		JPanel pnl4 = new JPanel();
		JPanel pnl5 = new JPanel();
		JPanel pnl6 = new JPanel();


        pnl5.setBorder(new EmptyBorder(0, 100, 0, 0));
        pnl6.setBorder(new EmptyBorder(0, 100, 0, 0));



		pnl1.add(lbl_maPDH);
		pnl1.add(txt_maPDH);
		pnl2.add(lbl_nhanVien);
		pnl2.add(txt_nhanVien);
		pnl3.add(lbl_khachHang);
		pnl3.add(txt_khachHang);
		pnl4.add(lbl_soDT);
		pnl4.add(txt_soDT);
		pnl5.add(lbl_ngayLapPhieu);
		pnl5.add(dc_ngayLapPhieu);
		pnl6.add(lbl_ngayLayHang);
		pnl6.add(dc_ngayLayHang);


		FontAwesomeIcon icoTimKiem = new FontAwesomeIcon();
		icoTimKiem.setIcon(FontAwesome.SEARCH);

		FontAwesomeIcon icoLamMoi = new FontAwesomeIcon();
		icoLamMoi.setIcon(FontAwesome.REFRESH);

		btn_timKiem = new JButton(" Tìm kiếm");
		btn_lamMoi = new JButton(" Làm mới");
		btn_xemChiTiet = new JButton("Xem chi tiết");

		btn_timKiem.setIcon(icoTimKiem.toIcon());
		btn_lamMoi.setIcon(icoLamMoi.toIcon());

		btn_timKiem.setFont(font);
		btn_lamMoi.setFont(font);
		btn_xemChiTiet.setFont(font);

		btn_timKiem.setPreferredSize(size2);
		btn_lamMoi.setPreferredSize(size2);
		btn_xemChiTiet.setPreferredSize(size2);


		JPanel pnl_center = new JPanel(new BorderLayout());
		JPanel pnl_nc = new JPanel(new GridBagLayout());
        pnl_nc.setBorder(new EmptyBorder(25, 0, 20, 0));
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;

        pnl_nc.add(pnl2, gbc);
        gbc.gridx = 1;
        pnl_nc.add(pnl1, gbc);

        gbc.gridy = 1;
        pnl_nc.add(pnl5, gbc);

        gbc.gridx = 0;
        pnl_nc.add(pnl3, gbc);

        gbc.gridy = 2;
        pnl_nc.add(pnl4, gbc);

        gbc.gridx = 1;
        pnl_nc.add(pnl6, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        JPanel pnl8 = new JPanel();
        pnl8.setBorder(new EmptyBorder(0, 100, 0, 0));
        pnl8.add(btn_timKiem);
        pnl_nc.add(pnl8, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        JPanel pnl9 = new JPanel();
        pnl9.setBorder(new EmptyBorder(0, 100, 0, 0));
        pnl9.add(btn_xemChiTiet);
        pnl_nc.add(pnl9, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        JPanel pnl10 = new JPanel();
        pnl10.setBorder(new EmptyBorder(0, 100, 0, 0));
        pnl10.add(btn_lamMoi);
        pnl_nc.add(pnl10, gbc);



        pnl_center.add(pnl_nc, BorderLayout.NORTH);

        String header[] = { "STT", "Mã PDH", "Nhân viên", "Khách hàng","Số điện thoại", "Ngày lập phiếu", "Ngày lấy hàng", "Trạng thái"};

        tbl_model= new DefaultTableModel(header, 0);

        dsPDH = new ArrayList<>();
        pDHDao = new PhieuDatHangDao();

        try {
			dsPDH = (ArrayList<PhieuDatHang>) pDHDao.getDSPhieuDatHang();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DocDuLieuVaoTable(false);
        tbl = new JTable(tbl_model);

        JScrollPane pnl_tbl = new JScrollPane(tbl);
        pnl_center.add(pnl_tbl, BorderLayout.CENTER);

        JPanel pnl_south = new JPanel();
        pnl_south.setBorder(new EmptyBorder(25, 0, 25, 0));

        JLabel lbl_tongTien = new JLabel("Tổng tiền: ");
        JLabel lbl_tienKhachDua = new JLabel("Tiền khách đưa: ");
        JLabel lbl_tienThua = new JLabel("Tiền thừa: ");

        lbl_tongTien.setFont(font);
        lbl_tienKhachDua.setFont(font);
        lbl_tienThua.setFont(font);

        txt_tongTien = new JTextField();
        txt_tienKhachDua = new JTextField();
        txt_tienThua = new JTextField();

        txt_tongTien.setEditable(false);
        txt_tienThua.setEditable(false);

        txt_tongTien.setFont(font);
        txt_tienKhachDua.setFont(font);
        txt_tienThua.setFont(font);

        txt_tongTien.setPreferredSize(size1);
        txt_tienKhachDua.setPreferredSize(size1);
        txt_tienThua.setPreferredSize(size1);

        btn_thanhToan = new JButton(" Thanh toán");
        btn_thanhToan.setPreferredSize(size2);
        btn_thanhToan.setFont(font);

        FontAwesomeIcon icoThanhToan = new FontAwesomeIcon();
        icoThanhToan.setColor1(Color.GREEN);
        icoThanhToan.setColor2(Color.GREEN);
        icoThanhToan.setIcon(FontAwesome.MONEY);

        btn_thanhToan.setIcon(icoThanhToan.toIcon());

        JPanel pnl_11 = new JPanel();
        JPanel pnl_12 = new JPanel();
        JPanel pnl_13 = new JPanel();

        pnl_11.add(lbl_tongTien);
        pnl_11.add(txt_tongTien);
        pnl_12.add(lbl_tienKhachDua);
        pnl_12.add(txt_tienKhachDua);
        pnl_13.add(lbl_tienThua);
        pnl_13.add(txt_tienThua);

        pnl_12.setBorder(new EmptyBorder(0, 30, 0, 30));
        pnl_13.setBorder(new EmptyBorder(0, 0, 0, 100));

        pnl_south.add(pnl_11);
        pnl_south.add(pnl_12);
        pnl_south.add(pnl_13);
        pnl_south.add(btn_thanhToan);

        // Thêm sự kiện
        btn_lamMoi.addActionListener(this);
        btn_thanhToan.addActionListener(this);
        btn_timKiem.addActionListener(this);
        btn_xemChiTiet.addActionListener(this);
        txt_khachHang.addKeyListener(this);
        txt_nhanVien.addKeyListener(this);
        txt_maPDH.addKeyListener(this);
        txt_soDT.addKeyListener(this);
        txt_tienKhachDua.addKeyListener(this);

        tbl.addMouseListener(this);

        add(pnl_center, BorderLayout.CENTER);
		add(pnl_north, BorderLayout.NORTH);
		add(pnl_south, BorderLayout.SOUTH);
	}

	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(1400, 800);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btn_lamMoi)) {
			XoaRong();
		}else if(o.equals(btn_timKiem)) {
			TimPDH();
		}else if(o.equals(btn_thanhToan)) {
			ThanhToan();
		}

	}
	private void DocDuLieuVaoTable(boolean trangThai) {
		int i = 0;
		for (PhieuDatHang p: dsPDH) {
			if (p.isTrangThai() == trangThai) {
				Object[] row = {i++, p.getMaPDH(), p.getNhanVien().getHoTenNV(), p.getKhachHang().getHoTenKH(),
						p.getKhachHang().getSDT(), p.getNgayLapPDH(), p.getNgayLayHang(),  p.isTrangThai() ? "Đã thanh toán" : "Chưa thanh toán",};
				tbl_model.addRow(row);
			}

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
			if(o.equals(txt_tienKhachDua)) {
				if(ValidTienKhachDua()) {
					Double tienKD = Double.parseDouble(txt_tienKhachDua.getText());
					Double tongTien = Double.parseDouble(txt_tongTien.getText());
					if(tienKD < tongTien) {
						JOptionPane.showMessageDialog(this, "Tiền khách đưa phải >= tổng tiền!");
						txt_tienKhachDua.requestFocus();
						return;
					}else {
						txt_tienThua.setText(decimalFormat.format(tienKD - tongTien));
					}
				}
			} else {
				TimPDH();
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			LayDuLieuTuBangLenTxt();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	private void LayDuLieuTuBangLenTxt() throws ParseException {
		int i = tbl.getSelectedRow();
		txt_maPDH.setText(tbl.getValueAt(i, 1)+"");
		txt_nhanVien.setText(tbl.getValueAt(i, 2)+"");
		txt_khachHang.setText(tbl.getValueAt(i, 3)+"");
		txt_soDT.setText(tbl.getValueAt(i, 4)+"");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		dc_ngayLapPhieu.setDate(formatter.parse(tbl.getValueAt(i, 5)+""));
		dc_ngayLayHang.setDate(formatter.parse(tbl.getValueAt(i, 6)+""));
		PhieuDatHangDao pdhDao = new PhieuDatHangDao();
		try {
			Double tongTien = (double) pdhDao.GetTongTien(tbl.getValueAt(i, 1)+"");
			txt_tongTien.setText(decimalFormat.format(tongTien));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void XoaRong() {
		tbl.clearSelection();
		txt_maPDH.setText("");
		txt_nhanVien.setText("");
		txt_khachHang.setText("");
		txt_soDT.setText("");
		dc_ngayLapPhieu.setDate(null);
		dc_ngayLayHang.setDate(null);
	}
	private void TimPDH() {
	    PhieuDatHangDao pDHDao = new PhieuDatHangDao();

	    LocalDate ngayLP = getDateFromJDateChooser(dc_ngayLapPhieu);
	    LocalDate ngayLH = getDateFromJDateChooser(dc_ngayLayHang);

	    PhieuDatHang pdh = new PhieuDatHang(
	            txt_maPDH.getText().trim(),
	            new NhanVien("", txt_nhanVien.getText().trim()),
	            new KhachHang("", txt_khachHang.getText().trim(), txt_soDT.getText().trim()),
	            ngayLP,
	            ngayLH,
	            false,
	            null);

	    try {
	        List<PhieuDatHang> dsPD = pDHDao.timDSPhieuDatHang(pdh);
	        if (dsPD.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy Phiếu đặt hàng nào!");
	            txt_maPDH.requestFocus();
	        } else {
	            tbl_model.setRowCount(0);
	            dsPDH = (ArrayList<PhieuDatHang>) dsPD;
	            DocDuLieuVaoTable(pdh.isTrangThai());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private LocalDate getDateFromJDateChooser(JDateChooser dateChooser) {
	    Date date = dateChooser.getDate();
	    if (date == null) {
	        return null;
	    } else {
	        Instant instant = date.toInstant();
	        ZoneId zoneId = ZoneId.systemDefault();
	        return instant.atZone(zoneId).toLocalDate();
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
	private void ThanhToan() {
		if (tbl.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu đặt hàng muốn thanh toán!");
			return;
		}else if(ValidTienKhachDua()) {
			Double tienKD = Double.parseDouble(txt_tienKhachDua.getText());
			Double tongTien = Double.parseDouble(txt_tongTien.getText());
			if(tienKD < tongTien) {
				JOptionPane.showMessageDialog(this, "Tiền khách đưa phải >= tổng tiền!");
				txt_tienKhachDua.requestFocus();
				return;
			}else {
				PhieuDatHangDao PDHDAO = new PhieuDatHangDao();
				try {
					if(PDHDAO.ThanhToanPDH(txt_maPDH.getText().trim(), tienKD) == 1) {
						JOptionPane.showMessageDialog(this, "Thanh toán thành công!");	
						ReLoadData();
					}
					
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	private void ReLoadData() {
		XoaRong();
		txt_tienKhachDua.setText("");
		txt_tienThua.setText("");
		txt_tongTien.setText("");
		tbl_model.setRowCount(0);
		DocDuLieuVaoTable(false);
	}
}
