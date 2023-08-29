package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import DAO.DBConnect;
import DAO.ImportDao;
import DAO.InvoiceDao;
import DAO.ProductDao;
import DAO.add_account;
import DTO.Account;
import DTO.ImportDetail;
import DTO.Imports;
import DTO.Invoice;
import DTO.InvoiceDetail;
import DTO.Product;
import DTO.Provider;
import DTO.Staff;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class ImportPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	List<Provider> providerList = new ArrayList<>();
	List<Product> productList1 = new ArrayList<>();
	List<Product> productList = new ArrayList<>();
	List<Imports> importsList = new ArrayList<>();
	List<Staff> staffList = new ArrayList<>();
	List<ImportDetail> importsDetailList = new ArrayList<>();
	DefaultTableModel tableModel;
	private JTable table_imports;
	private DefaultTableModel table_model;
	private DefaultTableModel table_model2;
	private JTable table_product2;
	private JTable table_product;
	private JTextField jtfTongTien;
	private JTextField jtfTenSanPham;
	private JTextField jtfMaSanPham;
	private JTextField jtfDonGia;
	private JTextField jtfSoLuong;
	private JTextField jtfNgayNhap;
	private int index = -1;
	private int idProvider;

	private DefaultTableModel table_model3;
	private JTable table_productimports;
	

	public ImportPanel(int id_user) {

	
		setBounds(100, 100, 900, 617);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 880, 601);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Nh\u1EADp h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);

		listProider();
		listStaff();
//		listProduct();
		
		JLabel jlbMaNhaCungCap = new JLabel("Mã NCC");
		jlbMaNhaCungCap.setBounds(327, 38, 88, 16);
		panel.add(jlbMaNhaCungCap);

		JLabel jlbSanPham = new JLabel("Sản phẩm");
		jlbSanPham.setBounds(327, 73, 64, 13);
		panel.add(jlbSanPham);

		JComboBox providerName = new JComboBox();
		providerName.setBounds(387, 38, 110, 26);
		panel.add(providerName);

		DefaultComboBoxModel<Object> comboBoxModel1 = (DefaultComboBoxModel) providerName.getModel();
		comboBoxModel1.removeAllElements();
		for(Provider provider : providerList)
		{
			comboBoxModel1.addElement(provider.getTenNhaCungCap());
		}
		
		
//		for(Provider provider : providerList)
//		{
//			if(provider.getTenNhaCungCap() == providerName.getSelectedItem())
//			{
//				JOptionPane.showConfirmDialog(this, "Bạn có dsdmuốn thêm sản phẩm này!!!");
//
//			}
//		}
		
//		DefaultComboBoxModel<String> comboBoxModel1 = (DefaultComboBoxModel<String>) providerName.getModel();
//		comboBoxModel1.removeAllElements();
//		for (Provider provider : providerList)
//			comboBoxModel1.addElement(provider.getTenNhaCungCap());
		
		
		JButton btn_add = new JButton("Add");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

				
				tableModel = (DefaultTableModel) table_product2.getModel();
				String tensp =  jtfTenSanPham.getText();
				String sl = jtfSoLuong.getText();
				String msp = jtfMaSanPham.getText();
				String dongia = jtfDonGia.getText();
				
				float dg = Float.valueOf(dongia);
				
				int masp = Integer.valueOf(msp);
				
				int sluong = Integer.valueOf(sl);
					
				String dVT = "abc";
				String mota = "abc";
				int ncc = ProductDao.checkncc(masp);
				int maLoai = 1;
				String trangthai = "true";
				
				
				Product product1 = new Product(masp,tensp,sluong,dg,dVT,mota,maLoai,trangthai,ncc);
				productList1.add(product1);		
				tableModel.setRowCount(0);
				
				for(Product product : productList1)
				{
					tableModel.addRow(new Object[]
							{
									product.getMaSanPham(),
									product.getTenSanPham(),
									product.getSoLuong(),
									product.getDonGia(),
									product.ThanhTien(),
							
							});
				}
				  float total = 0;
				for(Product product : productList1)
				{
					total += product.ThanhTien();
		
				}
				String total_new = String.valueOf(total);
				jtfTongTien.setText(total_new);
				jtfSoLuong.setText("");
				

			}
		});
		
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				tableModel = (DefaultTableModel) table_product.getModel();
//				String tensp = jtfTenSanPham.getText();
//				String sl = jtfSoLuong.getText();
//				String dvt = jtfTongTien.getText();
//				String dongia = jtfDonGia.getText();
//				String mota = jtfMoTa.getText();
//				String trangthai = jtfTrangThai.getText();
//				Category category = categoryList.get(categoryName.getSelectedIndex());
//				int idCategory = category.getMaLoai();
//				Provider provider = providerList.get(providerName.getSelectedIndex());
//				int idProvider = provider.getMaNhaCungCap();
//				insert(tensp, dvt, dongia, trangthai, sl, idCategory, idProvider);
				loadProduct();
