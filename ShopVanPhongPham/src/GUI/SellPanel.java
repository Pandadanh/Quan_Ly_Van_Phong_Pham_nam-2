package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import BLL.CustomerManagerController;
import DAO.DBConnect;
import DAO.InvoiceDao;
import DAO.ProductDao;
import DAO.add_account;
import DTO.Account;
import DTO.Category;
import DTO.Customer;
import DTO.Invoice;
import DTO.InvoiceDetail;
import DTO.Product;
import DTO.Provider;
import DTO.Staff;
//import Export.WriteExcelExample;
import javax.swing.JTextPane;

public class SellPanel extends JPanel {
	List<Product> productList = new ArrayList<>();
	List<Product> productList1 = new ArrayList<>();
	List<Customer> customerList = new ArrayList<>();
	List<Staff> staffList = new ArrayList<>();
	DefaultTableModel tableModel;
	private JTable table_product;
	private DefaultTableModel table_model;
	private JTextField jtfTongTien;
	private JLabel jlbMaKhachHang;
	private JTable table_product2;
	private JTextField jtfTimKiem;
	private JTextField jtfTenSanPham;
	private JTextField jtfMaSanPham;
	private JTextField jtfDonGia;
	private JTextField jtfSoLuong;
	private JTextField jtfNgayBan;
	 private int index = -1;
	 private JTextField jtfMaKhachHang;
	 private JTable table_customer;
	 private JTextField jtfTimKiem1;


