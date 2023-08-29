package BLL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.ClassTableModel;
import DAO.DBConnect;
import DAO.ProductDao;
import DAO.ProductDaoImpl;
import DTO.Category;
import DTO.Product;


public class ProductManagerController {
	private JPanel jpnView;
	private JButton btnAdd;
	private JTextField jtfSearch;
	
	private ProductDao productDao = null;
	private String[] listColumn = {"Mã SP", "Mã loại", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Đơn giá", "Mô tả", "Trạng thái", "Mã NCC"};
	
	private TableRowSorter<TableModel> rowSorter = null;

	public ProductManagerController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
		this.jpnView = jpnView;
		this.btnAdd = btnAdd;
		this.jtfSearch = jtfSearch;
		
		this.productDao = new ProductDaoImpl();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setDataToModel() {
		
		List<Product> listItem = productDao.getList();
		
		DefaultTableModel model = new ClassTableModel().setTableProduct(listItem, listColumn);
		
		JTable table = new JTable(model); 
		rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		
		jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = jtfSearch.getText();
				if(text.trim().length() == 0) 
					rowSorter.setRowFilter(null);
				else 
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = jtfSearch.getText();
				if(text.trim().length() == 0) 
					rowSorter.setRowFilter(null);
				else 
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
			}
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.getTableHeader().setPreferredSize(new Dimension(100, 50));
		table.setRowHeight(50);
		
		table.validate();
		table.repaint();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int selectedRowIndex = table.getSelectedRow();
					selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);
					
					Product product = new Product();
					product.setMaSanPham((int) model.getValueAt(selectedRowIndex, 0));
					product.setMaLoai((int) model.getValueAt(selectedRowIndex, 1));
					product.setTenSanPham(model.getValueAt(selectedRowIndex, 2).toString());
//					product.setHinhAnh(model.getValueAt(selectedRowIndex, 3).toString());
					product.setdVT(model.getValueAt(selectedRowIndex, 3).toString());
					product.setSoLuong((int)model.getValueAt(selectedRowIndex, 4));
					product.setDonGia((float) model.getValueAt(selectedRowIndex, 5));
					product.setMoTa(model.getValueAt(selectedRowIndex, 6).toString());
					product.setTrangThai((String) model.getValueAt(selectedRowIndex, 7));
					
//					ProductJFrame frame = new ProductJFrame(product);
//					frame.setTitle("THONG TIN SAN PHAM");
//					frame.setResizable(false);
//					frame.setLocationRelativeTo(null);
//					
//					
//					frame.setVisible(true);
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setPreferredSize(new Dimension(1300, 400));
		
		jpnView.removeAll();
		jpnView.setLayout(new BorderLayout());
		jpnView.add(scrollPane);
		jpnView.validate();
		jpnView.repaint();
	}
	
	public void setEvent() {
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
//				ProductJFrame frame = new ProductJFrame(new Product());
//				frame.setTitle("THEM THONG TIN SAN PHAM");
//				frame.setLocationRelativeTo(null);
//				frame.setResizable(false);
//				frame.setVisible(true);
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				super.mouseEntered(e);
			}
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				super.mouseExited(e);
			}
		});
	}
}