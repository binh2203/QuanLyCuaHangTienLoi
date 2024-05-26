package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import dao.NhanVienDao;
import entity.NhanVien;
import entity.TaiKhoan;
import javaswingdev.FontAwesomeIcon;

public class Pnl_TrangChu extends JPanel implements ActionListener{
    private Image backgroundImage;
    private String imagePath = "src/image/trangchu_background.jpg";
	private JLabel lbl_tennv;
	private JLabel lbl_chucVu;
	private JButton btn_dangXuat;
	private FontAwesomeIcon icoDangXuat;
	private JFrame frm_TrangChu;
	private TaiKhoan taiKhoan;
	private JLabel lbl_manv;
	private NhanVien nhanVien;

	public Pnl_TrangChu() {
		frm_TrangChu = new JFrame();
        backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(new BorderLayout());


        Frm_DangNhap frm_DangNhap = new Frm_DangNhap();
        taiKhoan = frm_DangNhap.getTaiKhoan();

        NhanVienDao nvDao = new NhanVienDao();
        try {
			nhanVien = nvDao.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNV());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        JLabel label = new JLabel("CỬA HÀNG TIỆN LỢI");
        label.setBorder(BorderFactory.createEmptyBorder(0, 20, 50, 20));
        label.setForeground(Color.WHITE);
        label.setFont(new Font("arial", Font.BOLD, 30));
        JLabel label1 = new JLabel("Thành viên nhóm 28: Nguyễn Tuấn Anh, Diêm Công Bình, "
        		+ "Nguyễn Tấn Đắt, Nguyễn Văn Kha");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("arial", Font.BOLD, 20));
        label1.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));

        pnl.setBackground(new Color(0, 0, 0, 0));
        pnl.setBorder(BorderFactory.createLineBorder(Color.white));

        lbl_manv = new JLabel("Nhân viên: " + nhanVien.getMaNV());
        lbl_manv.setForeground(Color.WHITE);
        lbl_manv.setFont(new Font("arial", Font.BOLD, 25));
        lbl_manv.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));

        lbl_tennv = new JLabel("Họ và tên: " + nhanVien.getHoTenNV());
        lbl_tennv.setForeground(Color.WHITE);
        lbl_tennv.setFont(new Font("arial", Font.BOLD, 25));
        lbl_tennv.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 80));

        String chucVu = taiKhoan.isChucVu()? "Quản lý" : "Nhân viên";
        lbl_chucVu = new JLabel("Chức vụ: " + chucVu);
        lbl_chucVu.setForeground(Color.WHITE);
        lbl_chucVu.setFont(new Font("arial", Font.BOLD, 25));
        lbl_chucVu.setBorder(BorderFactory.createEmptyBorder(20, 0, 100, 0));

        icoDangXuat = new FontAwesomeIcon();
        icoDangXuat.setColor1(Color.white);
        icoDangXuat.setColor2(Color.WHITE);
        icoDangXuat.setIcon(javaswingdev.FontAwesome.ARROW_LEFT);
        icoDangXuat.setSize(18);

        btn_dangXuat = new JButton("]  Đăng xuất  ");
        btn_dangXuat.setForeground(Color.WHITE);
        btn_dangXuat.setBackground(new java.awt.Color(0, 125, 254));
        btn_dangXuat.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_dangXuat.setFont(new Font("arial", Font.BOLD, 25));
        btn_dangXuat.setIcon(icoDangXuat.toIcon());

        pnl.add(lbl_manv);
        pnl.add(lbl_tennv);

        pnl.add(lbl_chucVu);

        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(btn_dangXuat);
        box.add(Box.createHorizontalGlue());

        pnl.add(box);

        JPanel pnl_west = new JPanel(new BorderLayout());
        pnl_west.setBackground(new Color(0, 0, 0, 0));
        pnl_west.add(label, BorderLayout.NORTH);
        pnl_west.setBorder(BorderFactory.createEmptyBorder(80, 50, 50, 50));
        pnl_west.add(pnl);

        add(label1, BorderLayout.SOUTH);
        add(pnl_west, BorderLayout.WEST);


        btn_dangXuat.addActionListener(this);
	}
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(1400, 800);
	}


	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    if (backgroundImage != null) {
	    	g.drawImage(backgroundImage, 0, 0, 1400, 800, this);
	    }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frm_TrangChu = (JFrame) SwingUtilities.getWindowAncestor(this);
		if(frm_TrangChu != null) {
			try {
				Thread.sleep(1111);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frm_TrangChu.dispose();
	        Frm_DangNhap frm_DangNhap = new Frm_DangNhap();
	        frm_DangNhap.setVisible(true);
		}


	}
}
