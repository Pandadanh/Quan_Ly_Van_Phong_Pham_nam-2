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

import BLL.CustomerManagerController;
import BLL.check_action;
import BLL.check_all;
import BLL.check_input;
import DAO.AccountDao;
import DAO.CustomerDao;
import DAO.DBConnect;
import DAO.add_account;
import DTO.Account;
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

public class AccountPanel extends JPanel {

	DefaultTableModel tableModel;
	private int id = 1;
	private int level = 0;
	private JTable table;
	private JTable table_product;
	List<Account> list_account = new ArrayList<>();
	private check_action check_action = new check_action();

	private int index = -1;

	private JLabel tf_matk;

	private JTextField tf_sdt;

	private JTextField tf_pw;

	private JTextField tf_lv;
	private check_all check_all = new check_all();

	public AccountPanel(int id) {
		this.id = id;
		add_account add_account = new add_account();
		level = (add_account.add(id)).getLevel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 672);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 652);
		panel.setBorder(new TitledBorder(null, "Nhập Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);

		Font font = new Font("Arial", Font.PLAIN, 16);

		JLabel lpmtk = new JLabel("Mã Tài Khoản :");
		lpmtk.setFont(font);
		lpmtk.setBounds(465, 50, 118, 26);
		panel.add(lpmtk);
		tf_matk = new JLabel();
		tf_matk.setBounds(590, 50, 118, 26);
		tf_matk.setFont(font);
		panel.add(tf_matk);

		JLabel lp_sdt = new JLabel("SĐT");
		lp_sdt.setFont(font);
		lp_sdt.setBounds(468, 107, 66, 13);
		panel.add(lp_sdt);
		tf_sdt = new JTextField();
		tf_sdt.setBounds(548, 100, 220, 26);
		panel.add(tf_sdt);

		JLabel lp_pw = new JLabel("PassWord");
		lp_pw.setFont(font);
		lp_pw.setBounds(465, 158, 74, 19);
		panel.add(lp_pw);
		tf_pw = new JTextField();
		tf_pw.setBounds(548, 156, 220, 26);
		panel.add(tf_pw);

		JLabel lp_lv = new JLabel("Level");
		lp_lv.setFont(font);
		lp_lv.setBounds(468, 218, 45, 21);
		panel.add(lp_lv);
		tf_lv = new JTextField();
		tf_lv.setBounds(548, 213, 220, 26);
		panel.add(tf_lv);

		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check_action.check_action_user_by_level(level, 2, "add") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {

					tableModel = (DefaultTableModel) table_product.getModel();
					Account lastCustomer = list_account.get(list_account.size() - 1);
					int length = lastCustomer.getMaTaiKhoan() + 1;

					int index = 0;
					if (tf_sdt.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập sđt");
						index = -1;
					} else {
						if (!check_input.checknumber(tf_sdt.getText())) {
							JOptionPane.showMessageDialog(panel, "Sai cú pháp số điện thoại");
							index = -1;
						} else

						if (!check_input.checkNumberLength(tf_sdt.getText())) {
							JOptionPane.showMessageDialog(panel, "Số điện thoại không đúng");
							System.out.println(" wtf");
							index = -1;
						}
					}
					
					if (tf_pw.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập mật khẩu");
						index = -1;
					}
					
					if (tf_lv.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập lv");
						index = -1;
					} else {
						if (!check_input.checknumber(tf_lv.getText())) {
							JOptionPane.showMessageDialog(panel, "Sai cú pháp level");
						}
					}
					if (index == 0) {
						int manv = Integer.valueOf(length);
						String password = tf_pw.getText();
						int sdt = Integer.valueOf(tf_sdt.getText());
						int level = Integer.valueOf(tf_lv.getText());

						if (check_all.checkDataExist("account", "sDT", String.valueOf(sdt))) {
							JOptionPane.showMessageDialog(panel, "Số điện thoại đã có xin nhập lại !!!");
						} else {
							AccountDao.insert(manv, sdt, password, level);
							loadProduct();
							showAll();
							tf_lv.setText("");
							tf_sdt.setText("");
							tf_pw.setText("");
							JOptionPane.showMessageDialog(panel, "Thêm nhân viên thành công!!!");
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
				if (check_action.check_action_user_by_level(level, 2, "update") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				}

				else {

					int selectedIndex = table_product.getSelectedRow();
					if (selectedIndex >= 0) {

						Account customer = list_account.get(selectedIndex);

						int index = 0;
						if (tf_sdt.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập sđt");
							index = -1;
						} else {
							if (!check_input.checknumber(tf_sdt.getText())) {
								JOptionPane.showMessageDialog(panel, "Sai cú pháp số điện thoại");
								index = -1;
							} else

							if (!check_input.checkNumberLength(tf_sdt.getText())) {
								JOptionPane.showMessageDialog(panel, "Số điện thoại không đúng");
								System.out.println(" wtf");
								index = -1;
							}
						}
						
						if (tf_pw.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập mật khẩu");
							index = -1;
						}
						
						if (tf_lv.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập lv");
							index = -1;
						} else {
							if (!check_input.checknumber(tf_lv.getText())) {
								JOptionPane.showMessageDialog(panel, "Sai cú pháp level");
							}
						}
						if (index == 0) {

							int option = JOptionPane.showConfirmDialog(panel, "Bạn có muốn update sản phẩm này !!!");
							if (option == 0) {
								int manv = Integer.valueOf(customer.getMaTaiKhoan());
								tableModel = (DefaultTableModel) table_product.getModel();
								String password = tf_pw.getText();
								int sdt = Integer.valueOf(tf_sdt.getText());
								int level = Integer.valueOf(tf_lv.getText());

								AccountDao.update(manv, sdt, password, level);
								loadProduct();
								showAll();

								tf_lv.setText("");
								tf_sdt.setText("");
								tf_pw.setText("");

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
				if (check_action.check_action_user_by_level(level, 2, "delete") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {

					int selectedIndex = table_product.getSelectedRow();
					if (selectedIndex >= 0) {

						Account customer = list_account.get(selectedIndex);
						int option = JOptionPane.showConfirmDialog(panel, "Xóa nhân viên này !!!");
						if (option == 0) {
							AccountDao.delete(customer.getMaTaiKhoan());
							loadProduct();
							showAll();
							JOptionPane.showMessageDialog(panel, "Xóa thành công !!!");
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

				String[] options = { "maTaiKhoan", "sDT", "passWord", "level" };
				String selectedOption = (String) JOptionPane.showInputDialog(panel, "Chọn cột để tìm kiếm:", "Tìm kiếm",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (selectedOption != null && !selectedOption.equals("")) {

					String keyword = JOptionPane.showInputDialog(panel, "Nhập từ khóa cần tìm:", "Tìm kiếm",
							JOptionPane.PLAIN_MESSAGE);
					if (keyword != null && !keyword.equals("")) {

						List<Account> list_Cus = searchCus(selectedOption, keyword);

						if (list_Cus.size() > 0) {

							tableModel.setRowCount(0);

							for (Account row : list_Cus) {
								tableModel.addRow(new Object[] { row.getMaTaiKhoan(), row.getsDT(), row.getPassWord(),
										row.getLevel(),

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

		table_product.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, },
						new String[] { "Mã TK", "SĐT", "PassWord", "Level" }));
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
					Account customer = list_account.get(index);

					tf_pw.setText(customer.getPassWord().toString());

					int level = customer.getLevel();
					String a = String.valueOf(level);
					tf_lv.setText(a.toString());

					int sdt = customer.getsDT();
					String b = String.valueOf(sdt);
					tf_sdt.setText(b.toString());

					int ma = customer.getMaTaiKhoan();
					String c = String.valueOf(ma);
					tf_matk.setText(c.toString());

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
					tf_lv.setText("");
					tf_sdt.setText("");
					tf_pw.setText("");
					tf_sdt.setText("");

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
		list_account.clear();
		try {

			Connection cons = DBConnect.getConnection();
			String sql = "select * from account";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Account staff = new Account(r1.getInt("maTaiKhoan"), r1.getInt("sDT"), r1.getString("passWord"),
						r1.getInt("level"));
				list_account.add(staff);

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

		for (Account row : list_account) {
			tableModel.addRow(new Object[] { row.getMaTaiKhoan(), row.getsDT(), row.getPassWord(), row.getLevel(),

			});
		}

	}

	private List<Account> searchCus(String columnName, String keyword) {
		List<Account> result = new ArrayList<>();
		String query;
		PreparedStatement stmt;
		try {
			Connection cons = DBConnect.getConnection();
			if (isNumeric(keyword)) {
				query = "SELECT * FROM account WHERE " + columnName + " = ?";
				stmt = cons.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(keyword));
			} else {
				query = "SELECT * FROM account WHERE " + columnName + " LIKE ?";
				stmt = cons.prepareStatement(query);
				stmt.setString(1, "%" + keyword + "%");
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("maTaiKhoan");
				int name = rs.getInt("sDT");
				String phone = rs.getString("passWord");
				int level = rs.getInt("level");

				Account customer = new Account(id, name, phone, level);
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