//				showAll();
//				tableModel.setRowCount(0);
//
//				for (Product product : productList) {
//					tableModel.addRow(new Object[] { product.getMaSanPham(), product.getMaLoai(),
//							product.getTenSanPham(), product.getdVT(), product.getSoLuong(), product.getDonGia(),
//							product.getTrangThai(), product.getMaNhaCungCap()
//
//					});
//				}
			}
		});
		btn_add.setBounds(179, 343, 101, 21);
		panel.add(btn_add);

		JButton btn_order = new JButton("Order");
		btn_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ngayNhap = jtfNgayNhap.getText();
				String tt = jtfTongTien.getText();
				Float tongtien = Float.valueOf(tt);	
				Provider provider = providerList.get(providerName.getSelectedIndex());
				int idProvider = provider.getMaNhaCungCap();
				
				add_account add_account = new add_account();
				Account account=add_account.add(id_user);
				
				System.out.println("'ac"+account.getMaTaiKhoan());

				int maNhanVien = InvoiceDao.checkmanv(account.getMaTaiKhoan());
				
				ImportDao.insertSell(ngayNhap,maNhanVien,idProvider,tongtien);
				int aa = ImportDao.checkmahd(ngayNhap);
				System.out.println("'a"+aa);
				
				for(Product product : productList1)
				{
					ImportDao.insertDetailSell(aa,product.getMaSanPham(),product.getSoLuong(),product.ThanhTien());

				}
				productList1.clear();
				tableModel.setRowCount(0);
				
				for(Product product : productList1)
				{
					tableModel.addRow(new Object[]
							{
									product.getMaSanPham(),
									product.getTenSanPham(),
									product.getSoLuong(),
									product.getDonGia(),
									product.ThanhTien(),
							
							});
				}
				jtfTongTien.setText("");
				jtfMaSanPham.setText("");
				jtfTenSanPham.setText("");
				jtfDonGia.setText("");
				jtfSoLuong.setText("");
				tableModel = (DefaultTableModel) table_imports.getModel();

				loadImport();
				tableModel.setRowCount(0);

				for (Imports imports : importsList) {
					tableModel.addRow(new Object[] { imports.getMaPhieuNhap(),imports.getNgayNhap(), imports.getMaNhaCungCap(), imports.getMaNhanVien(), imports.getTongTien()});
				}
				tableModel = (DefaultTableModel) table_product.getModel();

				loadProduct();
				tableModel.setRowCount(0);

				for (Product product : productList) {
					tableModel.addRow(new Object[] { product.getMaSanPham(), product.getTenSanPham(),
						 product.getSoLuong(), product.getDonGia()

					});
				}

			}
		});
		btn_order.setBounds(695, 320, 155, 21);
		panel.add(btn_order);
		
		
		JLabel jlbTongTien_1 = new JLabel("Tổng tiền");
		jlbTongTien_1.setBounds(695, 221, 64, 13);
		panel.add(jlbTongTien_1);
		
		jtfTongTien = new JTextField();
		jtfTongTien.setColumns(10);
		jtfTongTien.setBounds(695, 246, 155, 40);
		jtfTongTien.enable(false);
		panel.add(jtfTongTien);
		
		JLabel lblNgy = new JLabel("Ngày");
		lblNgy.setBounds(695, 105, 40, 13);
		panel.add(lblNgy);
		
		 //khai báo đối tượng current thuộc class LocalDateTime
	    LocalDateTime current = LocalDateTime.now();
	    //sử dụng class DateTimeFormatter để định dạng ngày giờ theo kiểu pattern
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    //sử dụng phương thức format() để định dạng ngày giờ hiện tại rồi gán cho chuỗi formatted
	    String formatted = current.format(formatter);
		
		jtfNgayNhap = new JTextField(formatted);
		jtfNgayNhap.setForeground(new Color(0, 0, 0));
		jtfNgayNhap.setColumns(10);
		jtfNgayNhap.setBounds(695, 133, 155, 36);
		jtfNgayNhap.enable(false);
		panel.add(jtfNgayNhap);
		
		table_product2 = new JTable(table_model);
		table_product2.setBounds(484, 200, 366, 177);
		Object data1[][] = { { null, null, null, null, null}, { null, null, null, null, null} };
		Object listColumn1[] = { "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành Tiền"};
		JTableHeader header1 = table_product2.getTableHeader();
		header1.setBackground(Color.yellow);
		table_product2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 SP", "T\u00EAn SP", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1", "Thành Tiền"
			}
		));
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(327, 96, 350, 245);
		scrollPane1.setViewportView(table_product2);
		panel.add(scrollPane1);
		panel.setVisible(true);
		
		//table product
		table_product = new JTable(table_model2);
		table_product.setLayout(new BorderLayout());
		Object data2[][] = { { null, null, null}, { null, null, null} };
		Object listColumn2[] = { "Mã SP", "Tên SP", "Đơn giá"};
			
		table_product.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 SP", "T\u00EAn SP", "S\u1ED1 L\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1 "
			}
		));
		table_product.setBounds(26, 70, 404, 358);
		JTableHeader header2 = table_product.getTableHeader();
		header2.setBackground(Color.yellow);
				
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(30, 38, 250, 248);
		scrollPane2.setViewportView(table_product);
		panel.add(scrollPane2);
		panel.setVisible(true);

