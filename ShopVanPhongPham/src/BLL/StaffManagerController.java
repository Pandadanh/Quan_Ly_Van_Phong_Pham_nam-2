//package BLL;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.MouseAdapter;
//import java.util.List;
//
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.RowFilter;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;
//import javax.swing.table.TableRowSorter;
//
//import DAO.ClassTableModel;
//import DAO.StaffDao;
//import DAO.StaffDaoImpl;
//import DTO.Account;
//import DTO.Staff;
//import GUI.StaffJFrame;
//
//public class StaffManagerController {
//	private JPanel jpnView;
//	private JButton btnAdd;
//	private JTextField jtfSearch;
//	
//	private StaffDao staffDao = null;
//	private String[] listColumn = {"MNV", "Họ Tên", "SDT", "Email", "Địa chỉ", "Level", "MTK", "Status"};
//	
//	private TableRowSorter<TableModel> rowSorter = null;
//
//	public StaffManagerController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch) {
//		this.jpnView = jpnView;
//		this.btnAdd = btnAdd;
//		this.jtfSearch = jtfSearch;
//		
//		this.staffDao = new StaffDaoImpl();
//	}
//	
//	public void setDataToModel() {
//		List<Staff> listItem = staffDao.getList();
//		
//		DefaultTableModel model = new ClassTableModel().setTableStaff(listItem, listColumn);
//		
//		JTable table = new JTable(model); 
//		table.getColumnModel().getColumn(0).setPreferredWidth(20);
//		table.getColumnModel().getColumn(1).setPreferredWidth(120);
//		table.getColumnModel().getColumn(3).setPreferredWidth(180);
//		table.getColumnModel().getColumn(5).setPreferredWidth(30);
//		table.getColumnModel().getColumn(6).setPreferredWidth(20);
//		rowSorter = new TableRowSorter<>(table.getModel());
//		table.setRowSorter(rowSorter);
//		
//		jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
//			
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				String text = jtfSearch.getText();
//				if(text.trim().length() == 0) {
//					rowSorter.setRowFilter(null);
//				} else {
//					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
//				}
//			}
//			
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				String text = jtfSearch.getText();
//				if(text.trim().length() == 0) {
//					rowSorter.setRowFilter(null);
//				} else {
//					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
//					
//				}
//			}
//			
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//		table.getTableHeader().setPreferredSize(new Dimension(100, 50));
//		table.setRowHeight(50);
//		
//		table.validate();
//		table.repaint();
//		
//		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(java.awt.event.MouseEvent e) {
//				if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
//					DefaultTableModel model = (DefaultTableModel) table.getModel();
//					int selectedRowIndex = table.getSelectedRow();
//					selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);
//					
//					Staff staff = new Staff();
//					staff.setMaNhanVien((int) model.getValueAt(selectedRowIndex, 0));
//					staff.setHoTen(model.getValueAt(selectedRowIndex, 1).toString());
//					staff.setsDT((int) model.getValueAt(selectedRowIndex, 2));
//					staff.setEmail(model.getValueAt(selectedRowIndex, 3).toString());
//					staff.setDiaChi(model.getValueAt(selectedRowIndex, 4).toString());
//					staff.setMaLevel((int) model.getValueAt(selectedRowIndex, 5));
//					staff.setMaTaiKhoan((int) model.getValueAt(selectedRowIndex, 6));
//					staff.setTrangThai((int) model.getValueAt(selectedRowIndex, 7));
//					
//					StaffJFrame frame = new StaffJFrame(staff);
//					frame.setTitle("THONG TIN NHAN VIEN");
//					frame.setResizable(false);
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//				}
//			}
//		});
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.getViewport().add(table);
//		scrollPane.setPreferredSize(new Dimension(1300, 400));
//		
//		jpnView.removeAll();
//		jpnView.setLayout(new BorderLayout());
//		jpnView.add(scrollPane);
//		jpnView.validate();
//		jpnView.repaint();
//	}
//	
//	public void setEvent() {
//		btnAdd.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(java.awt.event.MouseEvent e) {
//				StaffJFrame frame = new StaffJFrame(new Staff());
//				frame.setTitle("THEM THONG TIN NHAN VIEN");
//				frame.setLocationRelativeTo(null);
//				frame.setResizable(false);
//				frame.setVisible(true);
//			}
//			
//			@Override
//			public void mouseEntered(java.awt.event.MouseEvent e) {
//				// TODO Auto-generated method stub
//				super.mouseEntered(e);
//			}
//			
//			@Override
//			public void mouseExited(java.awt.event.MouseEvent e) {
//				// TODO Auto-generated method stub
//				super.mouseExited(e);
//			}
//		});
//	}
//}
