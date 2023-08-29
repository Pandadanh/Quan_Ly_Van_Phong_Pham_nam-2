package BLL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
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

import DAO.CategoryDao;
import DAO.CategoryDaoImpl;
import DAO.ClassTableModel;
import DTO.Category;

public class CategoryManagerController {
	private JPanel jpnView;
	private JButton btnAdd;
	private JTextField jtfSearch;
	
	private CategoryDao categoryDao = null;
	private String[] listColumn = {"Mã loại", "Tên loại", "Trạng thái"};
	
	private TableRowSorter<TableModel> rowSorter = null;

	/**
	 * @wbp.parser.entryPoint
	 */
	public CategoryManagerController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
		this.jpnView = jpnView;
		this.btnAdd = btnAdd;
		this.jtfSearch = jtfSearch;
		
		this.categoryDao = new CategoryDaoImpl();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setDataToModel() {
		List<Category> listItem = categoryDao.getList();
		
		DefaultTableModel model = new ClassTableModel().setTableCategory(listItem, listColumn);
		
		JTable table = new JTable(model); 
		rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		
		jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String text = jtfSearch.getText();
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String text = jtfSearch.getText();
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
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
					
					Category category = new Category();
					category.setMaLoai((int) model.getValueAt(selectedRowIndex, 0));
					category.setTenLoai(model.getValueAt(selectedRowIndex, 1).toString());
//					category.setTrangThai((Boolean) model.getValueAt(selectedRowIndex, 2));
					
//					CategoryJFrame frame = new CategoryJFrame(category);
//					frame.setTitle("THONG TIN LOAI");
//					frame.setResizable(false);
//					frame.setLocationRelativeTo(null);
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
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setEvent() {
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
//				CategoryJFrame frame = new CategoryJFrame(new Category());
//				frame.setTitle("THEM THONG TIN LOAI");
//				frame.setLocationRelativeTo(null);
//				frame.setResizable(false);
//				frame.setVisible(true);
			}
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
			}
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
			}
		});
	}

	

}