	public SellPanel(int id_user) {
		initComponet();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 617);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 880, 601);
		panel.setBorder(new TitledBorder((Border) new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ho\u00E1 \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);

		jlbMaKhachHang = new JLabel("Số Điện Thoại");
		jlbMaKhachHang.setBounds(484, 180, 113, 21);
		panel.add(jlbMaKhachHang);

		JLabel jlbSanPham = new JLabel("Sản phẩm");
		jlbSanPham.setBounds(484, 252, 64, 13);
		panel.add(jlbSanPham);
		
		listStaff();
		listCustomer();
//		comboBoxModel.removeAllElements();
//		for (Staff staff : staffList)
//			comboBoxModel.addElement(staff.getMaNhanVien());
//		comboBoxModel1.removeAllElements();
//		for (Customer customer : customerList)
//			comboBoxModel1.addElement(customer.getsDT());
		
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
				int maLoai = 1;
				int ncc = 1;
				String trangthai = "true";
				int checksl = InvoiceDao.checkslsp(masp, sluong);
				if(checksl != -1)
				{
					JOptionPane.showMessageDialog(panel, "Sản phẩm không đủ số lượng ! ");
					jtfSoLuong.setText("");
				}else {
					InvoiceDao.checklaisladd(masp, sluong);
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
					loadProduct();
					tableModel = (DefaultTableModel) table_product.getModel();
					showAll2();
				}
				
			}
		});

		btn_add.setBounds(294, 554, 136, 21);
		panel.add(btn_add);

		JButton btn_update = new JButton("Payment");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maKhachHang1 = jtfMaKhachHang.getText();
				
				if(productList1.isEmpty() || maKhachHang1.isEmpty())
				{
					JOptionPane.showMessageDialog(panel, "Vui lòng nhập đủ thông tin ");
				}else {
			
			
				String ngayBan = jtfNgayBan.getText();
				String tt = jtfTongTien.getText();
				Float tongtien = Float.valueOf(tt);	
				
				int maKhachHang;
				if(maKhachHang1.equals("#"))
				{
					 maKhachHang = 1;

				}else {
					int mkh = Integer.valueOf(maKhachHang1);
					 maKhachHang = InvoiceDao.checkmakh(mkh);

				}
				
				add_account add_account = new add_account();
				 Account account=add_account.add(id_user);
				int maNhanVien = InvoiceDao.checkmanv(account.getMaTaiKhoan());
				InvoiceDao.insertSell(ngayBan,maNhanVien,maKhachHang,tongtien);
				int aa = InvoiceDao.checkmahd(ngayBan);
				

			
				for(Product product : productList1)
				{
					InvoiceDao.insertDetailSell(aa,product.getMaSanPham(),product.getSoLuong(),product.ThanhTien());
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
				tableModel = (DefaultTableModel) table_product.getModel();
				loadProduct();
				tableModel.setRowCount(0);

				for (Product product : productList) {
					tableModel.addRow(new Object[] { product.getMaSanPham(), product.getMaLoai(), product.getTenSanPham(),
						 product.getSoLuong(), product.getDonGia()

					});
				}
				try {
					xuatpdf(aa);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jtfMaKhachHang.setText("");
				if(maKhachHang != 1)
				{
					JOptionPane.showMessageDialog(panel, "Khách hàng thân thiết");
				}else {
					JOptionPane.showMessageDialog(panel, "Khách hàng vãng lai");
				}
				JOptionPane.showMessageDialog(panel, "Mua hàng thành công  !!!");
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
				

			}
			}
		});
		
		btn_update.setBounds(728, 554, 122, 21);
		panel.add(btn_update);

		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = table_product2.getSelectedRow();
				if(selectedIndex >= 0)
				{
					
				
//					Product product1 = productList1.get(selectedIndex);
					int option = JOptionPane.showConfirmDialog(panel, "Bạn Có Muốn Xóa Sản Phẩm Này !!!");
					if(option == 0) {
						
								int masp = productList1.get(selectedIndex).getMaSanPham();
								int sl = productList1.get(selectedIndex).getSoLuong();
						
								InvoiceDao.checklaisl(masp,sl);
								tableModel = (DefaultTableModel) table_product.getModel();
								loadProduct();
								showAll2();
								
								productList1.remove(selectedIndex);
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
		btn_delete.setBounds(594, 554, 122, 21);
		panel.add(btn_delete);

		//table product
		table_product = new JTable(table_model);
		table_product.setLayout(new BorderLayout());
		table_product.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		Object data[][] = { { "1", "1", "1", "1", "1"}, { "1", "1", "1", "1", "1"} };
		
		Object listColumn[] = { "Mã SP", "Mã loại", "Tên sản phẩm", "Số lượng", "Đơn giá"};
	
		table_product.setModel(new DefaultTableModel(data, listColumn));
		table_product.setBounds(26, 70, 404, 358);
		JTableHeader header = table_product.getTableHeader();
		header.setBackground(Color.yellow);
//		panel.add(table_product);

JScrollPane scrollPane = new JScrollPane();
scrollPane.setBounds(41, 74, 400, 319);
scrollPane.setViewportView(table_product);
panel.add(scrollPane);
panel.setVisible(true);
		

		tableModel = (DefaultTableModel) table_product.getModel();

		loadProduct();
		tableModel.setRowCount(0);

		for (Product product : productList) {
			tableModel.addRow(new Object[] { product.getMaSanPham(), product.getMaLoai(), product.getTenSanPham(),
				 product.getSoLuong(), product.getDonGia()

			});
		}
		
		
		
		JLabel jlbTongTien_1 = new JLabel("Tổng tiền");
		jlbTongTien_1.setBounds(493, 512, 64, 13);
		panel.add(jlbTongTien_1);
		
		jtfTongTien = new JTextField();
		jtfTongTien.setColumns(10);
		jtfTongTien.setBounds(567, 500, 151, 38);
		jtfTongTien.enable(false);
		panel.add(jtfTongTien);
		
		jtfTimKiem = new JTextField();
		jtfTimKiem.setBounds(100, 24, 220, 30);
		panel.add(jtfTimKiem);
		jtfTimKiem.setColumns(10);
		
		JLabel lblTmKiem = new JLabel("Tìm kiếm");
		lblTmKiem.setBounds(26, 31, 88, 16);
		panel.add(lblTmKiem);
		
		table_product2 = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành Tiền"
			}
		));
		table_product2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_product2.setBounds(484, 200, 366, 177);
