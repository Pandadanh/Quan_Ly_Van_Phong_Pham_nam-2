package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BLL.CustomerManagerController;
import DAO.DBConnect;
import DAO.ImportDao;
import DTO.ImportDetail;
import DTO.Imports;
import DTO.Invoice;
import DTO.InvoiceDetail;
import DTO.Product;
import DTO.Provider;
import DTO.Staff;

import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.table.TableModel;

public class ImportDetailPanel extends JPanel {
	private JTextField jtfSearch;

	private static final long serialVersionUID = 1L;
	List<Provider> providerList = new ArrayList<>();
	List<Product> productList = new ArrayList<>();
	List<Imports> importList = new ArrayList<>();
	List<ImportDetail> importdetailList = new ArrayList<>();

	List<Staff> staffList = new ArrayList<>();
	DefaultTableModel tableModel;
	DefaultTableModel tableModel2;
	private JTable table_importdetail;
	private JTable table_import;
	private DefaultTableModel table_model;
	private DefaultTableModel table_model2;
	 private int index = -1;


	public ImportDetailPanel() {
		initComponet();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 617);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 880, 601);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ho\u00E1 \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);
//		comboBoxModel1.removeAllElements();
//		for (Provider provider : providerList)
//			comboBoxModel1.addElement(provider.getTenNhaCungCap());

		//table product
		table_import = new JTable(table_model2);
		table_import.setLayout(new BorderLayout());
		table_import.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		Object data2[][] = { {"1", "1"}, {"1", "1"} };
		
		Object listColumn2[] = { "Mã SP", "Tên sản phẩm"};
	
		table_import.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã HD", "Ngày Bán", "Mã NV", "Mã KH", "Tổng Tiền"
			}
		));
		table_import.setBounds(32, 25, 156, 232);
		JTableHeader header = table_import.getTableHeader();
		header.setBackground(Color.yellow);
//		panel.add(table_product);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 80, 705, 248);
		scrollPane.setViewportView(table_import);
		panel.add(scrollPane);
		panel.setVisible(true);
		
		tableModel = (DefaultTableModel) table_import.getModel();
		
		loadInvoice();
		tableModel.setRowCount(0);

		for (Imports imports : importList) {
			tableModel.addRow(new Object[] {imports.getMaPhieuNhap(), imports.getNgayNhap(), imports.getMaNhanVien(), imports.getMaNhaCungCap(), imports.getTongTien()});
		}
//		showAll();

	
//		tableModel2.setRowCount(0);

//		for (Product product : productList) {
//			tableModel2.addRow(new Object[] { product.getMaSanPham(), product.getTenSanPham() });
//		}
//		
		
		//table invoice
		table_importdetail = new JTable(table_model);
		table_importdetail.setLayout(new BorderLayout());
		table_importdetail.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		Object data[][] = {};
		Object listColumn[] = { "Mã SP","Số Lượng", "Giá", "Tổng Giá"};
	
		table_importdetail.setModel(new DefaultTableModel(data, listColumn));
		table_importdetail.setBounds(31, 356, 820, 213);
		JTableHeader header2 = table_importdetail.getTableHeader();
		header2.setBackground(Color.yellow);
//		panel.add(table_invoicedetail);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(106, 357, 745, 204);
		scrollPane1.setViewportView(table_importdetail);
		panel.add(scrollPane1);
		panel.setVisible(true);
		
//		tableModel = (DefaultTableModel) table_invoicedetail.getModel();
//		
//		loadInvoice();
//		tableModel.setRowCount(0);
//
//		for (Invoice invoice : invoiceList) {
//			tableModel.addRow(new Object[] {invoice.getMaHoaDon(), invoice.getNgayBan(), invoice.getMaNhanVien(), invoice.getMaKhachHang(), invoice.getTongTien()});
//		}
		
		jtfSearch = new JTextField();
		jtfSearch.setBounds(77, 38, 220, 30);
		panel.add(jtfSearch);
		jtfSearch.setColumns(10);
		
		JLabel lblTmKiem = new JLabel("Tìm kiếm");
		lblTmKiem.setBounds(47, 24, 88, 16);
		panel.add(lblTmKiem);
		
		JLabel lblChiTitHa = new JLabel("Chi Tiết Hóa Đơn");
		lblChiTitHa.setBounds(434, 330, 259, 16);
		panel.add(lblChiTitHa);
		
table_import.addMouseListener(new MouseListener() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
					index  = table_import.getSelectedRow();
					if(index >= 0)
					{
						Imports imports = importList.get(index);
						tableModel = (DefaultTableModel) table_importdetail.getModel();
						listInvoice(imports.getMaPhieuNhap());
						tableModel.setRowCount(0);
						for (ImportDetail importdetail : importdetailList) {
							tableModel.addRow(new Object[] {  importdetail.getMaChiTietPhieuNhap(),importdetail.getMaSanPham(), importdetail.getSoLuong(), importdetail.getThanhTien()});
						}
				
						
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
			Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, e);
		}
	}


	private void initComponet() {
	}
	
	private void loadProduct() {
		productList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM Product";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Product product = new Product(r1.getInt("maSanPham"),r1.getString("tenSanPham"),r1.getInt("soLuong"),r1.getFloat("donGia"),r1.getString("dVT"),r1.getString("moTa"),r1.getInt("maLoai"),r1.getString("trangThai"),r1.getInt("maNhaCungCap"));
				productList.add(product);
			}
			r1.close();
			cons.close();
		} catch (SQLException e) {
			
		}
	}
	private void listInvoice(int mahoadon) {
		importdetailList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM importdetail  where maNhapHang = '"+mahoadon+"'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				ImportDetail importdetail = new ImportDetail(r1.getInt("maChiTietNhapHang"), r1.getInt("maNhapHang"),
						r1.getInt("maSanPham"), r1.getInt("soLuong"), r1.getFloat("thanhTien"));
				importdetailList.add(importdetail);
			}
			r1.close();
			cons.close();

		} catch (SQLException e) {
			
		}
	}
	private void loadInvoice() {
//		productList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM import";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Imports imports = new Imports(r1.getInt("maNhapHang"), r1.getDate("ngayNhap"),r1.getTime("ngaynhap"),
						r1.getInt("maNhaCungCap"), r1.getInt("maNhanVien"), r1.getFloat("tongTien"));
				importList.add(imports);
			}
			r1.close();
			cons.close();

		} catch (SQLException e) {
			
		}
	}
	private void showAll(){
		tableModel.setRowCount(0);
		
	for(Product product : productList)
	{
		tableModel.addRow(new Object[]
				{
						product.getTenSanPham(),
						product.getMaLoai(),
						product.getTenSanPham(),
						product.getdVT(),
						product.getSoLuong(),
						product.getDonGia(),
						product.getMoTa(),
						product.getTrangThai(),
						product.getMaNhaCungCap()
				
				});
	}

	}
}