//		tableModel = (DefaultTableModel) table_product.getModel();
//
//		loadProduct();
//		tableModel.setRowCount(0);
//
//		for (Product product : productList) {
//			tableModel.addRow(new Object[] { product.getMaSanPham(), product.getTenSanPham(), product.getSoLuong(),product.getDonGia()});
//		}
//		
		Provider provider = providerList.get(providerName.getSelectedIndex());
//		int idProvider = provider.getMaNhaCungCap();
		
		providerName.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				for(Provider provider : providerList)
				{
					if(provider.getTenNhaCungCap() == providerName.getSelectedItem())
					{
						String abc1 = provider.getTenNhaCungCap().toString();
						loadProduct1(abc1);
						tableModel = (DefaultTableModel) table_product.getModel();
						tableModel.setRowCount(0);

						for (Product product : productList) {
							tableModel.addRow(new Object[] { product.getMaSanPham(), product.getTenSanPham(), product.getSoLuong(),product.getDonGia()});
						}

					}
				}
				int idd = -1;
				for(Product product : productList1)
				{
					for(Provider provider : providerList)
					{
						if(product.getMaNhaCungCap() == provider.getMaNhaCungCap())
						{
							if(provider.getTenNhaCungCap() ==  providerName.getSelectedItem())
							{
								idd = 0;
							}
							
						}
					}
				
				}
				if(idd != 0)
				{
					productList1.clear();
					tableModel = (DefaultTableModel) table_product2.getModel();
					tableModel.setRowCount(0);

					for (Product product : productList1) {
						tableModel.addRow(new Object[] { product.getMaSanPham(), product.getTenSanPham(), product.getSoLuong(), product.getDonGia()});
					}
				}
				
				
			}
			