//		panel.add(table_product2);
//		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(517, 275, 330, 203);
		scrollPane1.setViewportView(table_product2);
		panel.add(scrollPane1);
		panel.setVisible(true);
//		
		JLabel jlbMaSanPham = new JLabel("Mã SP");
		jlbMaSanPham.setBounds(26, 465, 64, 13);
		panel.add(jlbMaSanPham);
		
		JLabel jlbDonGia = new JLabel("Đơn giá");
		jlbDonGia.setBounds(26, 512, 64, 13);
		panel.add(jlbDonGia);
		
		JLabel jlbTenSanPham = new JLabel("Tên SP");
		jlbTenSanPham.setBounds(232, 465, 64, 13);
		panel.add(jlbTenSanPham);
		
		JLabel jlbSoLuong = new JLabel("Số lượng");
		jlbSoLuong.setBounds(232, 512, 64, 13);
		panel.add(jlbSoLuong);
		
		jtfTenSanPham = new JTextField();
		jtfTenSanPham.setBackground(new Color(255, 0, 0));
		jtfTenSanPham.setColumns(10);
		jtfTenSanPham.setBounds(294, 458, 136, 26);
		jtfTenSanPham.enable(false);
		panel.add(jtfTenSanPham);
		
		jtfMaSanPham = new JTextField();
		jtfMaSanPham.setBackground(new Color(255, 0, 0));
		jtfMaSanPham.setFont(new Font("Tahoma", Font.BOLD, 10));
		jtfMaSanPham.setColumns(10);
		jtfMaSanPham.setBounds(84, 458, 136, 26);
		jtfMaSanPham.enable(false);
		panel.add(jtfMaSanPham);
		
		jtfDonGia = new JTextField();
		jtfDonGia.setBackground(new Color(255, 0, 0));
		jtfDonGia.setColumns(10);
		jtfDonGia.setBounds(84, 505, 136, 26);
		jtfDonGia.enable(false);
		panel.add(jtfDonGia);
		
		jtfSoLuong = new JTextField();
		jtfSoLuong.setColumns(10);
		jtfSoLuong.setBounds(294, 505, 136, 26);
		panel.add(jtfSoLuong);
		
		JLabel lblNgy = new JLabel("Ngày");
		lblNgy.setBounds(484, 76, 40, 13);
		panel.add(lblNgy);
		
		 //khai báo đối tượng current thuộc class LocalDateTime
	    LocalDateTime current = LocalDateTime.now();
	    //sử dụng class DateTimeFormatter để định dạng ngày giờ theo kiểu pattern
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    //sử dụng phương thức format() để định dạng ngày giờ hiện tại rồi gán cho chuỗi formatted
	    String formatted = current.format(formatter);
		
		jtfNgayBan = new JTextField(formatted);
		jtfNgayBan.setColumns(10);
		jtfNgayBan.setBounds(517, 89, 330, 38);
		jtfNgayBan.enable(false);
		panel.add(jtfNgayBan);
		
		JLabel lblNewLabel = new JLabel("Chi tiết hoá đơn");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(567, 21, 239, 30);
		panel.add(lblNewLabel);
		
		jtfMaKhachHang = new JTextField();
		jtfMaKhachHang.setBounds(529, 202, 136, 26);
		jtfMaKhachHang.enable(false);
		panel.add(jtfMaKhachHang);
		jtfMaKhachHang.setColumns(10);
		
		JButton btnTimKiem = new JButton("search");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel) table_product.getModel();
				String item = jtfTimKiem.getText().toString();
				search(item);
				showAll();
			}
		});
		btnTimKiem.setBounds(345, 28, 85, 26);
		panel.add(btnTimKiem);
		
		JScrollPane scrollPane1_1 = new JScrollPane();
		scrollPane1_1.setBounds(728, 156, 119, 87);
		panel.add(scrollPane1_1);
		
		table_customer = new JTable(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
			},
			new String[] {
				"Số Điện Thoại"
			}
		));
		table_customer.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane1_1.setViewportView(table_customer);
		
