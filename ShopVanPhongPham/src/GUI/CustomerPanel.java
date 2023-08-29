package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import BLL.check_action;
import BLL.check_all;
import BLL.check_input;
import DAO.CustomerDao;
import DAO.DBConnect;
import DAO.add_account;
import DTO.Customer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

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

public class CustomerPanel extends JPanel {

	DefaultTableModel tableModel;
	private JTextField tf_sdt;
	private JTextField tf_hoten;
	private JLabel tf_makh;
	private JTable table;
	private JTable table_product;
	List<Customer> list_Customers = new ArrayList<>();

	private int index = -1;

	private check_all check_all = new check_all();
	private int id;

	public CustomerPanel(int id_user) {
		this.id = id_user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 672);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 652);
		panel.setBorder(new TitledBorder(null, "Nhập khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);

		Font font = new Font("Arial", Font.PLAIN, 16);

		JLabel lp_mkh = new JLabel("Mã khách hàng :");
		lp_mkh.setFont(font);
		lp_mkh.setBounds(465, 50, 118, 26);
		panel.add(lp_mkh);
		tf_makh = new JLabel();
		tf_makh.setBounds(590, 50, 118, 26);
		tf_makh.setFont(font);
		panel.add(tf_makh);

		JLabel lp_hoten = new JLabel("Họ Tên");
		lp_hoten.setFont(font);
		lp_hoten.setBounds(468, 107, 66, 13);
		panel.add(lp_hoten);
		tf_hoten = new JTextField();
		tf_hoten.setBounds(548, 100, 220, 26);
		panel.add(tf_hoten);

		JLabel lp_sdt = new JLabel("SĐT");
		lp_sdt.setFont(font);
		lp_sdt.setBounds(468, 162, 45, 13);
		panel.add(lp_sdt);
		tf_sdt = new JTextField();
		tf_sdt.setBounds(548, 155, 220, 26);
		panel.add(tf_sdt);
		add_account add_account = new add_account();

		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 3, "add") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					tableModel = (DefaultTableModel) table_product.getModel();

					Customer lastCustomer = list_Customers.get(list_Customers.size() - 1);
					int length = lastCustomer.getMaKhachHang() + 1;
					int index = 0;
					if (tf_hoten.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
						index = -1;
					} else {
						if (!check_input.checkSpecialCharacters(tf_hoten.getText())) {
							JOptionPane.showMessageDialog(panel, "Không sử dụng kí tự đặc biệt");
							System.out.println(" wtf");
							index = -1;
						}
					}

					if (tf_sdt.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập số điện thoại");
						index = -1;
					} else {
						if (!check_input.checknumber(tf_sdt.getText())) {
							JOptionPane.showMessageDialog(panel, "Sai cú pháp số điện thoại");
							System.out.println(" wtf");
							index = -1;
						} else

						if (!check_input.checkNumberLength(tf_sdt.getText())) {
							JOptionPane.showMessageDialog(panel, "Số điện thoại không đúng");
							System.out.println(" wtf");
							index = -1;
						}
					}

					if (index == 0)
					{
						String manv = String.valueOf(length);
						String hoten = tf_hoten.getText();
						String sdt = tf_sdt.getText();
						if (check_all.checkDataExist("customer", "sDT", sdt)) {
							JOptionPane.showMessageDialog(panel, "Số điện thoại đã có xin nhập lại !!!");
						} else {
							CustomerDao.insert(manv, hoten, sdt);
							loadProduct();
							showAll();

							tf_hoten.setText("");
							tf_makh.setText("");
							tf_sdt.setText("");
							JOptionPane.showMessageDialog(panel, "Thêm sản phẩm thành công!!!");
						}
					}
				}
			}
		});
		btn_add.setBounds(465, 309, 144, 49);
		btn_add.setFont(new Font("Arial", Font.BOLD, 18));
		btn_add.setForeground(new Color(255, 255, 255));
		btn_add.setBorder(null);
		btn_add.setOpaque(true);
		btn_add.setBackground(new Color(12, 129, 160));
		panel.add(btn_add);

		JButton btn_update = new JButton("UPDATE");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 3, "update") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int selectedIndex = table_product.getSelectedRow();
					if (selectedIndex >= 0) {

						Customer customer = list_Customers.get(selectedIndex);
						int index = 0;
						if (tf_hoten.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
							index = -1;
						} else {
							if (!check_input.checkSpecialCharacters(tf_hoten.getText())) {
								JOptionPane.showMessageDialog(panel, "Không sử dụng kí tự đặc biệt");
								System.out.println(" wtf");
								index = -1;
							}
						}

						if (tf_sdt.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập số điện thoại");
							index = -1;
						} else {
							if (!check_input.checknumber(tf_sdt.getText())) {
								JOptionPane.showMessageDialog(panel, "Sai cú pháp số điện thoại");
								System.out.println(" wtf");
								index = -1;
							} else

							if (!check_input.checkNumberLength(tf_sdt.getText())) {
								JOptionPane.showMessageDialog(panel, "Số điện thoại không đúng");
								System.out.println(" wtf");
								index = -1;
							}
						}

						if (index == 0) {
							int option = JOptionPane.showConfirmDialog(panel, "Bạn có muốn update sản phẩm này !!!");
							if (option == 0) {
								String manv = Integer.toString(customer.getMaKhachHang());
								tableModel = (DefaultTableModel) table_product.getModel();

								String hoten = tf_hoten.getText();
								String sdt = tf_sdt.getText();

								CustomerDao.update(manv, hoten, sdt);
								loadProduct();
								showAll();

								tf_hoten.setText("");
								tf_makh.setText("");
								tf_sdt.setText("");

								JOptionPane.showMessageDialog(panel, "Update sản phẩm thành công!!!");
							}
						}
					}
				}
			}
		});
		btn_update.setBounds(678, 309, 144, 49);
		btn_update.setFont(new Font("Arial", Font.BOLD, 18));
		btn_update.setForeground(new Color(255, 255, 255));
		btn_update.setBorder(null);
		btn_update.setOpaque(true);
		btn_update.setBackground(new Color(12, 129, 160));
		panel.add(btn_update);

		JButton btn_delete = new JButton("DELETE");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 3, "delete") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int selectedIndex = table_product.getSelectedRow();
					if (selectedIndex >= 0) {

						Customer customer = list_Customers.get(selectedIndex);
						int option = JOptionPane.showConfirmDialog(panel, "Xóa Khach hang nay !!!");
						if (option == 0) {
							CustomerDao.delete(customer.getMaKhachHang());
							loadProduct();
							showAll();
							JOptionPane.showMessageDialog(panel, "Xóa Khach hang thành công !!!");
						}
					}

				}
			}
		});
		btn_delete.setBounds(678, 415, 144, 49);
		btn_delete.setFont(new Font("Arial", Font.BOLD, 18));
		btn_delete.setForeground(new Color(255, 255, 255));
		btn_delete.setBorder(null);
		btn_delete.setOpaque(true);
		btn_delete.setBackground(new Color(12, 129, 160));
		panel.add(btn_delete);

		JButton btn_find = new JButton("SEARCH");
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] options = { "maKhachHang", "hoten", "sDT" };
				String selectedOption = (String) JOptionPane.showInputDialog(panel, "Chọn cột để tìm kiếm:", "Tìm kiếm",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (selectedOption != null && !selectedOption.equals("")) {

					String keyword = JOptionPane.showInputDialog(panel, "Nhập từ khóa cần tìm:", "Tìm kiếm",
							JOptionPane.PLAIN_MESSAGE);
					if (keyword != null && !keyword.equals("")) {

						List<Customer> list_Cus = searchCus(selectedOption, keyword);

						if (list_Cus.size() > 0) {

							tableModel.setRowCount(0);

							for (Customer row : list_Cus) {
								tableModel.addRow(new Object[] { row.getMaKhachHang(), row.getHoTen(), row.getsDT(),

								});
							}

						} else {
							JOptionPane.showMessageDialog(panel, "Không tìm thấy kết quả nào!");
						}
					} else
						JOptionPane.showMessageDialog(panel, "error");
				}
			}
		});

		btn_find.setBounds(465, 415, 144, 49);
		btn_find.setFont(new Font("Arial", Font.BOLD, 18));
		btn_find.setForeground(new Color(255, 255, 255));
		btn_find.setBorder(null);
		btn_find.setOpaque(true);
		btn_find.setBackground(new Color(12, 129, 160));
		panel.add(btn_find);

		table_product = new JTable();

		table_product.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null }, },
				new String[] { "Mã KH", "Họ tên", "SĐT" }));
		TableColumnModel columnModel = table_product.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(75);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(100);

		table_product.setBounds(30, 320, 820, 300);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 42, 398, 500);
		scrollPane.setViewportView(table_product);
		panel.add(scrollPane);
		panel.setVisible(true);

		tableModel = (DefaultTableModel) table_product.getModel();

		loadProduct();
		showAll();

		table_product.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				index = table_product.getSelectedRow();
				if (index >= 0) {
					Customer customer = list_Customers.get(index);

					tf_hoten.setText(customer.getHoTen().toString());

					int manv = customer.getMaKhachHang();
					String vl = String.valueOf(manv);
					tf_makh.setText(vl.toString());

					int sdt = customer.getsDT();
					String a = String.valueOf(sdt);
					tf_sdt.setText(a.toString());

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

		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!table_product.getBounds().contains(e.getPoint())) {
					loadProduct();
					showAll();
					tf_makh.setText("");
					tf_sdt.setText("");
					tf_hoten.setText("");

				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

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

	private void loadProduct() {
		list_Customers.clear();
		try {

			Connection cons = DBConnect.getConnection();
			String sql = "select * from customer";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Customer staff = new Customer(r1.getInt("MaKhachHang"), r1.getString("hoTen"), r1.getInt("sDT"));
				list_Customers.add(staff);

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

		for (Customer row : list_Customers) {
			tableModel.addRow(new Object[] { row.getMaKhachHang(), row.getHoTen(), row.getsDT(),

			});
		}

	}

	private List<Customer> searchCus(String columnName, String keyword) {
		List<Customer> result = new ArrayList<>();
		String query;
		PreparedStatement stmt;
		try {
			Connection cons = DBConnect.getConnection();
			if (isNumeric(keyword)) {
				query = "SELECT * FROM customer WHERE " + columnName + " = ?";
				stmt = cons.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(keyword));
			} else {
				query = "SELECT * FROM customer WHERE " + columnName + " LIKE ?";
				stmt = cons.prepareStatement(query);
				stmt.setString(1, "%" + keyword + "%");
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("maKhachHang");
				String name = rs.getString("hoTen");
				int phone = rs.getInt("sDT");

				Customer customer = new Customer(id, name, phone);
				result.add(customer);
			}

			stmt.close();
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
}