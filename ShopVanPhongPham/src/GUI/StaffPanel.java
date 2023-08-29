package GUI;

import java.awt.Color;
import java.awt.EventQueue;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.AccountManagerController;
import BLL.check_action;
import BLL.check_all;
import BLL.check_input;
import DAO.DBConnect;
import DAO.StaffDao;
import DAO.add_account;
import DTO.Level;
import DTO.Staff;

public class StaffPanel extends JPanel {
	private int id = 1;
	private int level = 0;
	DefaultTableModel tableModel;
	private JLabel tf_manv;
	private JTextField tf_sdt;
	private JTextField tf_email;
	private JTextField tf_diachi;
	private JTextField tf_hoten;
	private JTextField tf_taikhoan;
	private JTable table_product;

	private String list_trangthai[] = new String[3];
	private check_action check_action = new check_action();

	List<Staff> list_staff = new ArrayList<>();
	List<Level> list_level = new ArrayList<>();

	private check_all check_all = new check_all();

	private int index = -1;
	private JComboBox tf_trangthai;
	private JComboBox tf_malevel;

	public StaffPanel(int id) {

		this.id = id;
		add_account add_account = new add_account();
		level = (add_account.add(id)).getLevel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 672);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 652);
		panel.setBorder(new TitledBorder(null, "Nhập nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);

		JLabel lp_manv = new JLabel("Mã Nhân Viên");
		lp_manv.setBounds(30, 50, 100, 16);
		panel.add(lp_manv);
		tf_manv = new JLabel();
		tf_manv.setBounds(140, 50, 220, 26);
		panel.add(tf_manv);

		JLabel lp_level = new JLabel("Mã level");
		lp_level.setBounds(30, 107, 100, 13);
		panel.add(lp_level);
		tf_malevel = new JComboBox();
		tf_malevel.setBounds(140, 100, 220, 26);

		panel.add(tf_malevel);

		JLabel lp_taikhoan = new JLabel("Mã Tài Khoản");
		lp_taikhoan.setBounds(30, 162, 100, 16);
		panel.add(lp_taikhoan);
		tf_taikhoan = new JTextField();
		tf_taikhoan.setBounds(140, 158, 220, 26);
		panel.add(tf_taikhoan);

		JLabel lp_trangthai = new JLabel("Trạng thái");
		lp_trangthai.setBounds(30, 222, 100, 13);
		panel.add(lp_trangthai);
		tf_trangthai = new JComboBox();
		tf_trangthai.setBounds(140, 215, 220, 26);
		panel.add(tf_trangthai);

		JLabel lp_hoten = new JLabel("Họ Tên");
		lp_hoten.setBounds(390, 50, 45, 13);
		panel.add(lp_hoten);
		tf_hoten = new JTextField();
		tf_hoten.setBounds(450, 46, 220, 26);
		panel.add(tf_hoten);

		JLabel lp_sdt = new JLabel("SĐT");
		lp_sdt.setBounds(390, 107, 45, 13);
		panel.add(lp_sdt);
		tf_sdt = new JTextField();
		tf_sdt.setBounds(450, 100, 220, 26);
		panel.add(tf_sdt);

		JLabel lp_email = new JLabel("email");
		lp_email.setBounds(390, 162, 45, 13);
		panel.add(lp_email);
		tf_email = new JTextField();
		tf_email.setBounds(450, 155, 220, 26);
		panel.add(tf_email);

		JLabel lp_diachi = new JLabel("Địa chỉ");
		lp_diachi.setBounds(390, 222, 45, 13);
		panel.add(lp_diachi);
		tf_diachi = new JTextField();
		tf_diachi.setBounds(450, 215, 220, 26);
		panel.add(tf_diachi);

		listlevel();

		DefaultComboBoxModel<Object> comboBoxModel = (DefaultComboBoxModel) tf_malevel.getModel();
		for (Level row : list_level) {
			comboBoxModel.addElement(row.getTenLevel());

		}

		list_trangthai[0] = "Off";
		list_trangthai[1] = "On";
		DefaultComboBoxModel<Object> comboBoxModel1 = (DefaultComboBoxModel) tf_trangthai.getModel();
		for (String row : list_trangthai) {

			comboBoxModel1.addElement(row);
		}

		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check_action.check_action_user_by_level(level, 4, "add") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					{
						tableModel = (DefaultTableModel) table_product.getModel();

						int index = 0;
						if (tf_hoten.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
							index = -1;
						} else {
							if (!check_input.checkSpecialCharacters(tf_hoten.getText())) {
								JOptionPane.showMessageDialog(panel, "Tên Nhân viên không sử dụng kí tự đặc biệt");
								System.out.println(" wtf");
								index = -1;
							}
						}
						if (tf_sdt.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập số điện thoại");
							index = -1;
						} else {
							if (!check_input.checknumber(tf_sdt.getText())) {
								JOptionPane.showMessageDialog(panel, "nhập sai số điện thoại");
								System.out.println(" wtf");
								index = -1;
							} else if (!check_input.checkNumberLength(tf_sdt.getText())) {
								JOptionPane.showMessageDialog(panel, "Số điện thoại không đúng");
								System.out.println(" wtf");
								index = -1;
							}
						}
						if (tf_email.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập email");
							index = -1;
						} else {
							if (!check_input.checkEmailFormat(tf_email.getText())) {
								JOptionPane.showMessageDialog(panel, "sai email");
								System.out.println(" wtf");
								index = -1;
							}
						}
						
						if (tf_diachi.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập email");
							index = -1;
						}
						if (index == 0) {
							int length = list_staff.size() + 1;
							String manv = String.valueOf(length);
							String hoten = tf_hoten.getText();
							String sdt = tf_sdt.getText();
							String email = tf_email.getText();
							String diachi = tf_diachi.getText();
							String tk = tf_taikhoan.getText();
							Level level = list_level.get(0);
							int idlevel = level.getMaLevel();
							int idtrangthai = 1;
							if (check_all.checkDataExist("staff", "sDT", sdt)) {
								JOptionPane.showMessageDialog(panel, "Số điện thoại đã có xin nhập lại !!!");
							} else {
								StaffDao.insert(manv, hoten, sdt, email, diachi, idlevel, idtrangthai, tk);
								loadProduct();
								showAll();
								tf_diachi.setText("");
								tf_email.setText("");
								tf_hoten.setText("");
								tf_manv.setText("");
								tf_sdt.setText("");
								tf_taikhoan.setText("");
								JOptionPane.showMessageDialog(panel, "Thêm sản phẩm thành công!!!");
							}
						}
					}
				}

			}
		});
		btn_add.setBounds(700, 45, 120, 36);
		btn_add.setFont(new Font("Arial", Font.BOLD, 18));
		btn_add.setForeground(new Color(255, 255, 255));
		btn_add.setBorder(null);
		btn_add.setOpaque(true);
		btn_add.setBackground(new Color(12, 129, 160));
		panel.add(btn_add);

		JButton btn_update = new JButton("UPDATE");
		btn_update.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (check_action.check_action_user_by_level(level, 4, "update") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					{
						int selectedIndex = table_product.getSelectedRow();
						if (selectedIndex >= 0) {

							Staff staff = list_staff.get(selectedIndex);
							int index = 0;
							if (tf_hoten.getText().isEmpty()) {
								JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
								index = -1;
							} else {
								if (!check_input.checkSpecialCharacters(tf_hoten.getText())) {
									JOptionPane.showMessageDialog(panel, "Tên Nhân viên không sử dụng kí tự đặc biệt");
									System.out.println(" wtf");
									index = -1;
								}
							}
							if (tf_sdt.getText().isEmpty()) {
								JOptionPane.showMessageDialog(panel, "Chưa nhập số điện thoại");
								index = -1;
							} else {
								if (!check_input.checknumber(tf_sdt.getText())) {
									JOptionPane.showMessageDialog(panel, "nhập sai số điện thoại");
									System.out.println(" wtf");
									index = -1;
								} else if (!check_input.checkNumberLength(tf_sdt.getText())) {
									JOptionPane.showMessageDialog(panel, "Số điện thoại không đúng");
									System.out.println(" wtf");
									index = -1;
								}
							}
							if (tf_email.getText().isEmpty()) {
								JOptionPane.showMessageDialog(panel, "Chưa nhập email");
								index = -1;
							} else {
								if (!check_input.checkEmailFormat(tf_email.getText())) {
									JOptionPane.showMessageDialog(panel, "sai email");
									System.out.println(" wtf");
									index = -1;
								}
							}
							if (tf_diachi.getText().isEmpty()) {
								JOptionPane.showMessageDialog(panel, "Chưa nhập email");
								index = -1;
							}
							if (index == 0) {
								int option = JOptionPane.showConfirmDialog(panel,
										"Bạn có muốn update sản phẩm này !!!");
								if (option == 0) {
									String manv = Integer.toString(staff.getMaNhanVien());
									tableModel = (DefaultTableModel) table_product.getModel();

									String hoten = tf_hoten.getText();
									String sdt = tf_sdt.getText();
									String email = tf_email.getText();
									String diachi = tf_diachi.getText();
									String tk = tf_taikhoan.getText();
									Level level = list_level.get(0);
									int idlevel = level.getMaLevel();

									int idtrangthai = tf_trangthai.getSelectedIndex();

									StaffDao.update(manv, hoten, sdt, email, diachi, idlevel, idtrangthai, tk);
									loadProduct();
									showAll();
									tf_diachi.setText("");
									tf_email.setText("");
									tf_hoten.setText("");
									tf_manv.setText("");
									tf_sdt.setText("");
									tf_taikhoan.setText("");
									JOptionPane.showMessageDialog(panel, "Update sản phẩm thành công!!!");
								}
							}
						}
					}
				}
			}
		});
		btn_update.setBounds(700, 100, 120, 36);
		btn_update.setFont(new Font("Arial", Font.BOLD, 18));
		btn_update.setForeground(new Color(255, 255, 255));
		btn_update.setBorder(null);
		btn_update.setOpaque(true);
		btn_update.setBackground(new Color(12, 129, 160));
		panel.add(btn_update);

		JButton btn_delete = new JButton("DELETE");
		btn_delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (check_action.check_action_user_by_level(level, 4, "delete") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				}

				int selectedIndex = table_product.getSelectedRow();
				if (selectedIndex >= 0) {

					Staff staff = list_staff.get(selectedIndex);
					int option = JOptionPane.showConfirmDialog(panel, "Set trạng thái nghỉ việc !!!");
					if (option == 0) {
						StaffDao.delete(staff.getMaNhanVien());
						loadProduct();
						showAll();
					}

				}

			}
		});
		btn_delete.setBounds(700, 155, 120, 36);
		btn_delete.setFont(new Font("Arial", Font.BOLD, 18));
		btn_delete.setForeground(new Color(255, 255, 255));
		btn_delete.setBorder(null);
		btn_delete.setOpaque(true);
		btn_delete.setBackground(new Color(12, 129, 160));
		panel.add(btn_delete);

		JButton btn_find = new JButton("SEARCH");
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] options = { "maNhanVien", "hoTen", "sDT", "email", "diaChi", "maLevel", "trangThai",
						"maTaiKhoan" };
				String selectedOption = (String) JOptionPane.showInputDialog(panel, "Chọn cột để tìm kiếm:", "Tìm kiếm",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (selectedOption != null && !selectedOption.equals("")) {

					String keyword = JOptionPane.showInputDialog(panel, "Nhập từ khóa cần tìm:", "Tìm kiếm",
							JOptionPane.PLAIN_MESSAGE);
					if (keyword != null && !keyword.equals("")) {

						List<Staff> list_staff = searchStaff(selectedOption, keyword);

						if (list_staff.size() > 0) {

							tableModel.setRowCount(0);

							for (Staff row : list_staff) {
								tableModel.addRow(new Object[] { row.getMaNhanVien(), row.getHoTen(), row.getsDT(),
										row.getEmail(), row.getDiaChi(), row.getMaLevel(), row.getTrangThai(),
										row.getMaTaiKhoan(),

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

		btn_find.setBounds(700, 210, 120, 36);
		btn_find.setFont(new Font("Arial", Font.BOLD, 18));
		btn_find.setForeground(new Color(255, 255, 255));
		btn_find.setBorder(null);
		btn_find.setOpaque(true);
		btn_find.setBackground(new Color(12, 129, 160));
		panel.add(btn_find);

		table_product = new JTable();
		table_product.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null }, },
				new String[] { "maNhanVien", "hoTen", "sDT", "email", "diaChi", "maLevel", "trangThai",
						"maTaiKhoan" }));
		table_product.setBounds(30, 320, 820, 300);

//		panel.add(table_product);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 291, 820, 278);
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
					Staff staff = list_staff.get(index);

					tf_hoten.setText(staff.getHoTen().toString());
					tf_email.setText(staff.getEmail().toString());
					tf_diachi.setText(staff.getDiaChi().toString());

					int manv = staff.getMaNhanVien();
					String vl = String.valueOf(manv);
					tf_manv.setText(vl.toString());

					int sdt = staff.getsDT();
					String a = String.valueOf(sdt);
					tf_sdt.setText(a.toString());

					int level = staff.getMaLevel();
					tf_malevel.setSelectedIndex(level - 1);

					int trangthai = staff.getTrangThai();
					if (trangthai == 1) {
						tf_trangthai.setSelectedIndex(1);
					} else {
						tf_trangthai.setSelectedIndex(0);
					}

					int matk = staff.getMaTaiKhoan();
					String tk = String.valueOf(matk);
					tf_taikhoan.setText(tk.toString());

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
					tf_diachi.setText("");
					tf_email.setText("");
					tf_hoten.setText("");
					tf_manv.setText("");
					tf_sdt.setText("");
					tf_taikhoan.setText("");
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

	private void showAll() {
		tableModel.setRowCount(0);

		for (Staff row : list_staff) {
			tableModel.addRow(new Object[] { row.getMaNhanVien(), row.getHoTen(), row.getsDT(), row.getEmail(),
					row.getDiaChi(), row.getMaLevel(), row.getTrangThai(), row.getMaTaiKhoan(),

			});
		}

	}

	private void loadProduct() {
		list_staff.clear();
		try {

			Connection cons = DBConnect.getConnection();
			String sql = "select * from staff";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Staff staff = new Staff(r1.getInt("maNhanVien"), r1.getString("hoTen"), r1.getInt("sDT"),
						r1.getString("email"), r1.getString("diaChi"), r1.getInt("maLevel"), r1.getInt("trangThai"),
						r1.getInt("maTaiKhoan"));
				list_staff.add(staff);

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

	private void listlevel() {
		list_level.clear();
		try {

			Connection cons = DBConnect.getConnection();

			String sql = "select * from level ";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Level level = new Level(r1.getInt("maLevel"), r1.getString("tenLevel"));
				list_level.add(level);
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

	private void loadProductsortlevel(int id) {
		list_staff.clear();
		try {

			Connection cons = DBConnect.getConnection();
			String sql = "select * from staff where maLevel = " + id;
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Staff staff = new Staff(r1.getInt("maNhanVien"), r1.getString("hoTen"), r1.getInt("sDT"),
						r1.getString("email"), r1.getString("diaChi"), r1.getInt("maLevel"), r1.getInt("trangThai"),
						r1.getInt("maTaiKhoan"));
				list_staff.add(staff);

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

	private void loadProductsorttrangthai(int id) {
		list_staff.clear();
		try {

			Connection cons = DBConnect.getConnection();
			String sql = "select * from staff where trangThai = " + id;
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Staff staff = new Staff(r1.getInt("maNhanVien"), r1.getString("hoTen"), r1.getInt("sDT"),
						r1.getString("email"), r1.getString("diaChi"), r1.getInt("maLevel"), r1.getInt("trangThai"),
						r1.getInt("maTaiKhoan"));
				list_staff.add(staff);

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

	private List<Staff> searchStaff(String columnName, String keyword) {
		List<Staff> result = new ArrayList<>();
		String query;
		PreparedStatement stmt;
		try {
			Connection cons = DBConnect.getConnection();
			if (isNumeric(keyword)) {
				query = "SELECT * FROM staff WHERE " + columnName + " = ?";
				stmt = cons.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(keyword));
			} else {
				query = "SELECT * FROM staff WHERE " + columnName + " LIKE ?";
				stmt = cons.prepareStatement(query);
				stmt.setString(1, "%" + keyword + "%");
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("maNhanVien");
				String name = rs.getString("hoTen");
				int phone = rs.getInt("sDT");
				String email = rs.getString("email");
				String address = rs.getString("diaChi");
				int level = rs.getInt("maLevel");
				int trangthai = rs.getInt("trangThai");
				int accountID = rs.getInt("maTaiKhoan");

				Staff staff = new Staff(id, name, phone, email, address, level, trangthai, accountID);
				result.add(staff);
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
