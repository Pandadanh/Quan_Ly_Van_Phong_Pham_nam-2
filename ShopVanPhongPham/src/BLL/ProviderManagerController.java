package BLL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.List;

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
import DAO.ProviderDao;
import DAO.ProviderDaoImpl;
import DTO.Provider;


public class ProviderManagerController {
	private JPanel jpnView;
	private JButton btnAdd;
	private JTextField jtfSearch;
	
	private ProviderDao providerDao = null;
	private String[] listColumn = {"Mã NCC", "Tên NCC", "SDT", "Email","Địa chỉ","Ghi chú"};
	
	private TableRowSorter<TableModel> rowSorter = null;

	public ProviderManagerController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
		this.jpnView = jpnView;
		this.btnAdd = btnAdd;
		this.jtfSearch = jtfSearch;
		
		this.providerDao = new ProviderDaoImpl();
	}
	
	public void setDataToModel() {
		List<Provider> listItem = providerDao.getList();
		
		DefaultTableModel model = new ClassTableModel().setTableProvider(listItem, listColumn);
		
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
					
					Provider provider = new Provider();
					provider.setMaNhaCungCap((int) model.getValueAt(selectedRowIndex, 0));
					provider.setTenNhaCungCap(model.getValueAt(selectedRowIndex, 1).toString());
					provider.setsDT((int)model.getValueAt(selectedRowIndex, 2));
//					product.setHinhAnh(model.getValueAt(selectedRowIndex, 3).toString());
					provider.setEmail(model.getValueAt(selectedRowIndex, 3).toString());
					
					provider.setDiaChi(model.getValueAt(selectedRowIndex, 4).toString());
					provider.setGhiChu(model.getValueAt(selectedRowIndex, 5).toString());
					
//					ProviderJFrame frame = new ProviderJFrame(provider);
//					frame.setTitle("THONG TIN NHA CUNG CAP");
//					frame.setResizable(false);
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add(table);
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
//				ProviderJFrame frame = new ProviderJFrame(new Provider());
//				frame.setTitle("THEM THONG TIN SAN PHAM");
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