//			index  = (int) providerName.getSelectedIndex();
//			if(index >= 0) {
//				System.out.println("ddddadok");
////				Product product = productList.get(index);
////					
////				String tensp = product.getTenSanPham();
////				String tsp = String.valueOf(tensp);
////				int msp = product.getMaSanPham();
////				String masp = String.valueOf(msp);
////				float dongia = product.getDonGia();
////				String dg = String.valueOf(dongia);
////		
////				jtfTenSanPham.setText(tsp.toString());
////				jtfDonGia.setText(dg.toString());
////				jtfMaSanPham.setText(masp.toString());
//			}else
//				System.out.println("dsadok");
			@Override
			public void mousePressed(MouseEvent e) {
//				index  = (int) providerName.getSelectedIndex();
//				if(index >= 0) {
//					System.out.println("ddddadok");
////					Product product = productList.get(index);
////						
////					String tensp = product.getTenSanPham();
////					String tsp = String.valueOf(tensp);
////					int msp = product.getMaSanPham();
////					String masp = String.valueOf(msp);
////					float dongia = product.getDonGia();
////					String dg = String.valueOf(dongia);
////			
////					jtfTenSanPham.setText(tsp.toString());
////					jtfDonGia.setText(dg.toString());
////					jtfMaSanPham.setText(masp.toString());
//				}else
//					System.out.println("dsadok");
			}

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		table_product.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				index  = table_product.getSelectedRow();
				if(index >= 0) {
					Product product = productList.get(index);
						
					String tensp = product.getTenSanPham();
					String tsp = String.valueOf(tensp);
					int msp = product.getMaSanPham();
					String masp = String.valueOf(msp);
					float dongia = product.getDonGia();
					String dg = String.valueOf(dongia);
			
					jtfTenSanPham.setText(tsp.toString());
					jtfDonGia.setText(dg.toString());
					jtfMaSanPham.setText(masp.toString());
				}else
					System.out.println("dsadok");
			}

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		JLabel jlbMaSanPham = new JLabel("Mã SP");
		jlbMaSanPham.setBounds(30, 298, 36, 13);
		panel.add(jlbMaSanPham);
		
		JLabel jlbDonGia = new JLabel("Đơn giá");
		jlbDonGia.setBounds(137, 298, 55, 13);
		panel.add(jlbDonGia);
		
		JLabel jlbTenSanPham = new JLabel("Tên SP");
		jlbTenSanPham.setBounds(30, 323, 64, 13);
		panel.add(jlbTenSanPham);
		
		JLabel jlbSoLuong = new JLabel("Số lượng");
		jlbSoLuong.setBounds(30, 348, 64, 13);
		panel.add(jlbSoLuong);
		
		jtfTenSanPham = new JTextField();
		jtfTenSanPham.setColumns(10);
		jtfTenSanPham.setBounds(82, 316, 198, 26);
		jtfTenSanPham.enable(false);
		panel.add(jtfTenSanPham);
		
		jtfMaSanPham = new JTextField();
		jtfMaSanPham.setColumns(10);
		jtfMaSanPham.setBounds(75, 291, 55, 26);
		jtfMaSanPham.enable(false);
		panel.add(jtfMaSanPham);
		
		jtfDonGia = new JTextField();
		jtfDonGia.setBounds(190, 291, 90, 26);
		jtfDonGia.enable(false);
		panel.add(jtfDonGia);
		
		jtfSoLuong = new JTextField();
		jtfSoLuong.setColumns(10);
		jtfSoLuong.setBounds(92, 341, 75, 26);
		panel.add(jtfSoLuong);
		
		
		table_imports = new JTable(table_model3);
		table_imports.setLayout(new BorderLayout());
		
		Object data[][] = { { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null } };
		
		Object listColumn[] = { "Mã Nhập Hàng", "Ngày nhập","Giờ Nhập","Mã NCC", "Mã NV", "Tổng tiền"};
	
		table_imports.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 Nh\u1EADp H\u00E0ng", "Ng\u00E0y nh\u1EADp", "M\u00E3 NCC", "M\u00E3 NV", "T\u1ED5ng ti\u1EC1n", "New column"
			}
		));
		table_imports.setBounds(695, 320, 155, 21);
		JTableHeader header3 = table_imports.getTableHeader();
		header3.setBackground(Color.yellow);
		table_imports.setModel(new DefaultTableModel(data, listColumn));
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(30, 402, 442, 178);
		scrollPane3.setViewportView(table_imports);
		panel.add(scrollPane3);
		
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int selectedIndex = table_product2.getSelectedRow();
				if(selectedIndex >= 0)
				{
					
				
//					Product product1 = productList1.get(selectedIndex);
					int option = JOptionPane.showConfirmDialog(btn_delete, "Bạn Có Muốn Xóa Sản Phẩm Này !!!");
					if(option == 0) {
						
								productList1.remove(selectedIndex);
								tableModel.setRowCount(0);
								
								for(Product product : productList1)
								{
									tableModel.addRow(new Object[]
											{
													product.getMaSanPham(),
													product.getTenSanPham(),
													product.getSoLuong(),
													product.getDonGia(),
													product.ThanhTien(),
											
											});
								}
								  float total = 0;
								for(Product product1 : productList1)
								{
									total += product1.ThanhTien();
						
								}
								String total_new = String.valueOf(total);
								jtfTongTien.setText(total_new);
								
								jtfMaSanPham.setText("");
								jtfTenSanPham.setText("");
								jtfDonGia.setText("");
								jtfSoLuong.setText("");
						}
						
					
					}
			}
		});
		btn_delete.setBounds(387, 346, 85, 21);
		panel.add(btn_delete);
		
		JButton btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btn_delete, "Bạn Có Muốn Xóa All Sản Phẩm Này !!!");
				if(option == 0) {
					productList1.clear();
					tableModel = (DefaultTableModel) table_product2.getModel();
					tableModel.setRowCount(0);
					
					for(Product product : productList1)
					{
						tableModel.addRow(new Object[]
								{
										product.getMaSanPham(),
										product.getTenSanPham(),
										product.getSoLuong(),
										product.getDonGia(),
										product.ThanhTien(),
								
								});
					}
					jtfTongTien.setText("0.0");
					}
			}
		});
		btnDeleteAll.setBounds(508, 346, 99, 21);
		panel.add(btnDeleteAll);
		
		JScrollPane scrollPane3_1 = new JScrollPane();
		scrollPane3_1.setBounds(512, 402, 338, 178);
		panel.add(scrollPane3_1);
		
		table_productimports = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 CTPN ", "M\u00E3 Phi\u1EBFu Nh\u1EADp", "M\u00E3 SP", "S\u1ED1 L\u01B0\u1EE3ng", "Th\u00E0nh Ti\u1EC1n"
			}
		));
		scrollPane3_1.setViewportView(table_productimports);
		
		JLabel lblDanhSachsNhajap = new JLabel("Danh Sách Nhập Hàng");
		lblDanhSachsNhajap.setBounds(27, 379, 155, 13);
		panel.add(lblDanhSachsNhajap);
		
		JLabel lblChiTitPhiu = new JLabel("Chi Tiết Phiếu Nhập");
		lblChiTitPhiu.setBounds(470, 377, 155, 13);
		panel.add(lblChiTitPhiu);
		panel.setVisible(true);
		
		tableModel = (DefaultTableModel) table_imports.getModel();

		loadImport();
		tableModel.setRowCount(0);

		for (Imports imports : importsList) {
			Date ngay = imports.getNgayNhap();
			Time gio = imports.getNgaynhap();
			String ngaygio1 = String.valueOf(ngay);
			String ngaygio2 = String.valueOf(gio);
			String trungian = " - ";
			String ngaygio3 = ngaygio1.concat(trungian);
			String ngaygio = ngaygio3.concat(ngaygio2);
			tableModel.addRow(new Object[] { imports.getMaPhieuNhap(),imports.getNgayNhap(),imports.getNgaynhap(), imports.getMaNhaCungCap(), imports.getMaNhanVien(), imports.getTongTien()});
		}
		table_imports.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				index  = table_imports.getSelectedRow();
			
				if(index >= 0) {
					
					Imports imports = importsList.get(index);
					int ma = imports.getMaPhieuNhap();
	
					loadInvoiceDetail(ma);
					tableModel = (DefaultTableModel) table_productimports.getModel();
					tableModel.setRowCount(0);
					
					for (ImportDetail importsDetail : importsDetailList) {
						tableModel.addRow(new Object[] {importsDetail.getMaChiTietPhieuNhap(), importsDetail.getMaPhieuNhap(),
								importsDetail.getMaSanPham(), importsDetail.getSoLuong(), importsDetail.getThanhTien()});
					}
				}else
					System.out.println("dsadok");
			}

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}

	private void setDefaultCloseOperation(int exitOnClose) {}

	public void insert(String tensp, String dvt, String dongia, String trangthai, String sl, int idCategory, int idProvider) {
		try {
			Connection cons = DBConnect.getConnection();
			initComponet();
			String sql = "INSERT INTO Product (maLoai, tenSanPham, dVT, soLuong, donGia, trangThai,`maNhaCungCap`) VALUES ( ?, ? , ?, ?, ?, ?,?)";
			PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setLong(1, idCategory);
			ps.setString(2, tensp);
			ps.setString(3, dvt);
			ps.setString(4, sl);
			ps.setString(5, dongia);
			ps.setString(6, trangthai);
			ps.setLong(7, idProvider);

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			int generatedKeys = 0;
			if (rs.next()) {
				generatedKeys = rs.getInt(1);
			}
			ps.close();
			rs.close();
			cons.close();
			JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm sản phẩm này!!!");
		} catch (SQLException e) {
			
		}
	}
	private void loadProduct1(String id) {
		productList.clear();
		try {
			
			Connection cons = DBConnect.getConnection();
			String sql = "select product.*,category.maLoai idCategory, provider.maNhaCungCap idProvider from product INNER JOIN category ON  product.maLoai = category.maLoai INNER JOIN provider ON product.maNhaCungCap = provider.maNhaCungCap  WHERE product.trangThai = 'true' AND provider.tenNhaCungCap = '"+id+"'";

//			String sql1 = "select product.*,category.maLoai idCategory, provider.maNhaCungCap idProvider  from product INNER JOIN category 	ON  product.maLoai = category.maLoai INNER JOIN provider 	ON product.maNhaCungCap = provider.maNhaCungCap  WHERE `provider.tenNhaCungCap` ="  + id;
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				
				Product product = new Product(r1.getInt("maSanPham"), r1.getString("tenSanPham"), r1.getInt("soLuong"),
						r1.getFloat("donGia"), r1.getString("dVT"),r1.getString("moTa"), r1.getInt("idCategory"), r1.getString("trangThai"),
						r1.getInt("idProvider"));
				productList.add(product);
				
			}
			
			 r1.close();
			 

			cons.close();
			
		} catch (SQLException e) {
			
		}
	}
	private void loadProduct() {
		productList.clear();
		try {
			
			Connection cons = DBConnect.getConnection();
			
			String sql = "select product.*,category.maLoai idCategory, provider.maNhaCungCap idProvider  from product INNER JOIN category 	ON  product.maLoai = category.maLoai INNER JOIN provider 	ON product.maNhaCungCap = provider.maNhaCungCap";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				
				Product product = new Product(r1.getInt("maSanPham"),r1.getString("tenSanPham"),r1.getInt("soLuong"),r1.getFloat("donGia"),r1.getString("dVT"),r1.getString("moTa"),r1.getInt("idCategory"),r1.getString("trangThai"),r1.getInt("idProvider"));
				productList.add(product);
				
			}
			
			 r1.close();
			 

			cons.close();
			
		} catch (SQLException e) {

		}
	}
	private void loadImport() {
		importsList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Import`";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Imports imports = new Imports(r1.getInt("maNhapHang"), r1.getDate("ngayNhap"),r1.getTime("ngaynhap"), r1.getInt("maNhaCungCap"), r1.getInt("maNhanVien"), r1.getFloat("tongTien"));
				importsList.add(imports);
				System.out.println(""+r1.getTime("ngayNhap")+r1.getTime("ngaynhap"));
			}
			r1.close();
			cons.close();
		} catch (SQLException e) {
			Logger.getLogger(ImportPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	private void listProider () {
		providerList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Provider`";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Provider provider = new Provider(r1.getInt("maNhaCungCap"), r1.getString("tenNhaCungCap"), r1.getInt("sDT"), r1.getString("email"),
						r1.getString("diaChi"), r1.getString("ghiChu"), r1.getInt("trangThai"));
				providerList.add(provider);
				
			}
			r1.close();
			cons.close();
		} catch (Exception e) {
			Logger.getLogger(ProviderPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	private void listStaff() {
		staffList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Staff`";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Staff staff = new Staff(r1.getInt("maNhanVien"), r1.getString("hoTen"), r1.getInt("sDT"), r1.getString("email")
						, r1.getString("diaChi"), r1.getInt("maLevel"), r1.getInt("trangThai"), r1.getInt("maTaiKhoan"));
				staffList.add(staff);
				
			}
			r1.close();
			cons.close();
		} catch (SQLException e) {
			Logger.getLogger(StaffPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
	
	private void loadInvoiceDetail(int ma) {
		importsDetailList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `importdetail`, `import` WHERE `import`.`maNhapHang`= `importdetail`.`maNhapHang`"
					+ "AND `importdetail`.`maNhapHang` = " + ma;
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				ImportDetail importDetail = new ImportDetail(r1.getInt("maChiTietNhapHang"), r1.getInt("maNhapHang"),
						r1.getInt("maSanPham"), r1.getInt("soLuong"), r1.getFloat("thanhTien"));
				importsDetailList.add(importDetail);
			}
			r1.close();
			cons.close();
		}catch (SQLException e) {
			Logger.getLogger(InvoicePanel.class.getName()).log(Level.SEVERE, null, e);
		}
	
	}
	private void initComponet() {
	}
}

