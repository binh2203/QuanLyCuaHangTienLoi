package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import connectDB.DBConnection;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.attribute.AclEntry;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;


public class Pnl_TimHD extends JPanel implements ActionListener, MouseListener, ListSelectionListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtTimHD;
	private JTable tableHoaDon;
	private DefaultTableModel modalTableHD;
	private DefaultTableModel modalTableCTHD;
	private JTextField txtMaHD;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtNgayLapHD;
	private JTextField txtTenNV;
	private JTextField txtMaNV;
	private JTable tableChiTiet;
	private JTextField txtTongTT;
	private JTextField txtGiamGia;
	private JComboBox locHoaDon;
	private HoaDonDAO hoaDonDao;
	private ChiTietHoaDonDAO cTHoaDonDao;
	/**
	 * Create the panel.
	 */
	public Pnl_TimHD() {
		setLayout(null);
		hoaDonDao = new HoaDonDAO();
		cTHoaDonDao = new ChiTietHoaDonDAO();
		
		
		JPanel pnl_DSHD = new JPanel();
		pnl_DSHD.setBorder(new LineBorder(new Color(0, 64, 128), 4));
		pnl_DSHD.setBounds(21, 66, 576, 658);
		add(pnl_DSHD);
		pnl_DSHD.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DANH SÁCH HÓA ĐƠN");
		lblNewLabel.setForeground(new Color(64, 0, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(21, 21, 195, 35);
		pnl_DSHD.add(lblNewLabel);
		
		JLabel lblTopHa = new JLabel("Lọc hóa đơn theo: ");
		lblTopHa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTopHa.setBounds(21, 51, 134, 35);
		pnl_DSHD.add(lblTopHa);
		
		locHoaDon = new JComboBox();
		locHoaDon.setModel(new DefaultComboBoxModel(new String[] {"Top 10 hóa đơn có đơn giá cao nhất", "Tất cả danh sách hóa đơn"}));
		locHoaDon.setBounds(159, 51, 214, 35);
		pnl_DSHD.add(locHoaDon);
		
		txtTimHD = new JTextField();
		txtTimHD.setText("Tìm hóa đơn theo mã");
		txtTimHD.setColumns(10);
		txtTimHD.setBounds(383, 51, 168, 35);
		pnl_DSHD.add(txtTimHD);
		
		JScrollPane hoaDon = new JScrollPane();
		hoaDon.setBounds(10, 96, 556, 540);
		pnl_DSHD.add(hoaDon);
		
		tableHoaDon = new JTable();
		String[] header = {"Mã hóa đơn","Mã nhân viên","Mã khách hàng","Ngày lập hóa đơn","Tiền khách đưa","Trạng thái"};
		modalTableHD = new DefaultTableModel(header,0);
		tableHoaDon.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"M\u00E3 h\u00F3a \u0111\u01A1n", "M\u00E3 nh\u00E2n vi\u00EAn", "M\u00E3 kh\u00E1ch h\u00E0ng", "Ng\u00E0y l\u1EADp h\u00F3a \u0111\u01A1n", "Ti\u1EC1n kh\u00E1ch \u0111\u01B0a", "Tr\u1EA1ng th\u00E1i"
				}
			));
		tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(85);
		tableHoaDon.getColumnModel().getColumn(3).setPreferredWidth(95);
		tableHoaDon.setForeground(new Color(128, 0, 255));
		tableHoaDon.setBackground(Color.WHITE);
		hoaDon.setViewportView(tableHoaDon);
		
		JPanel pnl_CTHD = new JPanel();
		pnl_CTHD.setLayout(null);
		pnl_CTHD.setBorder(new LineBorder(new Color(0, 64, 128), 4));
		pnl_CTHD.setBounds(618, 66, 745, 658);
		add(pnl_CTHD);
		
		txtMaHD = new JTextField();
		txtMaHD.setEditable(false);
		txtMaHD.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaHD.setForeground(Color.BLACK);
		txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(150, 78, 220, 35);
		pnl_CTHD.add(txtMaHD);
		
		JLabel lblMHan = new JLabel("Mã hóa đơn:");
		lblMHan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMHan.setBounds(23, 78, 95, 35);
		pnl_CTHD.add(lblMHan);
		
		JLabel lblNewLabel_3 = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblNewLabel_3.setForeground(new Color(25, 25, 112));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel_3.setBounds(23, 0, 289, 68);
		pnl_CTHD.add(lblNewLabel_3);
		
		JLabel lblM = new JLabel("Mã khách hàng: ");
		lblM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblM.setBounds(390, 78, 121, 35);
		pnl_CTHD.add(lblM);
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(515, 78, 220, 35);
		pnl_CTHD.add(txtMaKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(515, 123, 220, 35);
		pnl_CTHD.add(txtTenKH);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnKhchHng.setBounds(390, 123, 143, 35);
		pnl_CTHD.add(lblTnKhchHng);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại: ");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSinThoi.setBounds(390, 172, 143, 35);
		pnl_CTHD.add(lblSinThoi);
		
		txtSDT = new JTextField();
		txtSDT.setEditable(false);
		txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSDT.setColumns(10);
		txtSDT.setBounds(515, 172, 220, 35);
		pnl_CTHD.add(txtSDT);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(390, 219, 143, 35);
		pnl_CTHD.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBounds(515, 219, 220, 35);
		pnl_CTHD.add(txtEmail);
		
		JLabel lblNgyLpHa = new JLabel("Ngày lập hóa đơn:");
		lblNgyLpHa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNgyLpHa.setBounds(23, 220, 143, 35);
		pnl_CTHD.add(lblNgyLpHa);
		
		txtNgayLapHD = new JTextField();
		txtNgayLapHD.setEditable(false);
		txtNgayLapHD.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgayLapHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNgayLapHD.setColumns(10);
		txtNgayLapHD.setBounds(150, 220, 220, 35);
		pnl_CTHD.add(txtNgayLapHD);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên: ");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTnNhnVin.setBounds(23, 173, 113, 35);
		pnl_CTHD.add(lblTnNhnVin);
		
		txtTenNV = new JTextField();
		txtTenNV.setEditable(false);
		txtTenNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(150, 173, 220, 35);
		pnl_CTHD.add(txtTenNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(150, 124, 220, 35);
		pnl_CTHD.add(txtMaNV);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên:");
		lblMNhnVin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMNhnVin.setBounds(23, 124, 103, 35);
		pnl_CTHD.add(lblMNhnVin);
		
		JScrollPane chiTiet = new JScrollPane();
		chiTiet.setBounds(10, 264, 725, 335);
		pnl_CTHD.add(chiTiet);
		
		tableChiTiet = new JTable();
		String[] headerCTHD = {"Mã sản phẩm","Tên sản phẩm","Số lượng","Giá bán","Thành tiền"};
		modalTableCTHD = new DefaultTableModel(headerCTHD,0);
		tableChiTiet.setModel(modalTableCTHD);
		tableChiTiet.getColumnModel().getColumn(1).setPreferredWidth(87);
		chiTiet.setViewportView(tableChiTiet);
		
		JLabel lblNewLabel_5 = new JLabel("Tổng thành tiền: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(5, 613, 143, 32);
		pnl_CTHD.add(lblNewLabel_5);
		
		txtTongTT = new JTextField();
		txtTongTT.setEditable(false);
		txtTongTT.setHorizontalAlignment(SwingConstants.CENTER);
		txtTongTT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTongTT.setColumns(10);
		txtTongTT.setBounds(147, 613, 220, 35);
		pnl_CTHD.add(txtTongTT);
		
		JLabel lblNewLabel_5_1 = new JLabel("Mức giảm giá:");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_1.setBounds(500, 613, 143, 32);
		pnl_CTHD.add(lblNewLabel_5_1);
		
		txtGiamGia = new JTextField();
		txtGiamGia.setEditable(false);
		txtGiamGia.setHorizontalAlignment(SwingConstants.CENTER);
		txtGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGiamGia.setColumns(10);
		txtGiamGia.setBounds(622, 613, 113, 35);
		pnl_CTHD.add(txtGiamGia);

		locHoaDon.addActionListener(this);
		txtTimHD.addActionListener(this);
		try {
			updateTableData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableHoaDon.getSelectionModel().addListSelectionListener(this);

	}
	
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(1400, 800); 
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (!e.getValueIsAdjusting()) {
	        int selectedRow = tableHoaDon.getSelectedRow();
	        if (selectedRow != -1) { // If a row is selected
	            String maHoaDon = (String) modalTableHD.getValueAt(selectedRow, 0); // Assuming the first column contains the maHoaDon
	            try {
					loadDataForChiTietHoaDon(maHoaDon);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    }
	}
	private void loadDataForChiTietHoaDon(String maHoaDon) throws SQLException {
	    // Clear existing data in modalTableCTHD
	    modalTableCTHD.setRowCount(0);
	    
	    // Call your DAO method to retrieve the details of the invoice based on maHoaDon
	    HoaDon hoaDon = hoaDonDao.timHoaDonTheoMa(maHoaDon);
	    
	    // Populate pnlChitiethoadon with the details of the invoice
	    if (hoaDon != null) {
	        txtMaHD.setText(hoaDon.getMaHD());
	        txtTenNV.setText(hoaDon.getNhanVien().getHoTenNV());
	        txtMaKH.setText(hoaDon.getKhachHang().getMaKH());
	        txtMaNV.setText(hoaDon.getNhanVien().getMaNV());
	        txtTenKH.setText(hoaDon.getKhachHang().getHoTenKH());
	        txtSDT.setText(hoaDon.getKhachHang().getSDT());
	        txtEmail.setText(hoaDon.getKhachHang().getEmail());
	        txtNgayLapHD.setText(hoaDon.getNgayLapHD().toString());
	        txtTongTT.setText(hoaDon.getTienKhachDua().toString());
	        // Now, you need to load chi tiết hóa đơn for this maHoaDon and populate tableChiTiet
	        List<ChiTietHoaDon> chiTietHoaDonList = cTHoaDonDao.getCTHDByID(maHoaDon);

	        // Loop through the retrieved list and add data to modalTableCTHD
	        for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonList) {
	            modalTableCTHD.addRow(new Object[]{
	                chiTietHoaDon.getSanPham().getMaSanPham(),
	                chiTietHoaDon.getSanPham().getTenSanPham(),
	                chiTietHoaDon.getSoLuong(),
	                chiTietHoaDon.getSanPham().getGiaNhap(),
	                
	            });
	        }
	        
	        // Set the updated modalTableCTHD to tableChiTiet
	        tableChiTiet.setModel(modalTableCTHD);
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		 if (e.getSource() == locHoaDon) {
	            String selectedOption = (String) locHoaDon.getSelectedItem();
	            if (selectedOption.equals("Top 10 hóa đơn có đơn giá cao nhất")) {
	                try {
	                    loadData(hoaDonDao.docTuBangTop10());
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            } else if (selectedOption.equals("Tất cả danh sách hóa đơn")) {
	                try {
	                    loadData(hoaDonDao.docTuBang());
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
	        }

	        if (e.getSource() == txtTimHD) {
	            String maHoaDonCanTim = txtTimHD.getText().trim().toUpperCase();
	            HoaDon hoaDonTimThay = null;
	            try {
	                hoaDonTimThay = hoaDonDao.timHoaDonTheoMa(maHoaDonCanTim);
	            } catch (Exception e1) {
	                e1.printStackTrace();
	            }

	            if (hoaDonTimThay != null) {
	                for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
	                    Object maHoaDon = tableHoaDon.getValueAt(i, 0);
	                    if (maHoaDon != null && maHoaDon.toString().trim().toUpperCase().equals(maHoaDonCanTim)) {
	                        tableHoaDon.setRowSelectionInterval(i, i);
	                        tableHoaDon.scrollRectToVisible(tableHoaDon.getCellRect(i, 0, true));
	                        return;
	                    }
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với mã " + maHoaDonCanTim);
	            }
	        }
	}
    private void loadData(List<HoaDon> dataList) {
        modalTableHD.setRowCount(0);
        for (HoaDon hoaDon : dataList) {
            modalTableHD.addRow(new Object[]{hoaDon.getMaHD(), hoaDon.getNhanVien().getMaNV(), hoaDon.getKhachHang().getMaKH(), hoaDon.getNgayLapHD(), hoaDon.getTienKhachDua()});
        }
    }
    private void updateTableData() throws SQLException {
        List<HoaDon> list = hoaDonDao.docTuBangTop10();
        for (HoaDon hoaDon : list) {
            String[] rowData = {
                    hoaDon.getMaHD(), hoaDon.getNhanVien().getMaNV() + "", hoaDon.getKhachHang().getMaKH() + "", hoaDon.getNgayLapHD() + "", hoaDon.getTienKhachDua() + ""
            };
            modalTableHD.addRow(rowData);
        }
        tableHoaDon.setModel(modalTableHD);
    }

}