//		JScrollPane scrollPane2 = new JScrollPane();
//		scrollPane2.setBounds(41, 74, 400, 319);
//		scrollPane2.setViewportView(table_customer);
//		panel.add(scrollPane2);
//		panel.setVisible(true);
//				

				tableModel = (DefaultTableModel) table_customer.getModel();

				loadCustomer();
				tableModel.setRowCount(0);

				for (Customer customer : customerList) {
					tableModel.addRow(new Object[] { customer.getsDT()
					});
				}
				
		//
		jtfTimKiem1 = new JTextField();
		jtfTimKiem1.setBounds(529, 156, 96, 21);
		panel.add(jtfTimKiem1);
		jtfTimKiem1.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel) table_customer.getModel();
				String item = jtfTimKiem1.getText().toString();
				search_customer(item);
				tableModel.setRowCount(0);
				
				for(Customer customer : customerList)
				{
					tableModel.addRow(new Object[]
							{
									customer.getsDT()
							
							});
				}
			}
		});
		btnNewButton.setBounds(635, 155, 88, 21);
		panel.add(btnNewButton);
		
		JLabel lblTmKiem_1 = new JLabel("Tìm kiếm");
		lblTmKiem_1.setBounds(484, 137, 88, 16);
		panel.add(lblTmKiem_1);
		
		JButton btnNewButton_1 = new JButton("Khách Vãng Lai");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sDT = "#";
				jtfMaKhachHang.setText(sDT);
			}
		});
		btnNewButton_1.setBounds(702, 248, 145, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete All");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(panel, "Bạn Có Muốn Xóa All Sản Phẩm Này !!!");
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
		btnNewButton_2.setBounds(458, 554, 122, 21);
		panel.add(btnNewButton_2);
	
table_product.addMouseListener(new MouseListener() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
					index  = table_product.getSelectedRow();
					if(index >= 0)
					{
						Product product = productList.get(index);
						jtfTenSanPham.setText(product.getTenSanPham().toString());
						
					
						
						int msp = product.getMaSanPham();
						String masp = String.valueOf(msp);
						
						float dongia = product.getDonGia();
						String dg = String.valueOf(dongia);
						
						String trangthai = product.getTrangThai();
						String tt = String.valueOf(trangthai);
				
						jtfDonGia.setText(dg.toString());
						jtfMaSanPham.setText(masp.toString());
						jtfSoLuong.setText("");
				
						
					}else {
						System.out.println("dsadok");
					}
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
			
		});

table_customer.addMouseListener(new MouseListener() {
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
			index  = table_customer.getSelectedRow();
			if(index >= 0)
			{
				
				Customer customer = customerList.get(index);
				int sdt = customer.getsDT();
				String sDT = String.valueOf(sdt);
				jtfMaKhachHang.setText(sDT);
//				
//				int msp = product.getMaSanPham();
//				String masp = String.valueOf(msp);
//				
//				float dongia = product.getDonGia();
//				String dg = String.valueOf(dongia);
//				
//				boolean trangthai = product.getTrangThai();
//				String tt = String.valueOf(trangthai);
//		
//				jtfDonGia.setText(dg.toString());
//				jtfMaSanPham.setText(masp.toString());
//				jtfSoLuong.setText("");
		
				
			}else {
				System.out.println("null");
			}
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
	
});
	}
	//
	
