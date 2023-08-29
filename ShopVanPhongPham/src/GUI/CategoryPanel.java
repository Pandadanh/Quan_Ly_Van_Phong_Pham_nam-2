package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import BLL.CategoryManagerController;
import BLL.check_action;
import BLL.check_all;
import BLL.check_input;
import DAO.CategoryDao;
import DAO.DBConnect;
import DAO.ProductDao;
import DAO.StaffDao;
import DAO.add_account;
import DTO.Category;
import DTO.Product;
import DTO.Provider;
import DTO.Staff;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.event.ActionEvent;

public class CategoryPanel extends JPanel {
	private int id = 1;
	private int level = 0;

	DefaultTableModel tableModel;
	private TableRowSorter<TableModel> rowSorter = null;
	private JLabel jlMaLoai;
	private JTextField jtfTenLoai;
	private JTable table_category;
	private int index = -1;

	private String list_trangthai[] = new String[3];

	List<Category> categorylist = new ArrayList<>();

	private check_all check_all = new check_all();
	private JComboBox jtfTrangthai;

	public CategoryPanel(int id_user) {
		this.id = id_user;

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 652);
		panel.setBorder((Border) new TitledBorder(null, "Thông tin loại sản phẩm", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);

		// tạo menu

		jlMaLoai = new JLabel();
		jlMaLoai.setBounds(150, 35, 243, 29);
		panel.add(jlMaLoai);

		JLabel lblMaLoai = new JLabel("Mã Loại");
		lblMaLoai.setBounds(42, 41, 60, 13);
		panel.add(lblMaLoai);

		jtfTenLoai = new JTextField();
		jtfTenLoai.setBounds(150, 93, 243, 36);
		panel.add(jtfTenLoai);
		jtfTenLoai.setColumns(10);

		JLabel lblTenLoai = new JLabel("Tên Loại");
		lblTenLoai.setBounds(42, 104, 60, 13);
		panel.add(lblTenLoai);

		// tạo combobox cho trạng thái
		jtfTrangthai = new JComboBox();
		jtfTrangthai.setBounds(150, 157, 243, 36);
		panel.add(jtfTrangthai);
		JLabel lblTrangthai = new JLabel("Trạng thái");
		lblTrangthai.setBounds(42, 169, 60, 13);
		panel.add(lblTrangthai);

		list_trangthai[0] = "Off";
		list_trangthai[1] = "On";
		DefaultComboBoxModel<Object> comboBoxModel1 = (DefaultComboBoxModel) jtfTrangthai.getModel();
		for (String row : list_trangthai) {

			comboBoxModel1.addElement(row);
		}

		// tạo nút bấm

		JButton btnAdd = new JButton("Add");

		btnAdd.setFont(new Font("Arial", Font.BOLD, 18));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBorder(null);
		btnAdd.setOpaque(true);
		btnAdd.setBackground(new Color(12, 129, 160));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 5, "add") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					tableModel = (DefaultTableModel) table_category.getModel();
					int index = 0;
					if (jtfTenLoai.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
						index = -1;
					} else {
						if (!check_input.checkSpecialCharacters(jtfTenLoai.getText())) {
							JOptionPane.showMessageDialog(panel, "Không sử dụng kí tự đặc biệt");
							System.out.println(" wtf");
							index = -1;
						}
					}
					if (index == 0) {
						int length = categorylist.size() + 1;
						String maloai = String.valueOf(length);
						String tenloai = jtfTenLoai.getText();
						int idtrangthai = jtfTrangthai.getSelectedIndex();
						if (check_all.checkDataExist("category", "tenLoai", tenloai)) {
							JOptionPane.showMessageDialog(panel, "Tên loại đã có xin nhập lại !!!");
						} else {
							CategoryDao.insert(maloai, tenloai, idtrangthai);
							loadCategory();
							showAll();
							jlMaLoai.setText("");
							jtfTenLoai.setText("");
							JOptionPane.showMessageDialog(panel, "Thêm sản phẩm thành công!!!");
						}
					}
				}
			}
		});
		btnAdd.setBounds(608, 36, 120, 36);
		panel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 5, "update") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int selectedIndex = table_category.getSelectedRow();
					if (selectedIndex >= 0) {

						int index = 0;
						if (jtfTenLoai.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
							index = -1;
						} else {
							if (!check_input.checkSpecialCharacters(jtfTenLoai.getText())) {
								JOptionPane.showMessageDialog(panel, "Không sử dụng kí tự đặc biệt");
								System.out.println(" wtf");
								index = -1;
							}
						}
						if (index == 0) {
							Category category = categorylist.get(selectedIndex);
							int option = JOptionPane.showConfirmDialog(panel, "Bạn có muốn update sản phẩm này !!!");
							if (option == 0) {
								String maloai = Integer.toString(category.getMaLoai());
								String tenloai = jtfTenLoai.getText();
								int idtrangthai = jtfTrangthai.getSelectedIndex();
								CategoryDao.update(maloai, tenloai, idtrangthai);
								loadCategory();
								showAll();
								jlMaLoai.setText("");
								jtfTenLoai.setText("");
//					jtfTrangThai.setText("");
								JOptionPane.showMessageDialog(panel, "Update sản phẩm thành công!!!");
							}
						}
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 18));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBorder(null);
		btnUpdate.setOpaque(true);
		btnUpdate.setBackground(new Color(12, 129, 160));
		btnUpdate.setBounds(608, 90, 120, 36);
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 5, "delete") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int selectedIndex = table_category.getSelectedRow();
					if (selectedIndex >= 0) {

						Category category = categorylist.get(selectedIndex);
						int option = JOptionPane.showConfirmDialog(panel, "Bạn Có Muốn Xóa Sản Phẩm Này !!!");
						if (option == 0) {
							CategoryDao.delete(category.getMaLoai());
							loadCategory();
							showAll();
						}
					}
				}

			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBorder(null);
		btnDelete.setOpaque(true);
		btnDelete.setBackground(new Color(12, 129, 160));
		btnDelete.setBounds(608, 155, 120, 36);
		panel.add(btnDelete);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Arial", Font.BOLD, 18));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBorder(null);
		btnSearch.setOpaque(true);
		btnSearch.setBackground(new Color(12, 129, 160));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] options = { "maLoai", "tenLoai", "trangThai" };
				String selectedOption = (String) JOptionPane.showInputDialog(panel, "Chọn cột để tìm kiếm:", "Tìm kiếm",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (selectedOption != null && !selectedOption.equals("")) {

					String keyword = JOptionPane.showInputDialog(panel, "Nhập từ khóa cần tìm:", "Tìm kiếm",
							JOptionPane.PLAIN_MESSAGE);
					if (keyword != null && !keyword.equals("")) {

						List<Category> categorylist = searchCategory(selectedOption, keyword);

						if (categorylist.size() > 0) {

							tableModel.setRowCount(0);

							for (Category row : categorylist) {
								tableModel
										.addRow(new Object[] { row.getMaLoai(), row.getTenLoai(), row.getTrangThai() });
							}

						} else {
							JOptionPane.showMessageDialog(panel, "Không tìm thấy kết quả nào!");
						}
					} else
						JOptionPane.showMessageDialog(panel, "error");
				}
			}

		});
		btnSearch.setBounds(608, 223, 120, 36);
		panel.add(btnSearch);

		// tạo bảng
		table_category = new JTable();
		table_category.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null }, },
				new String[] { "Mã loại", "Tên Loại", "Trạng thái" }));
		table_category.setBounds(30, 350, 820, 350);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 291, 820, 278);
		scrollPane.setViewportView(table_category);
		panel.add(scrollPane);
		panel.setVisible(true);

		initComponet();

		// chạy bảng
		tableModel = (DefaultTableModel) table_category.getModel();
		loadCategory();
		showAll();

		table_category.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				index = table_category.getSelectedRow();
				if (index >= 0) {
					Category category = categorylist.get(index);
					jtfTenLoai.setText(category.getTenLoai().toString());

					int sl = category.getMaLoai();
					String vl = String.valueOf(sl);

					int trangthai = category.getTrangThai();
					jlMaLoai.setText(vl.toString());
					if (trangthai == 1) {
						jtfTrangthai.setSelectedIndex(1);
					} else {
						jtfTrangthai.setSelectedIndex(0);
					}
				} else {
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

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub

	}

	private void loadCategory() {
		categorylist.clear();
		try {

			Connection cons = DBConnect.getConnection();

			String sql = "SELECT * FROM `category` WHERE 1";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Category category = new Category(r1.getInt("maLoai"), r1.getString("tenLoai"), r1.getInt("trangThai"));
				categorylist.add(category);
			}
			r1.close();
			cons.close();
		} catch (SQLException ex) {
			ex.printStackTrace();

			// Hiển thị JDialog với thông báo lỗi
			JDialog dialog = new JDialog();
			dialog.setTitle("Lỗi");
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel label = new JLabel("Lỗi: " + ex.getMessage());
			dialog.getContentPane().add(label);

			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}
	}

	private void showAll() {
		tableModel.setRowCount(0);

		for (Category category : categorylist) {
			tableModel.addRow(new Object[] { category.getMaLoai(), category.getTenLoai(), category.getTrangThai(),

			});
		}

	}

	private List<Category> searchCategory(String columnName, String keyword) {
		List<Category> result = new ArrayList<>();
		String query;
		PreparedStatement stmt;
		try {
			Connection cons = DBConnect.getConnection();
			if (isNumeric(keyword)) {
				query = "SELECT * FROM category WHERE " + columnName + " = ?";
				stmt = cons.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(keyword));
			} else {
				query = "SELECT * FROM category WHERE " + columnName + " LIKE ?";
				stmt = cons.prepareStatement(query);
				stmt.setString(1, "%" + keyword + "%");
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("maLoai");
				String name = rs.getString("tenLoai");
				int trangthai = rs.getInt("trangThai");
				Category category = new Category(id, name, trangthai);
				result.add(category);
			}

			stmt.close();
			cons.close();
		} catch (SQLException ex) {
			ex.printStackTrace();

			// Hiển thị JDialog với thông báo lỗi
			JDialog dialog = new JDialog();
			dialog.setTitle("Lỗi");
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel label = new JLabel("Lỗi: " + ex.getMessage());
			dialog.getContentPane().add(label);

			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}

		return result;
	}

	private boolean isNumeric(String str) {
		try {
			int num = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void initComponet() {
		// TODO Auto-generated method stub

	}
}
