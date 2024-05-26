package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javaswingdev.GradientDropdownMenu;
import javaswingdev.MenuEvent;

public class Frm_TrangChuQuanLy extends JFrame{
	private Pnl_TrangChu pnl_TrangChu = new Pnl_TrangChu();
	private Pnl_PhieuDatHang pnl_LPDH = new Pnl_PhieuDatHang();
	private Pnl_TimPDH pnl_TimKiemPDH = new Pnl_TimPDH();
	private Pnl_ThanhToanPDH pnl_ThanhToanPDH = new Pnl_ThanhToanPDH();
	private Pnl_TKSanPham pnl_TKSanPham = new Pnl_TKSanPham();
	private Pnl_TimHD pnl_timHD = new Pnl_TimHD();
	private Pnl_LHD pnl_LHD = new Pnl_LHD();
	private Pnl_QLNV pnl_QLNV = new Pnl_QLNV();
	private Pnl_KhachHang pnl_KhachHang = new Pnl_KhachHang();
	public Frm_TrangChuQuanLy() {
			setUndecorated(true);
	        setSize(1400, 800);
	        setResizable(false);
	        setLocationRelativeTo(null);
	        GradientDropdownMenu menu = new GradientDropdownMenu();
	        menu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
	        menu.setGradientColor(new Color(125, 0, 254), new Color(0, 125, 254));
	        menu.setBackground(new Color(0, 125, 254));
	        menu.addItem(" Trang chủ ");
	        menu.addItem("      Khách hàng       ", "Quản lý khách hàng");
	        menu.addItem("       Nhân viên       ", "Quản lý nhân viên");
	        menu.addItem("       Sản phẩm        ", "Quản lý sản phẩm");
	        menu.addItem("        Hóa đơn        ", "Tìm kiếm hóa đơn", "Lập hóa đơn");
	        menu.addItem("		Phiếu đặt hàng	 ", "Lập phiếu đặt", "Thanh toán phiếu đặt", "Tìm kiếm phiếu đặt");
	        menu.applay(this);

	        JPanel pnl_center = new JPanel();
	        pnl_center.setLayout(new BorderLayout());
	        pnl_center.add(pnl_TrangChu, BorderLayout.CENTER);
	        menu.addEvent(new MenuEvent(){






				//				@Override
	        	 @Override
				public void selected(int index, int subIndex, boolean menuItem){
		                if(menuItem) {
		                    String selectedMenuItem = menu.getMenuNameAt(index, subIndex).trim();

		                    if(selectedMenuItem.equals("Trang chủ")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_TrangChu, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }else if(selectedMenuItem.equals("Quản lý nhân viên")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_QLNV, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }else
		                    if(selectedMenuItem.equals("Quản lý sản phẩm")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_TKSanPham, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }else
		                    	if(selectedMenuItem.equals("Tìm kiếm phiếu đặt")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_TimKiemPDH, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }else if(selectedMenuItem.equals("Lập phiếu đặt")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_LPDH, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }
		                    else if(selectedMenuItem.equals("Thanh toán phiếu đặt")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_ThanhToanPDH, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }
		                    else if(selectedMenuItem.equals("Tìm kiếm hóa đơn")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_timHD, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }
		                    else if(selectedMenuItem.equals("Lập hóa đơn")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_LHD, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }else if(selectedMenuItem.equals("Quản lý khách hàng")) {
		                        pnl_center.removeAll();
		                        pnl_center.add(pnl_KhachHang, BorderLayout.CENTER);
		                        pnl_center.revalidate();
		                        pnl_center.repaint();
		                    }
		                }
		            }
		        });
		        this.add(pnl_center, BorderLayout.CENTER);
		   }


}