private void setDefaultCloseOperation(int exitOnClose) {
	// TODO Auto-generated method stub
	
}
	private void initComponet() {}


	private void loadCustomer() {
		customerList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "select * from Customer";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Customer customer = new Customer(r1.getInt("maKhachHang"), r1.getString("hoTen"), r1.getInt("sDT"));
				customerList.add(customer);
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
			String sql = "SELECT Product.*,`Category`.`maLoai` idCategory, Provider.`maNhaCungCap` idProvider  from Product INNER JOIN Category ON  Product.`maLoai` = Category.`maLoai` INNER JOIN Provider 	ON Product.`maNhaCungCap` = Provider.`maNhaCungCap`"+ "WHERE product.trangThai = 'true'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
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
	public  void search_customer(String item)
	{
		customerList.clear();
		try {
		Connection cons = DBConnect.getConnection();
		String sql = "SELECT * from Customer where sDT LIKE '%"+item+"%'";
		PreparedStatement pr = cons.prepareStatement(sql);
		ResultSet r1 = pr.executeQuery();
		while(r1.next()) {
			
			Customer customer = new Customer(r1.getInt("maKhachHang"), r1.getString("hoTen"), r1.getInt("sDT"));
			customerList.add(customer);
			
		}
		
		
		pr.close();
		r1.close();
		cons.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	
	public  void search(String item)
	{
		productList.clear();
		try {
		Connection cons = DBConnect.getConnection();
		String sql = "SELECT Product.*,`Category`.`maLoai` idCategory, Provider.`maNhaCungCap` idProvider  from Product INNER JOIN Category ON  Product.`maLoai` = Category.`maLoai` INNER JOIN Provider 	ON Product.`maNhaCungCap` = Provider.`maNhaCungCap` where tenSanPham LIKE '%"+item+"%'";
		PreparedStatement pr = cons.prepareStatement(sql);
		ResultSet r1 = pr.executeQuery();
		while(r1.next()) {
			
			Product product = new Product(r1.getInt("maSanPham"), r1.getString("tenSanPham"), r1.getInt("soLuong"),
					r1.getFloat("donGia"), r1.getString("dVT"),r1.getString("moTa"), r1.getInt("idCategory"), r1.getString("trangThai"),
					r1.getInt("idProvider"));
			productList.add(product);
			
		}
		
		
		pr.close();
		r1.close();
		cons.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	

	private void listStaff() {
		staffList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "select * from Staff";
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
		}
	}
	private void listCustomer() {
		customerList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "select * from Customer";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Customer customer = new Customer(r1.getInt("maKhachHang"), r1.getString("hoTen"), r1.getInt("sDT"));
				customerList.add(customer);
			}
			r1.close();
			cons.close();
		} catch (SQLException e) {
		}
	}	
	public void xuatpdf(int mahoadon) throws FileNotFoundException {
    	List<InvoiceDetail> invoicedetailList = new ArrayList<>();
    	List<Product> productList = new ArrayList<>();
    	List<Invoice> invoiceList = new ArrayList<>();

		String path = "C:/exceljava/hoadon"+mahoadon+".pdf";
		
		
		PdfWriter pdfWriter = new PdfWriter(path);
		
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.addNewPage();
		
		Document document = new Document(pdfDocument);

		    //Thêm 2 đoạn văn bản vào document
		
		    Table table = new Table(4); 
		    table.addCell("Tên SP");
		    table.addCell("SL");
		    table.addCell("Gia Ban");
		    table.addCell("Thanh Tien");
//		    int mahoadon = 16;
		    
			try {
				Connection cons = DBConnect.getConnection();
				String sql = "SELECT * FROM Invoice where maHoaDon= '"+mahoadon+"'";
				PreparedStatement pr = cons.prepareStatement(sql);
				ResultSet r1 = pr.executeQuery();
				while (r1.next()) {
					Invoice invoice = new Invoice(r1.getInt("maHoaDon"), r1.getDate("ngayBan"),r1.getTime("ngayBan"),
							r1.getInt("maNhanVien"), r1.getInt("maKhachHang"), r1.getFloat("tongTien"));
					invoiceList.add(invoice);
				}
				r1.close();
				cons.close();

			} catch (SQLException e) {
			}
			document.add(new Paragraph("PHIEU THANH TOAN STORE "));
			 document.add(new Paragraph("Ma CT "+invoiceList.get(0).getMaHoaDon()+" "+invoiceList.get(0).getNgayban()+ " " +invoiceList.get(0).getNgayBan()+" NV "+invoiceList.get(0).getMaNhanVien()));
		    try {
				Connection cons = DBConnect.getConnection();
				String sql = "SELECT * FROM invoicedetail  where maHoaDon = '"+mahoadon+"'";
				PreparedStatement pr = cons.prepareStatement(sql);
				ResultSet r1 = pr.executeQuery();
				while (r1.next()) {
					InvoiceDetail invoicedetail = new InvoiceDetail(r1.getInt("maChiTietHoaDon"), r1.getInt("maHoaDon"),
							r1.getInt("maSanPham"), r1.getInt("soLuong"), r1.getFloat("thanhTien"));
					invoicedetailList.add(invoicedetail);
				}
				r1.close();
				cons.close();

			} catch (SQLException e) {
			}
		    
		    productList.clear();
		    try {
		    	
		    	Connection cons = DBConnect.getConnection();
		    	
		    	String sql = "select product.*  from product INNER JOIN category 	ON  product.maLoai = category.maLoai INNER JOIN provider 	ON product.maNhaCungCap = provider.maNhaCungCap";
		    	PreparedStatement pr = cons.prepareStatement(sql);
		    	ResultSet r1 = pr.executeQuery();
		    	while(r1.next()) {
		    		
		    		Product product = new Product(r1.getInt("maSanPham"),r1.getString("tenSanPham"),r1.getInt("soLuong"),r1.getFloat("donGia"),r1.getString("dVT"),r1.getString("moTa"),r1.getInt("maLoai"),r1.getString("trangThai"),r1.getInt("maNhaCungCap"));
		    		productList.add(product);
		    		
		    	}
		    	
		    	 r1.close();
		    	 

		    	cons.close();
		    	
		    } catch (SQLException e) {
		    }
		   
		    for (InvoiceDetail invoicedetail : invoicedetailList) {
		    	String tensp = null;
		    	String gb = null;
		    	String sl = String.valueOf(invoicedetail.getSoLuong());
		    	String tt = String.valueOf(invoicedetail.getThanhTien());	
		    	 for (Product product : productList) 
		    		 {
		    			 if(product.getMaSanPham() == invoicedetail.getMaSanPham())
		    			 {
		    				  tensp = product.getTenSanPham();
		    				  gb = String.valueOf(product.getDonGia());
		    			 }
		    		 }
		    
				table.addCell(tensp);
				table.addCell(sl);
				table.addCell(gb);
				table.addCell(tt);
			}
	
		    document.add(table);
		    String total = String.valueOf(invoiceList.get(0).getTongTien());
		    document.add(new Paragraph("Phai thanh toan "+total));
//		document.add(new Paragraph("A Hello World PDF document . aaaa"));
//		document.add(paragraph1);
		
		document.close();
		System.out.println("hello");
	}
	private void showAll2(){
		
		tableModel.setRowCount(0);
		
	for(Product product : productList)
	{
		tableModel.addRow(new Object[]
				{
						product.getMaSanPham(),
						product.getMaLoai(),
						product.getTenSanPham(),
						product.getSoLuong(),
						product.getDonGia(),
				
				});
	}

	}
	private void showAll(){
		
		tableModel.setRowCount(0);
		
	for(Product product : productList)
	{
		tableModel.addRow(new Object[]
				{
						product.getMaSanPham(),
						product.getTenSanPham(),
						product.getSoLuong(),
						product.getDonGia(),
				
				});
	}

	}
}